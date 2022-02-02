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
        this.tasks = new ArrayList<>();
    }

    public List<Task> loadTasksFromFile() {

        if (!Files.exists(dirPath)) {
            createBlankFile(dirPath, filePath);
        } else {
            List<String> fileData = readFile();
            convertStringToTasks(fileData);
        }

        return this.tasks;
    }

    private void createBlankFile(Path dirPath, Path filePath) {
        try {
            Files.createDirectories(dirPath);
            Files.createFile(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> readFile() {
        try {
            return Files.readAllLines(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void convertStringToTasks(List<String> fileData) {
        if (fileData != null) {
            for (String line : fileData) {
                String[] item = line.split("\\|");
                switch (item[0].replaceAll("\\s", "")) {
                    case "D":
                        this.tasks.add(new Deadline(item[1].replaceAll("\\s", "").equals("1"), item[2], item[3]));
                        break;
                    case "E":
                        tasks.add(new Event(item[1].replaceAll("\\s", "").equals("1"), item[2], item[3]));
                        break;
                    default:
                        tasks.add(new Todo(item[1].replaceAll("\\s", "").equals("1"), item[2]));
                }
            }
        }
    }

    public void printCurrentDirectory() {
        File directory = new File("./");
        System.out.println(directory.getAbsolutePath());
    }

}