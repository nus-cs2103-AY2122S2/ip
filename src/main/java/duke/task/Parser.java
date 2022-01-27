package duke.task;

/**
 * Represents a parser used to make sense of user input. A <code>Parser</code> object corresponds to a parser.
 */
public class Parser {
    /**
     * Returns a new instance of <code>Parser</code> object.
     */
    public Parser() {
    }

    /**
     * Returns the parsed array of String for Deadline inputs.
     * @param string User input for Deadline creation.
     * @return parsed String array, with first element being the name and second element being date-by.
     * @throws IncompleteArgumentException If the string is not in the right format, i.e. two parts separated by "/by".
     */
    public String[] parseDeadline(String string) throws IncompleteArgumentException {
        String[] result = string.split("/by");

        for (int i = 0; i < result.length; i++) {
            result[i] = result[i].trim();
        }

        if (result.length < 2) {
            throw new IncompleteArgumentException("Incomplete Argument");
        }
        return result;
    }

    /**
     * Returns the parsed array of String for Event inputs.
     * @param string User input for Event creation.
     * @return parsed String array, with first element being the name and second element being date-at.
     * @throws IncompleteArgumentException If the string is not in the right format, i.e. two parts separated by "/at".
     */
    public String[] parseEvent(String string) throws IncompleteArgumentException {
        String[] result = string.split("/at");

        for (int i = 0; i < result.length; i++) {
            result[i] = result[i].trim();
        }

        if (result.length < 2) {
            throw new IncompleteArgumentException("Incomplete Argument");
        }
        return result;
    }
}
