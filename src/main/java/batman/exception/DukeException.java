package batman.exception;

public class DukeException extends Exception {

    protected Error err;

    public DukeException(Error err) {
        this.err = err;
    }

    /**
     * Returns a string if an invalid input is encountered.
     *
     * @return String object of an invalid input.
     */
    public String invalidInput() {
        if (this.err == Error.INVALID) {
            return "I'm sorry, but I don't know what that means.\n";
        }
        return "";
    }

    /**
     * Returns a string if an empty description is encountered.
     *
     * @return String object of an empty description.
     */
    public String emptyDesc() {
        if (this.err == Error.EMPTY_DESC) {
            return "The description of a task cannot be empty.\n";
        }
        return "";
    }

    /**
     * Returns a string if index in list does not exist.
     *
     * @return String object of a non-existing index in list.
     */
    public String listError() {
        if (this.err == Error.LIST_ERROR) {
            return "Index does not exists in array.\n";
        }
        return "";
    }

}
