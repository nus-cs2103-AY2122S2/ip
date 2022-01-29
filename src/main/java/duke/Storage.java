package duke;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * The Storage class, containing methods to create and load the folder and tasklist save file.
 *
 * @author Jet Tan
 */
public class Storage {
    public static final String DATA_FOLDER_PATH = "./data";
    public static final String TASKLIST_FILE_PATH = "./data/duke.txt";

    /**
     * Initializes the folder and file if they do not exist.
     */
    public static void initFiles() throws IOException {
        File folder = new File(DATA_FOLDER_PATH);
        if (!Files.exists(Path.of(DATA_FOLDER_PATH)) || !folder.isDirectory()) {
            System.out.println("Data folder not found, creating a new folder.");
            boolean isSuccess = folder.mkdirs();
            if (isSuccess) {
                System.out.println("Data folder has been created.");
            } else {
                throw new IOException("Failed to create folder, please try again");
            }
        }
        File file = new File(TASKLIST_FILE_PATH);
        if (!Files.exists(Path.of(TASKLIST_FILE_PATH)) || !file.isFile()) {
            System.out.println("Tasklist file not found, creating a new file.");
            boolean isSuccess = file.createNewFile();
            if (isSuccess) {
                System.out.println("Tasklist file has been created successfully.");
            } else {
                throw new IOException("Failed to create tasklist file, please try again.");
            }
        }
    }

    /**
     * Saves the tasks to the file.
     */
    public static void saveToFile() throws IOException {
        StringBuilder listString = new StringBuilder();
        for (int i = 0; i < TaskList.getTasks().size(); i++) {
            Task t = TaskList.getTasks().get(i);
            listString.append(i + 1).append(".").append(t.toString()).append("\n");
        }
        PrintWriter out = new PrintWriter(new FileWriter(TASKLIST_FILE_PATH, false));
        out.println(listString);
        out.close();
    }

    /**
     * Reads the tasks in the save file and loads them into memory.
     */
    public static void loadFile() throws FileNotFoundException {
        File file = new File(TASKLIST_FILE_PATH);
        Scanner s = new Scanner(file);
        while (s.hasNextLine()) {
            String taskString = s.nextLine();
            if (taskString.equals("")) { // reached EoF
                break;
            }
            String descWithDate = taskString.substring(9);
            Task taskToAdd;
            char taskType = taskString.charAt(3);
            boolean isDone = (taskString.charAt(6) == 'X');
            if (taskType == 'E') { // task is an event
                int indexOfDate = descWithDate.indexOf("(at: ");
                String desc = descWithDate.substring(0, indexOfDate);
                String date = descWithDate.substring(
                        indexOfDate + 4, descWithDate.length() - 1).trim(); // Dec-31-2022 2359
                DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("MMM-dd-yyyy HHmm");
                DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                LocalDateTime at = LocalDateTime.parse(date, displayFormat);
                taskToAdd = new Event(desc, at.format(inputFormat));
            } else if (taskType == 'D') { // task is a deadline
                int indexOfDate = descWithDate.indexOf("(by: ");
                String desc = descWithDate.substring(0, indexOfDate);
                String date = descWithDate.substring(
                        indexOfDate + 4, descWithDate.length() - 1).trim(); // Dec-31-2022 2359
                DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("MMM-dd-yyyy HHmm");
                DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                LocalDateTime by = LocalDateTime.parse(date, displayFormat);
                taskToAdd = new Deadline(desc, by.format(inputFormat));
            } else { // task is a todo
                taskToAdd = new Todo(descWithDate);
            }
            if (isDone) {
                taskToAdd.markAsDone();
            }
            TaskList.getTasks().add(taskToAdd);
        }
    }
}
