package bob.exception;
/**
 * {@inheritDoc}
 */
public class EventException extends BobException {

    /**
     * Constructor for an Event exception.
     */
    public EventException() {
        super("Adding an event requires [event name] + [/at] + \n"
                + "\t[date](yyyy-MM-dd) + [T] + " + "[start](HH:mm) + [-] + [end](HH:mm)! (ノಠ益ಠ)ノ彡┻━┻\n"
                + "\t[e.g. \"event seminar for people who dk how to add events /at 2022-02-22T02:22-22:22\"]");
    }
}
