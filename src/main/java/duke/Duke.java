package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Main duke application class
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.loadListFromDisk());
        } catch (FileNotFoundException err) {
            ui.displayLoadError();
            tasks = new TaskList();
        }
    }

    /**
     * List existing tasks created by user (if exists)
     *
     * @return List of tasks created by user
     */
    public String runListCommand() {
        return Ui.displayTaskList(tasks.getTasks());
    }

    /**
     * Mark task with index provided by the user
     *
     * @param input Input text provided by user
     * @return Returns the successfully marked message or invalid_task_index_error message if given index is invalid
     */
    public String runMarkCommand(String input) {
        int itemIndex = Integer.parseInt(input.split(" ")[1]);
        try {
            Task selectedTask = tasks.getTask(itemIndex - 1);
            selectedTask.markAsComplete();
            return Ui.displayMarkMsg(selectedTask.toString());
        } catch (IndexOutOfBoundsException err) {
            return Ui.INVALID_INDEX_ERROR;
        }
    }

    /**
     * Unmark task with index provided by the user
     *
     * @param input Input text from user
     * @return Returns the successfully unmarked message or invalid_task_index_error message if given index is invalid
     */
    public String runUnmarkCommand(String input) {
        int itemIndex = Integer.parseInt(input.split(" ")[1]);
        try {
            Task selectedTask = tasks.getTask(itemIndex - 1);
            selectedTask.markAsIncomplete();
            return Ui.displayUnmarkMsg(selectedTask.toString());
        } catch (IndexOutOfBoundsException err) {
            return Ui.INVALID_INDEX_ERROR;
        }
    }

    /**
     * Add deadline to list of tasks from input provided by user
     *
     * @param input Input text from user
     * @return Returns the successfully added deadline message or daedline_missing_timings_error
     * message if given deadline end timing is invalid or missing
     */
    public String runDeadlineCommand(String input) {
        String endTime = Parser.getDeadlineTiming(input);
        if (endTime == Ui.DEADLINE_INVALID_TIMINGS_ERROR) {
            return Ui.DEADLINE_INVALID_TIMINGS_ERROR;
        }

        LocalDateTime endDate = Parser.parseDateTime(endTime);
        String taskName = input.replaceAll("deadline", "").split("/by")[0];
        Deadline newDeadline = new Deadline(taskName, endDate);
        tasks.addTask(newDeadline);
        return Ui.displayListedText(newDeadline, tasks.getSize());
    }

    /**
     * Add event to list of tasks from input provided by user
     *
     * @param input Input text from user
     * @return Returns the successfully added event message or event_missing_timings_error
     * message if given event start or end timing is invalid or missing
     */
    public String runEventCommand(String input) {
        String taskName = input.replaceAll("event", "").split("/at")[0];
        String eventTimings = Parser.getEventTimings(input);
        String[] eventTimingsSplit = eventTimings.split(",");
        String startTimeString = eventTimingsSplit[0];
        String endTimingString = eventTimingsSplit[1];

        try {
            LocalDateTime startDate = Parser.parseDateTime(startTimeString);
            System.out.println("start date: " + startDate);
            LocalDateTime endDate = Parser.parseDateTime(endTimingString);
            System.out.println("end date: " + endDate);
            Event newEvent = new Event(taskName, startDate, endDate);
            tasks.addTask(newEvent);
            return Ui.displayListedText(newEvent, tasks.getSize());

        } catch (DateTimeParseException err) {
            return Ui.EVENT_INVALID_TIMINGS_ERROR;
        }
    }

    /**
     * Add TODO to list of tasks from input provided by user
     *
     * @param input Input text from user
     * @return Returns the successfully added TODO message or todo_invalid_name_error
     * message if given event start or end timing is invalid or missing
     */
    public String runTodoCommand(String input) {
        String taskName = input.replaceAll("todo", "");

        if (input.split(" ").length < 2) {
            return Ui.TASK_INVALID_NAME_ERROR;
        }

        Todo newTodo = new Todo(taskName);
        tasks.addTask(newTodo);
        return Ui.displayListedText(newTodo, tasks.getSize());
    }

    /**
     * Delete task with index specified by user
     *
     * @param input Input text from user
     * @return Returns the successfully deleted task message or invalid_index_error
     * message if given an invalid task index
     */
    public String runDeleteCommand(String input) {
        if (input.split("").length < 2) {
           return Ui.INVALID_INDEX_ERROR;
        }

        int taskIndex = Integer.parseInt(input.split(" ")[1]);
        try {
            Task deletedTask = tasks.removeTaskIndex(taskIndex - 1);
            if (deletedTask.getEventType() == Type.EVENT) {
                Event deletedEvent = (Event) deletedTask;
                return Ui.displayDeletedMessage(deletedEvent, tasks.getSize());
            } else if (deletedTask.getEventType() == Type.DEADLINE) {
                Deadline deletedDeadline = (Deadline) deletedTask;
                return Ui.displayDeletedMessage(deletedDeadline, tasks.getSize());
            } else if (deletedTask.getEventType() == Type.TODO) {
                Todo deletedTodo = (Todo) deletedTask;
                return Ui.displayDeletedMessage(deletedTodo, tasks.getSize());
            }
            // code should be EVENT/DEADLINE/TODO
            assert false;
        } catch (IndexOutOfBoundsException err) {
            return Ui.INVALID_INDEX_ERROR;
        }

        assert false;
        return "";
    }

    /**
     * Find task with search term provided by user
     *
     * @param input Input text from user
     * @return Returns the successfully found tasks or invalid_index_error
     * message if given an invalid task index
     */
    public String runFindCommand(String input) {
        String searchTerm = input.split(" ")[1];
        if (searchTerm == "") {
            return Ui.INVALID_SEARCH_TERM;
        }

        TaskList foundTasks = tasks.findTask(searchTerm);
        if (foundTasks.getSize() < 1) {
            return Ui.SEARCH_TERM_NOT_FOUND;
        }

        return Ui.displayFoundTaskList(foundTasks);
    }

    /**
     * Runs Level 5, 6 & 7 version of the app, Exception handling,
     * Hard disk storage
     *
     * @throws DukeException for checked errors handled by duke.Duke app
     */
    public String run(String input) throws DukeException {
        Command command = Parser.parse(input);

        switch (command) {
        case LIST:
            return runListCommand();

        case MARK:
            return runMarkCommand(input);

        case UNMARK:
            return runUnmarkCommand(input);

        case DEADLINE:
            return runDeadlineCommand(input);

        case EVENT:
            return runEventCommand(input);

        case TODO:
            return runTodoCommand(input);

        case DELETE:
            return runDeleteCommand(input);

        case FIND:
            return runFindCommand(input);

        case BYE:
            try {
                storage.saveListOnDisk(tasks);
            } catch (IOException err) {
                System.out.println("Error occurred while trying to save list data to disk");
                err.printStackTrace();
            }
            return this.ui.displayExitMsg();

        default:
            assert false;
        }

        assert false;
        return "";
    }
}
