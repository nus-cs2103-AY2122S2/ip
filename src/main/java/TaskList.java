import java.util.ArrayList;
import java.util.Iterator;

public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public void add(Task t) {
        this.tasks.add(t);
    }
    public Task remove(int i) {
        return this.tasks.remove(i);
    }
    public Task mark(int i) {
        this.tasks.get(i).setDone(true);
        return tasks.get(i);
    }
    public Task unmark(int i) {
        this.tasks.get(i).setDone(false);
        return tasks.get(i);
    }
    public void printTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + tasks.get(i).toString());
        }
    }
    public int size() {
        return this.tasks.size();
    }
    public Iterable<? extends CharSequence> saveToFile() {
        Iterator<Task> it = this.tasks.iterator();
        return (Iterable<String>) () -> new Iterator<>() {
            @Override
            public boolean hasNext() {
                return it.hasNext();
            }
            @Override
            public String next() {
                return it.next().formatSave();
            }
        };
    }
}
