import java.util.ArrayList;

public class TaskManager {
    private static final ArrayList<Task> taskList = new ArrayList<Task>();
    private static final String SPACE = "     ";

    TaskManager() {
    }

    public static void add(Task task) {
        taskList.add(task);
    }

    public static void delete(int i) {
        taskList.remove(i);
    }

    public static Task get(int i) {
        if (taskList.size() >= i) {
            return taskList.get(i - 1);
        } else {
            return new EmptyTask();
        }
    }

    public static void print() {
        int i = 1;
        for(Task task : taskList) {
            System.out.println(SPACE + i + ". " + get(i));
        }
    }

    public static void mark(int i) {
        if (get(i) == null) {
            System.out.println("No such task found!");
        } else if (get(i).isMarked()) {
            System.out.println("Task is already marked!");
        } else {
            get(i).mark();
        }
    }

    public static void unmark(int i) {
        if (get(i).isEmptyTask()) {
            System.out.println("No such task found!");
        } else if (!get(i).isMarked()) {
            System.out.println("Task is already unmarked!");
        } else {
            get(i).unmark();
        }
    }

    public static boolean isEmpty() {
        return taskList.isEmpty();
    }

    public static boolean isValidTask(int i) {
        return !get(i).isEmptyTask();
    }
}
