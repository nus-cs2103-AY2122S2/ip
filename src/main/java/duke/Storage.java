package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import duke.exception.DukeException;
import duke.task.Task;
import duke.ui.MessageUi;

/**
 * A class that interacts with the directory of the file. A file in this context
 * refers to the text file that stores the content of the task list.
 */
public class Storage {

    private Path dirPath;
    private Path filePath;

    /**
     * Coonstructor for the Storage class.
     * @param path Directory of the text file.
     */
    public Storage(String path) {
        this.dirPath = Paths.get(path.split("/")[0]);
        this.filePath = Paths.get(path);
    }

    /**
     * Creates a folder in a directory and then a file in it.
     *
     * @throws DukeException If directory cannot be found.
     */
    public void createNewFolderAndTextFile() throws DukeException {
        try {
            Files.createDirectories(dirPath);
            Files.createFile(filePath);
        } catch (IOException error) {
            throw new DukeException("File at " + error.getMessage() +
                    " could not be created");
        }
    }

    /**
     * Appends new task to the file.
     *
     * @param textToAdd Information of the task to be added to the file.
     * @throws IOException If the directory or file cannot be found.
     */
    public void appendToFile(String textToAdd) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath.toString(), true);
            fw.write(textToAdd);
            fw.close();
        } catch (IOException error) {
            throw new DukeException("File at " + error.getMessage() +
                    " could not be appended");
        }
    }

    /**
     * Overwrites the content of a specific task index in the file.
     * Used for command such as mark and unmark.
     *
     * @param lineNumber The index of the task to be modified.
     * @param data       The content of the task in the file.
     * @throws IOException If directory or file cannot be found.
     */
    public void setInFile(int lineNumber, String data) throws DukeException {
        try {
            List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
            lines.set(lineNumber - 1, data);
            Files.write(filePath, lines, StandardCharsets.UTF_8);
        } catch (IOException error) {
            throw new DukeException("File at " + error.getMessage() +
                    " could not be found");
        }
    }

    /**
     * Overwrites the entire content of the file.
     * Used for command such as delete.
     *
     * @param taskList The task list.
     * @throws IOException If directory or file cannot be found.
     */
    public void writeToFile(List<Task> taskList) throws DukeException {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            for (Task task : taskList) {
                stringBuilder.append(task.taskDescriptionForFile()
                        + System.lineSeparator());
            }
            FileWriter fw = new FileWriter(filePath.toString());
            fw.write(stringBuilder.toString());
            fw.close();
        } catch (IOException err) {
            throw new DukeException("File at " + err.getMessage() +
                    " could not be written to");
        }
    }

    /**
     * Load the contents of the file and store it as a list of strings.
     *
     * @return List of type String.
     * @throws DukeException If there is an error reading the file.
     * @throws IOException   If there is an error reading the file.
     */
    public List<String> loadFileContents() throws DukeException {
        List<String> data;
        try {
            Files.readAllLines(filePath, StandardCharsets.UTF_8);
            data = Files.readAllLines(filePath, StandardCharsets.UTF_8);
            return data;
        } catch (IOException err) {
            throw new DukeException("File at " + err.getMessage() +
                    " could not be loaded");
        }
    }
}
