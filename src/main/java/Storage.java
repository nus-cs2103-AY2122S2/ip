import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {
    String homeDir;
    ArrayList<Task> taskList;

    Storage() {
        this.homeDir = System.getProperty("user.dir");
        this.taskList = new ArrayList<>();
    }

    void load() {
        boolean directoryExists = new java.io.File(this.homeDir + "/data").exists();
        if (!directoryExists) {
            try {
                Files.createDirectories(Paths.get(this.homeDir + "/data"));
                File myObj = new File(this.homeDir + "/data/storage.txt");
                myObj.createNewFile();

            } catch (IOException e) {
                System.out.println("Load failed.");
            }
        } else {
            // parse the content in the file and add tasks into the arraylist.
        }
    }
}
