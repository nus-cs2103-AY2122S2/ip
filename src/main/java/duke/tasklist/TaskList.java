package duke.tasklist;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<Task>();
    }

    public String markTask(int index) throws IndexOutOfBoundsException {
        try {
            // Minus one as the index from the parameter is 1-based.
            Task task = taskList.get(index - 1);
            task.setDone();
            return task.toString();
        } catch (IndexOutOfBoundsException e) {
            // If the index is out of bound of the task list.
            throw new IndexOutOfBoundsException();
        }
    }

    public String unmarkTask(int index) throws IndexOutOfBoundsException {
        try {
            // Minus one as the index from the parameter is 1-based.
            Task task = taskList.get(index - 1);
            task.setUndone();
            return task.toString();
        } catch (IndexOutOfBoundsException e) {
            // If the index is out of bound of the task list.
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
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public String deleteFromIndex(int index) {
        // Minus one as the index from the parameter is 1-based.
        String deletedTask = taskList.get(index - 1).toString();
        taskList.remove(index - 1);
        return deletedTask;
    }

    public String updateDatabase() {
        String result = "";
        for (Task task: taskList) {
            result = result + task.updateIntoDatabase();
        }
        return result;
    }
}