package duke.component;

import java.io.IOException;

import java.time.DateTimeException;

import duke.exception.DukeException;
import duke.exception.EmptyTaskException;
import duke.exception.InvalidIndexException;
import duke.exception.MissingDateTimeException;
import duke.exception.UnknownCommandException;

import static duke.constant.Message.MESSAGE_DATE_TIME_FORMAT_EXCEPTION;
import static duke.constant.Message.MESSAGE_DUKE_EXCEPTION;
import static duke.constant.Message.MESSAGE_EMPTY_TASK_EXCEPTION;
import static duke.constant.Message.MESSAGE_FILE_NOT_FOUND;
import static duke.constant.Message.MESSAGE_INVALID_INDEX_EXCEPTION;
import static duke.constant.Message.MESSAGE_MISSING_DATE_TIME_EXCEPTION;
import static duke.constant.Message.MESSAGE_UNKNOWN_COMMAND_EXCEPTION;

/**
 * A class to handle exceptions thrown by Duke.
 */
public class ExceptionHandler {
    private static Ui ui = new Ui();

    /**
     * Handles Duke exception by printing the error message.
     * @param e Specified-Duke exception
     */
    public String handleDukeException(DukeException e) {
        if (e instanceof EmptyTaskException) {
            return ui.printMessage(MESSAGE_EMPTY_TASK_EXCEPTION);
        } else if (e instanceof MissingDateTimeException) {
            return ui.printMessage(MESSAGE_MISSING_DATE_TIME_EXCEPTION);
        } else if (e instanceof InvalidIndexException) {
            return ui.printMessage(MESSAGE_INVALID_INDEX_EXCEPTION);
        } else if (e instanceof UnknownCommandException) {
            return ui.printMessage(MESSAGE_UNKNOWN_COMMAND_EXCEPTION);
        } else {
            return ui.printMessage(MESSAGE_DUKE_EXCEPTION);
        }
    }

    /**
     * Handles other common exception by printing the error message.
     * @param e Default Exception class.
     */
    public String handleOtherException(Exception e) {
        if (e instanceof IOException) {
            return ui.printMessage(MESSAGE_FILE_NOT_FOUND);
        } else if (e instanceof DateTimeException) {
            return ui.printMessage(MESSAGE_DATE_TIME_FORMAT_EXCEPTION);
        } else {
            return ui.printMessage(MESSAGE_DUKE_EXCEPTION);
        }
    }
}
