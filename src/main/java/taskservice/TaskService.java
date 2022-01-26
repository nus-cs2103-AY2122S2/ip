package taskservice;

import tasks.Task;

public interface TaskService {
    Task[] get();
    void create(Task taskToCreate);
    void update(Task taskToUpdate);
    void delete(int id);
}
