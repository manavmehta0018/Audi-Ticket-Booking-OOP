package AudiTicketBook;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LogIn extends JFrame {

    private final JPanel contentPane;
    private final JTextField textField;
    private final JPasswordField passwordField;
    private final JLabel Error = new JLabel("");

    /**
     * Create the frame.
     */
    public LogIn() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 540);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(158, 194, 185));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Log In");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 44));
        lblNewLabel.setBounds(338, 82, 415, 55);
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setBounds(304, 301, 227, 20);
        contentPane.add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("BITS ID");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
        lblNewLabel_1.setBounds(207, 292, 124, 32);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("Password");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
        lblNewLabel_1_1.setBounds(207, 350, 124, 32);
        contentPane.add(lblNewLabel_1_1);

        passwordField = new JPasswordField();
        passwordField.setBounds(304, 360, 227, 20);
        contentPane.add(passwordField);

        JButton btnNewButton = new JButton("Log In");
        btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                log();
            }
        });
        btnNewButton.setBounds(360, 431, 107, 23);
        contentPane.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Back");
        btnNewButton_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Landing();
            }
        });
        btnNewButton_1.setBounds(10, 467, 107, 23);
        contentPane.add(btnNewButton_1);


        Error.setFont(new Font("Tahoma", Font.BOLD, 19));
        Error.setForeground(new Color(255, 28, 28));
        Error.setBounds(277, 179, 298, 46);
        contentPane.add(Error);
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LogIn frame = new LogIn();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void log() {
        String name = textField.getText();
        String password = passwordField.getText();
        try {
            if (name.equals("") || password.equals("")) throw new EmptyFieldException();
            Student s;
            s = Student.login(name, password);

            Thread t = new Thread(s);
            t.start();
            Thread.currentThread().interrupt();
            this.dispose();
        } catch (Exception e) {
            Error.setText(e.getMessage());
        }

    }

    private void Landing() {
        Main sc = new Main();
        sc.setVisible(true);
        this.dispose();
    }
}
