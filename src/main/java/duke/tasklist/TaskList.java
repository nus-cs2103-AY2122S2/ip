package duke.tasklist;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public String markTask(int index) throws IndexOutOfBoundsException {
        try {
            Task task = taskList.get(index - 1);
            task.setDone();
            return task.toString();
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        }
    }

    public String unmarkTask(int index) throws IndexOutOfBoundsException {
        try {
            Task task = taskList.get(index - 1);
            task.setUndone();
            return task.toString();
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        }
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void printTasks() {
        for (int index = 0; index < this.taskList.size(); index++) {
            System.out.println(Integer.toString(index + 1) + ". " + taskList.get(index).toString());
        }
    }

    public void printNoTasks() {
        System.out.println("Now you have " + this.taskList.size() + " tasks in the list.");
    }

    public String deleteFromIndex(int index) {
        String deletedTask = this.taskList.get(index - 1).toString();
        this.taskList.remove(index - 1);
        return deletedTask;
    }

    public String updateDatabase() {
        String result = "";
        for (Task task: this.taskList) {
            result = result + task.updateIntoDatabase();
        }
        return result;
    }
}