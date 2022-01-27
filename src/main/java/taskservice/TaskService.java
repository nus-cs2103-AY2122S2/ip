package taskservice;

import tasks.Task;

import java.util.Optional;

public interface TaskService {
    Optional<Task> getById(int id) throws TaskServiceException;
    Task[] get() throws TaskServiceException;
    void create(Task taskToCreate) throws TaskServiceException;
    void update(int id, Task taskToUpdate) throws TaskServiceException;
    void delete(int id) throws TaskServiceException;
}
