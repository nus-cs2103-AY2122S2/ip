package lucifer.exceptions;

/**
 * Class OutOfBoundsException which inherits from LuciferException.
 * @author Yu Meng
 */
public class OutOfBoundsException extends LuciferExceptions {
    /**
     * Constructor for Class OutOfBoundsException.
     */
    public OutOfBoundsException() {
        super("\uD83D\uDC7F\uD83D\uDD31 My Dear, you must be kidding me!\n"
                + "Please give me a valid number so I can grant your desires.\n"
                + "To check your current total tasks,type: list");
    }
}