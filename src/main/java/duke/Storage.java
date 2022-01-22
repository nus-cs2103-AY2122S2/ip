package duke;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
//deals with loading tasks from the file and saving tasks in the file
public class Storage {
    File file;

    public Storage(String filePath) {
        file = new File(filePath);
    }

    public Storage() {
    }

    public Storage load() throws DukeException {
        Path directoryExists = Paths.get("C:/repos/ip/data");
        //check if directory exists
        if (!Files.exists(directoryExists)) {
            throw new DukeException("Missing C:/repos/ip/data");
        }
        if (!file.exists()) {
            throw new DukeException("Missing C:/repos/ip/data/tasks.txt");
        }
        return this;
    }
}
