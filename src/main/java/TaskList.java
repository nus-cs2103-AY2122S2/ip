import java.io.*;
import java.util.*;

public class TaskList {
    private static Ui ui;
    private final DukeException dukeException;

    public ArrayList<Task> taskList;

    // This constructor is when there is no previous task / unable to load previous tasks.
    public TaskList() {
        taskList = new ArrayList<>();
        dukeException = new DukeException();
        ui = new Ui();
    }

    // This constructor is when you're able to load previous tasks / previous task exists.
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
        dukeException = new DukeException();
        ui = new Ui();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public int getSize() {
        return taskList.size();
    }

    public void addTask(Task task) {
        taskList.add(task);
        ui.addTask(task);
        ui.displayTaskAmount(taskList);
    }

    public void mark(int index) {
        try {
            Task selectedTask = taskList.get(index-1);
            selectedTask.setDone();
            ui.markTask(selectedTask);
        } catch (Exception e) {
            System.out.println("Task is invalid, please select a valid task number.");
        }
    }

    public void unmark(int index) {
        try {
            Task selectedTask = taskList.get(index-1);
            selectedTask.setUndone();
            ui.markTask(selectedTask);
        } catch (Exception e) {
            System.out.println("Task is invalid, please select a valid task number.");
        }
    }

    public void delete(int index) {
        try {
            Task removedTask = taskList.remove(index - 1);
            ui.deleteTask(removedTask);
            ui.displayTaskAmount(taskList);
        } catch (NumberFormatException noTaskNumber) {
            dukeException.noTaskNumber();
        } catch (IndexOutOfBoundsException invalidTaskNumber) {
            dukeException.invalidTaskNumber();
        }
    }
}