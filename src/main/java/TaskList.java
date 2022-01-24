import java.util.List;
import java.util.ArrayList;

public class TaskList {

    List<Task> tasks;

    TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    List<Task> getTasks() {
        return this.tasks;
    }

    Integer size() {
        return this.tasks.size();
    }

    Task getByNumber(int number) {
        return this.tasks.get(number - 1);
    }

    Task deleteByNumber(int number) {
        return tasks.remove(number - 1);
    }

    void add(Task task) {
        this.tasks.add(task);
    }

}
