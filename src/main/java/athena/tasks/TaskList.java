package athena.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Encapsulates a task list that keeps track of multiple tasks and supports
 * operations on them.
 */
public class TaskList {
    private final ArrayList<Task> tasks;
    private boolean wasModified;

    /**
     * Constructs a new TaskList object.
     */
    public TaskList() {
        tasks = new ArrayList<>();
        wasModified = false;
    }

    /**
     * Reconstructs a TaskList object from the given save data.
     *
     * @param taskListSaveFormat Save data of the task list.
     */
    public TaskList(List<String> taskListSaveFormat) {
        this();
        assert taskListSaveFormat.size() > 0;

        // Create the appropriate task based on each line of the save file and add to taskList
        for (int i = 0; i < taskListSaveFormat.size(); i++) {
            // Extract parameters of tasks from the save format
            String[] taskParams = taskListSaveFormat.get(i).split("\\|");
            String taskIcon = taskParams[0];
            boolean isTaskDone = taskParams[1].equals("1");
            String taskName = taskParams[2];
            String taskDateTime = "";
            if (taskParams.length >= 4) {
                taskDateTime = taskParams[3];
            }

            // Add the task with corresponding parameters to the task list.
            switch (taskIcon) {
            case "T":
                tasks.add(new Todo(taskName));
                break;
            case "E":
                tasks.add(new Event(taskName, taskDateTime));
                break;
            case "D":
                tasks.add(new Deadline(taskName, taskDateTime));
                break;
            default:
                break;
            }
            if (isTaskDone) {
                setTaskAsDone(i + 1);
            }
        }
    }

    public void setNotModified() {
        wasModified = false;
    }

    public boolean wasModified() {
        return wasModified;
    }

    /**
     * Adds a new todo to the TaskList and returns the task number of the newly added yodo.
     *
     * @param name Name of the todo.
     * @return Task number of the newly added todo.
     */
    public int addTodo(String name) {
        tasks.add(new Todo(name));
        wasModified = true;
        return getNumberOfTasks();
    }

    /**
     * Adds a new event to the TaskList and returns the task number of the newly added event.
     *
     * @param name Name of the event.
     * @param eventDate Date and time of the event.
     * @return Task number of the newly added event.
     */
    public int addEvent(String name, LocalDateTime eventDate) {
        tasks.add(new Event(name, eventDate));
        wasModified = true;
        return getNumberOfTasks();
    }

    /**
     * Adds a new deadline to the TaskList and returns the task number of the newly added deadline.
     *
     * @param name Name of the deadline.
     * @param dueDate Date and time of the deadline.
     * @return Task number of the newly added deadline.
     */
    public int addDeadline(String name, LocalDateTime dueDate) {
        tasks.add(new Deadline(name, dueDate));
        wasModified = true;
        return getNumberOfTasks();
    }

    /**
     * Returns true if the given task number is valid for the current task list.
     *
     * @param taskNumber Task number to check validity for.
     * @return True if the given task number is valid for this task list.
     */
    public boolean isValidTaskNumber(int taskNumber) {
        return (taskNumber >= 1 && taskNumber <= getNumberOfTasks());
    }

    /**
     * Marks the task with the corresponding task number as done. Assumes task number
     * is valid.
     *
     * @param taskNumber Task number of the task to mark as done.
     */
    public void setTaskAsDone(int taskNumber) {
        assert isValidTaskNumber(taskNumber);
        Task task = tasks.get(taskNumber - 1);
        task.setDone();
        wasModified = true;
    }

    /**
     * Marks the task with the corresponding task number as not done.
     * Assumes task number is valid.
     *
     * @param taskNumber Task number of the task to mark as not done.
     */
    public void setTaskAsNotDone(int taskNumber) {
        assert isValidTaskNumber(taskNumber);
        Task task = tasks.get(taskNumber - 1);
        task.setNotDone();
        wasModified = true;
    }

    /**
     * Deletes the task corresponding to the given task number. Assumes
     * the task number given is valid.
     *
     * @param taskNumber Task number of the task to be deleted.
     */
    public void deleteTask(int taskNumber) {
        assert isValidTaskNumber(taskNumber);
        tasks.remove(taskNumber - 1);
        wasModified = true;
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int getNumberOfTasks() {
        return tasks.size();
    }

    /**
     * Gets the task with the given task number in string format. Assumes
     * a valid task number.
     *
     * @param taskNumber Task number of the task to be returned in string format.
     * @return Desired task in string format.
     */
    public String getTaskString(int taskNumber) {
        assert isValidTaskNumber(taskNumber);
        return tasks.get(taskNumber - 1).toString();
    }

    public List<Integer> getTaskNumbersContainingPhrase(String searchPhrase) {
        Predicate<Task> taskPredicate = (task) -> task.toString().contains(searchPhrase);
        return getTaskNumbersMeetingPredicate(taskPredicate);
    }

    public List<Integer> getTaskNumbersWithinTimeWindow(LocalDate startDate, LocalDate endDate) {
        Predicate<Task> taskPredicate = (task) -> task.isFallingBetweenInclusive(startDate, endDate);
        return getTaskNumbersMeetingPredicate(taskPredicate);
    }

    private List<Integer> getTaskNumbersMeetingPredicate(Predicate<Task> predicate) {
        ArrayList<Integer> taskNumbers = new ArrayList<>();
        for (int i = 1; i <= getNumberOfTasks(); i++) {
            Task task = tasks.get(i - 1);
            if (predicate.test(task)) {
                taskNumbers.add(i);
            }
        }
        return taskNumbers;
    }

    public ArrayList<String> getSaveFormat() {
        ArrayList<String> output = new ArrayList<>();
        for (Task task : tasks) {
            output.add(task.getSaveFormat());
        }
        return output;
    }

    @Override
    public String toString() {
        if (tasks.size() == 0) {
            return "Empty list!";
        }
        StringBuilder output = new StringBuilder();
        for (int i = 1; i <= tasks.size(); i++) {
            output.append(String.format("%d. %s\n", i, tasks.get(i - 1)));
        }
        return output.toString().strip();
    }
}
