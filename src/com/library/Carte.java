package com.library;

public class Carte {
    private int cod;
    private String titlu;
    private String autor;
    private Gen gen;
    private int numarPagini;
    private int codClientCurent;

    public int getCodClientCurent() {
        return codClientCurent;
    }

    public void setCodClientCurent(int codClientCurent) {
        this.codClientCurent = codClientCurent;
    }

    public Carte(int cod, String titlu, String autor, Gen gen, int numarPagini, Boolean esteImprumutata) {
        this.cod = cod;
        this.titlu = titlu;
        this.autor = autor;
        this.gen = gen;
        this.numarPagini = numarPagini;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Gen getGen() {
        return gen;
    }

    public void setGen(Gen gen) {
        this.gen = gen;
    }

    public int getNumarPagini() {
        return numarPagini;
    }

    public void setNumarPagini(int numarPagini) {
        this.numarPagini = numarPagini;
    }


    @Override
    public String toString() {
        return "Carte: " + "\n" +
                "cod= " + cod + "\n" +
                "titlu= " + titlu + '\n' +
                "autor= " + autor + '\n' +
                "gen= " + gen + "\n" +
                "numar de pagini= " + numarPagini;
    }
}
