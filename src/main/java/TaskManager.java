import jdk.jshell.spi.SPIResolutionException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class TaskManager {
    private static final ArrayList<Task> taskList = new ArrayList<Task>();
    private static final String SPACE = "     ";

    TaskManager() {
    }

    public static void add(Task task) {
        taskList.add(task);
        save();
    }

    public static void delete(int i) {
        taskList.remove(i - 1);
        save();
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
            i++;
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

    public static void save() {
        try {
            FileWriter fw = new FileWriter("data/duke.txt", false);
            PrintWriter pw = new PrintWriter(fw);

            pw.write(taskList.size() + "\n");

            for (Task task : taskList) {
                String prefix = task.getPrefix();
                String postfix = task.getPostfix();
                String completedMarker = task.isMarked() ? "X" : " ";
                pw.write(prefix + "/"
                        + completedMarker + "/"
                        + task.getName() + "/"
                        + postfix + "\n");
            }

            pw.flush();
            pw.close();

            System.out.println(SPACE + "Tasklist has been updated...");
        } catch (IOException e) {
            System.out.println(SPACE + "An error occurred. Tasklist cannot be saved!");
        }
    }
}
