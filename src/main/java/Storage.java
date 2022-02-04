import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Storage {
    private String filePath;
    public static final String DATA_FOLDER_PATH = "./data";
    public static final String TASKLIST_FILE_PATH = "./data/duke.text";

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

    public static void loadFile() throws FileNotFoundException, DukeException {
        Scanner sc = new Scanner(new File(TASKLIST_FILE_PATH));
        while (sc.hasNext()) {
            String current = sc.nextLine();
            if (current.equals("")) {
                break;
            }
            System.out.println(current);
            String descriptionWithTime = current.substring(9);
            System.out.println(descriptionWithTime);
            int descriptionWithTimeIndex = descriptionWithTime.length() - 1;
            Task taskToAdd;
            String taskLetter = Character.toString(current.charAt(3));
            boolean isDone = (current.charAt(6) == 'X');
            DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("MMM-dd-yyyy HHmm");
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

            if (taskLetter == "E") {
                int eventAtIndex = descriptionWithTime.indexOf(" (at:");
                String eventDesc = descriptionWithTime.substring(0, eventAtIndex);
                int eventDateIndex = eventAtIndex + 5;
                String eventDate = descriptionWithTime.substring(eventDateIndex, descriptionWithTimeIndex).trim();
                LocalDateTime eventTime = LocalDateTime.parse(eventDate, displayFormat);
                taskToAdd = new Event(eventDesc, eventTime.format(inputFormat));
            } else if (taskLetter == "=D") {
                int deadlineAtIndex = descriptionWithTime.indexOf(" (by:");
                String deadlineDesc = descriptionWithTime.substring(0, deadlineAtIndex);
                int deadlineDateIndex = deadlineAtIndex + 4;
                String deadlineDate = descriptionWithTime.substring(deadlineDateIndex, descriptionWithTimeIndex).trim();
                LocalDateTime deadlineTime = LocalDateTime.parse(deadlineDate, displayFormat);
                taskToAdd = new Deadline(deadlineDesc, deadlineTime.format(inputFormat));
            } else {
                taskToAdd = new Todo(descriptionWithTime);
            }
            TaskList.add(taskToAdd);
        }
    }

    public static void saveToFile(TaskList taskList ) throws IOException {
        StringBuilder listString = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            Task t = taskList.get(i);
            listString.append(i + 1).append(".").append(t.toString()).append("\n");
        }
        PrintWriter out = new PrintWriter(new FileWriter(TASKLIST_FILE_PATH, false));
        out.println(listString);
        out.close();
    }
}
