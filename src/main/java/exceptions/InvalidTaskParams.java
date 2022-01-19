package exceptions;

public class InvalidTaskParams extends BaseException {
    /**
     * Constructor for InvalidTaskParams.
     */
    public InvalidTaskParams() {
        super();
    }

    /**
     * Constructor for InvalidTaskParams.
     *
     * @param errMsg error message.
     */
    public InvalidTaskParams(String errMsg) {
        super(errMsg);
    }
}
