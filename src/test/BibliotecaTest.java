package test;

import com.library.Biblioteca;
import com.library.Gen;
import com.library.NumeDejaExistentException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BibliotecaTest {
    Biblioteca biblioteca = new Biblioteca();

    @Test
    public void adaugaStudentTest() {
        int beforeAdding = biblioteca.getClienti().size();
        biblioteca.adaugaStudent("Alin Macau", "UNITBV", 3);
        int afterAdding = biblioteca.getClienti().size();

        assertEquals(beforeAdding + 1, afterAdding);
    }

    @Test
    public void adaugaStudentTestException() {
        biblioteca.adaugaStudent("Alin Macau", "UNITBV", 3);
        NumeDejaExistentException ex = assertThrows(NumeDejaExistentException.class, () -> {
            biblioteca.adaugaStudent("Alin Macau", "UNITBV", 3);
        });

        assertEquals("Nume Alin Macau deja existent!", ex.getMessage());
    }

    @Test
    public void adaugaProfesorTest() {
        int beforeAdding = biblioteca.getClienti().size();
        biblioteca.adaugaProfesor("Prof Ionescu", "SM");
        int afterAdding = biblioteca.getClienti().size();

        assertEquals(beforeAdding + 1, afterAdding);
    }

    @Test
    public void adaugaProfesorTestException() {
        biblioteca.adaugaProfesor("Prof Ionescu", "SM");
        NumeDejaExistentException ex = assertThrows(NumeDejaExistentException.class, () -> {
            biblioteca.adaugaProfesor("Prof Ionescu", "SM");
        });
        assertEquals("Nume Prof Ionescu deja existent!", ex.getMessage());
    }


//    @Test
//    public void afiseazaClientiTest(){
//        String result = "Cartea 1: Capra cu 3 iezi a fost adaugata!";
//        assertEquals(,biblioteca.afiseazaClienti());
//        assert
//    }

    @Test
    public void adaugaCarteTest(){
        int numarCarti  = biblioteca.getCarti().size();
        biblioteca.adaugaCarte("Capra cu 3 iezi", "ion Creanga", Gen.NUVELA, 100);
        int numarCartiDupaAdaugare = biblioteca.getCarti().size();

        assertEquals(numarCartiDupaAdaugare-1,numarCarti);
    }

}

