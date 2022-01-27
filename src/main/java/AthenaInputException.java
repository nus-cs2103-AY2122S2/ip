public class AthenaInputException extends Exception {
    private final ErrorCode errorCode;

    public AthenaInputException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        switch (errorCode) {
        case MISSING_TASK_NUMBER:
            return("Error. Please provide a valid task number.");
            // No fallthrough
        case INVALID_TASK_NUMBER:
            return("Error. There is no such task. Please try again.");
            // No fallthrough
        case MISSING_TASK_NAME:
            return("Error. Please provide a task name.");
            // No fallthrough
        case MISSING_TASK_DATETIME:
            return("Error. Please provide a date and/or time.");
            // No fallthrough
        default:
            return(""); // Should not get here.
            // No fallthrough
        }
    }
}
