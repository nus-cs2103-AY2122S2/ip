package duke;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * The Storage class, which entails methods to initialise, load and save the taskList file.
 *
 * @author Benjamin Koh
 */

public class Storage {

    public static final String DATA_FOLDER_PATH = "./data";
    public static final String TASKLIST_FILE_PATH = "./data/duke.text";

    /**
     * Initialise file for storage
     *
     * @throws IOException when input is invalid
     */

    public static void initialise() throws IOException {
        File folder = new File(DATA_FOLDER_PATH);
        if (!Files.exists(Path.of(DATA_FOLDER_PATH)) || !folder.isDirectory()) {
            System.out.println("Folder not found, I am creating the new folder now.");
            if (folder.mkdirs()) {
                System.out.println("Folder has been created");
            } else {
                throw new IOException("Failed to create folder. Try again.");
            }
        }
        File file = new File(TASKLIST_FILE_PATH);
        if (!Files.exists(Path.of(TASKLIST_FILE_PATH)) || !file.isFile()) {
            System.out.println("File not found, creating a new file.");
            boolean isSuccess = file.createNewFile();
            if (isSuccess) {
                System.out.println("File has been created successfully.");
            } else {
                throw new IOException("Failed to create file, please try again.");
            }
        }
    }

    /**
     * Read tasks and loads it into memory
     *
     * @throws FileNotFoundException when the data file is not found
     */

    public static void loadFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new File(TASKLIST_FILE_PATH));
        while (sc.hasNext()) {
            String current = sc.nextLine();
            if (current.equals("")) {
                break;
            }
            String descriptionWithTime = current.substring(9);
            int descriptionWithTimeIndex = descriptionWithTime.length() - 1;
            Task taskToAdd;
            String taskLetter = Character.toString(current.charAt(3));
            boolean isDone = (current.charAt(6) == 'X');
            DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("MMM-dd-yyyy HHmm");
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern(" yyyy-MM-dd HHmm");

            if (taskLetter.equals("E")) {
                int eventAtIndex = descriptionWithTime.indexOf(" (at:");
                String eventDesc = descriptionWithTime.substring(0, eventAtIndex);
                String eventDate = descriptionWithTime.substring(eventAtIndex + 5, descriptionWithTimeIndex).trim();
                LocalDateTime eventTime = LocalDateTime.parse(eventDate, displayFormat);
                taskToAdd = new Event(eventDesc, eventTime.format(inputFormat));
            } else if (taskLetter.equals("D")) {
                int deadlineAtIndex = descriptionWithTime.indexOf(" (by: ");
                String deadlineDesc = descriptionWithTime.substring(0, deadlineAtIndex);
                String deadlineDate = descriptionWithTime.substring(deadlineAtIndex + 6,
                        descriptionWithTimeIndex).trim();
                LocalDateTime deadlineTime = LocalDateTime.parse(deadlineDate, displayFormat);
                taskToAdd = new Deadline(deadlineDesc, deadlineTime.format(inputFormat));
            } else {
                taskToAdd = new Todo(descriptionWithTime);
            }
            if (isDone) {
                taskToAdd.markAsDone();
            }
            TaskList.add(taskToAdd);
        }
    }

    /**
     * Saves tasks to the file
     *
     * @param taskList current taskList being used in this instance
     * @throws IOException when input or output has issues
     */

    public static void saveToFile(TaskList taskList) throws IOException {
        StringBuilder listString = new StringBuilder();
        for (int i = 0; i < taskList.getSize(); i++) {
            Task t = taskList.get(i);
            listString.append(i + 1).append(".").append(t.toString()).append("\n");
        }
        PrintWriter out = new PrintWriter(new FileWriter(TASKLIST_FILE_PATH, false));
        out.println(listString);
        out.close();
    }
}
