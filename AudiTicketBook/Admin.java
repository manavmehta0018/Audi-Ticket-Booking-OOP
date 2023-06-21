package AudiTicketBook;

public class Admin extends User implements Runnable {

    public Admin() {
        super("admin", "password");
    }

    public static void addEvent(String name, String date, String time, int price) {
        Event.newInstance(name, time, date, price);
    }

    public static void removeEvent(Event e) {
        e.cancel();
    }

    public static void changeDetails(Event ev, String name, String date, String time, int price) {
        ev.setName(name);
        ev.setDate(date);
        ev.setSt(time);
        ev.setPrice(price);
    }

    public void run() {
        AdminScreen sc = new AdminScreen();
        sc.setVisible(true);
    }

}
