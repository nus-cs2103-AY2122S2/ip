package meep.task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the list of tasks.
 */
public class ListTask {
    private List<Task> tasks;

    /**
     * Constructor for class ListTask.
     */
    public ListTask() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Constructor for class ListTask.
     *
     * @param tasks list of tasks.
     */
    public ListTask(List<Task> tasks) {
        this.tasks = new ArrayList<Task>(tasks);
    }

    /**
     * Gets list.
     *
     * @return the list.
     */
    public List<Task> getList() {
        return this.tasks;
    }

    /**
     * Is the task list empty.
     *
     * @return the boolean.
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * Returns the size of task list.
     *
     * @return the size.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Adds a list of tasks to current tasklist.
     *
     * @param tasks the task list
     */
    public void addTaskList(List<Task> tasks) {
        this.tasks.addAll(tasks);
    }

    /**
     * Adds task to task list.
     *
     * @param task the task list.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes task according to the index.
     *
     * @param index the index of task to delete.
     * @throws IndexOutOfBoundsException If the index out of bounds.
     */
    public void deleteTask(int index) throws IndexOutOfBoundsException {
        this.tasks.remove(index - 1);
    }


    /**
     * Finds tasks according to given keyword.
     *
     * @param keyword the keyword user given to search.
     */
    public ListTask findTasks(String keyword) {
        ListTask result = new ListTask();
        for (int i = 0; i < tasks.size(); i++) {
            String input = tasks.get(i).toString().substring(7);
            if (input.contains(keyword)) {
                result.addTask(tasks.get(i));
            }
        }
        return result;
    }

    /**
     * Gets task according to the index.
     *
     * @param index the index of task to get.
     * @return the task
     * @throws IndexOutOfBoundsException If the index out of bounds.
     */
    public Task get(int index) throws IndexOutOfBoundsException {
        return this.tasks.get(index - 1);
    }

    /**
     * Generates task list string.
     *
     * @return the task list string.
     */
    public String generateTaskList() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(System.lineSeparator());
        int i = 1;
        for (Task task : tasks) {
            stringBuilder.append("     " + i + ".  "
                    + task.toString() + System.lineSeparator());
            i++;
        }
        return stringBuilder.toString();
    }

    /**
     * Generates task list with all the tasks are before given datetime.
     *
     * @param date the datetime.
     * @return the task list string.
     */
    public String generateTaskList(LocalDateTime date) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(System.lineSeparator());
        int i = 1;
        boolean isBeforeDate;

        for (Task task : tasks) {
            boolean isDeadline = (task instanceof Deadline);
            boolean isEvent = (task instanceof Event);
            if (isDeadline) {
                isBeforeDate = ((Deadline) task).getDate().isBefore(date);
                if (isBeforeDate) {
                    stringBuilder.append("     " + i + ".  " + task.toString() + System.lineSeparator());
                }
            } else if (isEvent) {
                isBeforeDate = ((Event) task).getDate().isBefore(date);
                if (isBeforeDate) {
                    stringBuilder.append("     " + i + ".  " + task.toString() + System.lineSeparator());
                }
            }
            i++;
        }
        return stringBuilder.toString();
    }


}
