package tesseract.main;

import tesseract.main.TaskList;
import tesseract.main.TessUi;
import tesseract.main.Date;

import tesseract.task.Deadline;
import tesseract.task.Event;
import tesseract.task.Task;
import tesseract.task.Todo;

import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;

/**
 * Represent a list of task.
 * @author Fan Jue
 * @version 0.1.0
 * @since 0.1.0
 */
public class TaskList {
    protected static final String INDENT2 = "        ";

    /** A list of all tasks */
    protected List<Task> taskList;
    /** The number of all current tasks */
    protected int numOfTasks;

    /** Create a TaskList to store and manipulate tasks */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
        this.numOfTasks = 0;
    }

    /** Create a TaskList from information obtained from storage memory */
    public TaskList(List<String> storageMemory) {
        this.taskList = new ArrayList<Task>();
        for (int i = 0; i < storageMemory.size(); i++) {
            String[] tasks = storageMemory.get(i).split("@", 4); // format: D@1@do something
            Task taskNew;
            if (tasks.length < 3) {
                break;
            }
            switch (tasks[0]) {
            case "E": //event
                taskNew = new Event(tasks[2], tasks[3]);
                break;
            case "D": //deadline
                taskNew = new Deadline(tasks[2], tasks[3]);
                break;
            default: // todo
                taskNew = new Todo(tasks[2]);
                break;
            }
            if (Integer.parseInt(tasks[1]) == 1) {
                taskNew.markAsDone();
            }
            this.taskList.add(taskNew);
        }
        this.numOfTasks = this.taskList.size();
    }

    /**
     * Create a temporary list of tasks, used for filtering.
     *
     * @param tasks A list of filtered tasks.
     * @param numOfTasks The number of tasks after filtering.
     */
    public TaskList(List<Task> tasks, int numOfTasks) {
        this.taskList = tasks;
        this.numOfTasks = numOfTasks;
    }

    /**
     * Return the number of tasks in the list.
     *
     * @return A number representing the number of all current tasks.
     */
    public int size() {
        return numOfTasks;
    }

    /**
     * Return the task at a specific position in the list.
     *
     * @param index The position of the queried task.
     * @return Return the queried task.
     */
    public Task get(int index) {
        return this.taskList.get(index);
    }

    /**
     * Return a TaskList of tasks that happen on a specific date.
     *
     * @param date Date on which the task happens.
     * @return The list of tasks filtered.
     */
    public TaskList filterByDate(Date date) {
        List<Task> tasks = new ArrayList<Task>();
        for (Task task : taskList) {
            if (task.isOn(date)) {
                tasks.add(task);
            }
        }
        return new TaskList(tasks, tasks.size());
    }

    /**
     * Return a new TaskList of tasks filtered by the given keyword.
     *
     * @param keyword The keyword to filter all match task description.
     * @return
     */
    public TaskList filterByKeyword(String keyword) {
        List<Task> tasks = new ArrayList<Task>();
        for (Task task : taskList) {
            if (task.isMatch(keyword)) {
                tasks.add(task);
            }
        }
        return new TaskList(tasks, tasks.size());
    }

    /**
     * Add a task into the list of tasks.
     *
     * @param tNew A task to be added into the TaskList.
     */
    public void addTask(Task tNew) {
        this.numOfTasks += 1;
        this.taskList.add(tNew);
    }

    /**
     * Return the task that has been deleted.
     *
     * @param index The index of the task to be deleted.
     * @return The removed task to be printed out by Ui.
     */
    public Task deleteTask(int index) {
        this.numOfTasks -= 1;
        return this.taskList.remove(index - 1);
    }

    /**
     * Return the task that has been marked as done.
     *
     * @param index The index of the task to be marked.
     * @return The marked task to be printed out by Ui.
     */
    public Task markAsDone(int index) {
        Task done = this.taskList.get(index - 1);
        done.markAsDone();
        return done;
    }

    /**
     * Return the task that has been marked as undone.
     *
     * @param index The index of the task to be marked.
     * @return The marked task to be printed out by Ui.
     */
    public Task markAsUndone(int index) {
        Task undone = this.taskList.get(index - 1);
        undone.markAsUndone();
        return undone;
    }

    /**
     * Return a string representation of the list of all tasks to be printed out.
     * @return A string represention of the TaskList.
     */
    @Override
    public String toString() {
        String msg = "";
        for (int i = 0; i < taskList.size(); i++) {
            msg += INDENT2 + (i + 1) + "." + taskList.get(i).toString() + "\n";
        }
        return msg;
    }
}
