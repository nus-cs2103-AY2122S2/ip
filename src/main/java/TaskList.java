import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private Storage storage;

    public TaskList(Storage storage) {
        this.tasks = new ArrayList<>();
        this.storage = storage;
    }

    public void load() throws DukeException {
        try {
            storage.load(this);
        } catch (FileNotFoundException e) {
            throw new DukeException.DukeIOException();
        }
    }

    public void save() throws DukeException {
        try {
            this.storage.save(tasks);
        } catch (IOException e) {
            throw new DukeException.DukeIOException();
        }
    }

    public void addTask(String description, LocalDateTime time, Task.Type T) {
        switch (T) {
        case TODO:
            this.tasks.add(new Todo(description));
            break;
        case DEADLINE:
            this.tasks.add(new Deadline(description, time));
            break;
        case EVENT:
            this.tasks.add(new Event(description, time));
            break;
        }
    }

    public void addTask(String description, Task.Type T) {
        addTask(description, null, T);
    }

    public String getTaskDescription(int taskId) {
        return this.tasks.get(taskId - 1).toString();
    }

    public String markTask(int taskId) {
        this.tasks.get(taskId - 1).mark();
        return this.tasks.get(taskId - 1).toString();
    }

    public int size() {
        return this.tasks.size();
    }

    public String unmarkTask(int taskId) {
        this.tasks.get(taskId - 1).unmark();
        return this.tasks.get(taskId - 1).toString();
    }

    public void delete(int taskId) {
        this.tasks.remove(taskId - 1);
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 1; i <= this.tasks.size(); i++) {
            str += i + "." + this.tasks.get(i - 1) + "\n";
        }
        return str;
    }
}
