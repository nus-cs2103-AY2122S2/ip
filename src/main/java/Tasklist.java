import java.lang.reflect.Array;

public class Tasklist {

    private Task[] tasks;
    private int filled;

    public Tasklist() {
        this.tasks = new Task[100];
        this.filled = 0;
    }

    public void addTask(Task t) {
        this.filled++;
        for (int i = 0; i < this.tasks.length; i++) {
            if (this.tasks[i] == null) {
                this.tasks[i] = t;
                return;
            }
        }
    }

    public int getTotalTasks() {
        return this.filled;
    }

    public Task mark(int index) {
        Task t = (Task)Array.get(this.tasks, index - 1);
        t.mark();
        return t;
    }

    public Task unmark(int index) {
        Task t = (Task)Array.get(this.tasks, index - 1);
        t.unmark();
        return t;
    }

    @Override
    public String toString() {
        StringBuilder allTodos = new StringBuilder();
        for (int i = 0; i < this.tasks.length; i++) {
            if (this.tasks[i] != null) {
                allTodos.append(i + 1)
                        .append(". ")
                        .append(this.tasks[i].toString())
                        .append("\n");
            } else {
                if (allTodos.length() == 0) {
                    return "No entries found, start by adding one!\n";
                } else {
                    break;
                }
            }
        }
        return allTodos.toString();
    }
}
