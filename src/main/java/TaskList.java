import java.util.ArrayList;
import java.util.List;

public class TaskList {
    protected List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> list) {
        this.tasks = list;
    }

    public Task getTaskAtIndex(int index) throws DukeException {
        try {
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Pardon me, but the provided index does not exist in your list.");
        }
    }

    public void addTask(Task t) {
        this.tasks.add(t);
    }

    public Task removeTask(int index) throws DukeException {
        this.getTaskAtIndex(index); //make sure task exists

        return this.tasks.remove(index);
    }

    public int getTasksCount() {
        return tasks.size();
    }

    public List<Task> getList() {
        return this.tasks;
    }

    public String toString() {
        StringBuilder listOfTasks = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            listOfTasks.append(i + 1).append(".").append(tasks.get(i)).append("\n");
        }

        return listOfTasks.toString();
    }
}
