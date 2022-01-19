public class IncompleteCommandException extends DazzException {
    public IncompleteCommandException(String command) {
        super("\tOOPS!!! The description of \"" + command + "\" cannot be empty.");
    }
}
