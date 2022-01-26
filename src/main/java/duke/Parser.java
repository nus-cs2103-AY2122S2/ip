package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Parser {
    static ArrayList<String> VALID_USER_COMMAND = new ArrayList<String>(Arrays.asList("todo", "event", "deadline", "mark",
            "unmark", "list", "bye", "delete", "find"));

    static String LINES = "    ---------------------------------";

    // deal with the list command
    static void parserList(TaskList taskLists) {
        taskLists.list();
    }

    // deal with the todo command
    static void parserTodo(TaskList taskLists, String userInputTask) throws DukeException {
        Parser.taskDescriptionValidator("todo" ,userInputTask);

        // adding task to todoList
        Todo userToDoTask = new Todo(userInputTask);
        taskLists.addTask(userToDoTask);
    }

    static void parserDeadlineValidator(String userInputTask) throws DukeException {
        // handle error from empty task description
        try {
            Parser.taskDescriptionValidator("deadline", userInputTask);
        } catch (DukeException e) {
            System.out.println(LINES);
            System.out.println("    OOPS!!! The description of a deadline cannot be empty.");
            System.out.println(LINES);
            throw new DukeException("The description of a deadline cannot be empty.");
        }

        // handle error from empty by
        try {
            Parser.deadlineTaskValidator(userInputTask);
        } catch (DukeException e) {
            System.out.println(LINES);
            System.out.println("    OOPS!!! Deadline tasks require a by day.");
            System.out.println(LINES);
            throw new DukeException("Deadline tasks require a by day.");
        }

        // handle error when there is more than one by deadline day
        try {
            Parser.deadlineByDayValidator(userInputTask);
        } catch (DukeException e) {
            System.out.println(LINES);
            System.out.println("    OOPS!!! Deadline tasks can only have one by day.");
            System.out.println(LINES);
            throw new DukeException("Deadline tasks can only have one by day.");
        }
    }

    static void parserDeadline(TaskList taskLists, String userInputTask) {
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
        taskLists.addTask(userDeadlineTask);
    }

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

    static void parserEvent(TaskList taskLists, String userInputTask) {

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
        taskLists.addTask(userEventTask);
    }

    static void parserMark(TaskList taskLists, String[] userInputs) {
        int taskToMark = Integer.parseInt(userInputs[1]);
        taskLists.setTaskAsDone(taskToMark);
    }

    static void parserUnmark(TaskList taskLists, String[] userInputs) {
        int taskToUnmark = Integer.parseInt(userInputs[1]);
        taskLists.setTaskAsUnDone(taskToUnmark);
    }

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

    static void parserDelete(TaskList taskLists, String[] userInputs) {
        int taskToDelete = Integer.parseInt(userInputs[1]);

        // deleting task from the array list
        taskLists.removeTask(taskToDelete);
    }

    static void parserFind(TaskList taskLists, String userInputTask) {
        TaskList tasks = new TaskList(new ArrayList<Task>());

        for (int i = 0; i < taskLists.size(); i++) {
            if (taskLists.get(i).getDescription().contains(userInputTask)) {
                tasks.addWithoutPrint(taskLists.get(i));
            }
        }

        System.out.println(LINES);
        System.out.println("    These are my finding tasks in your list 😄:");
        for (int j = 0; j < tasks.size(); j++) {
            System.out.println("    " + (j + 1) + "." + tasks.get(j).toString());
        }
        System.out.println(LINES);
    }


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

    // check for when there is invalid date symbols
    static void deadlineDateFormatValidator(String by) throws DukeException {
        if (by.contains("/") || by.contains(".")) {
            throw new DukeException("Deadline date needs to be in the YYYY-MM-DD format");
        }

        if (!by.contains("-")) {
            throw new DukeException("Deadline date needs to be in the YYYY-MM-DD format");
        }
    }

    // check for when there is more than one at event date
    static void eventAtDateTimeValidator(String description) throws DukeException {
        String temp = description.replaceFirst("/at", "");

        if (temp.contains("/at")) {
            throw new DukeException("Invalid at date and time for event task: " + description);
        }
    }

    // check for when there is invalid date symbols
    static void eventDateFormatValidator(String by) throws DukeException {
        if (by.contains("/") || by.contains(".")) {
            throw new DukeException("Event date needs to be in the YYYY-MM-DD format");
        }

        if (!by.contains("-")) {
            throw new DukeException("Event date needs to be in the YYYY-MM-DD format");
        }
    }

    // check for when there is no task specify for delete
    static void deleteValidator(String taskNumber) throws DukeException {
        if (taskNumber.isEmpty()) {
            throw new DukeException("Empty task number for delete task");
        }
    }

    // check for when the task number specified is invalid for delete task
    static void deleteTaskNumberValidator(TaskList taskLists, String taskNumber) throws DukeException {
        if (Integer.parseInt(taskNumber) > taskLists.size()) {
            throw new DukeException("Invalid task number for delete task");
        }
    }

}
