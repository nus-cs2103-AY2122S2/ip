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

    private boolean isExit = false;

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

    public boolean getIsExit() {
        return this.isExit;
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
        String[] inputSplit = input.split(" ");

        boolean isValidInput = inputSplit.length == 2;
        assert isValidInput : "invalid input command!";

        int itemIndex = Integer.parseInt(inputSplit[1]);
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
        String[] inputSplit = input.split(" ");

        boolean isValidInput = inputSplit.length == 2;
        assert isValidInput : "invalid input command!";

        int itemIndex = Integer.parseInt(inputSplit[1]);
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
        String[] inputSplit = input.split(" ");

        boolean isValidInput = inputSplit.length == 2;
        assert isValidInput : "invalid input command!";

        int taskIndex = Integer.parseInt(inputSplit[1]) - 1;
        try {
            Task deletedTask = tasks.removeTaskIndex(taskIndex);

            boolean taskIsEvent = deletedTask.getEventType() == Type.EVENT;
            boolean taskIsDeadline = deletedTask.getEventType() == Type.DEADLINE;
            boolean taskIsTodo = deletedTask.getEventType() == Type.TODO;

            if (taskIsEvent) {
                Event deletedEvent = (Event) deletedTask;
                return Ui.displayDeletedMessage(deletedEvent, tasks.getSize());
            } else if (taskIsDeadline) {
                Deadline deletedDeadline = (Deadline) deletedTask;
                return Ui.displayDeletedMessage(deletedDeadline, tasks.getSize());
            } else if (taskIsTodo) {
                Todo deletedTodo = (Todo) deletedTask;
                return Ui.displayDeletedMessage(deletedTodo, tasks.getSize());
            }
            // code should be EVENT/DEADLINE/TODO
            assert false;

        } catch (IndexOutOfBoundsException err) {
            // if index provided by user is invalid
            return Ui.INVALID_INDEX_ERROR;
        }

        assert false;
        return "";
    }

    /**
     * Find task with search term provided by user
     *
     * @param input Input text from user
     * @return Returns the successfully found tasks or INVALID_SEARCH_TERM
     * OR SEARCH_TERM_NOT_FOUND message if given an invalid task index
     */
    public String runFindCommand(String input) {
        String[] inputSplit = input.split(" ");

        boolean isInvalidInput = inputSplit.length < 2;
        assert isInvalidInput : "invalid input command!";

        String searchTerm = inputSplit[1];
        boolean isEmptySearchTerm = searchTerm == "";

        if (isEmptySearchTerm) {
            return Ui.INVALID_SEARCH_TERM;
        }

        TaskList foundTasks = tasks.findTask(searchTerm);
        boolean isMatchingTaskNotFound = foundTasks.getSize() < 1;

        if (isMatchingTaskNotFound) {
            return Ui.SEARCH_TERM_NOT_FOUND;
        }

        return Ui.displayFoundTaskList(foundTasks);
    }

    /**
     * Tag specified task with tag term provided by user
     *
     * @param input Input text from user
     * @return Returns the successfully tagged task or INVALID_TAG_ERROR
     * message if given an invalid task index
     */
    public String runTagCommand(String input) {
        String[] inputSplit = input.split(" ");

        boolean isInvalidCommandFormat = inputSplit.length != 3;
        if (isInvalidCommandFormat) {
            return Ui.INVALID_TAG_TERM;
        }

        String tag = inputSplit[2];
        boolean isEmptyTag = tag == "";
        if (isEmptyTag) {
            return Ui.EMPTY_TAG_TERM;
        }

        int taskIndex = Integer.parseInt(inputSplit[1]) - 1;
        try {
            Task taggedTask = tasks.tagTask(taskIndex, tag);
            return Ui.displayTaggedMessage(taggedTask);

        } catch (IndexOutOfBoundsException err) {
            return Ui.INVALID_INDEX_ERROR;
        }
    }

    /**
     * Exit application and saves tasks locally
     *
     * @return Returns the exit message to user
     */
    public String runExitCommand() {
        try {
            storage.saveListOnDisk(tasks);
        } catch (IOException err) {
            err.printStackTrace();
        }
        this.isExit = true;
        return Ui.displayExitMsg();
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

        case TAG:
            return runTagCommand(input);

        case BYE:
            return runExitCommand();

        case ERROR:
            return Ui.displayInvalidCommandError();

        default:
            assert false;
        }

        assert false;
        return "";
    }
}
