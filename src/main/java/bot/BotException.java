package bot;

public class BotException extends Exception {
    private static final String prefix = "☹ OOPS!!! ";

    public BotException(String description) {
        super(BotException.prefix + description);
    }
}
