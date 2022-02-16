package duke.tasks;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
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

    public void displayTaskList() {
        if (taskList.isEmpty()) {
            System.out.println("Your current task list is empty");
            return;
        }
        int taskCounter = 1;
        System.out.println("These are the current tasks in your list:");
        for(Task task:taskList) {
            System.out.printf("%d. %s \n", taskCounter, task.toString());
            taskCounter++;
        }
    }

    public void displayTaskAdd(Task addedTask){
        System.out.println("This task has been added as requested:");
        System.out.println(addedTask.toString());
        System.out.printf("You now have %d item(s) in your list\n", taskList.size());
    }

    public void displayTaskDelete(Task deletedTask){
        System.out.println("As you wish. The following task has been removed");
        System.out.println(deletedTask.toString());
        System.out.printf("You now have %d item(s) in your list\n", taskList.size());
    }
}
