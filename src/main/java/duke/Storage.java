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
    private String path;
    private String fileDir;

    /**
     * Constructor for Storage class
     *
     * @param path the path to the storage file from root
     * @param fileDir the path to the storage directory from root
     */
    public Storage(String path, String fileDir) {
        this.path = path;
        this.fileDir = fileDir;
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
                Task task;
                String taskType = taskLine[0];
                try {
                    if (taskType.equals("T")) {
                        task = new Todo(taskLine[2]);
                    } else if (taskType.equals("D")) {
                        String dateTime = taskLine[3];
                        //Time is added
                        if (taskLine.length == 5) {
                            dateTime += " " + taskLine[4];
                        }
                        task = new Deadline(taskLine[2], dateTime);
                    } else if (taskType.equals("E")) {
                        String dateTime = taskLine[3];
                        //Time is added
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
        }
        return list;
    }

    /**
     * Writes current tasks in TaskList back to data.txt file
     *
     * @param tasks an arrayList of current tasks
     */
    public void storeTasks(ArrayList<Task> tasks) {
        //Saving the changes back to file
        File file = new File(fileDir);
        //if prev file exists, delete it and replace with new empty file
        try {
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        //Writing to empty txt file
        try {
            FileWriter fileWriter = new FileWriter(path, false);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (Task t: tasks) {
                String taskToAppend = "";

                //Identify task type
                if (t instanceof Todo) {
                    taskToAppend += "T~";
                } else if (t instanceof Deadline) {
                    taskToAppend += "D~";
                } else if (t instanceof Event) {
                    taskToAppend += "E~";
                }

                //Identify if task is done
                if (t.getDone()) {
                    taskToAppend += "X~";
                } else {
                    taskToAppend += " ~";
                }
                taskToAppend += t.getTaskName() + "~";
                if (t instanceof Deadline) {
                    Deadline tempTask = (Deadline) t;
                    String date = tempTask.getDate().toString();
                    if (tempTask.getTime() != null) {
                        date += "~" + tempTask.getTime().toString();
                    }
                    taskToAppend += date.trim();
                } else if (t instanceof Event) {
                    Event tempTask = (Event) t;
                    String date = tempTask.getDate().toString();
                    if (tempTask.getTime() != null) {
                        date += "~" + tempTask.getTime().toString();
                    }
                    taskToAppend += date.trim();
                }
                printWriter.println(taskToAppend);
            }
            printWriter.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
