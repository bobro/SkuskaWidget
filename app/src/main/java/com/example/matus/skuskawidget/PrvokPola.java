package com.example.matus.skuskawidget;

/**
 * Created by Matus on 10.7.15.
 */
public class PrvokPola {
    private String titulok;
    private String den;
    private String cas;
    private String dennik;
    private String linka;

    public PrvokPola(String pTitulok, String pDen, String pCas, String pDennik, String pLinka) {
        this.cas = pCas;
        this.den = pDen;
        this.titulok = pTitulok;
        this.dennik = pDennik;
        this.linka = pLinka;

    }

    public PrvokPola() {

        this.cas = "12:50";
        this.den = "12.10.2015";
        this.titulok = "Skusobny titulok";
        this.dennik = "Sme";
        this.linka = "http://sme.sk";
    }

    public String getCas() {
        return cas;
    }

    public String getDen() {
        return den;
    }

    public String getLinka() {
        return linka;
    }

    public String getDennik() {
        return dennik;
    }

    public String getTitulok() {
        return titulok;
    }

    public void setCas(String cas) {
        this.cas = cas;
    }

    public void setDen(String den) {
        this.den = den;
    }

    public void setDennik(String dennik) {
        this.dennik = dennik;
    }

    public void setLinka(String linka) {
        this.linka = linka;
    }

    public void setTitulok(String titulok) {
        this.titulok = titulok;
    }



}











