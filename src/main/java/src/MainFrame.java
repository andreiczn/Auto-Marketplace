import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private static final String PAROLA_ADMIN = "12345678"; 
    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Gestionare Parc Auto");
        setSize(600, 300);

        JLabel welcomeLabel = new JLabel("Bun venit! Alegeți o opțiune:");
        JButton manipulareButton = new JButton("Manipulare Stoc");
        JButton cautareButton = new JButton("Căutare Mașină");

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(Box.createVerticalStrut(50));
        add(welcomeLabel);
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(30));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(manipulareButton);
        buttonPanel.add(cautareButton);
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
    }

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

    private void deschideFrameManipulareStoc() {
        AutoFrame autovehiculFrame = new AutoFrame();
        autovehiculFrame.setVisible(true);
        this.dispose();
    }

    private void deschideFrameCautareMasina() {
        CautareFrame cautareFrame = new CautareFrame();
        cautareFrame.setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
