public class DukeException extends Exception{
    private String errorMessage;
    public DukeException (String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * @override Returns customised error message for DukeException when input is incorrect
     * @return String errorMessage
     */
    public String getMessage() {return this.errorMessage;}
}
