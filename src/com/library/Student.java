package com.library;

import java.util.Date;

public class Student extends Client{
    private final String facultate;
    private final int anDeStudiu;

    public Student(int id, String nume, int nrTotalCarti, Date dataRetur, String facultate, int anDeStudiu) {
        super(id, nume, nrTotalCarti, dataRetur);
        this.facultate = facultate;
        this.anDeStudiu = anDeStudiu;
    }
}
