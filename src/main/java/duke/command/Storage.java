package duke.command;



import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class Storage {
    private final Path path;
    private final TaskList taskList;

    public Storage(String strPath, TaskList taskList) {
        this.path = Paths.get(strPath);
        this.taskList = taskList;
        try {
            if (Files.notExists(path)) {
                Files.createDirectory(path.getParent());
                Files.createFile(path);
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void write() {
        try {
            Files.writeString(path, taskList.writeItem());
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
