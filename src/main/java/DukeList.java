import java.util.ArrayList;

public class DukeList {
    private static final int CAPACITY = 100;
    private ArrayList<Task> list;

    /**
     * Constructor for DukeList
     */
    public DukeList() {
        this.list = new ArrayList<>(CAPACITY);
    }

    /**
     * Add a new item to the list.
     *
     * @param item String to be added
     */
    public String add(String item) {
        Task task = new Task(item);
        this.list.add(task);
        return String.format("added: %s", item);
    }

    /**
     * Mark the task as completed given a task index.
     *
     * @param idx
     * @return
     */
    public String markTask(int idx) {
        Task task = this.list.get(idx - 1);
        task.markAsCompleted();

        return "Nice! I've marked this task as done:\n"
                + String.format("\t%s", task);
    }

    /**
     * Mark the task as uncompleted given a task index.
     *
     * @param idx
     * @return
     */
    public String unmarkTask(int idx) {
        Task task = this.list.get(idx - 1);
        task.markAsUncompleted();

        return "OK, I've marked this task as not done yet:\n"
                + String.format("\t%s", task);
    }

    @Override
    public String toString() {
        int size = this.list.size();
        StringBuilder result;

        if (size == 0) {
            result = new StringBuilder("List is empty. Add items to the list.");
        } else {
            result = new StringBuilder();
            for (int i=0; i<size; i++) {
                result.append(String.format("%d. %s\n", i + 1, this.list.get(i)));
            }
        }

        return result.toString();
    }
}
