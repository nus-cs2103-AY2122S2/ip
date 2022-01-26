package duke.handler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Tasklist;
import duke.task.Todo;
import duke.time.Time;

/**
 * Returns a class to handle reading, writing, creating and updating functionalities regarding external files.
 */
public class FileHandler {

    /**
     * Returns the desired path where the output file is stored.
     *
     * @return The path in string format.
     */
    public static String getFilePath() {
        String home = System.getProperty("user.home");
        java.nio.file.Path path = java.nio.file.Paths.get(home, "desktop", "ip", "data", "duke.txt");
        return String.valueOf(path);
    }

    /**
     * Attempts to read from the output file. If it does not exist, creates one. Afterwards, updates the list of tasks.
     *
     * @param list Tasklist that contains all tasks.
     */
    public static void readFromFile(Tasklist list) {
        String home = System.getProperty("user.home");
        java.nio.file.Path path = java.nio.file.Paths.get(home, "desktop", "ip", "data", "duke.txt");
        if (java.nio.file.Files.exists(Paths.get(FileHandler.getFilePath()))) {
            try {
                String input = Files.readString(path);
                String[] tasks = input.split("\\r?\\n");
                for (int i = 0; i < tasks.length; i++) {
                    String[] taskInfo = tasks[i].split("\\|");
                    boolean completed = taskInfo[1].equals("1");
                    switch (taskInfo[0]) {
                    case "T":
                        list.addTask(new Todo(completed, taskInfo[2]));
                        break;
                    case "D":
                        if (taskInfo.length == 4) {
                            list.addTask(new Deadline(completed, taskInfo[2],
                                    Time.convertToDate(taskInfo[3]), ""));
                        } else {
                            list.addTask(new Deadline(completed, taskInfo[2],
                                    Time.convertToDate(taskInfo[3]), taskInfo[4]));
                        }
                        break;
                    case "E":
                        if (taskInfo.length == 4) {
                            list.addTask(new Event(completed, taskInfo[2],
                                    Time.convertToDate(taskInfo[3]), ""));
                        } else {
                            list.addTask(new Event(completed, taskInfo[2],
                                    Time.convertToDate(taskInfo[3]), taskInfo[4]));
                        }
                        break;
                    default:
                        throw new ArrayIndexOutOfBoundsException();
                    }
                }
            } catch (IOException err) {
                System.out.println("Path specified incorrectly.");
            } catch (ArrayIndexOutOfBoundsException err) {
                FileHandler.createFolder();
            }
        } else {
            FileHandler.createFolder();
        }
    }

    /**
     * Writes to the output file in the specified format.
     *
     * @param list Tasklist that contains all tasks.
     */
    public static void writeToFile(Tasklist list) {
        String path = FileHandler.getFilePath();
        StringBuilder writeTasks = new StringBuilder();
        for (int i = 0; i < list.getTotalTasks(); i++) {
            String digit;
            Task t = list.getTask(i);
            if (t.isCompleted()) {
                digit = "1|";
            } else {
                digit = "0|";
            }
            if (t instanceof Todo) {
                writeTasks.append("T|").append(digit).append(t.getTaskName()).append("\n");
            } else if (t instanceof Event) {
                writeTasks.append("E|").append(digit).append(t.getTaskName()).append("|")
                        .append(((Event) t).getDeadline()).append("|")
                                .append(((Event) t).getTime()).append("\n");
            } else {
                writeTasks.append("D|").append(digit).append(t.getTaskName()).append("|")
                        .append(((Deadline) t).getDeadline()).append("|")
                                .append(((Deadline) t).getTime()).append("\n");
            }
        }
        String content = writeTasks.toString();
        try {
            FileWriter writer = new FileWriter(path);
            writer.write(content);
            writer.close();
        } catch (IOException err) {
            System.out.println("Path specified incorrectly.");
        }
    }

    /**
     * Creates directory for the output file.
     */
    public static void createFolder() {
        String home = System.getProperty("user.home");
        java.nio.file.Path path = java.nio.file.Paths.get(home, "desktop", "ip", "data");
        File file = new File(String.valueOf(path));
        file.mkdirs();
    }
}
