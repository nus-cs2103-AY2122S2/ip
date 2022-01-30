package athena.exceptions;

/**
 * Represents a possible type of user input error when giving commands to Athena.
 */
public enum InputErrorCode {
    INVALID_COMMAND,
    MISSING_TASK_NUMBER,
    INVALID_TASK_NUMBER,
    MISSING_TASK_NAME,
    MISSING_TASK_DATETIME,
    INVALID_TASK_DATETIME
}
