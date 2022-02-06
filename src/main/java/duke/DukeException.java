package duke;

/**
 * Class for DukeException, exceptions used in this project.
 */
public class DukeException extends Exception {
    private static final String IO_EXCEPTION_MSG = "cannot handle the file, sorry :(";
    private static final String FILE_NOT_FOUND_EXCEPTION_MSG = "No file found :(";
    private static final String FILE_INCOMPLETE_MSG =
            "Please check your file, some of its lines are corrupted :(";
    private static final String INVALID_COMMAND_MSG = "Sorry, I don't understand your command :(";
    private static final String INVALID_INDEX_MSG = "Sorry, I don't understand which task you're talking about :(";
    private static final String DESCRIPTION_EMPTY_MSG = "OOPS!!! The description cannot be empty.";
    private static final String DUPLICATE_MSG = "Are you sure you want to add this task? You have added the same one!";

    /**
     * Constructor for DukeException.
     * @param msg message that will be printed when the exception is thrown.
     */
    public DukeException(String msg) {
        super(msg);
    }

    /**
     * A type of DukeException to handle IOException.
     * @return DukeException
     */
    public static DukeException DukeIOException() {
        return new DukeException(IO_EXCEPTION_MSG);
    }

    /**
     * A type of DukeException to handle FileNotFoundException.
     * @return DukeException
     */
    public static DukeException DukeFileNotFoundException() {
        return new DukeException(FILE_NOT_FOUND_EXCEPTION_MSG);
    }

    /**
     * A type of DukeException to handle Incomplete lines on the file.
     * @return DukeException
     */
    public static DukeException DukeFileIncomplete() {
        return new DukeException(FILE_INCOMPLETE_MSG);
    }

    /**
     * A type of DukeException to handle invalid command.
     * @return DukeException
     */
    public static DukeException DukeInvalidCommand() {
        return new DukeException(INVALID_COMMAND_MSG);
    }

    /**
     * A type of DukeException to handle IndexOutOfBoundException.
     * @return DukeException
     */
    public static DukeException DukeInvalidIndex() {
        return new DukeException(INVALID_INDEX_MSG);
    }

    /**
     * A type of DukeException to handle Invalid todo command.
     * @return DukeException
     */
    public static DukeException DukeDescriptionEmpty() {
        return new DukeException(DESCRIPTION_EMPTY_MSG);
    }

    /**
     * A type of DukeException to handle Duplicate task.
     * @return DukeException
     */
    public static DukeException DukeDuplicate() {
        return new DukeException(DUPLICATE_MSG);
    }
}