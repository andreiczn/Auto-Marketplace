import javax.swing.*;
import java.util.Iterator;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AutoFrame extends JFrame {
    private List<Autovehicul> listaAutovehicule = new ArrayList<>();
    private JTextField marcaField, modelField, anField, kmField, pretField;
    private JButton adaugaButton, actualizeazaButton, stergeButton;

    private static final String FILE_PATH = "stoc.txt";

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
    }
    
    private void afiseazaListaAutovehicule() {
        System.out.println("Lista de autovehicule:");
        for (Autovehicul autovehicul : listaAutovehicule) {
            System.out.println(autovehicul);
        }
    }

    private void scrieInFisier() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            if (!listaAutovehicule.isEmpty()) {
                writer.newLine();
            }

            for (Autovehicul autovehicul : listaAutovehicule) {
                writer.write(autovehicul.toString());
                writer.newLine(); // Adaugă o linie nouă după fiecare mașină
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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





    
    private void adaugaAutovehicul() {
        String marca = marcaField.getText();
        String model = modelField.getText();
        int an = Integer.parseInt(anField.getText());
        double km = Double.parseDouble(kmField.getText());
        double pret = Double.parseDouble(pretField.getText());

        Autovehicul autovehicul = new Autovehicul(marca, model, an, km, pret);

        // Se adauga masina, se rescrie fisierul
        listaAutovehicule.add(autovehicul);
        scrieInFisier();

        JOptionPane.showMessageDialog(this, "Autovehicul adăugat:\n" + autovehicul.toString(), "Succes", JOptionPane.INFORMATION_MESSAGE);
    }

    private void actualizeazaAutovehicul() {
        String marca = marcaField.getText();
        String model = modelField.getText();

        // Caută autovehiculul în lista
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
            // Actualizare campuri
            autovehiculCautat.setAn(Integer.parseInt(anField.getText()));
            autovehiculCautat.setKm(Double.parseDouble(kmField.getText()));
            autovehiculCautat.setPret(Double.parseDouble(pretField.getText()));

            // Actualizeaza lista în memorie
            listaAutovehicule.set(indexAutovehicul, autovehiculCautat);

            JOptionPane.showMessageDialog(this, "Autovehicul actualizat:\n" + autovehiculCautat.toString(), "Succes", JOptionPane.INFORMATION_MESSAGE);

            // Dupa actualizare, rescrie fisierul complet
            scrieInFisier();
        } else {
            // Autovehiculul nu a fost gasit
            JOptionPane.showMessageDialog(this, "Autovehicul nu a fost găsit.", "Atenție", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void stergeAutovehicul() {
        String marca = marcaField.getText();
        String model = modelField.getText();

        boolean autovehiculSters = stergeAutovehiculDinLista(marca, model);

        if (autovehiculSters) {
            JOptionPane.showMessageDialog(this, "Autovehicul șters.", "Succes", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Autovehicul nu a fost găsit.", "Atenție", JOptionPane.WARNING_MESSAGE);
        }
    }

    private boolean stergeAutovehiculDinLista(String marca, String model) {
        // Folosim un iterator pentru a evita ConcurrentModificationException
        Iterator<Autovehicul> iterator = listaAutovehicule.iterator();

        while (iterator.hasNext()) {
            Autovehicul autovehicul = iterator.next();
            if (autovehicul.getMarca().equals(marca) && autovehicul.getModel().equals(model)) {
                iterator.remove(); 
                scrieInFisier(); // După ștergere, se rescrie fisierul
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AutoFrame autoFrame = new AutoFrame();
            autoFrame.afiseazaListaAutovehicule();
            autoFrame.setVisible(true);
        });
    }
}
