package AudiTicketBook;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Student extends User implements Savable<Student>, Runnable {
    private static List<Student> studentList;
    private final String name;
    private final List<Booking> bookings;

    public Student(String name, String password, String collegeID) {
        super(collegeID, password);
        this.name = name;
        this.bookings = new ArrayList<>();
    }

    public static void readFromMemory() throws InvalidFileException {
        try {
            studentList = new ArrayList<>();
            FileReader reader = new FileReader("data/StudentDetail.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Student s = getStudent(line);
                studentList.add(s);
            }
            reader.close();
        } catch (Exception e) {
            throw new InvalidFileException();
        }

    }

    private static Student getStudent(String line) {
        String[] arr = line.split("\\$");
        String name = arr[0];
        String userId = arr[1];
        String password = arr[2];
        List<Auditorium.Seat> seats = new ArrayList<>();
        Student s = new Student(name, password, userId);
        for (int i = 3; i < arr.length; i++) {
            int bookingid = Integer.parseInt(arr[i]);
            Booking b = Booking.getAllInstances().get(bookingid);
            s.bookings.add(b);
        }
        return s;
    }

    public static void writeToMemory() throws IOException {
        FileWriter writer = new FileWriter("data/StudentDetail.txt", false);
        for (Student s : studentList) {
            writer.write(s.encode());
        }
        writer.close();
    }

    public static Student newInstance(String n, String p, String id) {
        return new Student(n, p, id);
    }

    public static Student login(String u, String p) throws WrongPasswordException {
        for (Student s : studentList) {
            if (s.userid.equals(u) && s.password.equals(p)) {
                return s;
            }
        }
        throw new WrongPasswordException();
    }

    public static Student register(String name, String id, String pass) throws UserExistsException {
        Student s = new Student(name, pass, id);
        for (Student s1 : studentList) {
            if (s1.userid.equals(id)) {
                throw new UserExistsException();
            }
        }
        studentList.add(s);
        return s;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void book(Event E, List<Auditorium.Seat> seats) throws SeatAlreadyBookedException {
        bookings.add(Booking.book(E, seats));
    }

    public int getExpense() {
        int ans = 0;
        for (Booking b : bookings) {
            if (b.getStatus()) ans += b.getEvent().getPrice() * (b.getSeats().size());
        }
        return ans;
    }

    @Override
    public String encode() {
        String bookingList = "";
        for (Booking b : bookings) {
            bookingList += "$" + Booking.getAllInstances().indexOf(b);
        }
        return name + "$" + userid + "$" + password + bookingList + "\n";
    }

    @Override
    public void run() {
        UserScreen sc = new UserScreen(this);
        sc.setVisible(true);
    }
}
