package duke.exception;

import duke.exception.DukeException;

/**
 * Represents an exception that occurs when a user has entered a keyword such
 * as "deadline" or "event" but forget to put the keyword "/at" or "/by"
 * which will make parsing of the line fail.
 */
public class DukeMissingArgumentException extends DukeException {
    protected String keyword;
    public DukeMissingArgumentException(String keyword){
        super();
        this.keyword = keyword;
    }

    /**
     * Returns the String representation of <code>DukeMissingArgumentException</code> object.
     * @return a sentence that notify which keyword the user is missing.
     */
    @Override
    public String toString() {
        return "you are missing keyword: " + keyword;
    }
}
