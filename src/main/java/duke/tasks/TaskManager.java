package duke.tasks;

import duke.exceptions.InvalidOperationException;
import duke.ui.UiManager;
import java.util.ArrayList;

public class TaskManager {
    private UiManager um;
    private ArrayList<Task> tasks;
    private String taskList;

    public TaskManager(UiManager manager) {
        this.um = manager;
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task t) {
        tasks.add(t);
        um.printAdd(t, this.size());
    }

    public void insertTask(Task t) {
        tasks.add(t);
    }

    public void mark(Integer num) throws InvalidOperationException {
        Task t = tasks.get(num);
        t.mark();
        um.printMark(t);
    }

    public void labelDone(Integer num) throws InvalidOperationException {
        Task t = tasks.get(num);
        t.mark();
    }

    public void unmark(Integer num) throws InvalidOperationException {
        Task t = tasks.get(num);
        t.unmark();
        um.printUnmark(t);
    }

    public void labelUndone(Integer num) throws InvalidOperationException {
        Task t = tasks.get(num);
        t.unmark();
    }

    public void delete(int num) {
        String taskDetails = tasks.get(num).toString();
        tasks.remove(num);
        um.printDelete(taskDetails, tasks.size());
    }

    public void remove(int num) {
        String taskDetails = tasks.get(num).toString();
        tasks.remove(num);
    }

    public String findTasks(String s) {
        String tasksFound = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            Task currTask = tasks.get(i);
            if (currTask.toString().contains(s)) {
                tasksFound += "\n" + currTask.toString() ;
            }
        }
        return tasksFound;
    }

    public int size() {
        return tasks.size();
    }

    @Override
    public String toString() {
        String s = um.getLine() + "\n";
        s += "Here's your list, Good Sir:\n";
        for (int i = 0; i < tasks.size(); i++) {
            s += i + 1 + ". "+ tasks.get(i).toString() + "\n";
        }
        s += um.getLine();
        this.taskList = s;
        return s;
    }
}
