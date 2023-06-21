package AudiTicketBook;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Booking implements Savable<Booking> {
    private static List<Booking> bookingList;
    private final Event e;
    private final List<Auditorium.Seat> seats;

    public Booking(Event e, List<Auditorium.Seat> seats) {
        this.e = e;
        this.seats = seats;
    }

    public static synchronized void readFromMemory() throws InvalidFileException {
        try {
            bookingList = new ArrayList<>();
            FileReader reader = new FileReader("data/BookingDetail.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Booking b = getBooking(line);
                bookingList.add(b);
            }
            reader.close();
        } catch (Exception e) {
            throw new InvalidFileException();
        }
    }

    private static Booking getBooking(String line) throws SeatAlreadyBookedException {
        String[] arr = line.split("\\$");
        int event_id = Integer.parseInt(arr[0]);
        Event e = Event.getAllInstances().get(event_id);
        List<Auditorium.Seat> seats = new ArrayList<>();
        for (int i = 1; i < arr.length; i++) {
            int seatid = Integer.parseInt(arr[i]);
            Auditorium.Seat s = Auditorium.getMatrix()[seatid];
            s.book(e);
            seats.add(s);
        }
        Booking b = new Booking(e, seats);
        return b;
    }

    public static synchronized void writeToMemory() throws IOException {
        FileWriter writer = new FileWriter("data/BookingDetail.txt", false);
        for (Booking b : bookingList) {
            writer.write(b.encode());
        }
        writer.close();
    }

    public static List<Booking> getAllInstances() {
        return bookingList;
    }

    public static synchronized Booking book(Event e, List<Auditorium.Seat> seats) throws SeatAlreadyBookedException {
        for (Auditorium.Seat s : seats) {
            if (s.getIsBooked(e)) throw new SeatAlreadyBookedException();
        }
        Booking b = new Booking(e, seats);
        bookingList.add(b);
        for (Auditorium.Seat s : seats) {
            s.book(e);
        }
        return b;
    }

    public boolean getStatus() {
        return e.getStatus();
    }


    public Event getEvent() {
        return this.e;
    }

    public List<Auditorium.Seat> getSeats() {
        return seats;
    }

    @Override
    public String encode() {
        String seatList = "";
        for (Auditorium.Seat s : seats) {
            seatList += "$" + s.getId();
        }
        return Event.getAllInstances().indexOf(e) + seatList + "\n";

    }
}
