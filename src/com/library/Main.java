package com.library;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NumeDejaExistentException {

        Biblioteca biblioteca =     new Biblioteca();
        biblioteca.adaugaStudent("Alin Macau", "UNITBV", 3);
        biblioteca.adaugaProfesor("Prof Ionescu", "SM");
        biblioteca.adaugaProfesor("Gigel Popa", "SM");
//        biblioteca.afiseazaClienti();
//        biblioteca.afiseazaStudenti();

        biblioteca.adaugaCarte("Capra cu 3 iezi", "ion Creanga", Gen.NUVELA, 100);
        biblioteca.adaugaCarte("Moara cu noroc", "Sadoveanu", Gen.NUVELA, 300);
        biblioteca.adaugaCarte("Luceafarul", "Eminescu", Gen.POEZIE, 50);
//        biblioteca.afiseazaCarti();
//        biblioteca.afiseazaCartiDisponibile();
//        biblioteca.cautaCarte("Moara cu noroc");
//        biblioteca.filtreazaDupaGen(Gen.NUVELA);
//        biblioteca.sorteazaCarti();
//        biblioteca.sorteazaClienti();
//        biblioteca.celMaiFidelCititor();
//        biblioteca.imprumutaCarte(1);
//        biblioteca.returneazaCarte(1);
//        biblioteca.stergeCarte("Luceafarul");
//        biblioteca.stergeClient("Alin Macau");
//        biblioteca.arePenalitati(1);
//        biblioteca.exit();


        while (true) {
            biblioteca.callMethod(new Scanner(System.in).next());
        }


    }
}
