public class DukeInvalidCommandException extends DukeException {
    @Override
    public String toString() {
        return "The command you've typed in is not found. Did you spell it correctly?";
    }
}
