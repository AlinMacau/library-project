package com.library;

import java.util.Date;

public class Profesor extends Client{
    private final String materie;

    public Profesor(int id, String nume, int nrTotalCarti, Date dataRetur, String materie) {
        super(id, nume, nrTotalCarti, dataRetur);
        this.materie = materie;
    }


}
