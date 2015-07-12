package com.example.matus.skuskawidget;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;

/**
 * Created by Matus on 12.7.15.
 */
public class Settings extends Activity implements AdapterView.OnItemSelectedListener{

    Spinner spinnerNovin;
    Spinner spinnerCasu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        spinnerNovin = (Spinner) findViewById(R.id.spinner);
        String[] noviny = new String[] { "Sme","DennikN","HNoviny","Aktuality" };

        ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, noviny);

        adapter_state.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNovin.setAdapter(adapter_state);
        spinnerNovin.setOnItemSelectedListener(this);

        spinnerCasu = (Spinner) findViewById(R.id.spinner2);
        String[] casObnovy = new String[] { "30 minut","1 hodina","2 hodiny","5 hodin" };

        ArrayAdapter<String> adapter_state2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, casObnovy);

        adapter_state.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCasu.setAdapter(adapter_state2);
        spinnerCasu.setOnItemSelectedListener(this);

        // TODO prerobit inItemSelected na 2 rozdielne
        }


    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        spinnerNovin.setSelection(position);
        String selState = (String) spinnerNovin.getSelectedItem();
        Log.e("spinnerNovin", "oznacene " + selState);


        spinnerCasu.setSelection(position);
        String selState2 = (String) spinnerCasu.getSelectedItem();
        Log.e("spinnerCasu", "oznacene " + selState2);


    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }


}
