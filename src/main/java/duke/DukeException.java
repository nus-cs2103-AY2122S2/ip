package duke;

class DukeException extends Exception {
    DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "DukeException: " + this.getMessage();
    }
}
