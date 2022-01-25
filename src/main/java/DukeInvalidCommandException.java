public class DukeInvalidCommandException extends Exception{
    private static final String ERROR_INVALID_COMMAND = "OOPS!!! You have entered an invalid command :(";

    public DukeInvalidCommandException(){
        super(ERROR_INVALID_COMMAND);
    }
}