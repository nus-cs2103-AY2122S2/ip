package tasklist;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import storage.Storage;
import tasklist.TaskListException.TaskNotFoundException;
import tasks.Task;

/**
 * Represents a set of operations that can be performed on
 * a set of tasks that is persisted to some storage medium.
 */
public class StorageTaskList implements TaskList {
    private final Storage storage;
    private List<Task> tasks;
    private boolean hasLoadedFromStorage = false;

    /**
     * Returns a StorageTaskList object that allows for the manipulation
     * of a set of tasks from a given storage.
     * @param storage a medium that contains a set of tasks.
     * @throws TaskListException If a failure occurs when attempting to
     * retrieve and cache the tasks from the given storage.
     */
    public StorageTaskList(Storage storage) throws TaskListException {
        this.storage = storage;
        this.tasks = new ArrayList<>();
        this.get();
    }

    /**
     * Returns the total number of tasks that is currently present in
     * the given storage.
     *
     * @return The total number of tasks in the storage.
     */
    @Override
    public int size() {
        return this.tasks.size();
    }

    /**
     * Finds all tasks in the given storage that contains the given keyword
     * in their description.
     *
     * @param keyword the criteria to find the tasks in the given storage by.
     * @return All Task objects in the given storage whose description contains the given keyword.
     * @throws TaskListException In no cases at present.
     */
    @Override
    public Task[] find(String keyword) throws TaskListException {
        return this.tasks
                .stream()
                .filter(task -> task.getDescription().contains(keyword))
                .map(Task::clone)
                .toArray(Task[]::new);
    }

    /**
     * Finds and returns a task as based on its identifier in the given
     * storage. If no task with the given identifier is found, the
     * result returned would encapsulate nothing. Otherwise, it would
     * encapsulate the Task object that is found.
     *
     * @param id the identifier of the Task object to return from the given storage.
     * @return A result that possibly contains the Task object with the given identifier.
     * @throws TaskListException In no cases at present.
     */
    @Override
    public Optional<Task> getById(int id) throws TaskListException {
        try {
            return Optional.of(this.tasks.get(id).clone());
        } catch (IndexOutOfBoundsException ex) {
            return Optional.empty();
        }
    }

    /**
     * Returns all the tasks from the given storage.
     *
     * @return All Task objects in the given storage.
     * @throws TaskListException If the retrieval of tasks from the given
     * storage fails.
     */
    @Override
    public Task[] get() throws TaskListException {
        if (!this.hasLoadedFromStorage) {
            try {
                this.tasks = this.storage.load();
                this.hasLoadedFromStorage = true;
            } catch (Exception ex) {
                throw new TaskListException(ex.getMessage());
            }
        }

        return this.tasks.stream().map(Task::clone).toArray(Task[]::new);
    }

    /**
     * Adds a new task to the given storage.
     *
     * @param taskToCreate the task to be added to the given storage.
     * @throws TaskListException If the saving of the new task to the given
     * storage fails.
     */
    @Override
    public void add(Task taskToCreate) throws TaskListException {
        this.executeAndSave(tasks -> tasks.add(taskToCreate.clone()));
    }

    /**
     * Replaces the task with a given identifier from the given storage with a new task.
     *
     * @param id the identifier of the task to replace in the given storage.
     * @param taskToUpdate the task that will replace the current task with a given identifier in the given storage.
     * @throws TaskListException If the replacement of an existing task with a new one in
     * the given storage fails.
     */
    @Override
    public void update(int id, Task taskToUpdate) throws TaskListException {
        this.executeAndSave(tasks -> tasks.set(id, taskToUpdate.clone()));
    }

    /**
     * Removes a task with a given identifier from the given storage.
     *
     * @param id the identifier of the task to be removed from the given storage.
     * @throws TaskListException If the removal of a task from the given storage fails.
     */
    @Override
    public void remove(int id) throws TaskListException {
        this.executeAndSave(tasks -> tasks.remove(id));
    }

    private void executeAndSave(Consumer<List<Task>> execute) throws TaskListException {
        try {
            execute.accept(this.tasks);
            this.storage.save(this.tasks);
        } catch (IndexOutOfBoundsException ex) {
            throw new TaskNotFoundException();
        } catch (Exception ex) {
            throw new TaskListException(ex.getMessage());
        }
    }
}
