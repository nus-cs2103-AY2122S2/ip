package lucifer.exceptions;

/**
 * Class WrongFormatException which inherits from LuciferException.
 * @author Yu Meng
 */
public class WrongFormatException extends LuciferExceptions {
    /**
     * Constructor for Class WrongFormatException.
     */
    public WrongFormatException() {
        super("\uD83D\uDC7F\uD83D\uDD31 Oh Deary me! You have to tell me what you specifically desire."
                + "\n to check on available commands, type !help");
    }
}
