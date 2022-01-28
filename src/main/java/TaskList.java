import java.util.ArrayList;
import java.util.List;

/**
 * A class that represents the list of tasks.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Constructor to initialize an instance of TaskList class with
     * a new ArrayList of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks in the list
     */
    public int getNumOfTasks() {
        return tasks.size();
    }

    /**
     * Returns the task at the specified position in the list.
     *
     * @param index Index of the task in the list
     * @return The task at the specified position in the list
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Adds the ToDo task to the list.
     *
     * @param taskDescription Description of the ToDo task to be added
     * @return The ToDo task that was added
     */
    public Task addToDoTask(String taskDescription) {
        Task toDoTask = new ToDo(taskDescription);
        addTask(toDoTask);
        return toDoTask;
    }

    /**
     * Adds the Deadline task to the list.
     *
     * @param taskDescription Description of the Deadline task to be added
     * @param taskDateTime Date/time of the Deadline task to be added
     * @return The Deadline task that was added
     */
    public Task addDeadlineTask(String taskDescription, String taskDateTime) {
        Task deadlineTask = new Deadline(taskDescription, taskDateTime);
        addTask(deadlineTask);
        return deadlineTask;
    }

    /**
     * Adds the Event task to the list.
     *
     * @param taskDescription Description of the Event task to be added
     * @param taskDateTime Date/time of the Event task to be added
     * @return The Event task that was added
     */
    public Task addEventTask(String taskDescription, String taskDateTime) {
        Task eventTask = new Event(taskDescription, taskDateTime);
        addTask(eventTask);
        return eventTask;
    }

    /**
     * Adds the task to the list.
     *
     * @param task Task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Marks the task in the list as done based on the corresponding task
     * number in the list.
     *
     * @param taskNum Task number of the task to be marked as done
     * @return The task that was marked as done
     */
    public Task markDone(int taskNum) {
        Task task = getTask(taskNum - 1);
        task.setDone();
        return task;
    }

    /**
     * Marks the task in the list as not done yet based on the corresponding
     * task number in the list.
     *
     * @param taskNum Task number of the task to be marked as not done yet
     * @return The task that was marked as not done yet
     */
    public Task markNotDone(int taskNum) {
        Task task = getTask(taskNum - 1);
        task.setNotDone();
        return task;
    }

    /**
     * Deletes the task from the list based on the corresponding task number.
     *
     * @param taskNum Task number of the task to be deleted
     * @return The task that was deleted
     */
    public Task deleteTask(int taskNum) {
        Task task = getTask(taskNum - 1);
        tasks.remove(task);
        return task;
    }

    /**
     * Returns the string representation of the task list.
     *
     * @return The string representation of the task list
     */
    @Override
    public String toString() {
        StringBuilder listBuilder = new StringBuilder();

        for (int i = 0; i < getNumOfTasks(); i++) {
            String listItem = (i + 1) + "." + "\t" + getTask(i);

            if (i != 0) {
                listBuilder.append(System.lineSeparator());
            }

            listBuilder.append("\t").append(listItem);
        }

        return listBuilder.toString();
    }
}
