package pikabot;

import pikabot.task.Task;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public int noOfTasks() {
        return this.taskList.size();
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    //add
    public void add(Task task) {
        taskList.add(task);
    }

    //delete
    public void delete(int taskNumber) {
        int taskIndex = taskNumber - 1;
        try {
            taskList.remove(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            Ui.printExceptionCustomisedMessage("Invalid task number!");
        }
    }

    //mark
    public void markTaskAsDone(int taskNumber) {
        int taskIndex = taskNumber - 1;
        try {
            Task currTask = taskList.get(taskIndex);
            currTask.markAsDone();
        } catch (IndexOutOfBoundsException e) {
            Ui.printExceptionCustomisedMessage("Invalid task number!");
        }
    }

    //unmark
    public void markTaskAsUndone(int taskNumber) {
        int taskIndex = taskNumber - 1;
        try {
            Task currTask = taskList.get(taskIndex);
            currTask.markAsUndone();
        } catch (IndexOutOfBoundsException e) {
            Ui.printExceptionCustomisedMessage("Invalid task number!");
        }
    }

    //find
    public ArrayList<Task> find(String word) {
        ArrayList<Task> matchedArr = new ArrayList<Task>();

        for (Task task : taskList) {
            String description = task.getDescription();
            if (description.contains(word)) {
                matchedArr.add(task);
            }
        }
        return matchedArr;
    }

}
