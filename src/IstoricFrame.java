import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Fereastra pentru gestionarea și afișarea istoricului tranzacțiilor.
 *
 * <p>Această fereastră permite utilizatorului să adauge noi tranzacții și să vizualizeze istoricul existent al tranzacțiilor. De asemenea, salvează și încarcă istoricul în/dintr-un fișier.</p>
 */
public class IstoricFrame extends JFrame {
    public List<Istoric> istoricTranzactii = new ArrayList<>();
    public JTextArea istoricTextArea;
    public JTextField autovehiculMarcaField;
    public JTextField autovehiculModelField;
    public JTextField autovehiculAnFabricatieField;
    public JTextField autovehiculKmField;
    public JTextField autovehiculPretField;
    public JTextField clientNumeField;
    public JTextField clientPrenumeField;
    public JTextField clientAdresaField;
    public JTextField clientNumarTelefonField;
    public JTextField dataTranzactieField;
    public JCheckBox vanzareCheckBox;

    public static final String ISTORIC_FILE_PATH = "istoric.txt";

    /**
     * Constructorul clasei IstoricFrame.
     * Inițializează componentele ferestrei, le aranjează în frame și adaugă action listeners pentru evenimentele de adăugare a tranzacțiilor și revenire la meniul principal.
     */
    public IstoricFrame() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Istoric Tranzactii");
        setSize(1200, 800);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        istoricTextArea = new JTextArea(20, 60);
        istoricTextArea.setEditable(true);

        autovehiculMarcaField = new JTextField(20);
        autovehiculModelField = new JTextField(20);
        autovehiculAnFabricatieField = new JTextField(4);
        autovehiculKmField = new JTextField(10);
        autovehiculPretField = new JTextField(10);
        clientNumeField = new JTextField(20);
        clientPrenumeField = new JTextField(20);
        clientAdresaField = new JTextField(40);
        clientNumarTelefonField = new JTextField(15);
        dataTranzactieField = new JTextField(10);
        vanzareCheckBox = new JCheckBox("Vânzare");

        JButton adaugaTranzactieButton = new JButton("Adaugă Tranzacție");
        JButton meniuPrincipalButton = new JButton("Meniu Principal");

        // Autovehicul
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Autovehicul: "), gbc);

        gbc.gridy++;
        add(new JLabel("Marca: "), gbc);
        gbc.gridx++;
        add(autovehiculMarcaField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Model: "), gbc);
        gbc.gridx++;
        add(autovehiculModelField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("An Fabricație: "), gbc);
        gbc.gridx++;
        add(autovehiculAnFabricatieField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Kilometri Parcursi: "), gbc);
        gbc.gridx++;
        add(autovehiculKmField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Pret: "), gbc);
        gbc.gridx++;
        add(autovehiculPretField, gbc);

        // Client
        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Client: "), gbc);

        gbc.gridy++;
        add(new JLabel("Nume: "), gbc);
        gbc.gridx++;
        add(clientNumeField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Prenume: "), gbc);
        gbc.gridx++;
        add(clientPrenumeField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Adresa: "), gbc);
        gbc.gridx++;
        add(clientAdresaField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Numar Telefon: "), gbc);
        gbc.gridx++;
        add(clientNumarTelefonField, gbc);

        gbc.gridy++;
        add(new JLabel("Data Tranzacție (yyyy-MM-dd): "), gbc);
        gbc.gridx++;
        add(dataTranzactieField, gbc);
        gbc.gridx++;
        add(vanzareCheckBox, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        add(adaugaTranzactieButton, gbc);

        gbc.gridx++;
        add(meniuPrincipalButton, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        add(new JScrollPane(istoricTextArea), gbc);

        adaugaTranzactieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adaugaTranzactie();
            }
        });

        meniuPrincipalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deschideFramePrincipal();
            }
        });

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                salveazaIstoric();
            }
        });
    }

    /**
     * Deschide fereastra principală și închide fereastra curentă.
     */
    public void deschideFramePrincipal() {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
        this.dispose();
    }

    /**
     * Afișează istoricul tranzacțiilor în zona de text.
     */
    public void afiseazaIstoric() {
        istoricTextArea.setText("Istoric Tranzactii:\n\n");
        for (Istoric tranzactie : istoricTranzactii) {
            istoricTextArea.append(tranzactie.toString() + "\n\n");
        }
    }

    /**
     * Adaugă o nouă tranzacție în istoric.
     * Creează 2 obiecte, de tip Client și Autovehicul, în funcție de datele introduse de către utilizator
     * 
     */
    public void adaugaTranzactie() {
        Autovehicul autovehicul = new Autovehicul(
                autovehiculMarcaField.getText(),
                autovehiculModelField.getText(),
                Integer.parseInt(autovehiculAnFabricatieField.getText()),
                Double.parseDouble(autovehiculKmField.getText()),
                Double.parseDouble(autovehiculPretField.getText())
        );

        Client client = new Client(
                clientNumeField.getText(),
                clientPrenumeField.getText(),
                clientAdresaField.getText(),
                clientNumarTelefonField.getText()
        );

        String dataTranzactieText = dataTranzactieField.getText();
        boolean vanzare = vanzareCheckBox.isSelected();

        try {
            Date dataTranzactie = new SimpleDateFormat("yyyy-MM-dd").parse(dataTranzactieText);
            Istoric tranzactie = new Istoric(autovehicul, client, dataTranzactie, vanzare);
            istoricTranzactii.add(tranzactie);
            afiseazaIstoric();
            salveazaIstoric();
            // Se resetează
            autovehiculMarcaField.setText("");
            autovehiculModelField.setText("");
            autovehiculAnFabricatieField.setText("");
            autovehiculKmField.setText("");
            autovehiculPretField.setText("");
            clientNumeField.setText("");
            clientPrenumeField.setText("");
            clientAdresaField.setText("");
            clientNumarTelefonField.setText("");
            dataTranzactieField.setText("");
            vanzareCheckBox.setSelected(false);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Format de dată invalid! Folosiți yyyy-MM-dd.", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Încarcă istoricul tranzacțiilor din fișier la deschiderea frameului.
     */
    public void incarcaIstoric() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ISTORIC_FILE_PATH))) {
            istoricTranzactii = (List<Istoric>) ois.readObject();
            afiseazaIstoric();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Salvează istoricul tranzacțiilor în fișier la închiderea ferestrei.
     */
    public void salveazaIstoric() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ISTORIC_FILE_PATH))) {
            oos.writeObject(istoricTranzactii);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda principală care creează și afișează fereastra IstoricFrame.
     *
     * @param args Argumente de linie de comandă (nu sunt utilizate în acest caz).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            IstoricFrame istoricFrame = new IstoricFrame();
            istoricFrame.incarcaIstoric(); // Încarcă istoricul la pornire
            istoricFrame.setVisible(true);
        });
    }
}
