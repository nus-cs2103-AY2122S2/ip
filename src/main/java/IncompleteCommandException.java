public class IncompleteCommandException extends DazzException {
    public IncompleteCommandException(String command) {
        super("\t\u2639 OOPS!!! The description of \"" + command + "\" cannot be empty.");
    }
}
