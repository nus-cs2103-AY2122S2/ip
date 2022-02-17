package tasklist;

import tasks.Task;

import java.util.Optional;

public interface TaskList {
    int size();
    Task[] find(String keyword) throws TaskListException;
    Optional<Task> getById(int id) throws TaskListException;
    Task[] get() throws TaskListException;
    void add(Task taskToCreate) throws TaskListException;
    void update(int id, Task taskToUpdate) throws TaskListException;
    void remove(int id) throws TaskListException;
}
