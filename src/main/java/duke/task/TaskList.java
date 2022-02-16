package duke.task;

import java.util.ArrayList;
import java.util.stream.Collectors;

import duke.DukeException;
import duke.common.TaskType;

/**
 * A class that represents a task list.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Marks a task in the task list as done or undone.
     *
     * @param taskId The id of the targeted task.
     * @param isDone The mark status of the targeted task.
     * @throws DukeException If the task id is out of the index bound,
     * it throws a DukeException.
     */
    public void markTask(int taskId, boolean isDone) throws DukeException {
        assert taskId > 0 : "Task id should be greater than 0";
        try {
            if (isDone) {
                tasks.get(taskId).markAsDone();
            } else {
                tasks.get(taskId).markAsNotDone();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Adds task into task list.
     *
     * @param task The given task.
     */
    public void addTask(Task task) {
        assert task != null : "Cannot add empty task to task list";
        tasks.add(task);
    }

    /**
     * Filters task list based on the supplied keywords.
     *
     * @param keyword The keywords to search for in the task list
     * @return A filtered task list based on the supplied keywords.
     */
    public TaskList filterTasks(String keyword) {
        return new TaskList(this.tasks.stream().filter(task -> task.hasKeywords(keyword))
                .collect(Collectors.toCollection(ArrayList::new)));
    }

    /**
     * Deletes a task object from the task list based on the id supplied.
     *
     * @param taskId The id of the targeted task.
     * @throws DukeException If the task id is out of the task list index range,
     * it throws a DukeException.
     */
    public void deleteTask(int taskId) throws DukeException {
        try {
            assert taskId > 0 : "Task id should be greater than 0";
            tasks.remove(taskId);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(e.getMessage());
        }
    }

    public Task getTask(int taskId) throws DukeException {
        try {
            assert taskId > 0 : "Task id should be greater than 0";
            return this.tasks.get(taskId);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(e.getMessage());
        }
    }

    public int getSize() {
        return tasks.size();
    }
}
