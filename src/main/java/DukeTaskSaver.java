import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class DukeTaskSaver {
    String filePath;

    public DukeTaskSaver(String filePath) throws IOException {
        this.filePath = filePath;

        File f = new File(filePath);
        // if file does not exist, create file based on filepath
        if (!f.exists()) {
            // create data directory
            Files.createDirectories(Path.of(f.getParent()));

            // create new file
            if (f.createNewFile()) {
                System.out.println(filePath + " file created in the project root directory");
            }
        } else {
            System.out.println(filePath + " already exists in the project root directory");
        }
    }

    public void save(ArrayList<Task> todoList) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        for (int i = 0; i < todoList.size(); i++) {
            fw.write(todoList.get(i).toStringForSave() + "\n");
        }
        fw.close();
    }
}