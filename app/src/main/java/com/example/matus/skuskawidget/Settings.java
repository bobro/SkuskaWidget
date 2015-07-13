package com.example.matus.skuskawidget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Spinner;

/**
 * Created by Matus on 12.7.15.
 */
public class Settings extends Activity implements AdapterView.OnItemSelectedListener,View.OnClickListener {

    private Spinner spinnerNovin;
    private Spinner spinnerCasu;
    private Spinner spinnerRubriky;
    private String[] noviny = new String[]{"SME", "DennikN", "HNonline"};
    private String[] SmeRubriiky = new String[]{"Hlavne spravy","Z domova","Zahranicie","Ekonomika","Kultura","Sport","Tech","Auto"};
    private String[] DennikNRubriiky = new String[]{"Hlavne spravy","Slovensko","Svet","Ekonomika","Kultura","Sport","Veda","Shooty"};
    private String[] HNonlineRubriky = new String[]{"Hlavne spravy","Ekonomika","Slovensko","Svet","Sport"};

    private String[] casObnovy = new String[]{"30 minut", "1 hodina", "2 hodiny", "5 hodin"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        Button ulozenie = (Button) findViewById(R.id.btnsUlozenim);
        ulozenie.setOnClickListener(this);

        spinnerNovin = (Spinner) findViewById(R.id.spinner);
        spinnerRubriky = (Spinner) findViewById(R.id.spinnerRubrika);


        ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, noviny);

        adapter_state.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNovin.setAdapter(adapter_state);
        spinnerNovin.setOnItemSelectedListener(this);

        spinnerCasu = (Spinner) findViewById(R.id.spinner2);


        ArrayAdapter<String> adapter_state2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, casObnovy);

        adapter_state.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCasu.setAdapter(adapter_state2);
        spinnerCasu.setOnItemSelectedListener(this);

        ArrayAdapter<String> adapter_Rubriky = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, SmeRubriiky);

        adapter_Rubriky.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRubriky.setAdapter(adapter_Rubriky);
        spinnerRubriky.setOnItemSelectedListener(this);


        // TODO prerobit inItemSelected na 2 rozdielne
    }


    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        if(parent.equals(spinnerCasu)){

            spinnerCasu.setSelection(position);


        }else if(parent.equals(spinnerNovin)){

            spinnerNovin.setSelection(position);

            if(spinnerNovin.getSelectedItem().equals("SME")){

                ArrayAdapter<String> adapter_Rubriky = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, SmeRubriiky);

                adapter_Rubriky.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerRubriky.setAdapter(adapter_Rubriky);
                spinnerRubriky.setOnItemSelectedListener(this);

            }else if(spinnerNovin.getSelectedItem().equals("DennikN")){
                ArrayAdapter<String> adapter_Rubriky = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, DennikNRubriiky);

                adapter_Rubriky.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerRubriky.setAdapter(adapter_Rubriky);
                spinnerRubriky.setOnItemSelectedListener(this);

            }else if(spinnerNovin.getSelectedItem().equals("HNonline")){
                ArrayAdapter<String> adapter_Rubriky = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, HNonlineRubriky);

                adapter_Rubriky.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerRubriky.setAdapter(adapter_Rubriky);
                spinnerRubriky.setOnItemSelectedListener(this);

            }


        }else if(parent.equals(spinnerRubriky)){

            spinnerRubriky.setSelection(position);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }


    @Override
    public void onClick(View v) {

        // Perform action on click
        switch (v.getId()) {
            case R.id.btnsUlozenim:
                SharedPreferences preferencies = getSharedPreferences("WIDGET_FOR_RSS",Context.MODE_WORLD_WRITEABLE);
                SharedPreferences.Editor editor = preferencies.edit();
                editor.putString("Dennik", "" + spinnerNovin.getSelectedItem());
                editor.putString("Cas", "" + spinnerCasu.getSelectedItem());
                editor.putString("Rubrika",""+spinnerRubriky.getSelectedItem());

                Log.e("spinnerCasu", "oznavene: " + spinnerCasu.getSelectedItem());
                Log.e("spinnerNovin", "oznacene: " + spinnerNovin.getSelectedItem());
                Log.e("spinnerRubriky", "oznacene: " + spinnerRubriky.getSelectedItem());
                editor.commit();


                Intent intent = new Intent();
                setResult(RESULT_OK);
                this.finish();
                break;


        }
    }

}
