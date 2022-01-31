package duke.task;

import duke.command.CommandType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A class that represents the List of tasks and List of filtered tasks.
 */
public class TaskList {
    private final List<Task> tasks;
    private List<Task> filteredTasks;
    private CommandType filterCommandType;
    private String filterInfo;

    /**
     * Constructor to initialize an instance of TaskList class with List of
     * tasks as a new ArrayList, List of filteredTasks as a new ArrayList,
     * filterCommandType as null and filterInfo as null.
     */
    public TaskList() {
        tasks = new ArrayList<>();
        filteredTasks = new ArrayList<>();
        filterCommandType = null;
        filterInfo = null;
    }

    /**
     * Returns the number of tasks in the List of tasks.
     *
     * @return The number of tasks in the List of tasks
     */
    public int getNumOfTasks() {
        return tasks.size();
    }

    /**
     * Returns the number of tasks in the List of filtered tasks.
     *
     * @return The number of tasks in the List of filtered tasks
     */
    public int getNumOfFilteredTasks() {
        return filteredTasks.size();
    }

    /**
     * Returns the task at the specified position in the List of tasks.
     *
     * @param index Index of the task in the List of tasks
     * @return The task at the specified position in the List of tasks
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the task at the specified position in the List of filtered tasks.
     *
     * @param index Index of the task in the List of filtered tasks
     * @return The task at the specified position in the List of filtered tasks
     */
    public Task getFilteredTask(int index) {
        return filteredTasks.get(index);
    }

    /**
     * Checks if the task list has filtered tasks, i.e., List of filtered
     * tasks is not empty.
     *
     * @return Flag to indicate if the List of filteredTasks is not empty
     */
    public boolean hasFilter() {
        return !filteredTasks.isEmpty();
    }

    /**
     * Returns the filter command type.
     *
     * @return The filter command type
     */
    public CommandType getFilterCommandType() {
        return filterCommandType;
    }

    /**
     * Returns the filter information.
     *
     * @return The filter information
     */
    public String getFilterInfo() {
        return filterInfo;
    }

    /**
     * Resets the filtered tasks.
     *
     * This will initialise List of filteredTasks as a new ArrayList,
     * filterCommandType as null and filterInfo as null.
     */
    public void resetFilteredTasks() {
        filteredTasks = new ArrayList<>();
        filterCommandType = null;
        filterInfo = null;
    }

    /**
     * Sets the filtered tasks with tasks that occurs on the specified date.
     *
     * This will initialise List of filteredTasks with the tasks that
     * occurs on the specified date, filterCommandType as Print CommandType
     * and filterInfo as the specified date.
     *
     * @param dateStr Specified date
     */
    public void setFilteredTasksByDate(String dateStr) {
        filteredTasks = tasks.stream()
                .filter(task -> task.isOnDate(dateStr))
                .collect(Collectors.toList());

        filterCommandType = CommandType.PRINT;
        filterInfo = dateStr;
    }

    /**
     * Sets the filtered tasks with tasks that contains the keyword in the
     * description.
     *
     * This will initialise List of filteredTasks with the tasks that
     * contains the keyword in the description, filterCommandType as Find
     * CommandType and filterInfo as the keyword.
     *
     * @param keyword Keyword in the description
     */
    public void setFilteredTasksByKeyword(String keyword) {
        filteredTasks = tasks.stream()
                .filter(task -> task.hasKeyword(keyword))
                .collect(Collectors.toList());

        filterCommandType = CommandType.FIND;
        filterInfo = keyword;
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
     * Marks the task as done based on the corresponding task number
     * in the List of tasks or List of filtered tasks.
     *
     * @param taskNum Task number of the task to be marked as done
     * @return The task that was marked as done
     */
    public Task markDone(int taskNum) {
        Task task = !hasFilter() ? getTask(taskNum - 1) : getFilteredTask(taskNum - 1);
        task.setDone();
        return task;
    }

    /**
     * Marks the task as not done yet based on the corresponding task number
     * in the List of tasks or List of filtered tasks.
     *
     * @param taskNum Task number of the task to be marked as not done yet
     * @return The task that was marked as not done yet
     */
    public Task markNotDone(int taskNum) {
        Task task = !hasFilter() ? getTask(taskNum - 1) : getFilteredTask(taskNum - 1);
        task.setNotDone();
        return task;
    }

    /**
     * Deletes the task based on the corresponding task number
     * in the List of tasks or List of filtered tasks.
     *
     * @param taskNum Task number of the task to be deleted
     * @return The task that was deleted
     */
    public Task deleteTask(int taskNum) {
        Task task = !hasFilter() ? getTask(taskNum - 1) : getFilteredTask(taskNum - 1);
        tasks.remove(task);

        if (hasFilter()) {
            filteredTasks.remove(task);
        }

        return task;
    }

    /**
     * Returns the string representation of the List of tasks.
     *
     * @return The string representation of the List of tasks
     */
    public String listOfTasksString() {
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

    /**
     * Returns the string representation of the List of filtered tasks.
     *
     * @return The string representation of the List of filtered tasks
     */
    public String listOfFilteredTasksString() {
        StringBuilder listBuilder = new StringBuilder();

        for (int i = 0; i < getNumOfFilteredTasks(); i++) {
            String listItem = (i + 1) + "." + "\t" + getFilteredTask(i);

            if (i != 0) {
                listBuilder.append(System.lineSeparator());
            }

            listBuilder.append("\t").append(listItem);
        }

        return listBuilder.toString();
    }

    /**
     * Returns the string representation of the task list.
     *
     * @return The string representation of the List of tasks if there
     * is no filter and the List of filtered tasks if there is a filter
     */
    @Override
    public String toString() {
        return !hasFilter() ? listOfTasksString() : listOfFilteredTasksString();
    }
}
