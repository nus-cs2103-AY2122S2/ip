package duke;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {

    private String fileName;
    private String filePath;

    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
        Path newFile;
        java.nio.file.Path path = java.nio.file.Paths.get("data");
        boolean directoryExists = java.nio.file.Files.exists(path);
        try {
            if (!directoryExists) {
                java.nio.file.Files.createDirectories(path);
            }
            Path dukeFile = path.resolve("tasks.txt");
            newFile = Files.createFile(dukeFile);
            this.fileName = "";
        } catch (FileAlreadyExistsException e) {
            newFile = Paths.get(filePath);
            this.fileName = newFile.toString();
        }
    }

    public void update(TaskList tasks) throws IOException {
        ArrayList<String> data = new ArrayList<String>();
        for (Task task : tasks.getList()) {
            data.add(task.toString());
        }
        java.nio.file.Files.write(Paths.get(this.filePath), data, StandardCharsets.UTF_8);
    }

    public String load() {
        return this.fileName;
    }
}
