package com.example.matus.skuskawidget;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends Activity implements View.OnClickListener {

    private ArrayList<PrvokPola> pole= new ArrayList<PrvokPola>();
    private TextView nazov;
    private GiveArrayList obj=null;
    private ImageButton refresh;
    private ImageButton setting;
    protected Context context = this;

        //TODO spravit shared preferencies na dennik aky prave chce pozerat - v alarm manayerovi budem prepisovat datum na + cas ktory chcem
        //TODO v obnovit budem vztvarat alarmanazer



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget);
        nazov = (TextView) findViewById(R.id.nazov);
        refresh = (ImageButton) findViewById(R.id.renew);
        refresh.setOnClickListener(this);
        setting = (ImageButton) findViewById(R.id.settings);
        setting.setOnClickListener(this);



        /*
        pole.add(new PrvokPola());
        pole.add(new PrvokPola("Dalsi titulok","1.5.2015","12:25","Sme","http://sme.sk"));
        pole.add(new PrvokPola("Treti titulok", "25.25.2015", "12:25", "Aktuality", "http://aktuality.sk"));
        */


        new Obnova().execute();


    }


    public void onClick(View v) {
        // Perform action on click
        switch(v.getId()) {
            case R.id.renew:

                new Obnova().execute();
                break;

            case R.id.settings:
                //Stop MediaPlayer
               // Log.e(" linka"," "+pole.size());
                Intent intent = new Intent(MainActivity.this,Settings.class);
                startActivity(intent);
                break;

        }
    }



    private class Obnova extends AsyncTask<Void, Void, Void> {



        @Override
        protected Void doInBackground(Void... params) {

            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isAvailable() && netInfo.isConnected()) {


                //TODO viac casopisov

                obj = new RSSSmeCitac();
                obj.fetchXML();

                RSSSmeCitac pom =(RSSSmeCitac) obj;


                Log.e("Asyntask", "pocas");

                while (!((RSSSmeCitac) obj).getkoniec()){


                }


                // Log.e("Ukoncenie","ukoncena");
                }
                return null;
            }


            @Override
            protected void onPreExecute () {
                Log.e("Asyntask", "Pred");
          //      pole.clear();

            }

            @Override
            protected void onPostExecute (Void Void){
                super.onPostExecute(Void);

                pole = obj.getArrayList();

                RSSSmeCitac pom =(RSSSmeCitac) obj;

            //    pom.vypis();

                nazov.setText(obj.getDennik());

                if (obj.getDennik().equals("SME")) {
                    ((TextView) findViewById(R.id.nazov)).setTextColor(getResources().getColor(R.color.DarkRed));
                    Log.e("Asyntask", "vypis");

                }
                ListView listView = (ListView) findViewById(R.id.list);

                CustomAdapter adapter= new CustomAdapter(context,pole);

                listView.setAdapter(adapter);


            }
        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
