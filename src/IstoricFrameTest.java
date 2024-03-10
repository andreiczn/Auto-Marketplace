import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

public class IstoricFrameTest {

    @Test
    public void testAdaugaTranzactie() {
        IstoricFrame istoricFrame = new IstoricFrame();

        
        istoricFrame.autovehiculMarcaField.setText("MarcaTest");
        istoricFrame.autovehiculModelField.setText("ModelTest");
        istoricFrame.autovehiculAnFabricatieField.setText("2022");
        istoricFrame.autovehiculKmField.setText("100");
        istoricFrame.autovehiculPretField.setText("20000");
        istoricFrame.clientNumeField.setText("NumeTest");
        istoricFrame.clientPrenumeField.setText("PrenumeTest");
        istoricFrame.clientAdresaField.setText("AdresaTest");
        istoricFrame.clientNumarTelefonField.setText("123456789");
        istoricFrame.dataTranzactieField.setText("2022-01-24");
        istoricFrame.vanzareCheckBox.setSelected(true);

        
        istoricFrame.adaugaTranzactie();
        assertEquals(1, istoricFrame.istoricTranzactii.size());
    }

    @Test
    /*public void testAfiseazaIstoric() {
        IstoricFrame istoricFrame = new IstoricFrame();

        // Adaugă o tranzacție pentru a afișa
        istoricFrame.istoricTranzactii.add(new Istoric(
                new Autovehicul("MarcaTest", "ModelTest", 2022, 100, 20000),
                new Client("NumeTest", "PrenumeTest", "AdresaTest", "123456789"),
                new Date(),
                true
        ));

        // Redirectează ieșirea standard pentru a captura afișarea
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Verifică dacă afișarea istoricului funcționează corect
        istoricFrame.afiseazaIstoric();
        assertEquals("Istoric Tranzactii:\n\n" +
                "Marca: MarcaTest\n" +
                "Model: ModelTest\n" +
                "An Fabricație: 2022\n" +
                "Kilometri Parcursi: 100.0\n" +
                "Pret: 20000.0\n" +
                "\n" +
                "Nume: NumeTest\n" +
                "Prenume: PrenumeTest\n" +
                "Adresa: AdresaTest\n" +
                "Numar Telefon: 123456789\n" +
                "\n" +
                "Data Tranzacție: " + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "\n" +
                "Tip Tranzacție: Vânzare\n\n", outContent.toString());

        
        System.setOut(System.out);
    }*/

    //@Test
    public void testSalveazaIstoric() {
        IstoricFrame istoricFrame = new IstoricFrame();

        
        istoricFrame.istoricTranzactii.add(new Istoric(
                new Autovehicul("MarcaTest", "ModelTest", 2022, 100, 20000),
                new Client("NumeTest", "PrenumeTest", "AdresaTest", "123456789"),
                new Date(),
                true
        ));

   
        istoricFrame.salveazaIstoric();

     
        IstoricFrame istoricFrameSalvat = new IstoricFrame();
        istoricFrameSalvat.incarcaIstoric();

       
        assertEquals(1, istoricFrameSalvat.istoricTranzactii.size());
    }

    /*@Test
    public void testIncarcaIstoric() {
        IstoricFrame istoricFrame = new IstoricFrame();

      
        istoricFrame.istoricTranzactii.add(new Istoric(
                new Autovehicul("MarcaTest", "ModelTest", 2022, 100, 20000),
                new Client("NumeTest", "PrenumeTest", "AdresaTest", "123456789"),
                new Date(),
                true
        ));

       
        istoricFrame.salveazaIstoric();


        istoricFrame.istoricTranzactii.clear();


        istoricFrame.incarcaIstoric();
        assertEquals(1, istoricFrame.istoricTranzactii.size());
    }*/
}
