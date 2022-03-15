package duke.exceptions;

public class WrongDateFormatException extends DukeException {
    private String type;

    public WrongDateFormatException(String type) {
        this.type = type;
    }

    /**
     * returns error message from exception
     * @return String containing details of error
     */
    @Override
    public String getMessage() {
        return "OOPS!!! " + type + " is in the wrong format please follow dd/MM/YYYY HHmm";
    }
}
