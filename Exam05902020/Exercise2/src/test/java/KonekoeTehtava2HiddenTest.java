import fi.helsinki.cs.tmc.edutestutils.Points;
import org.junit.Test;
import org.junit.Rule;
import fi.helsinki.cs.tmc.edutestutils.ReflectionUtils;
import fi.helsinki.cs.tmc.edutestutils.Reflex;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

public class KonekoeTehtava2HiddenTest {

    private static final String TALLENNIN = "Saver";
    private static final String POIKKEUS = "PasswordException";
    private static final String PAIVAKIRJA = "SecretDiary";
    
    @Test
    @Points("t2-tallennin-on-olemassa")
    public void tallenninExists() {
        Class tallennin = ReflectionUtils.findClass(TALLENNIN);
        assertTrue(tallennin.isInterface());
    }

    @Test
    @Points("t2-salasanapoikkeus-on-olemassa")
    public void poikkeusExists() {
        Reflex.reflect(POIKKEUS).requirePublic().ctor().taking(String.class).requireExists();
    }

    @Test
    @Points("t2-salasanapoikkeus-extend-exception")
    public void poikkeusExtends() {
        Class superclass = ReflectionUtils.findClass(POIKKEUS).getSuperclass();
        assertEquals(Exception.class, superclass);
    }

    @Test
    @Points("t2-paivakirja-on-olemassa")
    public void paivakirjaExists() {
        Reflex.reflect(PAIVAKIRJA).requirePublic().ctor().taking(String.class).requireExists();
    }
    @Test
    @Points("t2-paivakirja-toteuttaa-tallennin")
    public void paivakirjaImplements() {
        Class interfaces[] = ReflectionUtils.findClass(PAIVAKIRJA).getInterfaces();
        Class tallennin = ReflectionUtils.findClass(TALLENNIN);
        Class correct[] = {tallennin};
        assertTrue(Arrays.equals(interfaces, correct));
    }
}
