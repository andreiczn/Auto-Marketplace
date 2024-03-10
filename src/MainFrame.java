import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Fereastra principală pentru gestionarea parcului auto.
 *
 * <p>Această fereastră servește ca meniu principal, oferind utilizatorului opțiuni precum manipularea stocului, căutarea de mașini sau înregistrarea achizițiilor/vânzărilor.</p>
 */
public class MainFrame extends JFrame {
    private static final String PAROLA_ADMIN = "12345678";

    /**
     * Constructorul clasei MainFrame.
     * Inițializează componentele ferestrei și adaugă ascultători pentru evenimentele de verificare a parolei și deschidere a altor ferestre corespunzătoare opțiunilor alese.
     */
    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Gestionare Parc Auto");
        setSize(600, 300);

        JLabel welcomeLabel = new JLabel("Bun venit! Alegeți o opțiune:");
        JButton manipulareButton = new JButton("Manipulare Stoc");
        JButton cautareButton = new JButton("Căutare Mașină");
        JButton istoricButton = new JButton("Înregistrare Achiziție/Vânzare");

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(Box.createVerticalStrut(50));
        add(welcomeLabel);
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(30));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(manipulareButton);
        buttonPanel.add(cautareButton);
        buttonPanel.add(istoricButton);
        add(buttonPanel);

        manipulareButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                verificaParolaAdmin();
            }
        });

        cautareButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deschideFrameCautareMasina();
            }
        });

        istoricButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                verificaParolaAdmin2();
            }
        });
    }

    /**
     * Verifică parola pentru manipularea stocului și deschide fereastra corespunzătoare dacă parola este corectă.
     */
    private void verificaParolaAdmin() {
        JPasswordField parolaField = new JPasswordField();
        Object[] ob = {parolaField};
        int result = JOptionPane.showConfirmDialog(null, ob, "Introdu parola:", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            char[] input = parolaField.getPassword();
            String parolaIntrodusa = new String(input);

            if (parolaIntrodusa.equals(PAROLA_ADMIN)) {
                deschideFrameManipulareStoc();
            } else {
                JOptionPane.showMessageDialog(this, "Parolă greșită!", "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Verifică parola pentru deschiderea istoricului și deschide fereastra corespunzătoare dacă parola este corectă.
     */
    private void verificaParolaAdmin2() {
        JPasswordField parolaField = new JPasswordField();
        Object[] ob = {parolaField};
        int result = JOptionPane.showConfirmDialog(null, ob, "Introdu parola:", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            char[] input = parolaField.getPassword();
            String parolaIntrodusa = new String(input);

            if (parolaIntrodusa.equals(PAROLA_ADMIN)) {
                deschideFrameIstoric();
            } else {
                JOptionPane.showMessageDialog(this, "Parolă greșită!", "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Deschide fereastra pentru manipularea stocului.
     */
    private void deschideFrameManipulareStoc() {
        AutoFrame autovehiculFrame = new AutoFrame();
        autovehiculFrame.setVisible(true);
        this.dispose();
    }

    /**
     * Deschide fereastra pentru căutarea mașinilor.
     */
    private void deschideFrameCautareMasina() {
        CautareFrame cautareFrame = new CautareFrame();
        cautareFrame.incarcaDateDinFisier();
        cautareFrame.setVisible(true);
        this.dispose();
    }

    /**
     * Deschide fereastra pentru vizualizarea istoricului tranzacțiilor.
     */
    private void deschideFrameIstoric() {
        IstoricFrame istoricFrame = new IstoricFrame();
        istoricFrame.incarcaIstoric();
        istoricFrame.setVisible(true);
        this.dispose();
    }

    /**
     * Metoda principală care creează și afișează fereastra MainFrame.
     *
     * @param args Argumente de linie de comandă (nu sunt utilizate în acest caz).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
