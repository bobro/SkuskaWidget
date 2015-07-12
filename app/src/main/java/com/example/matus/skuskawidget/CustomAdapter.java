package com.example.matus.skuskawidget;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matus on 10.7.15.
 */
public class CustomAdapter extends ArrayAdapter<PrvokPola> {



        private Activity myContext;

        private ArrayList<PrvokPola> data;



        public CustomAdapter(Context context, ArrayList<PrvokPola> objects) {

            super(context,R.layout.widget,objects);


            myContext = (Activity) context;

            data = objects;
        }



        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = myContext.getLayoutInflater();

            View rowView = inflater.inflate(R.layout.prvok, null);



            TextView textDatumView = (TextView) rowView.findViewById(R.id.datum);
            textDatumView.setText(data.get(position).getDen());

            TextView textCasView = (TextView) rowView.findViewById(R.id.cas);
            textCasView.setText(data.get(position).getCas());

            TextView textTitulokView = (TextView) rowView.findViewById(R.id.titulok);
            textTitulokView.setText(data.get(position).getTitulok());

            TextView textDennikView = (TextView) rowView.findViewById(R.id.denniknazov);
            textDennikView.setText(data.get(position).getDennik());


            return rowView;

        }



}
