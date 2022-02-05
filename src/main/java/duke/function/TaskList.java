package duke.function;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.task.Task;

/**
 * Represents a collection of Tasks.
 */
public class TaskList {
    /**
     * A list of tasks.
     */
    private List<Task> tasks;

    /**
     * Initializes the TaskList with an empty ArrayList of Tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Initializes the TaskList with the given List of Tasks.
     *
     * @param tasks tasks to be inserted into the TaskList.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Returns the number of tasks.
     *
     * @return The number of tasks.
     */
    public Integer getSize() {
        return this.tasks.size();
    }

    /**
     * Prints tasks out with their index (1-based).
     *
     * @param ui
     */
    public void printTasks(Ui ui) {
        if (tasks.size() == 0) {
            System.out.println("You currently do not have any tasks *quack*, please add some more");
        } else {
            ui.print("These are your tasks *quack*:");
            for (int i = 1; i <= this.getSize(); i++) {
                Task task = this.getByNumber(i);
                ui.print(String.format("%d. %s", i, task.toString()));
            }
        }
    }

    /**
     * Returns string of tasks with their index (1-based).
     *
     * @param ui
     */
    public String toString(Ui ui) {
        if (tasks.size() == 0) {
            return ui.printReturn("You currently do not have any tasks *quack*, please add some more");
        } else {
            String output = "";
            output += ui.printReturn("These are your tasks:");
            for (int i = 1; i <= this.getSize(); i++) {
                Task task = this.getByNumber(i);
                output += ui.printReturn(String.format("%d. %s", i, task.toString()));
            }
            return output;
        }
    }

    /**
     * Returns string of tasks with their index and tags (1-based).
     *
     * @param ui
     */
    public String toStringTags(Ui ui) {
        if (tasks.size() == 0) {
            return ui.printReturn("You currently do not have any tasks *quack*, please add some more");
        } else {
            String output = "";
            output += ui.printReturn("These are your tasks:");
            for (int i = 1; i <= this.getSize(); i++) {
                Task task = this.getByNumber(i);
                output += ui.printReturn(String.format("%d. %s (%s)", i, task.toString(), task.returnTags()));
            }
            return output;
        }
    }

    /**
     * Returns a new TaskList after filtering tasks with provided keyword.
     *
     * @param keyword Provided keyword.
     * @return
     */
    public TaskList filterByKeyword(String keyword) {
        TaskList newTaskList = new TaskList();
        for (Task task : this.getTasks()) {
            boolean nameContains = task
                    .getName()
                    .toLowerCase()
                    .contains(keyword.toLowerCase());
            boolean tagContains = task
                    .getTags()
                    .stream()
                    .map((tag) -> tag.toLowerCase())
                    .collect(Collectors.toList())
                    .contains(keyword.toLowerCase());

            if (nameContains || tagContains) {
                newTaskList.add(task);
            }
        }
        return newTaskList;
    }

    /**
     * Returns the task according to its index (1-based).
     *
     * @param number The index (1-based) of the task.
     * @return The task retrieved from its index (1-based).
     */
    public Task getByNumber(int number) {
        return this.tasks.get(number - 1);
    }

    /**
     * Deletes the task according to its index (1-based).
     *
     * @param number The index (1-based) of the task.
     * @return The task deleted from its index (1-based).
     */
    public Task deleteByNumber(int number) {
        return tasks.remove(number - 1);
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

}
