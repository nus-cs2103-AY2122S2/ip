package duke.task;

import java.util.ArrayList;
import java.util.Comparator;
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
     * @return The marked task in string.
     * @throws DukeException If the task id is out of the index bound,
     * it throws a DukeException.
     */
    public String markTask(int taskId, boolean isDone) throws DukeException {
        assert taskId > 0 : "Task id should be greater than 0";
        try {
            Task task = tasks.get(taskId);
            if (isDone) {
                task.markAsDone();
            } else {
                task.markAsNotDone();
            }
            return task.toString();
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
     * Filters task list based on the task type.
     *
     * @param taskType The task type to be filtered.
     * @return A filtered and sortable task list based on the supplied task type.
     */
    public TaskList filterTasks(TaskType taskType) throws DukeException {
        ArrayList<Task> filteredTask = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.hasSameTaskType(taskType)) {
                // Only sortable task will make it through this block
                filteredTask.add(task);
            }
        }
        return new TaskList(filteredTask);
    }

    /**
     * Filters sortable task list based on the task type.
     *
     * @param taskType The sortable task type to be filtered.
     * @return A filtered and sortable task list based on the supplied task type.
     */
    public ArrayList<? extends Sortable> filterSortableTasks(TaskType taskType) throws DukeException {
        ArrayList<Sortable> filteredTask = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.hasSameTaskType(taskType)) {
                // Only sortable task will make it through this block
                filteredTask.add((Sortable) task);
            }
        }
        return filteredTask;
    }

    /**
     * Deletes a task object from the task list based on the id supplied.
     *
     * @param taskId The id of the targeted task.
     * @return The string description of the task.
     * @throws DukeException If the task id is out of the task list index range,
     * it throws a DukeException.
     */
    public String deleteTask(int taskId) throws DukeException {
        try {
            assert taskId > 0 : "Task id should be greater than 0";
            Task task = tasks.get(taskId);
            tasks.remove(taskId);
            return task.toString();
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

    /**
     * Filters task list based on the task type.
     *
     * @param taskType The class type in the task list
     * @return A filtered task list based on the supplied task type.
     */
    public TaskList sortTasks(TaskType taskType) throws DukeException {
        ArrayList<? extends Sortable> filteredTasks = this.filterSortableTasks(taskType);
        if (taskType == TaskType.DEADLINE) {
            @SuppressWarnings("unchecked")
            ArrayList<Deadline> deadlineTasks = (ArrayList<Deadline>) filteredTasks;
            return new TaskList(deadlineTasks.stream()
                .sorted(Comparator.comparing(Deadline :: getLocalDateTime))
                .collect(Collectors.toCollection(ArrayList::new)));
        }

        if (taskType == TaskType.EVENT) {
            @SuppressWarnings("unchecked")
            ArrayList<Event> eventTasks = (ArrayList<Event>) filteredTasks;

            return new TaskList(eventTasks.stream()
                .sorted(Comparator.comparing(Event :: getLocalDateTime))
                .collect(Collectors.toCollection(ArrayList::new)));
        }
        return new TaskList();
    }

    public int getSize() {
        return tasks.size();
    }
}
