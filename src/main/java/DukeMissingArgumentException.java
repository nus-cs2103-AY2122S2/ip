public class DukeMissingArgumentException extends DukeException {
    public DukeMissingArgumentException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        String result = Printer.divider + "\n";
        result += "    We are missing the following argument: " + this.getMessage() + "\n";
        result += Printer.divider;
        return result;
    }
}
