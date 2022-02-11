package tasklist;

import tasklist.TaskListException.TaskNotFoundException;

import storage.Storage;

import tasks.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class StorageTaskList implements TaskList {
    private final Storage storage;
    private List<Task> tasks;
    private boolean hasLoadedFromStorage = false;

    public StorageTaskList(Storage storage) throws TaskListException {
        this.storage = storage;
        this.tasks = new ArrayList<>();
        this.get();
    }

    @Override
    public int size() {
        return this.tasks.size();
    }

    @Override
    public Optional<Task> getById(int id) throws TaskListException {
        try {
            return Optional.of(this.tasks.get(id).clone());
        } catch (IndexOutOfBoundsException ex) {
            return Optional.empty();
        }
    }

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

        return this.tasks.stream().map((task) -> task.clone()).toArray(Task[]::new);
    }

    @Override
    public void add(Task taskToCreate) throws TaskListException {
        this.executeAndSave(tasks -> tasks.add(taskToCreate.clone()));
    }

    @Override
    public void update(int id, Task taskToUpdate) throws TaskListException {
        this.executeAndSave(tasks -> tasks.set(id, taskToUpdate.clone()));
    }

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
