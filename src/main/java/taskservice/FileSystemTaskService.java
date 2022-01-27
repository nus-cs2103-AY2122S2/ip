package taskservice;

import tasks.Task;

import java.util.Optional;

public class FileSystemTaskService implements TaskService {
    @Override
    public Optional<Task> getById(int id) throws TaskServiceException {
        return Optional.empty();
    }

    @Override
    public Task[] get() throws TaskServiceException {
        return new Task[0];
    }

    @Override
    public void create(Task taskToCreate) throws TaskServiceException {

    }

    @Override
    public void update(int id, Task taskToUpdate) throws TaskServiceException {

    }

    @Override
    public void delete(int id) throws TaskServiceException {

    }
}
