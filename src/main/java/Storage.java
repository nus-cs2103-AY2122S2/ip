import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {
    private ArrayList<Task> tasks;

    Path dirPath = Paths.get("../data/");
    Path filePath = Paths.get("../data/data.txt");

    public Storage() {
        this.tasks = null;
    }

    public List<Task> loadTasksFromFile() {

        if (!Files.exists(dirPath)) {
            createBlankFile(dirPath, filePath);
        }

        return this.tasks;
    }

    public void createBlankFile(Path dirPath, Path filePath) {
        try {
            Files.createDirectories(dirPath);
            Files.createFile(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printCurrentDirectory() {
        File directory = new File("./");
        System.out.println(directory.getAbsolutePath());
    }

}