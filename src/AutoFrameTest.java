import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AutoFrameTest {

    @Test
    public void testAdaugaAutovehicul() {
        AutoFrame autoFrame = new AutoFrame();

        
        autoFrame.marcaField.setText("MarcaTest");
        autoFrame.modelField.setText("ModelTest");
        autoFrame.anField.setText("2022");
        autoFrame.kmField.setText("100");
        autoFrame.pretField.setText("20000");

        
        autoFrame.adaugaAutovehicul();

        
        Autovehicul autovehiculAdaugat = autoFrame.listaAutovehicule.get(autoFrame.listaAutovehicule.size() - 1);

        
        assertEquals("MarcaTest", autovehiculAdaugat.getMarca());
        assertEquals("ModelTest", autovehiculAdaugat.getModel());
        assertEquals(2022, autovehiculAdaugat.getAn());
        assertEquals(100, autovehiculAdaugat.getKm());
        assertEquals(20000, autovehiculAdaugat.getPret());
    }

    @Test
    public void testActualizeazaAutovehicul() {
        AutoFrame autoFrame = new AutoFrame();

        
        autoFrame.listaAutovehicule.add(new Autovehicul("MarcaTest", "ModelTest", 2022, 100, 20000));

        
        autoFrame.marcaField.setText("MarcaTest");
        autoFrame.modelField.setText("ModelTest");
        autoFrame.anField.setText("2023");
        autoFrame.kmField.setText("150");
        autoFrame.pretField.setText("25000");

        
        autoFrame.actualizeazaAutovehicul();

        
        Autovehicul autovehiculActualizat = autoFrame.listaAutovehicule.get(autoFrame.listaAutovehicule.size() - 1);

        
        assertEquals("MarcaTest", autovehiculActualizat.getMarca());
        assertEquals("ModelTest", autovehiculActualizat.getModel());
        assertEquals(2023, autovehiculActualizat.getAn());
        assertEquals(150, autovehiculActualizat.getKm());
        assertEquals(25000, autovehiculActualizat.getPret());
    }

    @Test
    public void testStergeAutovehicul() {
        AutoFrame autoFrame = new AutoFrame();

        
        autoFrame.listaAutovehicule.add(new Autovehicul("MarcaTest", "ModelTest", 2022, 100, 20000));

        
        autoFrame.marcaField.setText("MarcaTest");
        autoFrame.modelField.setText("ModelTest");

        
        autoFrame.stergeAutovehicul();

        
        assertTrue(autoFrame.listaAutovehicule.isEmpty());
    }
}
