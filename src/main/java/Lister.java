import java.util.ArrayList;
import java.util.HashMap;

import java.time.LocalDate;

public class Lister {
    ArrayList<Task> tasks;
    HashMap<LocalDate, NotableDate> dateMap;

    public Lister() {
        tasks = new ArrayList<>();
        dateMap = new HashMap<>();
    }

    public void add(Task task) {
        tasks.add(task);
        if (task instanceof Event) {
            Event e = (Event) task;
            e.date.addTask(task);
        } else if (task instanceof Deadline) {
            Deadline d = (Deadline) task;
            d.date.addTask(task);
        }
        System.out.println("   ________________________________________");
        System.out.println("    Fine I'll add this task in:\n      " + task);
        if (tasks.size() == 1) {
            System.out.println("    Now you have 1 task in the list. =.=");
        } else {
            System.out.println("    Now you have " + tasks.size() + " tasks in the list. =.=");
        }
        System.out.println("   ________________________________________");
    }

    public void list(ArrayList<Task> taskList) {
        System.out.println("   ________________________________________");
        if (taskList.size() == 0) {
            System.out.println("    Nothing to look at here... =.=");
        }
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
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
            Task task = tasks.get(i - 1);
            if (task instanceof Event) { // remove task from NotableDate tasklist
                Event e = (Event) task;
                e.date.tasks.remove(task);
            } else if (task instanceof Deadline) {
                Deadline d = (Deadline) task;
                d.date.tasks.remove(task);
            }
            String des = task.toString();
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
