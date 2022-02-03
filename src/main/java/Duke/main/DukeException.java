package Duke.main;

public class DukeException extends Exception {
    private String job;

    DukeException(String message) {
        super(message);
    }

    DukeException(String job, String message) {
        super(message);
        this.job = job;
    }

    @Override
    public String toString() {
        return "☹ OOPS!!! The description of a " + this.job + " cannot be empty.";
    }

}
