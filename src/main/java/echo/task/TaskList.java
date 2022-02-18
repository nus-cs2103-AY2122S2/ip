package echo.task;

import java.util.ArrayList;

import echo.utils.EchoException;


/**
 * This class encapsulates the list of tasks associated with Echo.
 */
public class TaskList {
    /** ArrayList of task. */
    private final ArrayList<Task> TASKS;

    /**
     * Constructor for TaskList class.
     */
    public TaskList() {
        this.TASKS = new ArrayList<>();
    }

    /**
     * Constructor for TaskList class.
     *
     * @param taskList TaskList.
     */
    public TaskList(TaskList taskList) {
        this.TASKS = taskList.TASKS;
    }

    /**
     * Adds task to task list.
     *
     * @param task Task.
     *
     * @throws EchoException If task already exist in TASKS.
     */
    public void add(Task task) throws EchoException {
        if (checkDuplicate(task)) {
            throw new EchoException(String.format("Duplicated task found: %s ", task.toString()));
        }
        this.TASKS.add(task);
    }

    /**
     * Deletes task.
     *
     * @param i Task index.
     */
    public void delete(int i) {
        this.TASKS.remove(i);
    }

    /**
     * Marks task.
     *
     * @param i Task index.
     */
    public void mark(int i) {
        this.TASKS.get(i).mark();
    }

    /**
     * Unmarks task.
     *
     * @param i Task index.
     */
    public void unmark(int i) {
        this.TASKS.get(i).unmark();
    }

    /**
     * Returns string representation of task for saving.
     *
     * @param i Task index.
     */
    public String saveTaskFormat(int i) {
        return this.TASKS.get(i).saveFormat();
    }

    /**
     * Returns size of TaskList.
     *
     * @return Size of TaskList.
     */
    public int size() {
        return TASKS.size();
    }

    /**
     * Returns string representation of task index and description.
     *
     * @param i Task index.
     *
     * @return String representing task status.
     */
    public String taskStatus(int i) {
        return String.format("%d. %s", i + 1, TASKS.get(i).toString());
    }

    /**
     * Checks if TaskList is empty.
     *
     * @return True if empty; Otherwise false.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Finds task containing the description.
     *
     * @param s Description to find.
     *
     * @return String representation of tasks.
     */
    public String find(String s) {
        StringBuilder tasksString = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            String desc = TASKS.get(i).DESCRIPTION;
            if (desc.contains(s)) {
                tasksString.append(taskStatus(i).concat("\n"));
            }
        }
        if (tasksString.length() != 0) {
            tasksString.setLength(tasksString.length() - 1);
        }
        return tasksString.toString();
    }

    /**
     * Checks is a duplicated task exist.
     *
     * @param newTask New task to be added.
     *
     * @return If task already exist in TASKS, returns true; Otherwise false.
     */
    private boolean checkDuplicate(Task newTask) {
        boolean hasDuplicate = false;
        for (Task task : TASKS) {
            if (newTask.equals(task)) {
                hasDuplicate = true;
                break;
            }
        }
        return hasDuplicate;
    }

    /**
     * Returns string representation of task list.
     *
     * @return String representation of task list.
     */
    @Override
    public String toString() {
        StringBuilder listString = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            listString.append(taskStatus(i).concat("\n"));
        }
        listString.setLength(listString.length() - 1);
        return listString.toString();
    }
}
