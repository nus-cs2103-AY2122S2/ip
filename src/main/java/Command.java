public class Command {
    public static final String COMMAND_WORD = "command";

    public Command() { }

    public String execute(ListTask tasks) {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    }

    public String toString() {
        return COMMAND_WORD;
    }


}