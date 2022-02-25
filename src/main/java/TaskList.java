import java.util.ArrayList;

public class TaskList<T extends Task> {
    private ArrayList<T> tasks;

    @SuppressWarnings("unchecked")
    public TaskList(ArrayList<T> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(T task) {
        this.tasks.add(task);
    }

    public int size() {
        return this.tasks.size();
    }

    public T get(int i) {
        return this.tasks.get(i-1);
    }

    public T remove(int i) {
        return this.tasks.remove(i-1);
    }

    public void markDone(int i, boolean done) {
        this.tasks.get(i-1).markDone(done);
    }

    @Override
    public String toString() {
        String result = "";
        for (int i=0; i<tasks.size(); i++) {
            result += (i+1) + ". " + tasks.get(i) + "\n";
        }
        return result;
    }
}