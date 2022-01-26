public class InvalidCommand extends Command {
    public static final String DEFAULT_MESSAGE = "I don't understand anything - I want to speak with your manager";
    protected String message;

    public InvalidCommand(String message) {
        this.message = message;
    }

    public InvalidCommand() {
        this.message = DEFAULT_MESSAGE;
    }

    @Override
    public void execute(Ui ui, Storage storage) throws KarenException {
        throw new KarenException(this.message);
    }
}
