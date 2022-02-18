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
     * @return feedback footer of new task added
     */
    public String processNewTask(Task curr) {
        tasks.add(curr);
        return ResponseFormatter.printFeedbackFooter("Got it. I've added this task:", curr, tasks);
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
        Task curr = tasks.get(number - 1);
        assert curr != null : "Invalid Task that is marked as Done!";

        curr.setDone();
        return ResponseFormatter.printFeedbackFooter("Nice! I've marked this task as done:", curr, tasks);
    }

    /**
     * @param inputArray String[] containing the original user input but split by words
     * @return response
     */
    public String handleUnMark(String[] inputArray) {
        int number = Integer.parseInt(inputArray[1]);
        Task curr = tasks.get(number - 1);
        assert curr != null : "Invalid Task that is marked as Done!";

        curr.setUndone();
        return ResponseFormatter.printFeedbackFooter("OK, I've marked this task as not done yet:", curr, tasks);
    }

    /**
     * @param inputArray    String[] containing the original user input but split by words
     * @param originalInput Original user input captured as a String
     * @return response
     */
    public String handleTodo(String[] inputArray, String originalInput) {
        try {
            if (inputArray.length <= 1) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
        } catch (DukeException e) {
            return ResponseFormatter.printDukeException(e, "Please try again:");
        }

        Task curr = new Todo(originalInput.substring(4));
        return processNewTask(curr);
    }

    /**
     * @param originalInput Original user input captured as a String
     * @return response
     */
    public String handleDeadline(String originalInput) {
        String metaInfo = originalInput.split("/by")[1];
        String strippedCommand = originalInput.substring(8);
        Task curr;
        try {
            curr = new Deadline(strippedCommand.split("/")[0], metaInfo);
        } catch (DateTimeParseException ex) {
            return ResponseFormatter.printMessage("Kindly input Date and Time in dd/mm/yyyy hhmm format!"
                    + "\nPlease try again:");
        }
        return processNewTask(curr);
    }

    /**
     * @param originalInput Original user input captured as a String
     * @return response
     */
    public String handleEvent(String originalInput) {
        String metaInfo = originalInput.split("/at")[1];
        String strippedCommand = originalInput.substring(5);
        Task curr;
        try {
            curr = new Event(strippedCommand.split("/")[0], metaInfo);
        } catch (DateTimeParseException ex) {
            return ResponseFormatter.printMessage("Kindly input Date and Time in dd/mm/yyyy hhmm format!"
                    + "\nPlease try again:");
        }
        return processNewTask(curr);
    }

    /**
     * @param inputArray String[] containing the original user input but split by words
     * @return response
     */
    public String handleDelete(String[] inputArray) {
        int number = Integer.parseInt(inputArray[1]);
        boolean isValidIndex = ((number) <= 0) || ((number) > tasks.size());

        try {
            if (isValidIndex) {
                throw new DukeException("Hey! That item does not exist!");
            }
        } catch (DukeException e) {
            return ResponseFormatter.printDukeException(e, "Please try again");
        }

        Task curr = tasks.get(number - 1);
        assert curr != null : "The task you are trying to delete is invalid!";

        tasks.remove(curr);

        return ResponseFormatter.printFeedbackFooter("Noted. I've removed this task:", curr, tasks);
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
}
