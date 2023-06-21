package AudiTicketBook;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Event implements Savable<Event> {
    private static List<Event> eventList;
    private String name;
    private String time;
    private String date;
    private int price;
    private boolean is_cancelled;

    public Event(String name, String time, String date, int price) {
        this.name = name;
        this.time = time;
        this.date = date;
        this.price = price;
        this.is_cancelled = false;
    }

    public static List<Event> getAllInstances() {
        return eventList;
    }

    public static synchronized void readFromMemory() throws IOException, InvalidFileException {
        try {
            eventList = new ArrayList<>();
            FileReader reader = new FileReader("data/EventDetail.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Event e = getEvent(line);
                eventList.add(e);
            }
            reader.close();
        } catch (Exception e) {
            throw new InvalidFileException();
        }
    }

    private static Event getEvent(String line) {
        String[] arr = line.split("\\$");
        String name = arr[0];
        String time = arr[1];
        String date = arr[2];
        int price = Integer.parseInt(arr[3]);
        boolean is_cancelled = arr[4].equals("1");
        Event e = new Event(name, time, date, price);
        if (is_cancelled) e.cancel();
        return e;
    }

    public static synchronized void writeToMemory() throws IOException {
        FileWriter writer = new FileWriter("data/EventDetail.txt", false);
        for (Event e : eventList)
            writer.write(e.encode());
        writer.close();
    }

    public static synchronized Event newInstance(String name, String time, String date, int price) {
        Event e = new Event(name, time, date, price);
        eventList.add(e);
        return e;
    }

    public boolean getStatus() {
        return !is_cancelled;
    }

    public void cancel() {
        synchronized (this) {
            this.is_cancelled = true;
        }
    }

    public String encode() {
        String tmp;
        if (is_cancelled) tmp = "1";
        else tmp = "0";
        return this.name + "$" + this.time + "$" + this.date + "$" + this.price + "$" + tmp + "\n";
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        synchronized (this) {
            this.price = price;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        synchronized (this) {
            this.name = name;
        }
    }

    public String getTime() {
        synchronized (this) {
            return time;
        }
    }

    public String getDate() {
        synchronized (this) {
            return date;
        }
    }

    public void setDate(String Et) {
        synchronized (this) {
            this.date = Et;
        }
    }

    public void setSt(String St) {
        synchronized (this) {
            this.time = St;
        }
    }

}
