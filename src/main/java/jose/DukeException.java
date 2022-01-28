package jose;

/**
 * A basic exception class that allows for more personalised error handling and messaging.
 */
public class DukeException extends Exception{
    /**
     * Class constructor.
     *
     * @param msg The desired message to be shown to the user.
     */
    public DukeException(String msg) {
        super(msg);
    }
}
