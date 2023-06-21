package AudiTicketBook;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class Main extends JFrame {

    private final JPanel contentPane;

    /**
     * Create the frame.
     */
    public Main() {
        setResizable(false);
        setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 540);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(158, 194, 185));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnNewButton = new JButton("Log In");
        btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Log_Screen(e);
            }
        });
        btnNewButton.setBounds(173, 350, 107, 23);
        contentPane.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Admin");
        btnNewButton_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Admin_Screen(e);
            }
        });
        btnNewButton_1.setBounds(383, 428, 107, 23);
        contentPane.add(btnNewButton_1);

        JLabel lblNewLabel = new JLabel("AUDI BOOKING SYSTEM");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
        lblNewLabel.setBounds(294, 69, 484, 151);
        contentPane.add(lblNewLabel);

        JButton btnNewButton_2 = new JButton("Register");
        btnNewButton_2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Register();
            }
        });
        btnNewButton_2.setBounds(584, 350, 107, 23);
        contentPane.add(btnNewButton_2);
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) throws IOException, InvalidFileException {

        Auditorium obj = new Auditorium();
        synchronized (obj) {
            Event.readFromMemory();
            Booking.readFromMemory();
            Student.readFromMemory();
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main frame = new Main();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                try {
                    synchronized (obj) {
                        Student.writeToMemory();
                        Booking.writeToMemory();
                        Event.writeToMemory();
                    }
                    System.out.println("Data Saved Successfully");
                } catch (Exception e) {
                    System.out.println("Could not save data");
                }

            }
        }, "Shutdown-thread"));
    }

    private void Log_Screen(MouseEvent E) {
        LogIn screen2 = new LogIn();
        screen2.setVisible(true);
        this.dispose();
    }

    private void Register() {
        RegisterScreen sc = new RegisterScreen();
        sc.setVisible(true);
        this.dispose();
    }

    private void Admin_Screen(MouseEvent E) {
        AdminLogIn screen2 = new AdminLogIn();
        screen2.setVisible(true);
        this.dispose();
    }
}
