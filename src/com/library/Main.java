/*
Javadoc missing



 */

package com.library;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Biblioteca biblioteca = new Biblioteca();


        biblioteca.adaugaStudent("Alin Macau", "UNITBV", 3);
        biblioteca.adaugaStudent("Ion Popescu ", "UNITBV", 1);
//        biblioteca.adaugaStudent("Alin Macau", "UNITBV", 3);
//        biblioteca.adaugaProfesor("Prof Ionescu", "SM");
        biblioteca.adaugaProfesor("Prof Ionescu", "SM");
        biblioteca.adaugaProfesor("Gigel Popa", "SM");


        biblioteca.afiseazaClienti();
//        biblioteca.afiseazaStudenti();

        biblioteca.adaugaCarte("Capra cu 3 iezi", "ion Creanga", Gen.NUVELA, 100);
        biblioteca.adaugaCarte("Moara cu noroc", "Sadoveanu", Gen.NUVELA, 300);
        biblioteca.adaugaCarte("Luceafarul", "Eminescu", Gen.POEZIE, 50);
//        biblioteca.afiseazaCarti();
//        biblioteca.afiseazaCartiDisponibile();
//        biblioteca.cautaCarte(2);
//        biblioteca.filtreazaDupaGen(Gen.NUVELA);
//        biblioteca.sorteazaCarti();
//        biblioteca.sorteazaClienti();
        biblioteca.celMaiFidelCititor();
        biblioteca.imprumutaCarte(2);
//        biblioteca.imprumutaCarte(2);
//        biblioteca.imprumutaCarte(1);
//        biblioteca.imprumutaCarte(1);
        biblioteca.returneazaCarte(2);
//        biblioteca.stergeCarte("Luceafarul");
//        biblioteca.stergeClient("Alin Macau");
//        biblioteca.arePenalitati("Alin Macau");
//        biblioteca.exit();


        while (true) {
            biblioteca.callMethod(new Scanner(System.in).next());
        }


    }
}
