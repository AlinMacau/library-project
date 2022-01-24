package com.library;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;


public class Biblioteca {

    ArrayList<Client> clienti = new ArrayList<>();
    ArrayList<Carte> carti = new ArrayList<>();


    public void adaugaStudent(String nume, String facultate, int anDeStudiu) {
        int counter = 0;
        Student s = new Student(clienti.size() + 1, nume, 0, null, facultate, anDeStudiu);
        try {
            for (Client client : clienti) {
                if (s.getNume().equals(client.getNume())) {
                    counter++;
                    throw new NumeDejaExistentException("Nume " + client.getNume() + " deja existent!");
                }

            }
        } catch (NumeDejaExistentException e) {
            e.printStackTrace();
        }
        if (counter == 0) {
            clienti.add(s);
        }


    }

    public void adaugaProfesor(String nume, String materie) {
        Profesor p = new Profesor(clienti.size() + 1, nume, 0, null, materie);
        int counter = 0;
        try {
            for (Client client : clienti) {
                if (p.getNume().equals(client.getNume())) {
                    counter++;
                    throw new NumeDejaExistentException("Nume " + client.getNume() + " deja existent!");
                }
            }
        } catch (NumeDejaExistentException e) {
            e.printStackTrace();
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
        System.out.println("Cartea " + c.getTitlu() + " a fost adaugata!");
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
        int counterCarte = 0;
        int counterClienti = 0;
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
                        counterCarte++;
                        counterClienti++;
                    }
                }
                try {
                    if (counterClienti == 0) {
                        throw new Exception("Acest client nu exista!");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (counterCarte != 0 && counterClienti != 0) {
            try {
                if (counterCarte == 0) {
                    throw new CarteIndisponibilaException("Cartea este indisponibila");
                }
            } catch (CarteIndisponibilaException ex) {
                ex.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

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
            if (clienti.get(i).getId() == id && clienti.get(i).getDataRetur() == null) {
                System.out.println("Clientul " + clienti.get(i).getNume() + " nu are imprumutata o carte momentan.");
            }
            else if (clienti.get(i).getId() == id) {
                if (Calendar.getInstance().getTime().before(clienti.get(i).getDataRetur())) {
                    System.out.println(clienti.get(i).getNume() + " nu are penalitati");
                } else {
                    System.out.println(clienti.get(i).getNume() + " are penalitati");
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

