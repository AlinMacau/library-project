package com.library;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;


public class Biblioteca {

    ArrayList<Client> clienti = new ArrayList<>();
    ArrayList<Carte> carti = new ArrayList<>();


    public void adaugaStudent(String nume, String facultate, int anDeStudiu) throws NumeDejaExistentException {
        int counter = 0;
        Student s = new Student(clienti.size() + 1, nume, 0, null, facultate, anDeStudiu);
        for (Client client : clienti) {
            if (s.getNume().equals(client.getNume())) {
                counter++;
//                System.out.println("Nume " + client.getNume() + " deja existent!");
                throw new NumeDejaExistentException("Nume " + client.getNume() + " deja existent!");
            }

        }
        if (counter == 0) {
            clienti.add(s);
        }


    }

    public void adaugaProfesor(String nume, String materie) throws NumeDejaExistentException {
        Profesor p = new Profesor(clienti.size() + 1, nume, 0, null, materie);
        int counter = 0;
        for (Client client : clienti) {
            if (client.getNume().equals(p.getNume())) {
                counter++;
//                System.out.println("Nume " + client.getNume() + " deja existent!");
                throw new NumeDejaExistentException("Nume " + client.getNume() + " deja existent!");
            }
        }
        if (counter == 0) {
            clienti.add(p);
        }
    }

    public void afiseazaClienti() {
        for (Client client : clienti) {
            System.out.println(client.getNume());
        }
    }

    public void afiseazaStudenti() {
        for (Client client : clienti) {
            if (client instanceof Student) {
                System.out.println(client.getNume());
            }
        }
    }

    public void adaugaCarte(String titlu, String autor, Gen gen, int numarPagini) {
        Carte c = new Carte(carti.size() + 1, titlu, autor, gen, numarPagini, false);
        carti.add(c);
        System.out.println("Cartea " + titlu + " a fost adaugata in biblioteca.");
    }

    public void afiseazaCarti() {
        for (Carte carte : carti) {
            System.out.println(carte.getTitlu());
        }
    }

    public void afiseazaCartiDisponibile() {
        for (Carte carte : carti) {
            if (carte.getEsteImprumutata().equals(false)) {
                System.out.println(carte.getTitlu());
            }
        }
    }

    public void cautaCarte(String titlu) {
        for (Carte carte : carti) {
            if (carte.getTitlu().equals(titlu)) {
                System.out.println(carte);
            }
        }
    }


    public void filtreazaDupaGen(Gen gen) {
        for (Carte carte : carti) {
            if (carte.getGen().equals(gen)) {
                System.out.println(carte.getTitlu());
            }
        }
    }

    public void sorteazaCarti() {
        carti.sort((o1, o2)
                -> o1.getTitlu().compareTo(
                o2.getTitlu()));
        System.out.println("Cartile au fost sortate in ordine alfabetica");
    }

    public void sorteazaClienti() {
        clienti.sort((o1, o2)
                -> o1.getNume().compareTo(
                o2.getNume()));
        System.out.println("Clientii au fost sortati in ordine allfabetica");
    }

    public void celMaiFidelCititor() {
        Client max = clienti.get(0);
        for (int i = 1; i < clienti.size(); i++) {
            if (clienti.get(i).getNrTotalCarti() > max.getNrTotalCarti()) {
                max = clienti.get(i);
            }
        }
        System.out.println(max.getNume());
    }

    public void imprumutaCarte(long cod) {
        try {
            int counter = 0;
            for (Carte carte : carti) {
                if (carte.getCod() == cod && carte.getEsteImprumutata() == false) {
                    System.out.println("Introduceti numele clientului care imprumuta cartea");
                    Scanner sc = new Scanner(System.in);
                    String nume = sc.nextLine();
                    for (Client client : clienti) {
                        if (client.getNume().equals(nume)) {
                            carte.setEsteImprumutata(true);//modificarea statusului cartii in "imprumutata"
                            System.out.println("Cartea " + carte.getTitlu() + " este imprumutata de " + client.getNume());

                            client.adaugaNrTotalCarti();//se adauga la nr de carti citite de un client
                            System.out.println(client.getNume() + " are imprumutate "
                                    + client.getNrTotalCarti() + " carti.");

                            client.setDataRetur();//se seteaza data de retur pentru acest client peste 30 de zile
                            System.out.println("Data de retur : " + client.getDataRetur());
                            counter++;
                        }
                    }
                }
            }
            if (counter == 0) {
//            System.out.println("Cartea este indisponibila");
                throw new CarteIndisponibilaException("Cartea este indisponibila");
            }
        } catch (CarteIndisponibilaException ex) {
            ex.printStackTrace();
        }
    }


    public void returneazaCarte(long cod) {
        for (Carte carte : carti) {
            if (carte.getCod() == cod) {
                carte.setEsteImprumutata(false);
                System.out.println(carte.getTitlu() + " a fost restituita.");
            }
        }
    }

    public void stergeCarte(String titlu) {
        for (int i = 0; i < carti.size(); i++) {
            if (carti.get(i).getTitlu().equals(titlu)) {
                carti.remove(carti.get(i));
                System.out.println("Cartea " + titlu + " a fost stearsa din biblioteca.");
            }
        }
    }

    public void stergeClient(String nume) {
        for (int i = 0; i < clienti.size(); i++) {
            if (clienti.get(i).getNume().equals(nume)) {
                clienti.remove(clienti.get(i));
                System.out.println("Clientul " + nume + " a fost sters din biblioteca.");
            }
        }
    }

    public void arePenalitati(int id) {
        for (int i = 0; i < clienti.size(); i++) {
            if (clienti.get(i).getId() == id) {
                if (Calendar.getInstance().getTime().before(clienti.get(i).getDataRetur())) {
                    System.out.println("Nu are penalitati");
                } else {
                    System.out.println("Are penalitati");
                }
            }
        }
    }

    public void exit() {
        System.out.println("The app is closed");
        System.exit(0);

    }

    public void callMethod(String methodName) {
        try {
            Method method = this.getClass().getMethod(methodName);
            method.invoke(this);
        } catch (Exception e) {
            System.out.println("Could not call method with name: " + methodName);
        }
    }


}

