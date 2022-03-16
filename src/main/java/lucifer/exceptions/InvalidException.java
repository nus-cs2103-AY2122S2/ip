package lucifer.exceptions;

/**
 * Class InvalidException which inherits from LuciferException.
 * @author Yu Meng
 */
public class InvalidException extends LuciferExceptions {
    /**
     * Constructor for Class InvalidException.
     */
    public InvalidException() {
        super("\uD83D\uDC7F\uD83D\uDD31 My my, you've got to specify your desire love\nLet me help you with an example:\n"
            + "deadline return book /by 12-12-2022 1800\nPlease try again my dear!\n"
                + "To check on available commands, type:\n!help");
    }
}
