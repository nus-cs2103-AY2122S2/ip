public class IncompleteInputException extends DukeException{
    public IncompleteInputException(String input) {
        super("     ☹ " + input + " is an incomplete command! Please enter another command.");
    }
}
