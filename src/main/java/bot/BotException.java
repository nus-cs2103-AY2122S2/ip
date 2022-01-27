package bot;

public class BotException extends Exception {
    private static final String PREFIX = "☹ OOPS!!! ";

    public BotException(String description) {
        super(BotException.PREFIX + description);
    }
}
