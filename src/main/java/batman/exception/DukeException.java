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
        if (this.err == Error.INVALID_COMMAND) {
            return "I'm sorry, but I don't know what that means.\n";
        }
        return "";
    }

    /**
     * Returns a string if an invalid argument is encountered.
     *
     * @return String object of an empty description.
     */
    public String invalidArg() {
        if (this.err == Error.INVALID_ARG) {
            return "The argument provided for the task is invalid.\n";
        }
        return "";
    }

    /**
     * Returns a string if an invalid index is encountered.
     *
     * @return resulting String object of invalid index.
     */
    public String invalidIndex() {
        if (this.err == Error.INVALID_DIGIT) {
            return "Please provide a number.\n";
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

    /**
     * Returns a string if event task is formatted wrongly.
     *
     * @return String object of a wrongly formatted event.
     */
    public String invalidEvent() {
        if (this.err == Error.INVALID_EVENT) {
            return "Format is wrong.\nevent <desc> /at <date> <time>";
        }
        return "";
    }

    /**
     * Returns a string if deadline task is formatted wrongly.
     *
     * @return String object of a wrongly formatted deadline.
     */
    public String invalidDeadline() {
        if (this.err == Error.INVALID_DEADLINE) {
            return "Format is wrong.\ndeadline <desc> /by <date> <time>";
        }
        return "";
    }

    /**
     * Returns a string if deadline task is formatted wrongly.
     *
     * @return String object of a wrongly formatted deadline.
     */
    public String emptyCommand() {
        if (this.err == Error.EMPTY_COMMAND) {
            return "Please enter a valid command.\n"
                   + "todo <desc>\n"
                   + "event <desc> /at <date> <time>\n"
                   + "deadline <desc> /by <date> <time>\n"
                   + "mark/unmark <index>\n"
                   + "delete <index>\n"
                   + "find <keyword>\n"
                   + "list";
        }
        return "";
    }

}
