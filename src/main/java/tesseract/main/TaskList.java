package tesseract.main;

import java.util.ArrayList;
import java.util.List;

import tesseract.task.Deadline;
import tesseract.task.Event;
import tesseract.task.Task;
import tesseract.task.Todo;


/**
 * Represent a list of task.
 *
 * @author Fan Jue
 * @version 0.1.0
 * @since 0.1.0
 */
public class TaskList {
    private static final int DESCRIPTION = 2;
    private static final int TASK_DATE = 3;
    private static final int MINIMUM_LENGTH = 3;
    private static final int MAXIMUM_LENGTH = 5;
    private static final String INDENT_ONE = "\t";
    // archived tasks' descriptions end with "A"
    private static final String ARCHIVE_SIGN = "A";
    private static final String EVENT = "E";
    private static final String DEADLINE = "D";
    private static final String TODO = "T";
    // A list of all tasks
    protected List<Task> taskList;
    // The number of all current tasks
    protected int numOfTasks;
    private List<Task> archiveList;

    /**
     * Create a TaskList to store and manipulate tasks
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
        this.archiveList = new ArrayList<Task>();
        this.numOfTasks = 0;
    }

    /**
     * Create a TaskList from information obtained from storage memory
     */
    public TaskList(List<String> storageMemory) {
        this.taskList = new ArrayList<Task>();
        this.archiveList = new ArrayList<Task>();
        for (String str : storageMemory) {
            String[] taskDescriptions = str.split("@", MAXIMUM_LENGTH); // format: D@1@do something
            Task taskNew;
            if (taskDescriptions.length < MINIMUM_LENGTH) {
                break;
            }
            switch (taskDescriptions[0]) {
            case EVENT: //event
                taskNew = new Event(taskDescriptions[DESCRIPTION], taskDescriptions[TASK_DATE]);
                break;
            case DEADLINE: //deadline
                taskNew = new Deadline(taskDescriptions[DESCRIPTION], taskDescriptions[TASK_DATE]);
                break;
            case TODO:
                taskNew = new Todo(taskDescriptions[DESCRIPTION]);
                break;
            default:
                // should not execute this line
                taskNew = new Todo(taskDescriptions[DESCRIPTION]);
            }
            if (Integer.parseInt(taskDescriptions[1]) == 1) {
                taskNew.markAsDone();
            }
            if (taskDescriptions[taskDescriptions.length - 1].equals(ARCHIVE_SIGN)) {
                this.archiveList.add(taskNew);
            } else {
                this.taskList.add(taskNew);
            }
        }
        this.numOfTasks = this.taskList.size();
        // check if all tasks from memory are imported
        assert((this.taskList.size() + archiveList.size()) == storageMemory.size());
    }

    /**
     * Create a temporary list of tasks, used for filtering.
     *
     * @param tasks      A list of filtered tasks.
     * @param numOfTasks The number of tasks after filtering.
     */
    public TaskList(List<Task> tasks, int numOfTasks) {
        this.taskList = tasks;
        this.numOfTasks = numOfTasks;
        this.archiveList = new ArrayList<Task>();
    }

    /**
     * Return a list of all tasks.
     *
     * @return A list of active tasks combined with archived tasks.
     */
    public TaskList allTasks() {
        List<Task> allTasks = new ArrayList<Task>(taskList);
        allTasks.addAll(archiveList);
        return new TaskList(allTasks, allTasks.size());
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
     * Return an archived task.
     *
     * @param index The index of the task to be archived.
     * @return An archived task
     */
    public Task archiveTask(int index) {
        Task toArchive = taskList.remove(index - 1);
        toArchive.archive();
        archiveList.add(toArchive);
        return toArchive;
    }

    /**
     * Return the task at a specific position in the list.
     *
     * @param index The position of the queried task.
     * @return Return the queried task.
     */
    public Task get(int index) {
        assert(index < this.numOfTasks);
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
        taskList.stream().filter(task -> task.isOn(date)).forEach(tasks::add);
        return new TaskList(tasks, tasks.size());
    }

    /**
     * Return a new TaskList of tasks filtered by the given keyword.
     *
     * @param keyword The keyword to filter all match task description.
     * @return A TaskList instance containing all tasks containing the keyword.
     */
    public TaskList filterByKeyword(String keyword) {
        List<Task> tasks = new ArrayList<Task>();
        taskList.stream().filter(task -> task.isMatch(keyword)).forEach(tasks::add);
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
        assert(index <= this.numOfTasks);
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
        assert(index <= this.numOfTasks);
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
        assert(index <= this.numOfTasks);
        Task undone = this.taskList.get(index - 1);
        undone.markAsUndone();
        return undone;
    }

    /**
     * Return a string representation of the list of all tasks to be printed out.
     *
     * @return A string representation of the TaskList.
     */
    @Override
    public String toString() {
        String msg = "";
        for (int i = 0; i < taskList.size(); i++) {
            msg += INDENT_ONE + (i + 1) + "." + taskList.get(i).toString() + "\n";
        }
        return msg;
    }
}
