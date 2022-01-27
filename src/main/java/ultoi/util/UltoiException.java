package ultoi.util;

public class UltoiException extends Exception {
    public static final String EXCEPTION_FACE = "<OnO>";
    private static final String EXCEPTION_COMMAND_MISMATCH = "Error! This command is used in a wrong way.";
    private static final String EXCEPTION_FAIL_TO_LOAD_FILE = "Error! ultoi.util.Ultoi cannot load your tasks.";
    private static final String EXCEPTION_FAIL_TO_SAVE_FILE = "Error! ultoi.util.Ultoi cannot save your tasks.";
    private static final String EXCEPTION_INDEX_OUT_OF_BOUND = "Error! We do not have the task you keyed in.";
    private static final String EXCEPTION_NO_SUCH_COMMAND = "Error! ultoi.util.Ultoi does not understand this command.";

    public UltoiException(String errorMessage) {
        super(errorMessage);
    }

    public static UltoiException commandMismatchException() {
        return new UltoiException(EXCEPTION_COMMAND_MISMATCH);
    }

    public static UltoiException failToLoadFileException() {
        return new UltoiException(EXCEPTION_FAIL_TO_LOAD_FILE);
    }

    public static UltoiException failToSaveFileException() {
        return new UltoiException(EXCEPTION_FAIL_TO_SAVE_FILE);
    }

    public static UltoiException indexOutOfBoundException() {
        return new UltoiException(EXCEPTION_INDEX_OUT_OF_BOUND);
    }

    public static UltoiException noSuchCommandException() {
        return new UltoiException(EXCEPTION_NO_SUCH_COMMAND);
    }
}
