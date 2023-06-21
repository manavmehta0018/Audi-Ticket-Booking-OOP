package AudiTicketBook;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class AdminScreen extends JFrame {

    private final JPanel contentPane;
    private final JTable table;
    private final List<Event> ev;
    private final JLabel Error;

    /**
     * Create the frame.
     */
    public AdminScreen() {
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
                Landing_Screen();
            }
        });
        String[] Columns = {"Name", "Date", "Timing", "Price", "Revenue"};

        List<Event> evs = Event.getAllInstances();
        ev = new ArrayList<>();
        for (Event e : evs) if (e.getStatus()) ev.add(e);
        int n = ev.size();
        String[][] Rows = new String[n][5];
        for (int i = 0; i < ev.size(); i++) {
            if (!ev.get(i).getStatus()) continue;
            Rows[i][0] = ev.get(i).getName();

            Rows[i][1] = ev.get(i).getDate();
            Rows[i][2] = ev.get(i).getTime();
            Rows[i][3] = ev.get(i).getPrice() + "";
            Rows[i][4] = Auditorium.getRevenue(ev.get(i)) + "";
        }
        contentPane.setLayout(null);

        table = new JTable(Rows, Columns);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(21, 11, 853, 397);
        contentPane.add(scrollPane);
        btnNewButton.setBounds(10, 455, 107, 23);
        contentPane.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Add Events");
        btnNewButton_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                add();
            }
        });
        btnNewButton_1.setBounds(469, 455, 107, 23);
        contentPane.add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("Change Details");
        btnNewButton_2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                changeDetails();
            }
        });
        btnNewButton_2.setBounds(752, 455, 107, 23);
        contentPane.add(btnNewButton_2);

        JButton btnNewButton_3 = new JButton("Remove Event");
        btnNewButton_3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                remove();
            }
        });
        btnNewButton_3.setBounds(265, 455, 107, 23);
        contentPane.add(btnNewButton_3);

        Error = new JLabel("");
        Error.setFont(new Font("Tahoma", Font.PLAIN, 18));
        Error.setForeground(new Color(255, 28, 28));
        Error.setHorizontalAlignment(SwingConstants.CENTER);
        Error.setBounds(304, 408, 289, 47);
        contentPane.add(Error);
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AdminScreen frame = new AdminScreen();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void add() {
        AddEvent sc = new AddEvent();
        sc.setVisible(true);
        this.dispose();

    }

    private void remove() {
        try {
            int row = table.getSelectedRow();

            if (row == -1) throw new NoEventSelectedException();
            Admin.removeEvent(ev.get(row));

            AdminScreen sc = new AdminScreen();
            sc.setVisible(true);
            this.dispose();
        } catch (Exception e) {
            Error.setText(e.getMessage());
        }
    }

    private void changeDetails() {
        try {
            int row = table.getSelectedRow();

            if (row == -1) throw new NoEventSelectedException();

            ChangeDetails Sc = new ChangeDetails(ev.get(row));
            Sc.setVisible(true);
            this.dispose();
        } catch (Exception e) {
            Error.setText(e.getMessage());
        }
    }

    private void Landing_Screen() {
        Main Screen2 = new Main();
        Screen2.setVisible(true);
        this.dispose();
    }
}
