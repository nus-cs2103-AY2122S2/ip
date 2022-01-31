package duke.tasklist;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * Returns a task list with all tasks that contains the keyword.
     *
     * @param keyword The keyword to be searched.
     * @return A new task list with the tasks containing the keyword.
     */
    public TaskList findTasksFromKeyword(String keyword) {
        TaskList filteredTaskList = new TaskList();

        // Gets all the tasks that contains the keyword.
        List<Task> filteredArrayList = taskList.stream()
                .filter(task -> task.getTaskName().contains(keyword))
                .collect(Collectors.toList());

        // Puts all the tasks in the filteredArrayList into the new Task List.
        for (Task task: filteredArrayList) {
            filteredTaskList.addTask(task);
        }

        return filteredTaskList;
    }
}