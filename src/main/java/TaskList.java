import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> ls = new ArrayList<>();

    public void add(Task task) {
        ls.add(task);
        System.out.println("Got it. I've added this task: ");
        System.out.println(task);
        System.out.println("Now you have " + this.numOfTasks() + " tasks in the list.");
    }

    public Task get(int item) {
        return ls.get(item - 1);
    }

    public int numOfTasks() {
        return ls.size();
    }

    public void delete(int index) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println(ls.get(index - 1));
        ls.remove(index - 1);
        System.out.println("Now you have " + this.numOfTasks() + " tasks in the list.");
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
