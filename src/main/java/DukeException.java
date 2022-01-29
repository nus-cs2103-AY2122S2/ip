public class DukeException extends Exception{
    private String errorMessage;
    public DukeException (String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * @override
     * @return String errorMessage
     */
    public String getMessage() {return this.errorMessage;}
}
