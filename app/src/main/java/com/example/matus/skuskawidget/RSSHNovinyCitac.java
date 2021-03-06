package com.example.matus.skuskawidget;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by Matus on 10.7.15.
 */

public class RSSHNovinyCitac implements GiveArrayList{

    private String title = "";
    private String link = "";
    private String datum ="";
    private String cas = "";
    private String dennik ="HNonline";
    private Context context = null;

    private boolean koniec=false;
    private boolean zaciatok=true;

    private HashMap<String,String> linky;

    private ArrayList<PrvokPola> pole;

    private String urlString="http://hnonline.sk/rss/8";
    private XmlPullParserFactory xmlFactoryObject;

    public volatile boolean parsingComplete = true;






    public RSSHNovinyCitac(Context context){

        pole = new ArrayList<PrvokPola>();
        this.context=context;

        linky = new HashMap<>();
        linky.put("Hlavne spravy","http://hnonline.sk/rss/8");
        linky.put("Slovensko","http://hnonline.sk/rss/8/119");
        linky.put("Svet","http://hnonline.sk/rss/8/120");
        linky.put("Ekonomika","http://hnonline.sk/rss/8/117");
        linky.put("Sport","http://hnonline.sk/rss/8/121");


    }

    public String getCas() {
        return cas;
    }

    @Override
    public String getDennik() {
        return dennik;
    }

    public String getDatum() {
        return datum;
    }

    public String getLink() {
        return link;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return urlString;
    }

    public void setDennik(String dennik) {
        this.dennik = dennik;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.urlString = url;
    }



    public boolean getkoniec(){
        return koniec;

    }

    public void vypis() {

        for (int i = 0; i < pole.size(); i++) {
            Log.e(i + ". prvok", "" + pole.get(i).getTitulok() + " " + pole.get(i).getDen() + " " + pole.get(i).getCas() + " " + pole.get(i).getDennik() + " " + pole.get(i).getLinka());

        }
    }

    public void fetchXML(){

        SharedPreferences preferences = context.getSharedPreferences("WIDGET_FOR_RSS", Context.MODE_PRIVATE);
        urlString=linky.get(preferences.getString("Rubrika",""));


        Thread thread = new Thread(new Runnable(){

            public void parseXMLAndStoreIt(XmlPullParser myParser) {
                int event;
                String text = null;


                try {
                    event = myParser.getEventType();

                    while (event != XmlPullParser.END_DOCUMENT) {
                        String name = myParser.getName();
                        //Log.e("asd",""+name);

                        switch (event) {
                            case XmlPullParser.START_TAG:

                                //  Log.i("starting", "" + myParser.getName());
                                break;

                            case XmlPullParser.TEXT:
                                text = myParser.getText();
                                break;

                            case XmlPullParser.END_TAG:

                                if (zaciatok) {

                                    if (name.equals("title")) {

                                        zaciatok = false;
                                    }
                                } else {

                                    // Log.i("ending",""+event);
                                    if (name.equals("title")) {
                                        title = text;
                                        //     Log.d("title ", "" + title);
                                    } else if (name.equals("link")) {
                                        link = text;
                                        //    Log.d("link ", "" + link);
                                    } else if (name.equals("pubDate")) {
                                        String pomocnyDatum = text;
                                        //Log.e("ok",""+ pomocnyDatum);

                                        datum = pomocnyDatum.substring(5, 16);
                                        cas = pomocnyDatum.substring(17, 25);
                                      //  Log.e("datum",""+datum);
                                      //  Log.e("cas","cas"+cas);
                                        pole.add(new PrvokPola(title, datum, cas, dennik, link));
                                        break;
                                    } else {

                                    }
                                    break;
                                }
                        }

                        event = myParser.next();
                    }
                    parsingComplete = false;
                    koniec=true;
                    Log.e("Ukoncene","rozparsovane");
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("Chyba", "Parsovanie RSSSme");
                    pole.add(new PrvokPola("\t\t\tNiekde nastala chyba.\n\t\t\tSkuste obnovit neskor", "", "", "", ""));
                    koniec=true;
                }
            }

            @Override
            public void run() {
                try {


                    URL url = new URL(urlString);
                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();

                    conn.setReadTimeout(1000 /* milliseconds */);
                    conn.setConnectTimeout(1500 /* milliseconds */);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    conn.connect();

                 //   Log.e("uspesne","pripojenie na stranku");
                    InputStream stream = conn.getInputStream();
                    xmlFactoryObject = XmlPullParserFactory.newInstance();
                    XmlPullParser myparser = xmlFactoryObject.newPullParser();


                   // Log.e("uspesne", "zaciatok parsovania");


                    myparser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                    myparser.setInput(stream, null);

                    Log.e("Ukoncene", "nacitane");


                    parseXMLAndStoreIt(myparser);
                    stream.close();

                 //  Log.e("druhe", "druhe");

               // vypis();

                }
                catch (Exception e) {
                    e.printStackTrace();
                    Log.e("Chyba", "url connection");
                    pole.add(new PrvokPola("\t\t\tNiekde nastala chyba.\n\t\t\tSkuste obnovit neskor", "", "", "", ""));
                    koniec=true;
                }

            }
        });
        thread.start();
    }

    @Override
    public ArrayList<PrvokPola> getArrayList() {
        return pole;
    }
}