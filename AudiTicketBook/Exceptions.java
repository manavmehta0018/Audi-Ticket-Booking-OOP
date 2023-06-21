package AudiTicketBook;

class WrongPasswordException extends Exception {
    public WrongPasswordException() {
        super("Wrong Username/Password.");
    }
}

class UserExistsException extends Exception {
    public UserExistsException() {
        super("User already exists.");
    }
}

class SeatAlreadyBookedException extends Exception {
    public SeatAlreadyBookedException() {
        super("Seat is already Booked.");
    }
}

class InvalidFileException extends Exception {
    public InvalidFileException() {
        super("File not formatted properly.");
    }
}

class EmptyFieldException extends Exception {
    public EmptyFieldException() {
        super("Field cannot be empty");
    }
}

class NoEventSelectedException extends Exception {
    public NoEventSelectedException() {
        super("No event selected");
    }
}
