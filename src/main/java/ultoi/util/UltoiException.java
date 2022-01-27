package ultoi.util;

/**
 * Represents the exceptions that could arise during the execution of  Ultoi bot.
 */
public class UltoiException extends Exception {
    public static final String EXCEPTION_FACE = "<OnO>";
    private static final String EXCEPTION_COMMAND_MISMATCH = "Error! This command is used in a wrong way.";
    private static final String EXCEPTION_FAIL_TO_LOAD_FILE = "Error! ultoi.util.Ultoi cannot load your tasks.";
    private static final String EXCEPTION_FAIL_TO_SAVE_FILE = "Error! ultoi.util.Ultoi cannot save your tasks.";
    private static final String EXCEPTION_INDEX_OUT_OF_BOUND = "Error! We do not have the task you keyed in.";
    private static final String EXCEPTION_NO_SUCH_COMMAND = "Error! ultoi.util.Ultoi does not understand this command.";

    /**
     * Creates a new Ultoi exception.
     *
     * @param errorMessage Error message.
     */
    public UltoiException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Returns a command mismatch exception.
     *
     * @return Ultoi exception that describes a command mismatch.
     */
    public static UltoiException commandMismatchException() {
        return new UltoiException(EXCEPTION_COMMAND_MISMATCH);
    }

    /**
     * Returns a fail to load file exception.
     *
     * @return Ultoi exception that describes a failure to load file.
     */
    public static UltoiException failToLoadFileException() {
        return new UltoiException(EXCEPTION_FAIL_TO_LOAD_FILE);
    }

    /**
     * Returns a fail to save file exception.
     *
     * @return Ultoi exception that describes a failure to save file.
     */
    public static UltoiException failToSaveFileException() {
        return new UltoiException(EXCEPTION_FAIL_TO_SAVE_FILE);
    }

    /**
     * Returns a index out of bound exception.
     *
     * @return Ultoi exception that describes an index out of bound.
     */
    public static UltoiException indexOutOfBoundException() {
        return new UltoiException(EXCEPTION_INDEX_OUT_OF_BOUND);
    }

    /**
     * Returns a no such command exception.
     *
     * @return Ultoi exception that describes an unidentified command.
     */
    public static UltoiException noSuchCommandException() {
        return new UltoiException(EXCEPTION_NO_SUCH_COMMAND);
    }
}
