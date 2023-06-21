package AudiTicketBook;

import java.io.IOException;
import java.util.List;

public interface Savable<T>{
    static void readFromMemory() throws IOException, InvalidFileException {

    }

    static void writeToMemory() throws IOException {

    }

    static List<Object> getAllInstances() {
        return null;
    }

    String encode();
}
