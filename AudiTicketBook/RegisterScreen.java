package AudiTicketBook;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegisterScreen extends JFrame {

    private final JPanel contentPane;
    private final JTextField textField;
    private final JPasswordField passwordField;
    private final JLabel Error = new JLabel("");
    private final JTextField Name_Student;

    /**
     * Create the frame.
     */
    public RegisterScreen() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 540);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(158, 194, 185));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Register");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 44));
        lblNewLabel.setBounds(308, 82, 415, 55);
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

        JButton btnNewButton = new JButton("Back");
        btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Landing();
            }
        });
        btnNewButton.setBounds(10, 467, 107, 23);
        contentPane.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Register");
        btnNewButton_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Registering();
            }
        });
        btnNewButton_1.setBounds(354, 418, 107, 23);
        contentPane.add(btnNewButton_1);


        Error.setFont(new Font("Tahoma", Font.BOLD, 19));
        Error.setForeground(new Color(255, 28, 28));
        Error.setBounds(293, 185, 269, 45);
        contentPane.add(Error);

        Name_Student = new JTextField();
        Name_Student.setBounds(304, 254, 227, 20);
        contentPane.add(Name_Student);
        Name_Student.setColumns(10);

        JLabel Name = new JLabel("Name");
        Name.setFont(new Font("Tahoma", Font.PLAIN, 19));
        Name.setBounds(207, 243, 124, 32);
        contentPane.add(Name);
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RegisterScreen frame = new RegisterScreen();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void Registering() {
        String user = textField.getText();
        String pwd = passwordField.getText();
        String name = Name_Student.getText();
        if (user.equals("") || pwd.equals("") || name.equals("")) {
            Error.setText("Field Cannot be empty");
            return;
        }
        Student S = new Student("", "", "");
        try {
            S = Student.register(name, user, pwd);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
            Error.setText(e.getMessage());
            return;

        }
        Thread t = new Thread(S);
        t.start();
        Thread.currentThread().interrupt();

        this.dispose();

    }

    private void Landing() {
        Main sc = new Main();
        sc.setVisible(true);
        this.dispose();
    }

}
