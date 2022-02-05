package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * contains task list
 */
public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * method to remove task in the list
     * @param index task to be removed
     * @return a list of task
     * @throws ExceptionHandler
     */
    public Task removeTask(int index) throws ExceptionHandler {
        if (index < 0 || index >= taskList.size()) {
            throw new ExceptionHandler("Invalid index");
        }
        return taskList.remove(index - 1);
    }

    public List<Task> getListOfTasks() {
        return taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public int getSize() {
        return taskList.size();
    }
}
