import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static final String ROOT = System.getProperty("user.dir");
    public static final String FOLDER = "data";
    public static final String FILE_NAME = "duke.txt";
    public static final Path PATH_TO_STORAGE_FILE = Paths.get(ROOT, FOLDER, FILE_NAME);

    public static void createFile() throws IOException {
        File file = new File(PATH_TO_STORAGE_FILE.toString());
        file.getParentFile().mkdirs();
        file.createNewFile();
    }

    public static ArrayList<Task> readDataFromFile() throws IOException{
        if (Files.notExists(PATH_TO_STORAGE_FILE)) {
            createFile();
        }

        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(PATH_TO_STORAGE_FILE.toString());
        Scanner sc = new Scanner(f);

        while (sc.hasNext()) {
            Task task = Parser.retrieveTaskFromStoredData(sc.nextLine());
            tasks.add(task);
        }
        return tasks;
    }

    public static void writeTaskToFile(TaskList taskList) throws IOException{
        FileWriter fw = new FileWriter(PATH_TO_STORAGE_FILE.toString());
        for (Task task: taskList.getTasks()) {
            fw.write(Parser.formatTaskToStore(task));
        }
        fw.close();
    }
}
