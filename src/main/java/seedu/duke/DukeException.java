package seedu.duke;

public class DukeException extends Exception {
    
    String message;

    DukeException() {
        this.message = "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

    DukeException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }

}
