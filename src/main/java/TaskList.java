import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

/**
 * Represents the a list of Tasks. <code>TaskList</code> object stores and handles users'
 * tasks.
 */
public class TaskList {
    /** Array of Activity in the list */
    ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param type the type of task
     * @param task description associated with the task
     * @return a response string for the user
     * @throws TaskListException if task type is unknown
     */
    public String add(TaskType type, String taskName) throws TaskListException{
        Task newTask;
        switch (type) {
        case Todo:
            newTask = new ToDoTask(taskName);
            tasks.add(newTask);
            break;
        default:
            throw new TaskListException("There is no such task!");
        }
        return "Got it! I have added a new " + type + " task:\n" + newTask
                + "\nYou have " + tasks.size() + " tasks in your list.";
    }

    public String add(TaskType type, String taskName, Date date) throws TaskListException{
        Task newTask;
        switch (type) {
        case Todo:
            newTask = new ToDoTask(taskName);
            tasks.add(newTask);
            break;
        case Event:
            newTask = new EventTask(taskName, date);
            tasks.add(newTask);
            break;
        case Deadline:
            newTask = new DeadlineTask(taskName, date);
            tasks.add(newTask);
            break;
        default:
            throw new TaskListException("There is no such task!");
        }
        return "Got it! I have added a new " + type + " task:\n" + newTask
                + "\nYou have " + tasks.size() + " tasks in your list.";
    }

    /**
     * Marks task in list as done.
     *
     * @param idx index of the task
     * @return the marked task
     * @throws TaskListException if index is out of bounds of task list size
     */
    public Task markDone(int idx) throws TaskListException {
        if (idx < 0 || idx >= tasks.size()) {
            throw new TaskListException("There is no task with index: " + (idx + 1));
        } else {
            Task ac = tasks.get(idx);
            ac.done();
            return ac;
        }
    }

    /**
     * Marks task in list as undone.
     *
     * @param idx index of the task
     * @return the unmarked task
     * @throws TaskListException if index is out of bounds of task list size
     */
    public Task markUndone(int idx) throws TaskListException {
        if (idx < 0 || idx >= tasks.size()) {
            throw new TaskListException("There is no task with index: " + (idx + 1));
        } else {
            Task ac = tasks.get(idx);
            ac.undone();
            return ac;
        }
    }

    /**
     * Deletes task in list by index.
     *
     * @param idx index of the task
     * @return response string to user
     * @throws TaskListException if index is out of bounds of task list size
     */
    public String delete(int idx) throws TaskListException {
        if (idx < 0 || idx >= tasks.size()) {
            throw new TaskListException("There is no task with index: " + (idx + 1));
        } else {
            Task ac = tasks.get(idx);
            tasks.remove(idx);
            return "I have deleted the following task:\n"
                    + ac.toString()
                    + "\nYou have " + tasks.size() + " tasks left.";
        }
    }

    /**
     * Returns a string of the activities added to the list,
     * in order of addition.
     *
     * @return the list of activities if list is not empty
     */
    @Override
    public String toString() {
        if (tasks.size() == 0) {
            return "Nothing is added yet.";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("You have the following upcoming tasks:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(".").append(tasks.get(i).toString()).append("\n");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
