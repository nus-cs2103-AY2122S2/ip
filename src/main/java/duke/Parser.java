package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Parser {
    private static final ArrayList<String> VALID_USER_COMMAND = new ArrayList<String>(Arrays.asList("todo", "event",
            "deadline", "mark", "unmark", "list", "bye", "delete", "find"));

    private static final String LINES = "    ---------------------------------";

    /**
     * Manages the listing of task list.
     *
     * @param taskLists list of tasks.
     */
    static String parserList(TaskList taskLists) {
        return taskLists.list();
    }

    /**
     * Manages the todo command.
     *
     * @param taskLists list of tasks.
     * @param userInputTask user input task.
     * @throws DukeException
     */
    static String parserTodo(TaskList taskLists, String userInputTask, Storage storage) throws DukeException, IOException {
        Parser.taskDescriptionValidator("todo", userInputTask);

        // adding task to todoList
        Todo userToDoTask = new Todo(userInputTask);
        taskLists.addWithoutPrint(userToDoTask);

        storage.save(taskLists);
        return taskLists.addTask(userToDoTask);
    }

    /**
     * Validates deadline command.
     *
     * @param userInputTask user input task.
     * @throws DukeException
     */
    static void parserDeadlineValidator(String userInputTask) throws DukeException {
        // handle error from empty task description
        try {
            Parser.taskDescriptionValidator("deadline", userInputTask);
        } catch (DukeException e) {
            System.out.println("    OOPS!!! The description of a deadline cannot be empty.");
            throw new DukeException("The description of a deadline cannot be empty.");
        }

        // handle error from empty by
        try {
            Parser.deadlineTaskValidator(userInputTask);
        } catch (DukeException e) {
            System.out.println("    OOPS!!! Deadline tasks require a by day.");
            throw new DukeException("Deadline tasks require a by day.");
        }

        // handle error when there is more than one by deadline day
        try {
            Parser.deadlineByDayValidator(userInputTask);
        } catch (DukeException e) {
            System.out.println("    OOPS!!! Deadline tasks can only have one by day.");
            throw new DukeException("Deadline tasks can only have one by day.");
        }
    }

    /**
     * Manages deadline command.
     *
     * @param taskLists list of tasks.
     * @param userInputTask user input task.
     */
    static String parserDeadline(TaskList taskLists, String userInputTask, Storage storage) throws IOException {
        // splitting deadline into description and by
        String[] deadlineTaskArr = userInputTask.split(" /by ");
        String deadlineDescription = deadlineTaskArr[0];
        String[] byAndTime = deadlineTaskArr[1].split(" ");
        String by = byAndTime[0];

        // splitting deadline into time and the rest
        LocalTime time = LocalTime.parse(byAndTime[1]);
        LocalDate deadlineDate = LocalDate.parse(by);

        // adding task to todoList
        Deadline userDeadlineTask = new Deadline(deadlineDescription, deadlineDate, time);
        taskLists.addWithoutPrint(userDeadlineTask);
        storage.save(taskLists);
        return taskLists.addTask(userDeadlineTask);
    }

    /**
     * Validates event command.
     *
     * @param userInputTask user input task.
     * @throws DukeException
     */
    static void parserEventValidator(String userInputTask) throws DukeException {
        // handle error from empty task description
        try {
            Parser.taskDescriptionValidator("event", userInputTask);
        } catch (DukeException e) {
            System.out.println(LINES);
            System.out.println("    OOPS!!! The description of an event cannot be empty.");
            System.out.println(LINES);
            throw new DukeException("The description of an event cannot be empty.");
        }

        // handle error when there is no datTime
        try {
            Parser.eventTaskValidator(userInputTask);
        } catch (DukeException e) {
            System.out.println(LINES);
            System.out.println("    OOPS!!! Event tasks require an at date and time.");
            System.out.println(LINES);
            throw new DukeException("Event tasks require an at date and time.");
        }

        // handle error when there is more than one dateTime
        try {
            Parser.eventAtDateTimeValidator(userInputTask);
        } catch (DukeException e) {
            System.out.println(LINES);
            System.out.println("    OOPS!!! Event tasks can only have one at date and time.");
            System.out.println(LINES);
            throw new DukeException("Event tasks can only have one at date and time.");
        }
    }

    /**
     * Manages event command.
     *
     * @param taskLists list of tasks.
     * @param userInputTask user input task.
     */
    static String parserEvent(TaskList taskLists, String userInputTask, Storage storage) throws IOException {

        // splitting event into description and dateTime
        String[] eventTaskArr = userInputTask.split(" /at ");
        String eventDescription = eventTaskArr[0];
        String[] eventDateAndTime = eventTaskArr[1].split(" ");
        String eventDate = eventDateAndTime[0];

        // splitting event into time and the rest
        String[] eventTimeAndTheRest = userInputTask.split(" ");

        String eventTime = eventDateAndTime[1];
        LocalTime atTime = LocalTime.parse(eventTime);
        LocalDate atDate = LocalDate.parse(eventDate);

        // adding task to todoList
        Event userEventTask = new Event(eventDescription, atDate, atTime);
        taskLists.addWithoutPrint(userEventTask);
        storage.save(taskLists);
        return taskLists.addTask(userEventTask);
    }

    /**
     * Manages mark command.
     *
     * @param taskLists list of tasks.
     * @param userInputs user input task.
     */
    static String parserMark(TaskList taskLists, String[] userInputs, Storage storage) throws IOException {
        int taskToMark = Integer.parseInt(userInputs[1]);
        taskLists.get(taskToMark - 1).markAsDone();
        storage.save(taskLists);
        return taskLists.setTaskAsDone(taskToMark);
    }

    /**
     * Manages unmark command.
     *
     * @param taskLists list of tasks.
     * @param userInputs user input task.
     */
    static String parserUnmark(TaskList taskLists, String[] userInputs, Storage storage) throws IOException {
        int taskToUnmark = Integer.parseInt(userInputs[1]);
        taskLists.get(taskToUnmark - 1).markAsNotDone();
        storage.save(taskLists);
        return taskLists.setTaskAsUnDone(taskToUnmark);
    }

    /**
     * Validates delete command.
     *
     * @param taskLists list of tasks.
     * @param userInputTask user input task.
     * @throws DukeException
     */
    static void parserDeleteValidator(TaskList taskLists, String userInputTask) throws DukeException {
        // handle error when there is no specified task number to be deleted
        try {
            Parser.deleteValidator(userInputTask);
        } catch (DukeException e) {
            System.out.println(LINES);
            System.out.println("    Delete command must have a specified task number to be deleted.");
            System.out.println(LINES);
            throw new DukeException("Delete command must have a specified task number to be deleted.");
        }

        // handle error when the task number specified is invalid
        try {
            Parser.deleteTaskNumberValidator(taskLists, userInputTask);
        } catch (DukeException e) {
            System.out.println(LINES);
            System.out.println("    Invalid task number to be deleted.");
            System.out.println(LINES);
            throw new DukeException("Invalid task number to be deleted.");
        }
    }

    /**
     * Manages delete command.
     *
     * @param taskLists list of tasks.
     * @param userInputs user input of task to delete.
     */
    static String parserDelete(TaskList taskLists, String[] userInputs, Storage storage) throws IOException {
        int taskToDelete = Integer.parseInt(userInputs[1]);

        // deleting task from the array list
        return taskLists.removeTask(taskToDelete, storage);
    }

    /**
     * Manages find command.
     *
     * @param taskLists list of tasks.
     * @param userInputTask user input task.
     */
    static String parserFind(TaskList taskLists, String ...userInputTask) {
        TaskList tasks = new TaskList(new ArrayList<Task>());

        for (int i = 0; i < taskLists.size(); i++) {
            if (taskLists.get(i).getDescription().contains(userInputTask[0])) {
                tasks.addWithoutPrint(taskLists.get(i));
            }
        }

        String output = "    These are my finding tasks in your list 😄:/n";
        for (int j = 0; j < tasks.size(); j++) {
            output += "    " + (j + 1) + "." + tasks.get(j).toString() + "/n";
        }
        return output;
    }

    /**
     * Validates if there is task description for input task.
     *
     * @param userCommand
     * @param description description of task.
     * @throws DukeException
     */
    static void taskDescriptionValidator(String userCommand, String description) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException("Invalid task description for " + userCommand);
        }
    }

    /**
     * Validates if there is invalid deadline for deadline task.
     *
     * @param description description of deadline task.
     * @throws DukeException
     */
    static void deadlineTaskValidator(String description) throws DukeException {
        if (!description.contains("/by")) {
            throw new DukeException("Invalid by deadline for deadline task: " + description);
        }
    }

    /**
     * Validates event task if there is no dateTime.
     *
     * @param description description of event task.
     * @throws DukeException
     */
    static void eventTaskValidator(String description) throws DukeException {
        if (!description.contains("/at")) {
            throw new DukeException("Invalid at date for event task: " + description);
        }
    }

    /**
     * Validates if the user has input invalid command.
     *
     * @param userCommand user input command.
     * @throws DukeException
     */
    static void userCommandValidator(String userCommand) throws DukeException {
        if (!VALID_USER_COMMAND.contains(userCommand)) {
            throw new DukeException("Invalid user command for input task");
        }
    }

    /**
     * Validates deadline task if there is more than one deadline day.
     *
     * @param description description of deadline task.
     * @throws DukeException
     */
    static void deadlineByDayValidator(String description) throws DukeException {
        String temp = description.replaceFirst("/by", "");

        if (temp.contains("/by")) {
            throw new DukeException("Invalid by day for deadline task: " + description);
        }
    }

    /**
     * Validates if there is invalid date format.
     *
     * @param by the deadline of this task.
     * @throws DukeException
     */
    static void deadlineDateFormatValidator(String by) throws DukeException {
        if (by.contains("/") || by.contains(".")) {
            throw new DukeException("Deadline date needs to be in the YYYY-MM-DD format");
        }

        if (!by.contains("-")) {
            throw new DukeException("Deadline date needs to be in the YYYY-MM-DD format");
        }
    }

    /**
     * Validates if there is more than one event date.
     *
     * @param description description of an event task.
     * @throws DukeException
     */
    static void eventAtDateTimeValidator(String description) throws DukeException {
        String temp = description.replaceFirst("/at", "");

        if (temp.contains("/at")) {
            throw new DukeException("Invalid at date and time for event task: " + description);
        }
    }

    /**
     * Validates if there is invalid date format for event task.
     *
     * @param by event date.
     * @throws DukeException
     */
    static void eventDateFormatValidator(String by) throws DukeException {
        if (by.contains("/") || by.contains(".")) {
            throw new DukeException("Event date needs to be in the YYYY-MM-DD format");
        }

        if (!by.contains("-")) {
            throw new DukeException("Event date needs to be in the YYYY-MM-DD format");
        }
    }

    /**
     * Validates if there is a task specify to delete.
     *
     * @param taskNumber task number to be deleted.
     * @throws DukeException
     */
    static void deleteValidator(String taskNumber) throws DukeException {
        if (taskNumber.isEmpty()) {
            throw new DukeException("Empty task number for delete task");
        }
    }

    /**
     * Validates if the task number specified to be deleted is valid.
     *
     * @param taskLists list of tasks.
     * @param taskNumber task number to be deleted.
     * @throws DukeException
     */
    static void deleteTaskNumberValidator(TaskList taskLists, String taskNumber) throws DukeException {
        if (Integer.parseInt(taskNumber) > taskLists.size()) {
            throw new DukeException("Invalid task number for delete task");
        }
    }
}
