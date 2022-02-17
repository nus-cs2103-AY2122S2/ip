package duke.task;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import duke.exception.DukeException;
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
        this.tasks = null;
    }

    /**
     * @return List of tasks
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * @param curr Task object to be added
     */
    public void processNewTask(Task curr) {
        tasks.add(curr);
        ResponseFormatter.printFeedbackFooter("Got it. I've added this task:", curr, tasks);
    }

    public void handleBye() {
        ResponseFormatter.printBye();
    }

    public void handleList() {
        ResponseFormatter.printList(tasks);
    }

    /**
     * @param inputArray String[] containing the original user input but split by words
     */
    public void handleMark(String[] inputArray) {
        int number = Integer.parseInt(inputArray[1]);
        Task curr = tasks.get(number - 1);

        curr.setDone();
        ResponseFormatter.printFeedbackFooter("Nice! I've marked this task as done:", curr, tasks);
    }

    /**
     * @param inputArray String[] containing the original user input but split by words
     */
    public void handleUnMark(String[] inputArray) {
        int number = Integer.parseInt(inputArray[1]);
        Task curr = tasks.get(number - 1);

        curr.setUndone();
        ResponseFormatter.printFeedbackFooter("OK, I've marked this task as not done yet:", curr, tasks);
    }

    /**
     * @param inputArray    String[] containing the original user input but split by words
     * @param originalInput Original user input captured as a String
     */
    public void handleTodo(String[] inputArray, String originalInput) {
        try {
            if (inputArray.length <= 1) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
        } catch (DukeException e) {
            ResponseFormatter.printDukeException(e, "Please try again:");
            return;
        }

        Task curr = new Todo(originalInput.substring(4));
        processNewTask(curr);
    }

    /**
     * @param originalInput Original user input captured as a String
     */
    public void handleDeadline(String originalInput) {
        String metaInfo = originalInput.split("/by")[1];
        String strippedCommand = originalInput.substring(8);
        Task curr = null;
        try {
            curr = new Deadline(" " + strippedCommand.split("/")[0], metaInfo);
        } catch (DateTimeParseException ex) {
            ResponseFormatter.printMessage("Kindly input Date and Time in dd/mm/yyyy hhmm format!\nPlease try again:");
            return;
        }
        processNewTask(curr);
    }

    /**
     * @param originalInput Original user input captured as a String
     */
    public void handleEvent(String originalInput) {
        String metaInfo = originalInput.split("/at")[1];
        String strippedCommand = originalInput.substring(5);
        Task curr = null;
        try {
            curr = new Event(" " + strippedCommand.split("/")[0], metaInfo);
        } catch (DateTimeParseException ex) {
            ResponseFormatter.printMessage("Kindly input Date and Time in dd/mm/yyyy hhmm format!\nPlease try again:");
            return;
        }
        processNewTask(curr);
    }

    /**
     * @param inputArray String[] containing the original user input but split by words
     */
    public void handleDelete(String[] inputArray) {
        int number = Integer.parseInt(inputArray[1]);

        try {
            if (((number) <= 0) || ((number) > tasks.size())) {
                throw new DukeException("Hey! That item does not exist!");
            }
        } catch (DukeException e) {
            ResponseFormatter.printDukeException(e, "Please try again");
            return;
        }

        Task curr = tasks.get(number - 1);
        tasks.remove(curr);

        ResponseFormatter.printFeedbackFooter("Noted. I've removed this task:", curr, tasks);
    }

    /**
     * @param inputArray String[] containing the original user input but split by words
     */
    public void handleFind(String[] inputArray) {
        String key = inputArray[1];
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : this.tasks) {
            int index = task.getDescription().indexOf(key);
            if (index != -1) {
                foundTasks.add(task);
            }
        }

        if (foundTasks.isEmpty()) {
            ResponseFormatter.printMessage("No tasks found matching \"" + key + "\" :-(");
        } else {
            ResponseFormatter.printFoundList(foundTasks, key);
        }
    }
}
