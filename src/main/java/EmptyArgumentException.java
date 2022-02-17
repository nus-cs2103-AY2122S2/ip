
class EmptyArgumentException extends DukeException {
    /**
     *
     * @param message
     */
    public EmptyArgumentException(String message) {
        super("☹ OOPS!!! The description of a " + message + " cannot be empty.");

    }
}