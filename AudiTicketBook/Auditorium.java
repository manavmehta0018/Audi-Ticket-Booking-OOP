package AudiTicketBook;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Auditorium {
    private static final int N = 100;
    private static Seat[] matrix;

    public Auditorium() {
        matrix = new Seat[N];

        for (int i = 0; i < N; i++) {
            matrix[i] = new Seat(i);
        }

    }

    public static Seat[] getMatrix() {
        return matrix;
    }

    public static int getRevenue(Event e) {
        int ans = 0;
        for (int i = 0; i < N; i++) {
            if (matrix[i].getIsBooked(e)) {
                ans += e.getPrice();
            }
        }
        return ans;
    }

    public static List<Seat> getAvailableSeats(Event e) {
        List<Seat> ans = new ArrayList<>();
        for (Seat s : matrix) {
            if (!s.getIsBooked(e)) ans.add(s);
        }
        return ans;
    }

    public class Seat {
        private final int id;
        Set<Event> is_booked;

        public Seat(int i) {
            id = i;
            is_booked = new HashSet<>();
        }

        public boolean getIsBooked(Event e) {
            return is_booked.contains(e);
        }

        public int getId() {
            return id;
        }

        public void book(Event e) throws SeatAlreadyBookedException {
            if (getIsBooked(e)) throw new SeatAlreadyBookedException();
            synchronized (this) {
                is_booked.add(e);
            }
        }
    }


}
