import java.util.ArrayList;

public class TaskBank {
    private static ArrayList<Task> bank = new ArrayList<Task>();

    public TaskBank() {}

    public static void addTask(Task task) {
        bank.add(task);
    }

    public static void deleteTask(int index) {
        bank.remove(index);
    }

    public static ArrayList<Task> getBank() {
        return bank;
    }
}
