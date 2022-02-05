package duke.sonautil;

import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * Deals with making sense of the user's command
 */
public class Parser {

    /**
     * Returns a String array (arr) of the user's command.
     * arr[0]: keyword of command, "unknown" if command invalid/does not exist
     * arr[1]: string representation of Duke.task index (if applicable, null otherwise)
     * arr[2] : string representation of time (if applicable, null otherwise)
     * arr[3]: "true" if time is entered by user, "false" if not entered; null if not applicable
     *
     * @param userMessage the command entered by user
     * @return details of user's command
     * @throws DukeException If command is invalid / not understood
     * @throws DateTimeParseException If date/time entered is invalid
     */
    public String[] processMessage(String userMessage) throws DukeException, DateTimeParseException {
        String[] command = new String[4];
        String[] split = userMessage.split(" ");

        if (userMessage.equals("list")) {
            command[0] = "list";

        } else if (userMessage.startsWith("mark") && split[0].equals("mark")) {
            command = processMarkCommand(userMessage);

        } else if (userMessage.startsWith("unmark") && split[0].equals("unmark")) {
            command = processUnmarkCommand(userMessage);

        } else if (userMessage.startsWith("todo") && split[0].equals("todo")) {
            command = processTodoCommand(userMessage);

        } else if (userMessage.startsWith("deadline") && split[0].equals("deadline")) {
            command = processDeadlineCommand(userMessage);

        } else if (userMessage.startsWith("event") && split[0].equals("event")) {
            command = processEventCommand(userMessage);

        } else if (userMessage.startsWith("delete") && split[0].equals("delete")) {
            command = processDeleteCommand(userMessage);

        } else if (userMessage.startsWith("schedule") && split[0].equals("schedule")) {
            command = processScheduleCommand(userMessage);

        } else if (userMessage.startsWith("find") && split[0].equals("find")) {
            command = processFindCommand(userMessage);

        } else {
            command[0] = "unknown";
            throw new DukeException(Ui.userUnknownInputMessage(userMessage));
        }

        return command;
    }

    /**
     * Processes user's 'mark' command
     *
     * @param userMessage user's message
     * @return string array of command information
     * @throws DukeException if command is invalid / not understood
     */
    private String[] processMarkCommand(String userMessage) throws DukeException {
        String[] command = new String[4];
        if (userMessage.replaceAll(" ", "").equals("mark")) {
            throw new DukeException(Ui.markNoNumberMessage());
        }

        //invalid number
        boolean isMarkCommand = userMessage.toLowerCase().matches("^mark \\d+|^mark -\\d+");

        if (!isMarkCommand) {
            throw new DukeException(Ui.userUnknownInputMessage(userMessage));
        }
        String clean = userMessage.substring(4).replaceAll(" ", "");

        //empty message after mark
        if (clean.equals("")) {
            throw new DukeException(Ui.markNoNumberMessage());
        }

        int taskIndex = Integer.parseInt(clean) - 1;
        command[0] = "mark";
        command[1] = String.valueOf(taskIndex);

        return command;
    }

    /**
     * Processes user's 'unmark' command
     *
     * @param userMessage user's message
     * @return string array of command information
     * @throws DukeException if command is invalid / not understood
     */
    private String[] processUnmarkCommand(String userMessage) throws DukeException {
        String[] command = new String[4];
        if (userMessage.replaceAll(" ", "").equals("unmark")) {
            throw new DukeException(Ui.unmarkNoNumberMessage());
        }

        //invalid number
        boolean isUnmarkCommand = userMessage.toLowerCase().matches("^unmark \\d+|^unmark -\\d+");
        if (!isUnmarkCommand) {
            throw new DukeException(Ui.userUnknownInputMessage(userMessage));
        }

        String clean = userMessage.substring(6).replaceAll(" ", "");

        //empty message after mark
        if (clean.equals("")) {
            throw new DukeException(Ui.unmarkNoNumberMessage());
        }

        int taskIndex = Integer.parseInt(clean) - 1;
        command[0] = "unmark";
        command[1] = String.valueOf(taskIndex);

        return command;
    }

    /**
     * Processes user's 'todo' command
     *
     * @param userMessage user's message
     * @return string array of command information
     * @throws DukeException if command is invalid / not understood
     */
    private String[] processTodoCommand(String userMessage) throws DukeException {
        String[] command = new String[4];
        if (userMessage.substring(4).replaceAll(" ", "").equals("")) {
            throw new DukeException(Ui.emptyTaskMessage());
        } else {
            String task = userMessage.substring(5);
            command[0] = "todo";
            command[1] = task;
        }

        return command;
    }

    /**
     * Processes user's 'deadline' command
     *
     * @param userMessage user's message
     * @return string array of command information
     * @throws DukeException if command is invalid / not understood
     * @throws DateTimeParseException if date is invalid
     */
    private String[] processDeadlineCommand(String userMessage) throws DukeException, DateTimeParseException {
        String[] command = new String[4];
        //empty body
        if (userMessage.substring(8).replaceAll(" ", "").equals("")) {
            throw new DukeException(Ui.emptyTaskMessage());
        }
        //no slash
        if (!userMessage.contains("/")) {
            throw new DukeException(Ui.deadlineNoSlashMessage());
        }

        String[] taskDetails = userMessage.substring(9).split("/", 2);

        //deadline is empty
        if (taskDetails[1].replaceAll(" ", "").equals("")) {
            throw new DukeException(Ui.deadlineDateEmptyMessage());
        }

        //processing date & time
        String dateTime = taskDetails[1].replaceAll(" ", "");
        LocalDateTime dateTimeComplete;
        String timeEntered = "false";

        if (dateTime.length() == 14) { //date & time
            LocalDate date = LocalDate.parse(dateTime.substring(0, 10));
            int hour = Integer.parseInt(dateTime.substring(10, 12));
            int min = Integer.parseInt(dateTime.substring(12));
            dateTimeComplete = date.atTime(hour, min);
            timeEntered = "true";

        } else if (dateTime.length() == 10) { //date only
            LocalDate date = LocalDate.parse(dateTime.substring(0, 10));
            dateTimeComplete = date.atTime(23, 59);

            //date & time invalid
        } else {
            throw new DukeException(Ui.dateTimeErrorMessage());
        }

        command[0] = "deadline";
        command[1] = taskDetails[0];
        command[2] = dateTimeComplete.toString();
        command[3] = timeEntered;
        return command;
    }

    /**
     * Processes user's 'event' command
     *
     * @param userMessage user's message
     * @return string array of command information
     * @throws DukeException if command is invalid / not understood
     * @throws DateTimeParseException if date is invalid
     */
    private String[] processEventCommand(String userMessage) throws DukeException, DateTimeParseException {
        String[] command = new String[4];
        //empty body
        if (userMessage.substring(5).replaceAll(" ", "").equals("")) {
            throw new DukeException(Ui.emptyTaskMessage());
        }
        //no slash
        if (!userMessage.contains("/")) {
            throw new DukeException(Ui.eventNoSlashMessage());
        }

        String[] taskDetails = userMessage.substring(6).split("/", 2);

        //event date is empty
        if (taskDetails[1].replaceAll(" ", "").equals("")) {
            throw new DukeException(Ui.eventDateEmptyMessage());
        }

        //processing date & time
        String dateTime = taskDetails[1].replaceAll(" ", "");
        LocalDateTime dateTimeComplete;
        String timeEntered = "false";

        if (dateTime.length() == 14) { //date & time
            LocalDate date = LocalDate.parse(dateTime.substring(0, 10));
            int hour = Integer.parseInt(dateTime.substring(10, 12));
            int min = Integer.parseInt(dateTime.substring(12));
            dateTimeComplete = date.atTime(hour, min);
            timeEntered = "true";

        } else if (dateTime.length() == 10) { //date only
            LocalDate date = LocalDate.parse(dateTime.substring(0, 10));
            dateTimeComplete = date.atTime(0, 0);

            //date & time invalid
        } else {
            throw new DukeException(Ui.dateTimeErrorMessage());
        }

        command[0] = "event";
        command[1] = taskDetails[0];
        command[2] = dateTimeComplete.toString();
        command[3] = timeEntered;
        return command;
    }

    /**
     * Processes user's 'delete' command
     *
     * @param userMessage user's message
     * @return string array of command information
     * @throws DukeException if command is invalid / not understood
     */
    private String[] processDeleteCommand(String userMessage) throws DukeException {
        String[] command = new String[4];
        if (userMessage.replaceAll(" ", "").equals("delete")) {
            throw new DukeException(Ui.taskRemoveEmptyMessage());
        }

        //invalid number
        boolean isValidCommand = userMessage.toLowerCase().matches("^delete \\d+|^delete -\\d+");
        if (!isValidCommand) {
            throw new DukeException(Ui.userUnknownInputMessage(userMessage));
        }

        String clean = userMessage.substring(6).replaceAll(" ", "");

        //empty message after delete
        if (clean.equals("")) {
            throw new DukeException(Ui.taskRemoveEmptyMessage());
        }

        int taskIndex = Integer.parseInt(clean) - 1;

        command[0] = "delete";
        command[1] = String.valueOf(taskIndex);
        return command;
    }

    /**
     * Processes user's 'schedule' command
     *
     * @param userMessage user's message
     * @return string array of command information
     * @throws DukeException if command is invalid / not understood
     * @throws DateTimeParseException if date is invalid
     */
    private String[] processScheduleCommand(String userMessage) throws DukeException, DateTimeParseException {
        String[] command = new String[4];
        String dateString = userMessage.substring(8).replaceAll(" ", "");

        //no date given
        if (dateString.equals("")) {
            throw new DukeException(Ui.scheduleNoDateErrorMessage());
        }

        //date is invalid
        LocalDate date = LocalDate.parse(dateString);
        command[0] = "schedule";
        command[1] = date.toString();
        return command;
    }

    /**
     * Processes user's 'find' command
     *
     * @param userMessage user's message
     * @return string array of command information
     * @throws DukeException if command is invalid / not understood
     */
    private String[] processFindCommand(String userMessage) throws DukeException {
        String[] command = new String[4];
        //only "find"
        if (userMessage.length() == 4) {
            throw new DukeException(Ui.findNoKeywordError());
        }

        String keyword = userMessage.substring(5);

        //no keyword given
        if (keyword.replaceAll(" ", "").equals("")) {
            throw new DukeException(Ui.findNoKeywordError());
        }
        command[0] = "find";
        command[1] = keyword;
        return command;
    }

}
