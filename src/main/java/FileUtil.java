import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    private static final String PROJECT_ROOT = System.getProperty("user.dir");
    private static final Path PATH = Paths.get(PROJECT_ROOT, "data", "duke.txt");

    private static boolean checkIfFileExists() {
        return Files.exists(PATH) && Files.isRegularFile(PATH);
    }

    public static void createFile() {
        try {
            if (checkIfFileExists()) {
                return;
            }

            Path parentDir = PATH.getParent();
            if (!Files.exists(parentDir)) { // check if data folder exists
                Files.createDirectories(parentDir);
            }

            Files.createFile(PATH);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static List<String> readFromFile() {
        try {
            return Files.readAllLines(PATH);
        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }

    public static void appendToFile(String content) {
        try {
            Files.write(PATH, content.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void writeTasksToFile(ArrayList<Task> tasks) {
        StringBuilder content = new StringBuilder();
        for (Task t: tasks) {
            content.append(t.formatForFile());
        }
        FileUtil.writeToFile(content.toString());
    }

    private static void writeToFile(String content) {
        try {
            Files.write(PATH, content.getBytes());
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}