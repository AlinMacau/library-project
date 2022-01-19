package com.library;

import java.util.Calendar;
import java.util.Date;

public class Client {
    private int id;
    private String nume;
    private int nrTotalCarti;
    private Date dataRetur;

    public Client(int id, String nume, int nrTotalCarti, Date dataRetur) {
        this.id = id;
        this.nume = nume;
        this.nrTotalCarti = nrTotalCarti;
        this.dataRetur = dataRetur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public int getNrTotalCarti() {
        return nrTotalCarti;
    }

    public void adaugaNrTotalCarti() {
        this.nrTotalCarti++;
    }

    public Date getDataRetur() {
        return dataRetur;
    }

    public void setDataRetur() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 30);
        this.dataRetur = calendar.getTime();
    }


}
