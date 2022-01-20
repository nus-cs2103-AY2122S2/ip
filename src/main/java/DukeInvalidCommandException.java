public class DukeInvalidCommandException extends DukeException {
    @Override
    public String toString() {
        return "your command entered does not belong to any command specified";
    }
}
