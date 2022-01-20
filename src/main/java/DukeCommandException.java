public class DukeCommandException extends DukeException {
    public DukeCommandException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        if (getMessage().isEmpty()) {
            return Printer.divider + "\n" + "    Please specify a command! \n" + Printer.divider;
        }
        String result = Printer.divider + "\n";
        result += "    Unknown command: " + this.getMessage() + "\n";
        result += Printer.divider;
        return result;
    }
}
