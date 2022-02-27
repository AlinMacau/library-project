package com.library;

import java.lang.reflect.Method;
import java.util.*;


public class Biblioteca {

    private ArrayList<Client> clienti = new ArrayList<>();
    private ArrayList<Carte> carti = new ArrayList<>();


    public ArrayList<Client> getClienti() {
        return clienti;
    }

    public ArrayList<Carte> getCarti() {
        return carti;
    }


    public void adaugaStudent(String nume, String facultate, int anDeStudiu) {
        Student s = new Student(clienti.size() + 1, nume, 0, null, facultate, anDeStudiu);
        List<Client> clientiCuAcestNume = clienti.stream()
                .filter(client -> client.getNume().equals(nume))
                .toList();
        if (clientiCuAcestNume.isEmpty())
            clienti.add(s);
        else
            try {
                throw new NumeDejaExistentException("Nume " + nume + " deja existent!");
            } catch (NumeDejaExistentException e) {
                e.printStackTrace();
            }
    }

    public void adaugaProfesor(String nume, String materie) {
        Profesor p = new Profesor(clienti.size() + 1, nume, 0, null, materie);
        List<Client> clientiCuAcestNume = clienti.stream()
                .filter(client -> client.getNume().equals(nume))
                .toList();
        if (clientiCuAcestNume.isEmpty())
            clienti.add(p);
        else
            try {
                throw new NumeDejaExistentException("Nume " + nume + " deja existent!");
            } catch (NumeDejaExistentException e) {
                e.printStackTrace();
            }

    }

    public void afiseazaClienti() {
        clienti.forEach(client -> System.out.println(client.getNume()));
    }

    public void afiseazaStudenti() {
        clienti.stream()
                .filter(client -> client instanceof Student)
                .forEach(client -> System.out.println(client.getNume()));
    }

    public void adaugaCarte(String titlu, String autor, Gen gen, int numarPagini) {
        Carte c = new Carte(carti.size() + 1, titlu, autor, gen, numarPagini, false);
        carti.add(c);
        System.out.println("Cartea " + c.getCod() + ": " + c.getTitlu() + " a fost adaugata!");
    }

    public void afiseazaCarti() {
        carti.forEach(carte -> System.out.println(carte.getTitlu()));
    }

    public void afiseazaCartiDisponibile() {
        carti.stream()
                .filter(carte -> carte.getCodClientCurent() == 0)
                .forEach(carte -> System.out.println(carte.getTitlu()));
    }

    public void cautaCarte(int cod) {
        if (cod <= carti.size()) {
            carti.stream()
                    .filter(carte -> carte.getCod() == cod)
                    .forEach(carte -> System.out.println(carte));
        } else
            System.out.println("Cartea nu exista. Biblioteca are " + carti.size() + " exemplare");
    }


    public void filtreazaDupaGen(Gen gen) {
        carti.stream()
                .filter(carte -> carte.getGen().equals(gen))
                .forEach(carte -> System.out.println(carte.getTitlu()));
    }

    public void sorteazaCarti() {
        carti.sort((o1, o2) -> o1.getNumarPagini() - o2.getNumarPagini());
        carti.forEach(carte -> System.out.println(carte.getTitlu()));
    }

    public void sorteazaClienti() {
        clienti.sort((o1, o2)
                -> o1.getNume().compareTo(
                o2.getNume()));
        clienti.forEach(client -> System.out.println(client.getNume()));
    }

    public void celMaiFidelCititor() {
        List<Client> clientiFideli = clienti.stream()
                .sorted((o1, o2) -> o2.getNrTotalCarti() - o1.getNrTotalCarti()).toList();
        if (!clientiFideli.isEmpty()) {
            System.out.println(clientiFideli.get(0).getNume() + " este cel mai fidel");
        }
    }

    public Boolean carteaExista(int cod) {
        boolean carteaExista = carti.stream()
                .map(carte -> carte.getCod())
                .toList().contains(cod);
        try {
            if (!carteaExista) {
                throw new CarteIndisponibilaException("Cartea nu exista");
            }
        } catch (CarteIndisponibilaException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Carte valideazaCarte(int cod) {
        if (carteaExista(cod)) {
            if (carti.get(cod - 1).getCodClientCurent() == 0) {
                return carti.get(cod - 1);
            } else {
                System.out.println("Cartea este imprumutata");
                return null;
            }
        }
        return null;
    }

    public Boolean clientulExista(String nume) {
        boolean clientulExista = clienti.stream()
                .map(Client::getNume)
                .toList().contains(nume);
        try {
            if (!clientulExista) {
                throw new Exception("Acest client nu exista!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public Client valideazaClient(String nume) {
        if (clientulExista(nume)) {
            List<Client> clientiCuAcestNume = clienti.stream()
                    .filter(client -> client.getNume().equals(nume))
                    .toList();
            if (!clientiCuAcestNume.isEmpty()) {
                if (clientiCuAcestNume.get(0).getDataRetur() == null) {
                    return clientiCuAcestNume.get(0);
                } else {
                    System.out.println("Clientul are deja o carte imprumutata");
                    return null;
                }
            }
        }
        return null;
    }

    public void imprumutaCarte(int cod) {
        if (valideazaCarte(cod) != null) {
            System.out.println("Introduceti numele clientului care imprumuta cartea");
            Scanner sc = new Scanner(System.in);
            String nume = sc.nextLine();
            if (valideazaClient(nume) != null) {
                Client clientActual = valideazaClient(nume);
                Carte carteActuala = valideazaCarte(cod);

                carteActuala.setCodClientCurent(cod);//legatura dintre client si carte la momentul imprumutului
                System.out.println("Cartea " + carteActuala.getTitlu() + " este imprumutata de " + clientActual.getNume());


                clientActual.adaugaNrTotalCarti();//se adauga la nr de carti citite de un client
                System.out.println(clientActual.getNume() + " are imprumutate "
                        + clientActual.getNrTotalCarti() + " carti.");

                clientActual.setDataRetur();//se seteaza data de retur pentru acest client peste 30 de zile
                System.out.println("Data de retur : " + clientActual.getDataRetur());
            }
        }

    }

    public void returneazaCarte(int cod) {
        if (valideazaCarte(cod) == null) {//metoda valideazaCarte returneaza null daca cartea exista
            //si este imprumutata
            Carte carteActuala = carti.stream()
                    .filter(carte -> carte.getCod() == cod)
                    .toList().get(0);
            Client clientActual = clienti.stream()
                    .filter(client -> client.getId() == carteActuala.getCodClientCurent())
                    .toList().get(0);

            carteActuala.setCodClientCurent(0);
            clientActual.resetDataRetur();
            System.out.println(carteActuala.getTitlu() + " a fost restituita.");
        } else
            System.out.println("Cartea nu este imprumutata sau nu exista");
    }

    public void stergeCarte(String titlu) {
        Optional<Carte> deSters = carti.stream()
                .filter(carte -> carte.getTitlu().equals(titlu))
                .findFirst();
        carti.remove(deSters.get().getCod() - 1);
    }

    public void stergeClient(String nume) {
        Optional<Client> deSters = clienti.stream()
                .filter(client -> client.getNume().equals(nume))
                .findFirst();
        clienti.remove(deSters.get().getId() - 1);
    }

    public void arePenalitati(String nume) {
        if (valideazaClient(nume) == null) {//met returneaza null daca clientul exista si are carte imprumutata
            List<Client> clientiCuAcestNume = clienti.stream()
                    .filter(client -> client.getNume().equals(nume))
                    .toList();
            if (!clientiCuAcestNume.isEmpty()) {
                Client clientActual = clientiCuAcestNume.get(0);

                if (Calendar.getInstance().getTime().before(clientActual.getDataRetur())) {
                    System.out.println(clientActual.getNume() + " nu are penalitati");
                } else {
                    System.out.println(clientActual.getNume() + " are penalitati");
                }
            } else {
                System.out.println("Clientul nu exista sau nu are carte imprumutata");
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

