class EmptyArgumentException extends DukeException {
    public EmptyArgumentException(String message) {
        super("☹ OOPS!!! The description of a " + message + " cannot be empty.");

    }
}