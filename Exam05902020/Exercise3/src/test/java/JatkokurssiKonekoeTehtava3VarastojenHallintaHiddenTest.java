
import fi.helsinki.cs.tmc.edutestutils.MockStdio;
import fi.helsinki.cs.tmc.edutestutils.Points;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.Rule;

public class JatkokurssiExercise3VarastojenHallintaHiddenTest {
@Rule
    public MockStdio io = new MockStdio();

    @Test
    @Points("ohja-varastot-1")
    public void kerrotaanTuntemattomastaKomennosta() {
        io.setSysIn("litium akku jees\nlopeta\n");

        try {
            Exercise3.main(new String[]{});
        } catch (Throwable t) {

        }

        assertTrue(io.getSysOut().toLowerCase().contains("tuntematon"));
    }

    @Test
    @Points("ohja-varastot-1")
    public void eiKerrotaTuntemattomastaKomennostaJosKomentoOk() {
        io.setSysIn("lisaa akku jees\nlopeta\n");

        try {
            Exercise3.main(new String[]{});
        } catch (Throwable t) {

        }

        assertFalse(io.getSysOut().toLowerCase().contains("tuntematon"));
    }

    @Test
    @Points("ohja-varastot-2")
    public void esineidenLisaaminenJaListaaminenYksittaisetEsineet() {
        String lisaykset = lisaysMjono("norja", "lohi", "sukset", "pipo");

        String syote = lisaykset + "listaa norja\nlopeta\n";
        io.setSysIn(syote);

        try {
            Exercise3.main(new String[]{});
        } catch (Throwable t) {

        }

        String[] outputPalat = io.getSysOut().split("\\s+");
        Map<String, Long> termCount = Arrays.stream(outputPalat).map(s -> s.trim()).filter(s -> !s.isEmpty()).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())); // 

        assertTrue("Syöte:\n" + syote + "\nTulostus:\n" + io.getSysOut() + "\nArvot:\n" + termCount, termCount.containsKey("lohi") && termCount.get("lohi").equals(1L));
        assertTrue("Syöte:\n" + syote + "\nTulostus:\n" + io.getSysOut() + "\nArvot:\n" + termCount, termCount.containsKey("sukset") && termCount.get("sukset").equals(1L));
        assertTrue("Syöte:\n" + syote + "\nTulostus:\n" + io.getSysOut() + "\nArvot:\n" + termCount, termCount.containsKey("pipo") && termCount.get("pipo").equals(1L));

    }

    @Test
    @Points("ohja-varastot-3")
    public void esineidenOlemassaOlonTarkastaminenEsineLoytyy() {
        String lisaykset = lisaysMjono("norja", "lohi", "sukset", "lohi", "pipo", "pipo", "pipo");

        String syote = lisaykset + "hae norja sukset\nlopeta\n";
        io.setSysIn(syote);

        try {
            Exercise3.main(new String[]{});
        } catch (Throwable t) {

        }

        assertFalse(io.getSysOut(), io.getSysOut().toLowerCase().contains("ei"));
    }

    @Test
    @Points("ohja-varastot-3")
    public void esineidenOlemassaOlonTarkastaminenEsineEiLoydy() {
        String lisaykset = lisaysMjono("norja", "lohi", "sukset", "lohi", "pipo", "pipo", "pipo");

        String syote = lisaykset + "hae norja doping\nlopeta\n";
        io.setSysIn(syote);

        try {
            Exercise3.main(new String[]{});
        } catch (Throwable t) {

        }

        assertTrue(io.getSysOut(), io.getSysOut().toLowerCase().contains("ei"));
    }

    @Test
    @Points("ohja-varastot-4")
    public void esineidenLisaaminenJaPoistaminenYksittaisetEsineet() {
        String lisaykset = lisaysMjono("norja", "lohi", "sukset", "pipo");

        String syote = lisaykset + "poista norja sukset\nlistaa norja\nlopeta\n";
        io.setSysIn(syote);

        try {
            Exercise3.main(new String[]{});
        } catch (Throwable t) {

        }

        String[] outputPalat = io.getSysOut().split("\\s+");
        Map<String, Long> termCount = Arrays.stream(outputPalat).map(s -> s.trim()).filter(s -> !s.isEmpty()).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())); // 

        assertTrue("Syöte:\n" + syote + "\nTulostus:\n" + io.getSysOut() + "\nArvot:\n" + termCount, termCount.containsKey("lohi") && termCount.get("lohi").equals(1L));
        assertFalse("Syöte:\n" + syote + "\nTulostus:\n" + io.getSysOut() + "\nArvot:\n" + termCount, termCount.containsKey("sukset"));
        assertTrue("Syöte:\n" + syote + "\nTulostus:\n" + io.getSysOut() + "\nArvot:\n" + termCount, termCount.containsKey("pipo") && termCount.get("pipo").equals(1L));
    }

    @Test
    @Points("ohja-varastot-5")
    public void useammanEsineenKanssaToimiminenListaus() {
        String lisaykset = lisaysMjono("norja", "lohi", "sukset", "lohi", "pipo", "pipo", "pipo");

        String syote = lisaykset + "listaa norja\nlopeta\n";
        io.setSysIn(syote);

        try {
            Exercise3.main(new String[]{});
        } catch (Throwable t) {

        }

        String[] outputPalat = io.getSysOut().split("\\s+");
        Map<String, Long> termCount = Arrays.stream(outputPalat).map(s -> s.trim()).filter(s -> !s.isEmpty()).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())); // 

        assertTrue("Syöte:\n" + syote + "\nTulostus:\n" + io.getSysOut() + "\nArvot:\n" + termCount, termCount.containsKey("lohi") && termCount.get("lohi").equals(2L));
        assertTrue("Syöte:\n" + syote + "\nTulostus:\n" + io.getSysOut() + "\nArvot:\n" + termCount, termCount.containsKey("sukset") && termCount.get("sukset").equals(1L));
        assertTrue("Syöte:\n" + syote + "\nTulostus:\n" + io.getSysOut() + "\nArvot:\n" + termCount, termCount.containsKey("pipo") && termCount.get("pipo").equals(3L));

    }

    @Test
    @Points("ohja-varastot-6")
    public void useammanEsineenKanssaToimiminenPoisto() {
        String lisaykset = lisaysMjono("norja", "lohi", "sukset", "lohi", "pipo", "pipo", "pipo");

        String syote = lisaykset + "poista norja pipo\nlistaa norja\nlopeta\n";
        io.setSysIn(syote);

        try {
            Exercise3.main(new String[]{});
        } catch (Throwable t) {

        }

        String[] outputPalat = io.getSysOut().split("\\s+");
        Map<String, Long> termCount = Arrays.stream(outputPalat).map(s -> s.trim()).filter(s -> !s.isEmpty()).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())); // 

        assertTrue("Syöte:\n" + syote + "\nTulostus:\n" + io.getSysOut() + "\nArvot:\n" + termCount, termCount.containsKey("lohi") && termCount.get("lohi").equals(2L));
        assertTrue("Syöte:\n" + syote + "\nTulostus:\n" + io.getSysOut() + "\nArvot:\n" + termCount, termCount.containsKey("sukset") && termCount.get("sukset").equals(1L));
        assertTrue("Syöte:\n" + syote + "\nTulostus:\n" + io.getSysOut() + "\nArvot:\n" + termCount, termCount.containsKey("pipo") && termCount.get("pipo").equals(2L));
    }

    @Test
    @Points("ohja-varastot-7")
    public void useammanVarastonKanssaToimiminenListaus() {
        String lisaykset = lisaysMjono("norja", "lohi", "sukset", "lohi", "pipo", "pipo", "pipo");
        lisaykset += lisaysMjono("suomi", "terva", "viina", "sauna", "kuokka", "pipo");
        lisaykset += lisaysMjono("norja", "lohi", "sukset", "lohi", "pipo", "pipo", "pipo");

        String syote = lisaykset + "listaa norja\nlopeta\n";
        io.setSysIn(syote);

        try {
            Exercise3.main(new String[]{});
        } catch (Throwable t) {

        }

        String[] outputPalat = io.getSysOut().split("\\s+");
        Map<String, Long> termCount = Arrays.stream(outputPalat).map(s -> s.trim()).filter(s -> !s.isEmpty()).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())); // 

        assertTrue("Syöte:\n" + syote + "\nTulostus:\n" + io.getSysOut() + "\nArvot:\n" + termCount, termCount.containsKey("lohi") && termCount.get("lohi").equals(4L));
        assertTrue("Syöte:\n" + syote + "\nTulostus:\n" + io.getSysOut() + "\nArvot:\n" + termCount, termCount.containsKey("sukset") && termCount.get("sukset").equals(2L));
        assertTrue("Syöte:\n" + syote + "\nTulostus:\n" + io.getSysOut() + "\nArvot:\n" + termCount, termCount.containsKey("pipo") && termCount.get("pipo").equals(6L));
        assertTrue("Syöte:\n" + syote + "\nTulostus:\n" + io.getSysOut() + "\nArvot:\n" + termCount, !termCount.containsKey("viina"));
        assertTrue("Syöte:\n" + syote + "\nTulostus:\n" + io.getSysOut() + "\nArvot:\n" + termCount, !termCount.containsKey("sauna"));
        assertTrue("Syöte:\n" + syote + "\nTulostus:\n" + io.getSysOut() + "\nArvot:\n" + termCount, !termCount.containsKey("terva"));
    }

    @Test
    @Points("ohja-varastot-8")
    public void useammanVarastonKanssaToimiminenHaku() {
        String lisaykset = lisaysMjono("norja", "lohi", "sukset", "lohi", "pipo", "pipo", "pipo");
        lisaykset += lisaysMjono("suomi", "terva", "viina", "sauna", "kuokka", "pipo");
        lisaykset += lisaysMjono("norja", "lohi", "sukset", "lohi", "pipo", "pipo", "pipo", "doping");

        String syote = lisaykset + "hae norja doping\nlopeta\n";
        io.setSysIn(syote);

        try {
            Exercise3.main(new String[]{});
        } catch (Throwable t) {

        }

        String[] outputPalat = io.getSysOut().split("\\s+");
        assertFalse(io.getSysOut(), io.getSysOut().toLowerCase().contains("ei"));
//        
//        Map<String, Long> termCount = Arrays.stream(outputPalat).map(s -> s.trim()).filter(s -> !s.isEmpty()).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())); // 
//
//        assertTrue("Syöte:\n" + syote + "\nTulostus:\n" + io.getSysOut() + "\nArvot:\n" + termCount, termCount.containsKey("lohi") && termCount.get("lohi").equals(4L));
//        assertTrue("Syöte:\n" + syote + "\nTulostus:\n" + io.getSysOut() + "\nArvot:\n" + termCount, termCount.containsKey("sukset") && termCount.get("sukset").equals(2L));
//        assertTrue("Syöte:\n" + syote + "\nTulostus:\n" + io.getSysOut() + "\nArvot:\n" + termCount, termCount.containsKey("pipo") && termCount.get("pipo").equals(6L));
//        assertTrue("Syöte:\n" + syote + "\nTulostus:\n" + io.getSysOut() + "\nArvot:\n" + termCount, !termCount.containsKey("viina"));
//        assertTrue("Syöte:\n" + syote + "\nTulostus:\n" + io.getSysOut() + "\nArvot:\n" + termCount, !termCount.containsKey("sauna"));
//        assertTrue("Syöte:\n" + syote + "\nTulostus:\n" + io.getSysOut() + "\nArvot:\n" + termCount, !termCount.containsKey("terva"));
    }

    @Test
    @Points("ohja-varastot-8")
    public void useammanVarastonKanssaToimiminenHaku2() {
        String lisaykset = lisaysMjono("norja", "lohi", "sukset", "lohi", "pipo", "pipo", "pipo", "doping");
        lisaykset += lisaysMjono("suomi", "terva", "viina", "sauna", "kuokka", "pipo");
        lisaykset += lisaysMjono("norja", "lohi", "sukset", "lohi", "pipo", "pipo", "pipo");

        String syote = lisaykset + "hae norja doping\nlopeta\n";
        io.setSysIn(syote);

        try {
            Exercise3.main(new String[]{});
        } catch (Throwable t) {

        }

        String[] outputPalat = io.getSysOut().split("\\s+");
        assertFalse(io.getSysOut(), io.getSysOut().toLowerCase().contains("ei"));
//        
//        Map<String, Long> termCount = Arrays.stream(outputPalat).map(s -> s.trim()).filter(s -> !s.isEmpty()).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())); // 
//
//        assertTrue("Syöte:\n" + syote + "\nTulostus:\n" + io.getSysOut() + "\nArvot:\n" + termCount, termCount.containsKey("lohi") && termCount.get("lohi").equals(4L));
//        assertTrue("Syöte:\n" + syote + "\nTulostus:\n" + io.getSysOut() + "\nArvot:\n" + termCount, termCount.containsKey("sukset") && termCount.get("sukset").equals(2L));
//        assertTrue("Syöte:\n" + syote + "\nTulostus:\n" + io.getSysOut() + "\nArvot:\n" + termCount, termCount.containsKey("pipo") && termCount.get("pipo").equals(6L));
//        assertTrue("Syöte:\n" + syote + "\nTulostus:\n" + io.getSysOut() + "\nArvot:\n" + termCount, !termCount.containsKey("viina"));
//        assertTrue("Syöte:\n" + syote + "\nTulostus:\n" + io.getSysOut() + "\nArvot:\n" + termCount, !termCount.containsKey("sauna"));
//        assertTrue("Syöte:\n" + syote + "\nTulostus:\n" + io.getSysOut() + "\nArvot:\n" + termCount, !termCount.containsKey("terva"));
    }

    @Test
    @Points("ohja-varastot-8")
    public void useammanVarastonKanssaToimiminenHaku3() {
        String lisaykset = lisaysMjono("norja", "lohi", "sukset", "lohi", "pipo", "pipo", "pipo", "doping");
        lisaykset += lisaysMjono("suomi", "terva", "viina", "sauna", "kuokka", "pipo");
        lisaykset += lisaysMjono("norja", "lohi", "sukset", "lohi", "pipo", "pipo", "pipo");

        String syote = lisaykset + "hae norja jemma\nlopeta\n";
        io.setSysIn(syote);

        try {
            Exercise3.main(new String[]{});
        } catch (Throwable t) {

        }

        String[] outputPalat = io.getSysOut().split("\\s+");
        assertTrue(io.getSysOut(), io.getSysOut().toLowerCase().contains("ei"));
//        
//        Map<String, Long> termCount = Arrays.stream(outputPalat).map(s -> s.trim()).filter(s -> !s.isEmpty()).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())); // 
//
//        assertTrue("Syöte:\n" + syote + "\nTulostus:\n" + io.getSysOut() + "\nArvot:\n" + termCount, termCount.containsKey("lohi") && termCount.get("lohi").equals(4L));
//        assertTrue("Syöte:\n" + syote + "\nTulostus:\n" + io.getSysOut() + "\nArvot:\n" + termCount, termCount.containsKey("sukset") && termCount.get("sukset").equals(2L));
//        assertTrue("Syöte:\n" + syote + "\nTulostus:\n" + io.getSysOut() + "\nArvot:\n" + termCount, termCount.containsKey("pipo") && termCount.get("pipo").equals(6L));
//        assertTrue("Syöte:\n" + syote + "\nTulostus:\n" + io.getSysOut() + "\nArvot:\n" + termCount, !termCount.containsKey("viina"));
//        assertTrue("Syöte:\n" + syote + "\nTulostus:\n" + io.getSysOut() + "\nArvot:\n" + termCount, !termCount.containsKey("sauna"));
//        assertTrue("Syöte:\n" + syote + "\nTulostus:\n" + io.getSysOut() + "\nArvot:\n" + termCount, !termCount.containsKey("terva"));
    }

    @Test
    @Points("ohja-varastot-8")
    public void useammanVarastonKanssaToimiminenHaku4() {
        String lisaykset = lisaysMjono("norja", "lohi", "sukset", "lohi", "pipo", "pipo", "pipo", "doping");
        lisaykset += lisaysMjono("suomi", "terva", "viina", "sauna", "kuokka", "pipo");
        lisaykset += lisaysMjono("norja", "lohi", "sukset", "lohi", "pipo", "pipo", "pipo");

        String syote = lisaykset + "hae suomi kuokka\nlopeta\n";
        io.setSysIn(syote);

        try {
            Exercise3.main(new String[]{});
        } catch (Throwable t) {

        }

        String[] outputPalat = io.getSysOut().split("\\s+");
        assertFalse(io.getSysOut(), io.getSysOut().toLowerCase().contains("ei"));
//        
//        Map<String, Long> termCount = Arrays.stream(outputPalat).map(s -> s.trim()).filter(s -> !s.isEmpty()).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())); // 
//
//        assertTrue("Syöte:\n" + syote + "\nTulostus:\n" + io.getSysOut() + "\nArvot:\n" + termCount, termCount.containsKey("lohi") && termCount.get("lohi").equals(4L));
//        assertTrue("Syöte:\n" + syote + "\nTulostus:\n" + io.getSysOut() + "\nArvot:\n" + termCount, termCount.containsKey("sukset") && termCount.get("sukset").equals(2L));
//        assertTrue("Syöte:\n" + syote + "\nTulostus:\n" + io.getSysOut() + "\nArvot:\n" + termCount, termCount.containsKey("pipo") && termCount.get("pipo").equals(6L));
//        assertTrue("Syöte:\n" + syote + "\nTulostus:\n" + io.getSysOut() + "\nArvot:\n" + termCount, !termCount.containsKey("viina"));
//        assertTrue("Syöte:\n" + syote + "\nTulostus:\n" + io.getSysOut() + "\nArvot:\n" + termCount, !termCount.containsKey("sauna"));
//        assertTrue("Syöte:\n" + syote + "\nTulostus:\n" + io.getSysOut() + "\nArvot:\n" + termCount, !termCount.containsKey("terva"));
    }

    @Test
    @Points("ohja-varastot-9 ohja-varastot-10")
    public void useammanVarastonKanssaToimiminenPoisto() {
        String lisaykset = lisaysMjono("norja", "lohi", "sukset", "lohi", "pipo", "pipo", "pipo");
        lisaykset += lisaysMjono("suomi", "terva", "viina", "sauna", "kuokka", "pipo");
        lisaykset += lisaysMjono("norja", "lohi", "sukset", "lohi", "pipo", "pipo", "pipo");

        String syote = lisaykset + "poista norja pipo\npoista norja sukset\npoista norja sukset\nlistaa norja\nlopeta\n";
        io.setSysIn(syote);

        try {
            Exercise3.main(new String[]{});
        } catch (Throwable t) {

        }

        String[] outputPalat = io.getSysOut().split("\\s+");
        Map<String, Long> termCount = Arrays.stream(outputPalat).map(s -> s.trim()).filter(s -> !s.isEmpty()).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())); // 

        assertTrue("Syöte:\n" + syote + "\nTulostus:\n" + io.getSysOut() + "\nArvot:\n" + termCount, termCount.containsKey("lohi") && termCount.get("lohi").equals(4L));
        assertTrue("Syöte:\n" + syote + "\nTulostus:\n" + io.getSysOut() + "\nArvot:\n" + termCount, !termCount.containsKey("sukset"));
        assertTrue("Syöte:\n" + syote + "\nTulostus:\n" + io.getSysOut() + "\nArvot:\n" + termCount, termCount.containsKey("pipo") && termCount.get("pipo").equals(5L));
        assertTrue("Syöte:\n" + syote + "\nTulostus:\n" + io.getSysOut() + "\nArvot:\n" + termCount, !termCount.containsKey("viina"));
        assertTrue("Syöte:\n" + syote + "\nTulostus:\n" + io.getSysOut() + "\nArvot:\n" + termCount, !termCount.containsKey("sauna"));
        assertTrue("Syöte:\n" + syote + "\nTulostus:\n" + io.getSysOut() + "\nArvot:\n" + termCount, !termCount.containsKey("terva"));
    }

    @Test
    @Points("ohja-varastot-9 ohja-varastot-10")
    public void useammanVarastonKanssaToimiminenPoisto2() {
        String lisaykset = lisaysMjono("norja", "lohi", "sukset", "lohi", "pipo", "pipo", "pipo");
        lisaykset += lisaysMjono("suomi", "terva", "viina", "sauna", "kuokka", "pipo");
        lisaykset += lisaysMjono("norja", "lohi", "sukset", "lohi", "pipo", "pipo", "pipo");

        String syote = lisaykset + "poista suomi terva\nlistaa suomi\nlopeta\n";
        io.setSysIn(syote);

        try {
            Exercise3.main(new String[]{});
        } catch (Throwable t) {

        }

        String[] outputPalat = io.getSysOut().split("\\s+");
        Map<String, Long> termCount = Arrays.stream(outputPalat).map(s -> s.trim()).filter(s -> !s.isEmpty()).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())); // 

        assertTrue("Syöte:\n" + syote + "\nTulostus:\n" + io.getSysOut() + "\nArvot:\n" + termCount, !termCount.containsKey("lohi"));
        assertTrue("Syöte:\n" + syote + "\nTulostus:\n" + io.getSysOut() + "\nArvot:\n" + termCount, !termCount.containsKey("sukset"));
        assertTrue("Syöte:\n" + syote + "\nTulostus:\n" + io.getSysOut() + "\nArvot:\n" + termCount, termCount.containsKey("viina"));
        assertTrue("Syöte:\n" + syote + "\nTulostus:\n" + io.getSysOut() + "\nArvot:\n" + termCount, termCount.containsKey("sauna"));
    }

    public String lisaysMjono(String paikka, String... esineet) {
        String lisaysMjono = "";

        for (String esine : esineet) {
            lisaysMjono += "lisaa " + paikka + " " + esine + "\n";
        }

        return lisaysMjono;
    }
}
