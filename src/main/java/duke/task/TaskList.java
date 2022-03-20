package duke.task;

import static duke.commons.core.Messages.MESSAGE_ADD_TASK;
import static duke.commons.core.Messages.MESSAGE_EMPTY_TASK_DESCRIPTION;
import static duke.commons.core.Messages.MESSAGE_INVALID_DATE;
import static duke.commons.core.Messages.MESSAGE_INVALID_INDEX;
import static duke.commons.core.Messages.MESSAGE_MARK_TASK_DONE;
import static duke.commons.core.Messages.MESSAGE_MARK_TASK_UNDONE;
import static duke.commons.core.Messages.MESSAGE_REMOVE_TASK_DONE;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import duke.exception.DukeException;
import duke.util.Parser;
import duke.util.ResponseFormatter;

/**
 * Handles all operations related to the List of Tasks
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructor
     *
     * @param tasks List of tasks to be initialized
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructor with empty List
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * @return List of tasks
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * @param curr Task object to be added
     * @return feedback footer of new task added
     */
    public String processNewTask(Task curr) {
        tasks.add(curr);
        return ResponseFormatter.printFeedbackFooter(MESSAGE_ADD_TASK, curr, tasks);
    }

    public String handleBye() {
        return ResponseFormatter.printBye();
    }

    public String handleList() {
        return ResponseFormatter.printList(tasks);
    }

    /**
     * @param inputArray String[] containing the original user input but split by words
     * @return response
     */
    public String handleMark(String[] inputArray) {
        int number = Integer.parseInt(inputArray[1]);
        try {
            if (isValidIndex(number)) {
                throw new DukeException(MESSAGE_INVALID_INDEX);
            }
        } catch (DukeException e) {
            return ResponseFormatter.printDukeException(e, "Please try again:");
        }

        Task curr = tasks.get(number - 1);
        assert curr != null : "Invalid Task that is marked as Done!";

        curr.setDone();
        return ResponseFormatter.printFeedbackFooter(MESSAGE_MARK_TASK_DONE, curr, tasks);
    }

    /**
     * @param inputArray String[] containing the original user input but split by words
     * @return response
     */
    public String handleUnMark(String[] inputArray) {
        int number = Integer.parseInt(inputArray[1]);
        try {
            if (isValidIndex(number)) {
                throw new DukeException(MESSAGE_INVALID_INDEX);
            }
        } catch (DukeException e) {
            return ResponseFormatter.printDukeException(e, "Please try again:");
        }
        Task curr = tasks.get(number - 1);
        assert curr != null : "Invalid Task that is marked as Done!";

        curr.setUndone();
        return ResponseFormatter.printFeedbackFooter(MESSAGE_MARK_TASK_UNDONE, curr, tasks);
    }

    /**
     * @param inputArray    String[] containing the original user input but split by words
     * @param originalInput Original user input captured as a String
     * @return response
     */
    public String handleTodo(String[] inputArray, String originalInput) {
        try {
            if (inputArray.length <= 1) {
                throw new DukeException(String.format(MESSAGE_EMPTY_TASK_DESCRIPTION, Todo.TASK_NAME));
            }
        } catch (DukeException e) {
            return ResponseFormatter.printDukeException(e, "Please try again:");
        }

        Task curr = new Todo(originalInput.substring(5));
        return processNewTask(curr);
    }

    /**
     * @param originalInput Original user input captured as a String
     * @return response
     */
    public String handleDeadline(String originalInput) {
        Parser parser = new Parser(originalInput);
        try {
            if (!parser.isValidDeadLineCommand() || !parser.isValidDescription(parser.getDeadlineInfo()[0])) {
                throw new DukeException(String.format(MESSAGE_EMPTY_TASK_DESCRIPTION, Deadline.TASK_NAME));
            }
        } catch (DukeException e) {
            return ResponseFormatter.printDukeException(e, "Please try again:");
        }

        Task curr;
        String[] deadlineInfo = parser.getDeadlineInfo();
        try {
            curr = new Deadline(deadlineInfo[0], deadlineInfo[1]);
        } catch (DateTimeParseException ex) {
            return ResponseFormatter.printMessage(MESSAGE_INVALID_DATE);
        }
        return processNewTask(curr);
    }

    /**
     * @param originalInput Original user input captured as a String
     * @return response
     */
    public String handleEvent(String originalInput) {
        Parser parser = new Parser(originalInput);
        try {
            if (!parser.isValidEventCommand() || !parser.isValidDescription(parser.getEventInfo()[0])) {
                throw new DukeException(String.format(MESSAGE_EMPTY_TASK_DESCRIPTION, Event.TASK_NAME));
            }
        } catch (DukeException e) {
            return ResponseFormatter.printDukeException(e, "Please try again:");
        }

        Task curr;
        String[] eventInfo = parser.getEventInfo();
        try {
            curr = new Event(eventInfo[0], eventInfo[1]);
        } catch (DateTimeParseException ex) {
            return ResponseFormatter.printMessage(MESSAGE_INVALID_DATE);
        }
        return processNewTask(curr);
    }

    /**
     * @param inputArray String[] containing the original user input but split by words
     * @return response
     */
    public String handleDelete(String[] inputArray) {
        int number = Integer.parseInt(inputArray[1]);
        try {
            if (isValidIndex(number)) {
                throw new DukeException(MESSAGE_INVALID_INDEX);
            }
        } catch (DukeException e) {
            return ResponseFormatter.printDukeException(e, "Please try again:");
        }

        Task curr = tasks.get(number - 1);
        assert curr != null : "The task you are trying to delete is invalid!";

        tasks.remove(curr);

        return ResponseFormatter.printFeedbackFooter(MESSAGE_REMOVE_TASK_DONE, curr, tasks);
    }

    /**
     * @param inputArray String[] containing the original user input but split by words
     * @return search results
     */
    public String handleFind(String[] inputArray) {
        String key = inputArray[1];
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : this.tasks) {
            int index = task.getDescription().indexOf(key);
            if (index != -1) {
                foundTasks.add(task);
            }
        }

        if (foundTasks.isEmpty()) {
            return ResponseFormatter.printMessage("No tasks found matching \"" + key + "\" :-(");
        } else {
            return ResponseFormatter.printFoundList(foundTasks, key);
        }
    }

    /**
     * Function to check if the supplied key is within the bounds of no. of tasks in the tasklist
     * @param key supplied index key to test
     */
    private boolean isValidIndex(int key) {
        return (key < 1) || (key > tasks.size());
    }
}
