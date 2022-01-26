package duke.task;

import java.util.ArrayList;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class TaskList {
    private final ArrayList<Task> tasks;
    /** list of handlers to be invoked when a change occurs. */
    private final ArrayList<Consumer<TaskList>> changeListeners;

    /**
     * Creates a new <code>TaskList</code>.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.changeListeners = new ArrayList<>();
    }

    /**
     * Adds the supplied {@link Task} object to the list.
     * @param task <code>Task</code> object to add to the list.
     * @return <code>Task</code> object added to the list.
     */
    public Task addTask(Task task) {
        this.tasks.add(task);
        this.notifyListeners();
        return task;
    }

    /**
     * Returns the number of tasks in the list.
     * @return Number of tasks in the list.
     */
    public int getTaskCount() {
        return this.tasks.size();
    }

    /**
     * Iterates through each {@link Task} in the list and applies <code>consumer</code> to the task.
     * @param consumer Function that takes the 0-based index of the task in the list and the task object.
     */
    public void doForEach(BiConsumer<Integer, ? super Task> consumer) {
        for (int i = 0; i < this.tasks.size(); i++) {
            consumer.accept(i, this.tasks.get(i));
        }
    }

    /**
     * Returns the {@link Task} at the given position in the list.
     * @param index Index of the task to be selected.
     * @return The <code>Task</code> at the given index, or null if index is invalid.
     */
    public Task getTaskByIndex(int index) {
        if (index >= this.tasks.size() || index < 0) {
            return null;
        }
        return this.tasks.get(index);
    }

    /**
     * Deletes the {@link Task} object at the given position in the list.
     * @param index Index of the task to be deleted.
     * @return The <code>Task</code> deleted, or null if nothing was deleted.
     */
    public Task deleteTask(int index) {
        if (index >= this.tasks.size() || index < 0) {
            return null;
        }
        final Task deletedTask = this.tasks.remove(index);
        this.notifyListeners();
        return deletedTask;
    }

    /**
     * Changes the completion status of the supplied {@link Task} to a new state.
     * @param task <code>Task</code> to changed.
     * @param isDone New completion status of the task.
     * @return The <code>Task</code> supplied as the argument.
     */
    public Task markTask(Task task, boolean isDone) {
        if (task.isDone() != isDone) {
            task.setDone(isDone);
            this.notifyListeners();
        }
        return task;
    }

    /**
     * Registers a new on-change observer that will be invoked when a change is made to the task list or any
     * tasks in the list.
     * @param listener Change handler to add.
     */
    public void registerListener(Consumer<TaskList> listener) {
        this.changeListeners.add(listener);
    }

    /**
     * Removes a previously registered on-change observer from the list of observers.
     * @param listener Change handler to remove.
     */
    public void removeListener(Consumer<TaskList> listener) {
        this.changeListeners.remove(listener);
    }

    /**
     * Invokes all the on-change observers currently registered to the task list.
     */
    private void notifyListeners() {
        this.changeListeners.forEach(handler -> {
            handler.accept(this);
        });
    }
}
