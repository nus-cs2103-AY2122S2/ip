import java.util.ArrayList;
import java.util.Arrays;


public class UserInputTaskValidator {
    static ArrayList<String> VALID_USER_COMMAND = new ArrayList<String>(Arrays.asList("todo", "event", "deadline", "mark", "unmark", "list", "bye", "delete"));

    // check for error when there is no task description
    static void taskDescriptionValidator(String userCommand, String description) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException("Invalid task description for " + userCommand);
        }
    }

    // check for error when there is invalid deadline without by
    static void deadlineTaskValidator(String description) throws DukeException {
        if (!description.contains("/by")) {
            throw new DukeException("Invalid by deadline for deadline task: " + description);
        }
    }

    // check for error when there is no dateTime
    static void eventTaskValidator(String description) throws DukeException {
        if (!description.contains("/at")) {
            throw new DukeException("Invalid at date for event task: " + description);
        }
    }

    // check for error when there is invalid userCommand
    static void userCommandValidator(String userCommand) throws DukeException {
        if (!VALID_USER_COMMAND.contains(userCommand)) {
            throw new DukeException("Invalid user command for input task");
        }
    }

    // check for when there is more than one by deadline day
    static void deadlineByDayValidator(String description) throws DukeException {
        String temp = description.replaceFirst("/by", "");

        if (temp.contains("/by")) {
            throw new DukeException("Invalid by day for deadline task: " + description);
        }
    }

    // check for when there is more than one at event date
    static void eventAtDateTimeValidator(String description) throws DukeException {
        String temp = description.replaceFirst("/at", "");

        if (temp.contains("/at")) {
            throw new DukeException("Invalid at date and time for event task: " + description);
        }
    }

    // check for when there is no task specify for delete
    static void deleteValidator(String taskNumber) throws DukeException {
        if (taskNumber.isEmpty()) {
            throw new DukeException("Empty task number for delete task");
        }
    }

    // check for when the task number specified is invalid for delete task
    static void deleteTaskNumberValidator(ArrayList<Task> todoList, String taskNumber) throws DukeException {
        if (Integer.parseInt(taskNumber) > todoList.size()) {
            throw new DukeException("Invalid task number for delete task");
        }
    }

}
