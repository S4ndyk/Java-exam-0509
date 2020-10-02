import fi.helsinki.cs.tmc.edutestutils.MockStdio;
import fi.helsinki.cs.tmc.edutestutils.Points;
import org.junit.Test;
import org.junit.Before;
import org.junit.Rule;
import fi.helsinki.cs.tmc.edutestutils.ReflectionUtils;
import fi.helsinki.cs.tmc.edutestutils.Reflex;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PaivakirjaHiddenTest {

  private static final String POIKKEUS = "PasswordException";
  private static final String PAIVAKIRJA = "SecretDiary";
  private static final String TARKISTA = "checkPassword";
  private static final String LISAA = "addNote";
  private static final String TULOSTA = "printNote";
  private static final String KAIKKI = "allNotes";
  private static final String MAARA = "noteCount";
  private static final String PSWD = "possu123";

  @Rule
  public MockStdio io = new MockStdio();

  @Test
  @Points("t2-lisaa-ei-salasana")
  public void addNoPassword() {
    Object pk = SalainenPaivakirja(PSWD);
    try {
      lisaaMerkinta(pk, "Hei päiväkirja!");
      fail();
    } catch (Throwable t) {
      Class klass = ReflectionUtils.findClass(POIKKEUS);
      assertEquals(klass, t.getClass());
    }
  }

  @Test
  @Points("t2-tulosta-ei-salasana")
  public void printNoPassword() {
    Object pk = SalainenPaivakirja(PSWD);
    try {
      tulostaMerkinta(pk, 12346789);
      fail();
    } catch (Throwable t) {
      Class klass = ReflectionUtils.findClass(POIKKEUS);
      assertEquals(klass, t.getClass());
    }
  }

  @Test
  @Points("t2-kaikki-ei-salasana")
  public void getAllNoPassword() {
    Object pk = SalainenPaivakirja(PSWD);
    try {
      kaikkiMerkinnat(pk);
      fail();
    } catch (Throwable t) {
      Class klass = ReflectionUtils.findClass(POIKKEUS);
      assertEquals(klass, t.getClass());
    }
  }

  @Test
  @Points("t2-maara-ei-salasana")
  public void sizeNoPassword() {
    Object pk = SalainenPaivakirja(PSWD);
    try {
      merkintojenMaara(pk);
      fail();
    } catch (Throwable t) {
      Class klass = ReflectionUtils.findClass(POIKKEUS);
      assertEquals(klass, t.getClass());
    }
  }

  @Test
  @Points("t2-lisaa-testi")
  public void addTest() {
    Object pk1 = SalainenPaivakirja(PSWD);
    tarkistaSalasana(pk1, PSWD);
    try {
      lisaaMerkinta(pk1, "Hello!");
      ArrayList actual = kaikkiMerkinnat(pk1);

      ArrayList expected = new ArrayList();
      expected.add("Hello!");

      assertEquals(expected, actual);
    } catch (Throwable t) {
      fail();
    }

    Object pk2 = SalainenPaivakirja(PSWD);
    tarkistaSalasana(pk2, PSWD);
    try {
      Random r = new Random(1337);
      ArrayList expected = new ArrayList();

      for (int i = 0; i < 100000; i++) {
        int l = r.nextInt();
        lisaaMerkinta(pk2, Integer.toString(l));
        expected.add(Integer.toString(l));
      }
      ArrayList actual = kaikkiMerkinnat(pk2);
      assertEquals(expected, actual);
    } catch (Throwable t) {
      fail();
    }

  }

  @Test
  @Points("t2-tulosta-testi")
  public void printTest() {
    Object pk = SalainenPaivakirja(PSWD);
    tarkistaSalasana(pk, PSWD);
    try {
      for (int i = 0; i < 100000; i++) {
        lisaaMerkinta(pk, Integer.toString(i));
      }
      tulostaMerkinta(pk, 5678);
      tulostaMerkinta(pk, 0);
      tulostaMerkinta(pk, 99999);
      List<String> rivit = Arrays.asList(io.getSysOut().trim().split("\n"));
      assertTrue(rivit.contains("5678"));
      assertTrue(rivit.contains("0"));
      assertTrue(rivit.contains("99999"));
    } catch (Throwable t) {
      fail();
    }
  }

  @Test
  @Points("t2-maara-test")
  public void sizeTest() {
    Object pk = SalainenPaivakirja(PSWD);
    tarkistaSalasana(pk, PSWD);
    try {
      for (int i = 1; i <= 100; i++) {
        lisaaMerkinta(pk, Integer.toString(i));
        assertTrue(merkintojenMaara(pk) == i);
      }
    } catch (Throwable t) {
      fail();
    }
  }

  private Object SalainenPaivakirja(String pswd) {
    try {
      return Reflex.reflect(PAIVAKIRJA).ctor().taking(String.class).invoke(pswd);
    } catch (Throwable t) {
      fail();
    }
    return null;
  }

  private void tarkistaSalasana(Object paivakirja, String yritys) {
    try {
      Reflex.reflect(PAIVAKIRJA).method(TARKISTA).returningVoid().taking(String.class).invokeOn(paivakirja, yritys);
    } catch (Throwable t) {
      fail();
    }
  }

  private void lisaaMerkinta(Object paivakirja, String merkinta) throws Throwable {
    try {
      Reflex.reflect(PAIVAKIRJA).method(LISAA).returningVoid().taking(String.class).invokeOn(paivakirja, merkinta);
    } catch (Throwable t) {
      throw t;
    }
  }

  private void tulostaMerkinta(Object paivakirja, int indeksi) throws Throwable {
    try {
      Reflex.reflect(PAIVAKIRJA).method(TULOSTA).returningVoid().taking(int.class).invokeOn(paivakirja, indeksi);
    } catch (Throwable t) {
      throw t;
    }
  }

  private ArrayList kaikkiMerkinnat(Object paivakirja) throws Throwable {
    try {
      return Reflex.reflect(PAIVAKIRJA).method(KAIKKI).returning(ArrayList.class).takingNoParams().invokeOn(paivakirja);
    } catch (Throwable t) {
      throw t;
    }
  }

  private int merkintojenMaara(Object paivakirja) throws Throwable {
    try {
      return Reflex.reflect(PAIVAKIRJA).method(MAARA).returning(int.class).takingNoParams().invokeOn(paivakirja);
    } catch (Throwable t) {
      throw t;
    }
  }
}