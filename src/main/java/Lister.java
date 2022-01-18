import java.util.ArrayList;
import java.util.Collections;

public class Lister {
    ArrayList<Task> tasks;

    public Lister() {
        tasks = new ArrayList<>();
    }

    public void add(Task task) {
        tasks.add(task);
        System.out.println("   ________________________________________");
        System.out.println("    Fine I'll add this task in:\n      " + task);
        if (tasks.size() == 1) {
            System.out.println("    Now you have 1 task in the list. =.=");
        } else {
            System.out.println("    Now you have " + tasks.size() + " tasks in the list. =.=");
        }
        System.out.println("   ________________________________________");
    }

    public void list() {
        System.out.println("   ________________________________________");
        if (tasks.size() == 0) {
            System.out.println("    Nothing to look at here... =.=");
        }
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.isDone) {
                System.out.println("    " + Integer.toString(i + 1) + "." + task);
            } else {
                System.out.println("    " + Integer.toString(i + 1) + "." + task);
            }
        }
        System.out.println("   ________________________________________");
    }

    public void mark(int i) {
        Task task = tasks.get(i - 1);
        System.out.println("   ________________________________________");
        if (i < 0 || i > tasks.size()) {
            System.out.println("    Invalid entry number entered! =.=");
        } else {
            task.markDone();
            System.out.println("    You've finished this task. Good for you... =.=\n      " + task);
        }
        System.out.println("   ________________________________________");
    }

    public void unmark(int i) {
        Task task = tasks.get(i - 1);
        System.out.println("   ________________________________________");
        if (i < 0 || i > tasks.size()) {
            System.out.println("    Invalid entry number entered! =.=");
        } else {
            task.markUndone();
            System.out.println("    Marked undone. Stop slacking off... =.=\n      " + task);
        }
        System.out.println("   ________________________________________");
    }

    public void delete(int i) {
        System.out.println("   ________________________________________");
        if (i < 0 || i > tasks.size()) {
            System.out.println("    Invalid entry number entered! =.=");
        } else {
            String des = tasks.get(i - 1).toString();
            tasks.remove(i - 1);
            System.out.println("    Fine. I've removed this task:\n      " + des);
            if (tasks.size() == 1) {
                System.out.println("    Now you have 1 task in the list. =.=");
            } else {
                System.out.println("    Now you have " + tasks.size() + " tasks in the list. =.=");
            }
        }
        System.out.println("   ________________________________________");
    }
}
