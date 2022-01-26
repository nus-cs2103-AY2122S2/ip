package taskservice;

import tasks.Task;

public class FileSystemTaskService implements TaskService {

    @Override
    public Task[] get() {
        return new Task[0];
    }

    @Override
    public void create(Task taskToCreate) {

    }

    @Override
    public void update(Task taskToUpdate) {

    }

    @Override
    public void delete(int id) {

    }
}
