package duke;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Storage handles reading from a file path for pre-existing tasks, and updates
 * the file whenever the tasks are updated.
 */
public class Storage {

    private String fileName;
    private String filePath;

    /**
     * Constructs an instance of the Storage class.
     *
     * @param filePath A string containing the file path to the saved tasks.
     * @throws IOException if an I/O error occurs.
     */
    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
        Path newFile;
        java.nio.file.Path path = java.nio.file.Paths.get("data");
        boolean directoryExists = java.nio.file.Files.exists(path);
        try {
            if (!directoryExists) {
                java.nio.file.Files.createDirectories(path);
            }
            Path dukeFile = path.resolve(filePath.substring(5));
            newFile = Files.createFile(dukeFile);
            this.fileName = "";
        } catch (FileAlreadyExistsException e) {
            newFile = Paths.get(filePath);
            this.fileName = newFile.toString();
        }
    }

    /**
     * Updates the save file with the new task list.
     *
     * @param tasks A TaskList containing the updated tasks.
     * @throws IOException if an I/O error occurs.
     */
    public void update(TaskList tasks) throws IOException {
        ArrayList<String> data = new ArrayList<String>();
        for (Task task : tasks.getList()) {
            data.add(task.toString());
        }
        java.nio.file.Files.write(Paths.get(this.filePath), data, StandardCharsets.UTF_8);
    }

    /**
     * Updates the save file with the new place list.
     *
     * @param places A PlaceList containing the updated places.
     * @throws IOException if an I/O error occurs.
     */
    public void update(PlaceList places) throws IOException {
        ArrayList<String> data = new ArrayList<String>();
        for (Place place : places.getList()) {
            data.add(place.toString());
        }
        java.nio.file.Files.write(Paths.get(this.filePath), data, StandardCharsets.UTF_8);
    }

    /**
     * Provides the file path to load the saved tasks into the task list.
     *
     * @return A string containing the file path if there is pre-existing data,
     * an empty string if there are no pre-existing data.
     */
    public String load() {
        return this.fileName;
    }
}
