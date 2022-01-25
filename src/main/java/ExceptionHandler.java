import java.io.IOException;
import java.time.DateTimeException;

public class ExceptionHandler {
    private static Ui ui = new Ui();

    public static void handleDukeException(DukeException e) {
        if (e instanceof EmptyTaskException) {
            ui.printMessage(Message.EMPTY_TASK_EXCEPTION);
        } else if (e instanceof MissingDateTimeException) {
            ui.printMessage(Message.MISSING_DATE_TIME_EXCEPTION);
        } else if (e instanceof InvalidIndexException) {
            ui.printMessage(Message.INVALID_INDEX_EXCEPTION);
        } else if (e instanceof UnknownCommandException) {
            ui.printMessage(Message.UNKNOWN_COMMAND_EXCEPTION);
        } else {
            ui.printMessage(Message.DUKE_EXCEPTION);
        }
    }

    public static void handleOtherException(Exception e) {
        if (e instanceof IOException) {
            ui.printMessage(Message.FILE_NOT_FOUND);
        } else if (e instanceof DateTimeException) {
            ui.printMessage(Message.DATE_TIME_FORMAT_EXCEPTION);
        }
    }
}
