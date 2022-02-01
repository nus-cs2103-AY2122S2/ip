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
                String date = task.getDate();
                String time = task.getTime();
                String completedMarker = task.isMarked() ? "X" : " ";
                pw.write(prefix + "/"
                        + completedMarker + "/"
                        + task.getName() + "/"
                        + date + "/"
                        + time + "\n");
            }

            pw.flush();
            pw.close();

            System.out.println(SPACE + "Tasklist is updating...");
        } catch (IOException e) {
            System.out.println(SPACE + "An error occurred. Tasklist cannot be saved!");
        }
    }

    public static void loadTask(String input) {
        String[] taskAsArray = input.split("/");

        char prefix = taskAsArray[0].charAt(0);
        boolean isCompleted = taskAsArray[1].equals("X");
        String name = taskAsArray[2];
        String date = taskAsArray[3];
        String time = taskAsArray[4];

        // Initializes a TaskCreator to create a new Task
        TaskCreator taskCreator = new TaskCreator(prefix,
                isCompleted,
                name,
                date,
                time);
        Task currentTask = taskCreator.createTask();

        // Adds newly created task into tasklist
        add(currentTask);
    }
}
