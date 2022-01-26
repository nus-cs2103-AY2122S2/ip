package duke.tasks;

import duke.exceptions.InvalidOperationException;
import duke.ui.UiManager;
import java.util.ArrayList;

public class TaskManager {
    private UiManager uiManager;
    private ArrayList<Task> tasks;
    private String taskList;

    public TaskManager(UiManager manager) {
        this.uiManager = manager;
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
        uiManager.printAdd(task, this.size());
    }

    public void insertTask(Task task) {
        tasks.add(task);
    }

    public void mark(Integer num) throws InvalidOperationException {
        Task task = tasks.get(num);
        task.mark();
        uiManager.printMark(task);
    }

    public void labelDone(Integer num) throws InvalidOperationException {
        Task task = tasks.get(num);
        task.mark();
    }

    public void unmark(Integer num) throws InvalidOperationException {
        Task task = tasks.get(num);
        task.unmark();
        uiManager.printUnmark(task);
    }

    public void labelUndone(Integer num) throws InvalidOperationException {
        Task task = tasks.get(num);
        task.unmark();
    }

    public void delete(int num) {
        String taskDetails = tasks.get(num).toString();
        tasks.remove(num);
        uiManager.printDelete(taskDetails, tasks.size());
    }

    public void remove(int num) {
        tasks.remove(num);
    }

    public int size() {
        return tasks.size();
    }

    @Override
    public String toString() {
        String string = uiManager.getLine() + "\n";
        string += "Here's your list, Good Sir:\n";
        for (int i = 0; i < tasks.size(); i++) {
            string += i + 1 + ". "+ tasks.get(i).toString() + "\n";
        }
        string += uiManager.getLine();
        this.taskList = string;
        return string;
    }
}
