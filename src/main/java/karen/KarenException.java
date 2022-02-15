package karen;

import karen.command.InvalidMessage;

/**
 * To rewrap runtime exception thrown while running Karen with
 * custom messages for user.
 */
public class KarenException extends Exception {
    private InvalidMessage invalidMessage;

    /**
     * Constructor for throwing a KarenException, used for backwards compatibility
     * and for custom String.format(...) formatting
     *
     * @param msg Error message
     */
    public KarenException(String msg) {
        super(msg);
    }

    /**
     * Constructor for KarenException for throwing a new KarenException
     *
     * @param invalidMsg Input InvalidMessage types
     */
    public KarenException(InvalidMessage invalidMsg) {
        super(invalidMsg.toString());
        invalidMessage = invalidMsg;
    }

    /**
     * Returns invalidMessage Enum type
     *
     * @return InvalidMessage Enum
     */
    public InvalidMessage getInvalidEnum() {
        return invalidMessage;
    }

}
