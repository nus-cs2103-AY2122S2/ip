/**
 * An abstract clas that represents the exceptions related to Duke.
 */
abstract class DukeException extends Exception {
    private String message;

    protected DukeException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
