public class DukeInvalidArgumentException extends DukeException {
    public DukeInvalidArgumentException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        String result = Printer.divider + "\n";
        result += "    Invalid argument(s): " + this.getMessage() + "\n";
        result += Printer.divider;
        return result;
    }
}

