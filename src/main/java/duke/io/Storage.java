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
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Handles all file saving and loading operations of Duke. <br>
 */
public class Storage {
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
     * @param arr list of tasks to be saved.
     * @return 0: save success, -1: error encountered.
     */
    public static int saveFile(String folderName, String fileName, ArrayList<Task> arr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
        try {
            Files.createDirectories(Paths.get(folderName));
            String filePath = folderName + "/" + fileName;
            File myObj = new File(filePath);
            myObj.createNewFile();
            FileWriter writer = new FileWriter(filePath);
            StringBuilder sb = new StringBuilder();
            for (Task t : arr) {
                if (t.toString().charAt(1) == 'T') {
                    sb.append(String.format("%s,%s,%s,",
                            t.toString().charAt(1), t.isDone() ? "T" : "F",
                            t.getTaskName()))
                        .append(";");
                } else if (t.toString().charAt(1) == 'D') {
                    sb.append(String.format("%s,%s,%s,%s",
                            t.toString().charAt(1),
                            t.isDone() ? "T" : "F",
                            t.getTaskName(), ((DeadlineTask) t).getDueDate().format(formatter)))
                        .append(";");
                } else if (t.toString().charAt(1) == 'E') {
                    sb.append(String.format("%s,%s,%s,%s",
                                    t.toString().charAt(1),
                                    t.isDone() ? "T" : "F", t.getTaskName(), (
                                            (EventTask) t).getDate().format(formatter)))
                            .append(";");
                }
            }
            sb.setLength((sb.length() - 1));
            writer.write(sb.toString());
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
     * @param tl TaskList to be updated.
     * @return 0: load success, -1: error encountered.
     */
    public static int loadFile(String filePath, TaskList tl) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
        try {
            FileInputStream fs = new FileInputStream(filePath);
            Scanner sc = new Scanner(fs);
            StringBuilder ss = new StringBuilder();
            while (sc.hasNextLine()) {
                ss.append(sc.nextLine());
            }
            sc.close();
            for (String s : ss.toString().split(";")) {
                String[] args = s.split(",");
                if (args[0].equals("T")) {
                    tl.addTask(args[2], args[1].equals("T"), null, 0);
                } else if (args[0].equals("D")) {
                    tl.addTask(args[2], args[1].equals("T"), LocalDate.parse(args[3], formatter), 1);
                } else if (args[0].equals("E")) {
                    tl.addTask(args[2], args[1].equals("T"), LocalDate.parse(args[3], formatter), 2);
                }
            }
            return 0;
        } catch (FileNotFoundException e) {
            return -1;
        }
    }
}
