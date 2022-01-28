import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<String> tasks) {
        this.tasks = new ArrayList<>();
        tasks.forEach(entry -> {
            //parse entries
            try {
                this.tasks.add(Parser.stringToTask(entry));
            } catch (DukeException e) {
                e.printStackTrace();
            }
        });
    }


    protected boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    protected Task getLast() {
        return this.tasks.get(this.tasks.size() - 1);
    }

    protected Task get(int index) {
        return this.tasks.get(index);
    }

    protected Task removeTask(int index) {
        return this.tasks.remove(index);
    }

    protected int size() {
        return this.tasks.size();
    }

    protected void addTask(Task task) throws DukeException {
        if (this.size() < 100) {
            this.tasks.add(task);
        } else {
            throw new DukeException("Number of tasks is >= 100, please remove some!");
        }
    }
}