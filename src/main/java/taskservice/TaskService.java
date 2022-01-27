package taskservice;

import tasks.Task;
import taskservice.exceptions.TaskServiceException;

import java.util.Optional;

public interface TaskService {
    Optional<Task> getById(int id) throws TaskServiceException;
    Task[] get() throws TaskServiceException;
    int getNumberOfTasks();
    void create(Task taskToCreate) throws TaskServiceException;
    void update(int id, Task taskToUpdate) throws TaskServiceException;
    void delete(int id) throws TaskServiceException;
}
