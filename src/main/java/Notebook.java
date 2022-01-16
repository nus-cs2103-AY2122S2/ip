import java.util.ArrayList;

public class Notebook {
    private ArrayList<String> tasks;

    public Notebook() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(String task) {
        this.tasks.add(task);
    }

    public int size() {
        return tasks.size();
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 1; i < this.tasks.size(); i++) {
            str += (i + ") " + this.tasks.get(i) + "\n");
        }
        return str;
    }
}
