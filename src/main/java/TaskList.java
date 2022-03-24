import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList = new ArrayList<>(100);

    public ArrayList<Task> getAllTask() {
        return taskList;
    }

    public void addTaskToTaskList(Task taskToBeAdded) {
        taskList.add(taskToBeAdded);
        Ui.taskAddedMessage(taskList, taskToBeAdded);
    }

    public void deleteTaskFromTaskList(int index) {
        Task taskToBeDeleted = taskList.get(index);
        taskList.remove(taskToBeDeleted);
        Ui.taskDeletedMessage(taskList, taskToBeDeleted);
    }

    public void viewTaskList() {
        Ui.viewTaskListMessage(taskList);
    }
}
