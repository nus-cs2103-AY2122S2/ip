package duke.tasks;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public String getTaskContent(int index) {
        return taskList.get(index).getContent();
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public void addTask(Task taskToAdd) {
        taskList.add(taskToAdd);
    }

    public Task deleteTask(int indexTaskToDelete) {
        taskList.remove(indexTaskToDelete);
        displayTaskDelete(taskList.get(indexTaskToDelete));
        return taskList.get(indexTaskToDelete);
    }

    public Task markTask(int indexTaskToMark) {
        Task taskToMark = taskList.get(indexTaskToMark);
        taskToMark.markAsDone();
        return taskToMark;
    }

    public Task unmarkTask(int indexTaskToUnmark) {
        Task taskToUnmark = taskList.get(indexTaskToUnmark);
        taskToUnmark.markAsNotDone();
        return taskToUnmark;
    }

    public int size() {
        return taskList.size();
    }

    public String toSaveData() {
        StringBuilder dataToWrite = new StringBuilder();
        for (Task task : taskList) {
            dataToWrite.append(task.toSaveData());
        }

        return dataToWrite.toString();
    }

    @Override
    public String toString() {
        if (taskList.size() == 0) {
            return "Your current task list is empty";
        }
        StringBuilder numberedTaskList = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            numberedTaskList.append(String.format("%d. %s \n", i + 1, taskList.get(i)));
        }
        return numberedTaskList.toString().trim();
    }

    public void displayTaskDelete(Task deletedTask){
        System.out.println("As you wish. The following task has been removed");
        System.out.println(deletedTask.toString());
        System.out.printf("You now have %d item(s) in your list\n", taskList.size());
    }
}
