import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Fereastra pentru căutarea autovehiculelor.
 *
 * <p>Această fereastră oferă utilizatorului posibilitatea de a introduce filtre pentru căutarea autovehiculelor în funcție de marcă, model, an, kilometraj și preț.</p>
 */
public class CautareFrame extends JFrame {
    private JTextField marcaField, modelField, anField, kmField, pretField;
    private JButton cautaButton, meniuPrincipalButton;
    private JTextArea rezultateArea;

    private List<Autovehicul> listaAutovehicule = new ArrayList<>();

    private static final String FILE_PATH = "stoc.txt";

    /**
     * Constructorul clasei CautareFrame.
     * Inițializează componentele ferestrei și adaugă action listeners pentru evenimentele de căutare și revenire la meniul principal.
     */
    public CautareFrame() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Cautare Autovehicule");
        setSize(600, 500);

        marcaField = new JTextField(15);
        modelField = new JTextField(15);
        anField = new JTextField(4);
        kmField = new JTextField(10);
        pretField = new JTextField(10);

        cautaButton = new JButton("Caută");
        meniuPrincipalButton = new JButton("Meniu Principal");
        rezultateArea = new JTextArea(10, 30);
        rezultateArea.setEditable(false);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Marca:"), gbc);
        gbc.gridx = 1;
        add(marcaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Model:"), gbc);
        gbc.gridx = 1;
        add(modelField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("An fabricație:"), gbc);
        gbc.gridx = 1;
        add(anField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Nr maxim de km:"), gbc);
        gbc.gridx = 1;
        add(kmField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Preț maxim:"), gbc);
        gbc.gridx = 1;
        add(pretField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(cautaButton, gbc);

        gbc.gridx = 1;
        add(meniuPrincipalButton, gbc);

        gbc.gridy = 6;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        JScrollPane scrollPane = new JScrollPane(rezultateArea);
        add(scrollPane, gbc);

        cautaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cautaCuFiltre();
            }
        });

        meniuPrincipalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deschideFramePrincipal();
            }
        });
    }

    /**
     * Revenirea la meniul principal.
     */
    private void deschideFramePrincipal() {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
        this.dispose();
    }

    /**
     * Încarcă date despre autovehiculele din fișier.
     */
    public void incarcaDateDinFisier() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String linie;
            while ((linie = reader.readLine()) != null) {
                Autovehicul autovehicul = parseazaLinie(linie);
                if (autovehicul != null) {
                    listaAutovehicule.add(autovehicul);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Parsează o linie din fișier și creează un obiect Autovehicul.
     * Se descompune formatul toString din clasa Autovehicul, se extrag atributele necesare care sunt mai apoi folosite pentru a crea obiectul de tip Autovehicul.
     * @param linie 
     * @return Obiectul Autovehicul creat sau null în caz de eroare.
     */
    private Autovehicul parseazaLinie(String linie) {
        try {
            if (linie.startsWith("Autovehiculul")) {
                String[] informatii = linie.split(" ");
                String marca = informatii[2].substring(0, informatii[2].length() - 1).substring(0, 1).toUpperCase() + informatii[2].substring(0, informatii[2].length() - 1).substring(1).toLowerCase();
                String model = informatii[4].substring(0, informatii[4].length()).toLowerCase();

                int indexAn = linie.indexOf("anul de fabricatie") + "anul de fabricatie".length();
                int an = Integer.parseInt(linie.substring(indexAn, indexAn + 5).trim());

                int indexKm = linie.indexOf("a parcurs in total") + "a parcurs in total".length();
                double km = Double.parseDouble(linie.substring(indexKm, linie.indexOf("km")).trim());

                int indexPret = linie.indexOf("Pretul autovehiculului este de") + "Pretul autovehiculului este de".length();
                double pret = Double.parseDouble(linie.substring(indexPret, linie.indexOf("euro")).trim());

                return new Autovehicul(marca, model, an, km, pret);
            } else {
                System.out.println("Format linie incorect");
                return null;
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Scrie lista de autovehicule în fișier.
     */
    private void scrieInFisier() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(listaAutovehicule);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Preia datele introduse de către autor în câmpurile aferente și apelează funcția de căutare.
     */
    private void cautaCuFiltre() {
        String marca = marcaField.getText();
        String model = modelField.getText();
        int an = anField.getText().isEmpty() ? 0 : Integer.parseInt(anField.getText());
        double km = kmField.getText().isEmpty() ? 0 : Double.parseDouble(kmField.getText());
        double pret = pretField.getText().isEmpty() ? 0 : Double.parseDouble(pretField.getText());

        List<Autovehicul> rezultate = cautaAutovehiculeCuFiltre(marca, model, an, km, pret);

        afiseazaRezultatele(rezultate);
    }

    /**
     * Caută autovehiculele din lista cu filtrele specificate.
     *
     * @param marca Filtrează după marcă.
     * @param model Filtrează după model.
     * @param an    Filtrează după an.
     * @param km    Filtrează după kilometraj.
     * @param pret  Filtrează după preț.
     * @return Lista de autovehicule care respectă filtrele.
     */
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

    /**
     * Afișează rezultatele în zona de text.
     *
     * @param rezultate
     */
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

    /**
     * Metoda principală care creează și afișează fereastra CautareFrame.
     *
     * @param args Argumente de linie de comandă (nu sunt utilizate în acest caz).
     */
    public static void main(String[] args) {
        CautareFrame cautareFrame = new CautareFrame();
        cautareFrame.incarcaDateDinFisier();
        SwingUtilities.invokeLater(() -> {
            cautareFrame.setVisible(true);
        });
    }
}
