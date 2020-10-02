import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.ReflectionUtils;
import fi.helsinki.cs.tmc.edutestutils.Reflex;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Arrays;

public class KonekoeTehtava1HiddenTest {

    private static final String KUUTIO = "Cube";
    private static final String MUOTO = "Shape";
    private static final String ANNA_ALA = "getArea";
    private static final String ANNA_TILAVUUS = "getVolume";

    @Test
    @Points("t1-rajapinta-muoto-on-olemassa")
    public void interfaceExists() {
        Reflex.reflect(MUOTO).requirePublic();
        assertTrue(ReflectionUtils.findClass(MUOTO).isInterface());
    }

    @Test
    @Points("t1-luokka-kuutio-on-olemassa")
    public void classExists() {
        Reflex.reflect(KUUTIO).requirePublic().ctor().taking(int.class).requireExists();
    }

    @Test
    @Points("t1-kuutio-toteuttaa-muodon")
    public void implementsInterface() {
        Class interfaces[] = ReflectionUtils.findClass(KUUTIO).getInterfaces();
        Class muoto = ReflectionUtils.findClass(MUOTO);
        Class correct[] = { muoto };
        assertTrue(Arrays.equals(interfaces, correct));
    }

    @Test
    @Points("t1-annaAla-pieni-testi")
    public void annaAlaSmall() {
        Object k1 = Kuutio(1);
        Object k2 = Kuutio(2);
        Object k3 = Kuutio(5);
        Object k4 = Kuutio(11);

        assertEquals(6, annaAla(k1));
        assertEquals(24, annaAla(k2));
        assertEquals(150, annaAla(k3));
        assertEquals(726, annaAla(k4));
    }

    @Test
    @Points("t1-annaAla-iso-testi")
    public void annaAlaBig() {
        Object k1 = Kuutio(1001);
        Object k2 = Kuutio(1134);
        Object k3 = Kuutio(1223);

        assertEquals(6012006, annaAla(k1));
        assertEquals(7715736, annaAla(k2));
        assertEquals(8974374, annaAla(k3));
    }

    @Test
    @Points("t1-annaAla-nolla-testi")
    public void annaAlaZero() {
        Object k = Kuutio(0);
        assertEquals(0, annaAla(k));
    }

    @Test
    @Points("t1-annaTilavuus-pieni-testi")
    public void annaTilavuusSmall() {
        Object k1 = Kuutio(1);
        Object k2 = Kuutio(2);
        Object k3 = Kuutio(5);
        Object k4 = Kuutio(11);

        assertEquals(1, annaTilavuus(k1));
        assertEquals(8, annaTilavuus(k2));
        assertEquals(125, annaTilavuus(k3));
        assertEquals(1331, annaTilavuus(k4));
    }

    @Test
    @Points("t1-annaTilavuus-iso-testi")
    public void annaTilavuusBig() {
        Object k1 = Kuutio(1001);
        Object k2 = Kuutio(1134);
        Object k3 = Kuutio(1223);

        assertEquals(1003003001, annaTilavuus(k1));
        assertEquals(1458274104, annaTilavuus(k2));
        assertEquals(1829276567, annaTilavuus(k3));
    }
    
    @Test
    @Points("t1-annaTilavuus-nolla-testi")
    public void annaTilavuusZero() {
        Object k = Kuutio(0);
        assertEquals(0, annaAla(k));
    }

    private Object Kuutio(int sivunPituus) {
        try {
            return Reflex.reflect(KUUTIO).ctor().taking(int.class).invoke(sivunPituus);
        } catch (Throwable t) {
            return null;
        }
    }

    private int annaAla(Object kuutio) {
        try {
            return Reflex.reflect(KUUTIO).method(ANNA_ALA).returning(int.class).takingNoParams().invokeOn(kuutio);
        } catch (Throwable t) {
            return -1;
        }
    }

    private int annaTilavuus(Object kuutio) {
        try {
            return Reflex.reflect(KUUTIO).method(ANNA_TILAVUUS).returning(int.class).takingNoParams().invokeOn(kuutio);
        } catch (Throwable t) {
            return -1;
        }
    }

}
