package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Class that handles loading and storing data, from and to data.txt file
 * Contains 2 attributes passed in when Duke runs
 */
public class Storage {
    private static String path;
    private static String fileDir;


    /**
     * Constructor for Storage class
     */
    public Storage() {
        this.path = System.getProperty("user.dir") + File.separator + "data" + File.separator + "data.txt";
        this.fileDir = System.getProperty("user.dir") + File.separator + "data";
    }

    /**
     * Loads data from data.txt if it exists in local storage
     *
     * @return a TaskList filled with tasks recorded in data.txt
     * @throws FileNotFoundException if file is not found or inaccessible
     */
    public TaskList load() throws FileNotFoundException {
        TaskList list = new TaskList();
        File file = new File(path); // create a File for the given file path
        if (file.exists()) {
            Scanner fileScanner = new Scanner(file); // create a Scanner using the File as the source
            while (fileScanner.hasNextLine()) {
                String[] taskLine = fileScanner.nextLine().split("~");
                String taskType = taskLine[0];
                readLine(taskType, taskLine, list);
            }
        }
        return list;
    }

    /**
     * Abstracted task for load function
     * @param taskType the type of task being read
     * @param taskLine the task details
     * @param list the TaskList
     */
    public void readLine(String taskType, String[] taskLine, TaskList list) {
        Task task;
        try {
            if (taskType.equals("T")) {
                task = new Todo(taskLine[2]);
            } else if (taskType.equals("D")) {
                String dateTime = taskLine[3];
                if (taskLine.length == 5) {
                    dateTime += " " + taskLine[4];
                }
                task = new Deadline(taskLine[2], dateTime);
            } else if (taskType.equals("E")) {
                String dateTime = taskLine[3];
                if (taskLine.length == 5) {
                    dateTime += " " + taskLine[4];
                }
                task = new Event(taskLine[2], dateTime);
            } else {
                throw new DukeException("Invalid task was read");
            }
            if (taskLine[1].equals("X")) {
                task.setDone(true);
            }
            list.addTaskNoPrint(task);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Writes current tasks in TaskList back to data.txt file
     *
     * @param tasks an arrayList of current tasks
     */
    public static void storeTasks(ArrayList<Task> tasks) {
        File file = new File(fileDir); //Saving the changes back to file
        try { //if prev file exists, delete it and replace with new empty file
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        try { //Writing to empty txt file
            writeToFile(tasks);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Abstracted method to write to storage file
     * @param tasks arraylist of tasks
     * @throws IOException
     */
    public static void writeToFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(path, false);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        for (Task t: tasks) {
            String taskToAppend = "";
            taskToAppend += taskText(t);
            taskToAppend += getDateTime(t);
            printWriter.println(taskToAppend);
        }
        printWriter.close();
    }

    /**
     * Abstracted function for writeToFile function that appends task type, task done status and task name
     * @param t task to check for
     * @return string containing task type, task done status and task name
     */
    public static String taskText(Task t) {
        String s = "";
        if (t instanceof Todo) { //Identify task type
            s += "T~";
        } else if (t instanceof Deadline) {
            s += "D~";
        } else if (t instanceof Event) {
            s += "E~";
        }
        if (t.getDone()) { //Identify if task is done
            s += "X~";
        } else {
            s += " ~";
        }
        s += t.getTaskName() + "~";
        return s;
    }

    /**
     * Abstracted function for writeToFile function that extracts date and time from a task
     * @param t the task to check
     * @return string containing the date and time if they exist
     */
    public static String getDateTime(Task t) {
        String s = "";
        if (t instanceof Deadline) {
            Deadline tempTask = (Deadline) t;
            String date = tempTask.getDate().toString();
            if (tempTask.getTime() != null) {
                date += "~" + tempTask.getTime().toString();
            }
            s += date.trim();
        } else if (t instanceof Event) {
            Event tempTask = (Event) t;
            String date = tempTask.getDate().toString();
            if (tempTask.getTime() != null) {
                date += "~" + tempTask.getTime().toString();
            }
            s += date.trim();
        }
        return s;
    }
}
