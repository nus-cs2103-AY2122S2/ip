import java.util.ArrayList;
import java.util.List;

public class TaskList {
    // Main data structure to store Task objects
    private List<Task> list = new ArrayList<>();

    /**
     * Add a Task to existing TaskList.
     *
     * @param task Task to add
     */
    public void addTask(Task task) {
        this.list.add(task);
    }

    @Override
    public String toString() {
        String result = "";

        for (int task_idx = 0; task_idx < this.list.size(); task_idx++) {
            result += String.format(
                    "%d. %s",
                    task_idx + 1,
                    this.list.get(task_idx));

            if (task_idx != this.list.size() - 1) result += "\n";
        }

        return result;
    }
}
