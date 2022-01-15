import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> ls = new ArrayList<>();

    public void add(Task task) {
        ls.add(task);
    }

    public Task get(int item) {
        return ls.get(item - 1);
    }

    public int numOfTasks() {
        return ls.size();
    }

    @Override
    public String toString() {
        int counter = 1;
        String output = "Here are the tasks in your list:\n";
        for (Task task : ls) {
            output += counter + "." + task + "\n";
            counter += 1;
        }
        return output.substring(0,output.length() - 1);
    }
}
