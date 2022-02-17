package duke.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import duke.task.TaskList;

/**
 * Handles all file saving and loading operations of Duke. <br>
 */
public class Storage {
    static final int TODO_TASK = 0;
    static final int DEADLINE_TASK = 1;
    static final int EVENT_TASK = 2;
    static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/M/yyyy");

    /**
     * Saves the content of the provided Task ArrayList into the file specified. <br>
     * Tasks are delimited by ';'. <br>
     * Tasks fields are delimited by ',', in the following format: <br>
     * <pre> TodoTask Task: T ,Task Done Status ,Task Name. </pre>
     * <pre> DeadlineTask Task: D, Task Done Status ,Task Name, Task Deadline Date. </pre>
     * <pre> EventTask: E ,Task Done Status ,Task Name, Task Event Date. <br> </pre>
     *
     * @param folderName folder name to create the save file.
     * @param fileName name of save file.
     * @param taskList list of tasks to be saved.
     * @return 0: save success, -1: error encountered.
     */
    public static int saveFile(String folderName, String fileName, TaskList taskList) {
        try {
            Files.createDirectories(Paths.get(folderName));
            String filePath = folderName + "/" + fileName;
            File myObj = new File(filePath);
            myObj.createNewFile();
            FileWriter writer = new FileWriter(filePath);
            writer.write(taskList.toFileString());
            writer.close();
            return 0;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    /**
     * Loads the content from the file specified into the specified TaskList.
     *
     * @param filePath path of the file to be read.
     * @param taskList TaskList to be updated.
     * @return 0: load success, -1: error encountered.
     */
    public static int loadFile(String filePath, TaskList taskList) {
        try {
            FileInputStream fs = new FileInputStream(filePath);
            Scanner sc = new Scanner(fs);
            StringBuilder sb = new StringBuilder();
            while (sc.hasNextLine()) {
                sb.append(sc.nextLine());
            }
            sc.close();
            if (sb.length() == 0) {
                return -1;
            }
            for (String taskString : sb.toString().split(";")) {
                addTaskFromString(taskString, taskList);
            }
            return 0;
        } catch (FileNotFoundException e) {
            return -1;
        }
    }

    /**
     * Adds a task constructed from the specified string into the specified task list
     */
    public static void addTaskFromString(String taskString, TaskList taskList) {
        String[] args = taskString.split(",");
        String taskType = args[0];
        String taskName = args[2];
        boolean isMarked = args[1].equals("T");
        int taskCode = taskType.equals("T") ? TODO_TASK : taskType.equals("D") ? DEADLINE_TASK : EVENT_TASK;
        LocalDate date = args.length < 4 ? null : LocalDate.parse(args[3], DATE_FORMATTER);

        taskList.addTask(taskName, isMarked, date, taskCode);
    }
}
