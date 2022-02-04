import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Scanner;

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
            String descriptionWithTime = current.substring(9);
            Task taskToAdd;
            String taskLetter = Character.toString(current.charAt(3));
            boolean isDone = (current.charAt(6) == 'X');

            switch (taskLetter) {
                case("E"):
                    String[] eventSplit = descriptionWithTime.split("at: ");
                    taskToAdd = new Event(eventSplit[0], eventSplit[1]);
                    break;
                case("D"):
                    String[] deadlineSplit = descriptionWithTime.split("by: ");
                    taskToAdd = new Event(deadlineSplit[0], deadlineSplit[1]);
                    break;
                default:
                    taskToAdd = new Todo(descriptionWithTime);
                    break;
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
