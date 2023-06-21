package AudiTicketBook;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChangeDetails extends JFrame {

    private final JPanel contentPane;
    private final JTextField NewName;
    private final JTextField NewDate;
    private final JTextField NewTime;
    private final JTextField NewPrice;
    private final Event ev;
    private final JLabel Error;

    /**
     * Create the frame.
     */
    public ChangeDetails(Event e) {
        ev = e;
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
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnNewButton.setBounds(27, 455, 107, 23);

        JLabel lblNewLabel = new JLabel("Movie Name:".concat(ev.getName()));
        lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel.setBounds(174, 75, 426, 106);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_2 = new JLabel("Time:".concat(ev.getTime()));
        lblNewLabel_2.setVerticalAlignment(SwingConstants.TOP);
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel_2.setBounds(174, 247, 426, 106);
        contentPane.add(lblNewLabel_2);

        JLabel lblNewLabel_1 = new JLabel("Date:".concat(ev.getDate()));
        lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel_1.setBounds(174, 160, 426, 106);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("Price:".concat(ev.getPrice() + ""));
        lblNewLabel_1_1.setVerticalAlignment(SwingConstants.TOP);
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel_1_1.setBounds(174, 324, 426, 106);
        contentPane.add(lblNewLabel_1_1);
        contentPane.add(btnNewButton);

        NewName = new JTextField(ev.getName());
        NewName.setBounds(488, 79, 260, 20);
        contentPane.add(NewName);
        NewName.setColumns(10);

        NewDate = new JTextField(ev.getDate());
        NewDate.setColumns(10);
        NewDate.setBounds(488, 164, 260, 20);
        contentPane.add(NewDate);

        NewTime = new JTextField(ev.getTime());
        NewTime.setColumns(10);
        NewTime.setBounds(488, 251, 260, 20);
        contentPane.add(NewTime);

        NewPrice = new JTextField(ev.getPrice() + "");
        NewPrice.setColumns(10);
        NewPrice.setBounds(488, 328, 260, 20);
        contentPane.add(NewPrice);

        JLabel lblEnterNewName = new JLabel("Enter New Name");
        lblEnterNewName.setVerticalAlignment(SwingConstants.TOP);
        lblEnterNewName.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblEnterNewName.setBounds(488, 52, 426, 23);
        contentPane.add(lblEnterNewName);

        JLabel lblEnterNewDate = new JLabel("Enter New Date");
        lblEnterNewDate.setVerticalAlignment(SwingConstants.TOP);
        lblEnterNewDate.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblEnterNewDate.setBounds(488, 141, 426, 23);
        contentPane.add(lblEnterNewDate);

        JLabel lblEnterNewTime = new JLabel("Enter New Time");
        lblEnterNewTime.setVerticalAlignment(SwingConstants.TOP);
        lblEnterNewTime.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblEnterNewTime.setBounds(488, 228, 426, 23);
        contentPane.add(lblEnterNewTime);

        JLabel lblEnterNewPrice = new JLabel("Enter New Price");
        lblEnterNewPrice.setVerticalAlignment(SwingConstants.TOP);
        lblEnterNewPrice.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblEnterNewPrice.setBounds(488, 305, 426, 23);
        contentPane.add(lblEnterNewPrice);

        JButton btnNewButton_1 = new JButton("Update");
        btnNewButton_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Update_Details();
            }
        });
        btnNewButton_1.setBounds(559, 391, 107, 23);
        contentPane.add(btnNewButton_1);

        Error = new JLabel("");
        Error.setFont(new Font("Tahoma", Font.PLAIN, 25));
        Error.setForeground(new Color(255, 28, 28));
        Error.setHorizontalAlignment(SwingConstants.CENTER);
        Error.setBounds(488, 425, 276, 53);
        contentPane.add(Error);

    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        Event e = new Event("", "", "", 0);
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ChangeDetails frame = new ChangeDetails(e);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void Update_Details() {
        try {
            String name, date, time, price;
            int pr = 0;
            name = NewName.getText();
            date = NewDate.getText();
            time = NewTime.getText();
            price = NewPrice.getText();
            if (name.equals("") && date.equals("") && time.equals("") && price.equals(""))
                throw new EmptyFieldException();
            if (name.equals(""))
                name = ev.getName();
            if (date.equals(""))
                date = ev.getDate();
            if (time.equals(""))
                time = ev.getTime();
            if (price.equals("")) {
                pr = ev.getPrice();
            } else
                pr = Integer.parseInt(price);

            Admin.changeDetails(ev, name, date, time, pr);

            ChangeDetails obj = new ChangeDetails(ev);
            obj.setVisible(true);
            this.dispose();
        } catch (Exception e) {
            Error.setText(e.getMessage());
        }

    }

    private void Admin() {
        AdminScreen sc = new AdminScreen();
        sc.setVisible(true);
        this.dispose();
    }
}
