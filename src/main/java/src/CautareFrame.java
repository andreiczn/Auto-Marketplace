import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CautareFrame extends JFrame {
    private JTextField marcaField, modelField, anField, kmField, pretField;
    private JButton cautaButton;
    private JTextArea rezultateArea;

    private List<Autovehicul> listaAutovehicule = new ArrayList<>();

    private static final String FILE_PATH = "stoc.txt";

    public CautareFrame() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Cautare Autovehicule");
        setSize(400, 300);

        marcaField = new JTextField(15);
        modelField = new JTextField(15);
        anField = new JTextField(4);
        kmField = new JTextField(10);
        pretField = new JTextField(10);

        cautaButton = new JButton("Caută");
        rezultateArea = new JTextArea(10, 30);
        rezultateArea.setEditable(false);

        setLayout(new GridLayout(6, 2));
        add(new JLabel("Marca:"));
        add(marcaField);
        add(new JLabel("Model:"));
        add(modelField);
        add(new JLabel("An fabricație:"));
        add(anField);
        add(new JLabel("Nr maxim de kilometri:"));
        add(kmField);
        add(new JLabel("Preț maxim:"));
        add(pretField);
        add(cautaButton);

        JScrollPane scrollPane = new JScrollPane(rezultateArea);
        add(scrollPane);

        cautaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cautaCuFiltre();
            }
        });
    }

    private void incarcaDateDinFisier() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String linie;
            while ((linie = reader.readLine()) != null) {
                // Pt debug
                System.out.println("Linie citită din fisier: " + linie);

                // Parsare linie si creare obiect Autovehicul
                Autovehicul autovehicul = parseazaLinie(linie);
                if (autovehicul != null) {
                    listaAutovehicule.add(autovehicul);
                }
            }

            
            //scrieInFisier(); // Altfel se rescrie in fisier lista cu autovehicule
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private Autovehicul parseazaLinie(String linie) {
        try {
            // Verificare format, pt debug
            if (linie.startsWith("Autovehiculul")) {
                // Formatul specific metodei toString()
                String[] informatii = linie.split(" ");

                // Extrage marca si model
                String marca = informatii[2].substring(0, informatii[2].length() - 1).substring(0, 1).toUpperCase() + informatii[2].substring(0, informatii[2].length() - 1).substring(1).toLowerCase();
                String model = informatii[4].substring(0, informatii[4].length()).toLowerCase(); // Elimină ultima virgulă și convertim la litere mici


                // Extrage anul
                int indexAn = linie.indexOf("anul de fabricatie") + "anul de fabricatie".length();
                int an = Integer.parseInt(linie.substring(indexAn, indexAn + 5).trim());

                // Extrage kilometrajul
                int indexKm = linie.indexOf("a parcurs in total") + "a parcurs in total".length();
                double km = Double.parseDouble(linie.substring(indexKm, linie.indexOf("km")).trim());

                // Extrage pret
                int indexPret = linie.indexOf("Pretul autovehiculului este de") + "Pretul autovehiculului este de".length();
                double pret = Double.parseDouble(linie.substring(indexPret, linie.indexOf("euro")).trim());

                // Crearea si returnarea unui obiect Autovehicul
                return new Autovehicul(marca, model, an, km, pret);
            } else {
                // Linie cu format incorect pentru un Autovehicul
                System.out.println("Format linie incorect");
                return null;
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        // in caz de eroare
        return null;
    }

    private void scriereInFisier() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(listaAutovehicule);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cautaCuFiltre() {
        String marca = marcaField.getText();
        String model = modelField.getText();
        int an = anField.getText().isEmpty() ? 0 : Integer.parseInt(anField.getText());
        double km = kmField.getText().isEmpty() ? 0 : Double.parseDouble(kmField.getText());
        double pret = pretField.getText().isEmpty() ? 0 : Double.parseDouble(pretField.getText());

        List<Autovehicul> rezultate = cautaAutovehiculeCuFiltre(marca, model, an, km, pret);

        afiseazaRezultatele(rezultate);
    }

 
    private List<Autovehicul> cautaAutovehiculeCuFiltre(String marca, String model, int an, double km, double pret) {
        List<Autovehicul> rezultate = new ArrayList<>();

        for (Autovehicul autovehicul : listaAutovehicule) {
            boolean potrivire = true;

            
            if (!marca.isEmpty() && !autovehicul.getMarca().toLowerCase().contains(marca.toLowerCase())) {
                potrivire = false;
            }

            if (!model.isEmpty() && !autovehicul.getModel().toLowerCase().contains(model.toLowerCase())) {
                potrivire = false;
            }

            if (an != 0 && autovehicul.getAn() != an) {
                potrivire = false;
            }

            if (km != 0 && autovehicul.getKm() > km) {
                potrivire = false;
            }

            if (pret != 0 && autovehicul.getPret() > pret) {
                potrivire = false;
            }

            if (potrivire) {
                rezultate.add(autovehicul);
            }
        }

        return rezultate;
    }


    private void afiseazaRezultatele(List<Autovehicul> rezultate) {
        rezultateArea.setText(""); // Curata zona de text

        if (!rezultate.isEmpty()) {
            for (Autovehicul autovehicul : rezultate) {
                rezultateArea.append(autovehicul.toString() + "\n");
            }
        } else {
            rezultateArea.append("Niciun rezultat găsit.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CautareFrame cautareFrame = new CautareFrame();
            cautareFrame.incarcaDateDinFisier();
            cautareFrame.setVisible(true);
        });
    }
}
