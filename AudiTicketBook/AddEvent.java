package AudiTicketBook;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddEvent extends JFrame {

    private final JPanel contentPane;
    private final JTextField NewName;
    private final JTextField NewDate;
    private final JTextField NewTime;
    private final JTextField NewPrice;
    private final JLabel Eventadd;
    private final JLabel Error;

    public AddEvent() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 540);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(158, 194, 185));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnNewButton = new JButton("Back");
        btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Admin();
            }
        });
        NewName = new JTextField();
        NewName.setBounds(323, 69, 260, 20);
        contentPane.add(NewName);
        NewName.setColumns(10);

        NewDate = new JTextField();
        NewDate.setColumns(10);
        NewDate.setBounds(323, 158, 260, 20);
        contentPane.add(NewDate);

        NewTime = new JTextField();
        NewTime.setColumns(10);
        NewTime.setBounds(323, 245, 260, 20);
        contentPane.add(NewTime);

        NewPrice = new JTextField();
        NewPrice.setColumns(10);
        NewPrice.setBounds(323, 322, 260, 20);
        contentPane.add(NewPrice);
        btnNewButton.setBounds(10, 456, 107, 23);
        contentPane.add(btnNewButton);
        JLabel lblEnterNewName = new JLabel("Enter Name");
        lblEnterNewName.setVerticalAlignment(SwingConstants.TOP);
        lblEnterNewName.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblEnterNewName.setBounds(205, 69, 426, 23);
        contentPane.add(lblEnterNewName);

        JLabel lblEnterNewDate = new JLabel("Enter Date");
        lblEnterNewDate.setVerticalAlignment(SwingConstants.TOP);
        lblEnterNewDate.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblEnterNewDate.setBounds(205, 158, 426, 23);
        contentPane.add(lblEnterNewDate);

        JLabel lblEnterNewTime = new JLabel("Enter Time");
        lblEnterNewTime.setVerticalAlignment(SwingConstants.TOP);
        lblEnterNewTime.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblEnterNewTime.setBounds(205, 245, 426, 23);
        contentPane.add(lblEnterNewTime);

        JLabel lblEnterNewPrice = new JLabel("Enter Price");
        lblEnterNewPrice.setVerticalAlignment(SwingConstants.TOP);
        lblEnterNewPrice.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblEnterNewPrice.setBounds(205, 322, 426, 23);
        contentPane.add(lblEnterNewPrice);

        JButton btnNewButton_1 = new JButton("Add");
        btnNewButton_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Add_Details();
            }
        });
        btnNewButton_1.setBounds(382, 378, 107, 23);
        contentPane.add(btnNewButton_1);

        Eventadd = new JLabel("");
        Eventadd.setFont(new Font("Tahoma", Font.BOLD, 24));
        Eventadd.setBounds(365, 441, 328, 40);
        contentPane.add(Eventadd);

        Error = new JLabel("");
        Error.setFont(new Font("Tahoma", Font.PLAIN, 18));
        Error.setForeground(new Color(255, 28, 28));
        Error.setHorizontalAlignment(SwingConstants.CENTER);
        Error.setBounds(251, 412, 358, 67);
        contentPane.add(Error);
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AddEvent frame = new AddEvent();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void Add_Details() {
        String name, date, time, price;

        name = NewName.getText();
        date = NewDate.getText();
        time = NewTime.getText();
        price = NewPrice.getText();

        if (name.equals("") || date.equals("") || time.equals("") || price.equals("")) {
            Error.setText("Fields cannot be empty");
            return;
        }
        Admin.addEvent(name, date, time, Integer.parseInt(price));

        Eventadd.setText("Event Added");
    }

    private void Admin() {
        AdminScreen sc = new AdminScreen();
        sc.setVisible(true);
        this.dispose();
    }

}
