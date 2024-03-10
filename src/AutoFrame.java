import javax.swing.*;
import java.util.Iterator;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Fereastra pentru gestionarea stocului autovehiculelor.
 *
 * <p>Această fereastră oferă utilizatorului funcționalități precum adăugarea, actualizarea și ștergerea autovehiculelor.</p>
 */
public class AutoFrame extends JFrame {
    public List<Autovehicul> listaAutovehicule = new ArrayList<>();
    public JTextField marcaField, modelField, anField, kmField, pretField;
    public JButton adaugaButton, actualizeazaButton, stergeButton, meniuPrincipalButton;

    private static final String FILE_PATH = "stoc.txt";

    /**
     * Constructorul clasei AutoFrame.
     * Inițializează componentele ferestrei și încarcă datele din fișier.
     * 
     * Se efectuează adăugarea butoanelor și aranjarea acestora în frame, dar și acțiuni pentru acestea.
     * 
     */
    public AutoFrame() {

        incarcaDateDinFisier();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Gestionare Parc Auto");
        setSize(600, 500);

        marcaField = new JTextField(20);
        modelField = new JTextField(20);
        anField = new JTextField(4);
        kmField = new JTextField(10);
        pretField = new JTextField(10);

        adaugaButton = new JButton("Adaugă");
        actualizeazaButton = new JButton("Actualizează");
        stergeButton = new JButton("Șterge");
        meniuPrincipalButton = new JButton("Meniu Principal");

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Aranjare in frame
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
        add(new JLabel("Kilometri parcurși:"), gbc);
        gbc.gridx = 1;
        add(kmField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Preț:"), gbc);
        gbc.gridx = 1;
        add(pretField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(adaugaButton, gbc);

        gbc.gridy = 6;
        add(actualizeazaButton, gbc);

        gbc.gridy = 7;
        add(stergeButton, gbc);

        gbc.gridy = 8;
        add(meniuPrincipalButton, gbc);

        adaugaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adaugaAutovehicul();
            }
        });

        actualizeazaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actualizeazaAutovehicul();
            }
        });

        stergeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                stergeAutovehicul();
            }
        });

        meniuPrincipalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deschideFramePrincipal();
            }
        });
    }

    /**
     * Deschide fereastra principală și închide fereastra curentă.
     */
    private void deschideFramePrincipal() {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
        this.dispose();
    }

    /**
     * Afișează lista de autovehicule în consolă, pentru verificare.
     */
    private void afiseazaListaAutovehicule() {
        System.out.println("Lista de autovehicule:");
        for (Autovehicul autovehicul : listaAutovehicule) {
            System.out.println(autovehicul);
        }
    }

    /**
     * Scrie informațiile despre autovehicul în fișier.
     *
     * @param autovehicul
     */
    private void scrieAutoInFisier(Autovehicul autovehicul) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            if (!listaAutovehicule.isEmpty()) {
                writer.newLine();
            }

            writer.write(autovehicul.toString());
            writer.newLine(); // Adaugă o linie nouă după fiecare mașină
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Scrie lista de autovehicule în fișier.
     */
    private void scrieInFisier() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Autovehicul autovehicul : listaAutovehicule) {
                writer.write(autovehicul.toString());
                writer.newLine(); // Adaugă o linie nouă după fiecare mașină
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Încarcă datele despre autovehicule din fișier.
     */
    private void incarcaDateDinFisier() {
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
     * Adaugă un autovehicul în listă și îl scrie în fișier.
     */
    public void adaugaAutovehicul() {
        String marca = marcaField.getText();
        String model = modelField.getText();
        int an = Integer.parseInt(anField.getText());
        double km = Double.parseDouble(kmField.getText());
        double pret = Double.parseDouble(pretField.getText());

        Autovehicul autovehicul = new Autovehicul(marca, model, an, km, pret);

        listaAutovehicule.add(autovehicul);
        scrieAutoInFisier(autovehicul);

        JOptionPane.showMessageDialog(this, "Autovehicul adăugat:\n" + autovehicul.toString(), "Succes", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Actualizează informațiile despre un autovehicul în listă, urmând ca mai apoi sa efectueze modificarea și în fișier.
     */
    public void actualizeazaAutovehicul() {
        String marca = marcaField.getText();
        String model = modelField.getText();

        Autovehicul autovehiculCautat = null;
        int indexAutovehicul = -1;

        for (int i = 0; i < listaAutovehicule.size(); i++) {
            Autovehicul autovehicul = listaAutovehicule.get(i);
            if (autovehicul.getMarca().equals(marca) && autovehicul.getModel().equals(model)) {
                autovehiculCautat = autovehicul;
                indexAutovehicul = i;
                break;
            }
        }

        if (autovehiculCautat != null && indexAutovehicul != -1) {
            autovehiculCautat.setAn(Integer.parseInt(anField.getText()));
            autovehiculCautat.setKm(Double.parseDouble(kmField.getText()));
            autovehiculCautat.setPret(Double.parseDouble(pretField.getText()));

            scrieInFisier();

            JOptionPane.showMessageDialog(this, "Autovehicul actualizat și rescris în fișier:\n" + autovehiculCautat.toString(), "Succes", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Autovehicul nu a fost găsit.", "Atenție", JOptionPane.WARNING_MESSAGE);
        }
    }
    

    /**
     * Șterge un autovehicul din listă, urmând ca mai apoi să îl șteargă și din fișier.
     */
    public void stergeAutovehicul() {
        String marca = marcaField.getText();
        String model = modelField.getText();

        boolean autovehiculSters = stergeAutovehiculDinLista(marca, model);

        if (autovehiculSters) {
            JOptionPane.showMessageDialog(this, "Autovehicul șters.", "Succes", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Autovehicul nu a fost găsit.", "Atenție", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Șterge un autovehicul din listă.
     *
     * @param marca Marca autovehiculului.
     * @param model Modelul autovehiculului.
     * @return {@code true} dacă autovehicul a fost șters, {@code false} altfel.
     */
    private boolean stergeAutovehiculDinLista(String marca, String model) {
        Iterator<Autovehicul> iterator = listaAutovehicule.iterator();

        while (iterator.hasNext()) {
            Autovehicul autovehicul = iterator.next();
            if (autovehicul.getMarca().equals(marca) && autovehicul.getModel().equals(model)) {
                iterator.remove();
                scrieInFisier();
                return true;
            }
        }
        return false;
    }

    /**
     * Metoda principală care creează și afișează fereastra AutoFrame.
     *
     * @param args Argumente de linie de comandă (nu sunt utilizate în acest caz).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AutoFrame autoFrame = new AutoFrame();
            autoFrame.afiseazaListaAutovehicule();
            autoFrame.setVisible(true);
        });
    }
}
