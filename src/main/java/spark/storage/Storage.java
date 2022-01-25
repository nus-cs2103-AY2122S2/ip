package spark.storage;

import spark.exceptions.fileexceptions.*;
import spark.tasks.tasktypes.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Holds methods for loading and saving Tasks to
 * a File in the user's hard disk.
 */
public class Storage {
    private final Path filePath;
    private final File tasksFile;

    /**
     * Opens the file at the specified relative file-path.
     * If no file can be found, creates a new file at the specified
     * file-path.
     */
    public Storage(String filePathString) throws ReadFileException {
        this.filePath = Paths.get(filePathString);
        this.tasksFile = new File(filePath.toString());

        if (!tasksFile.exists()) {
            try {
                this.tasksFile.createNewFile();
            } catch (IOException e) {
                throw new ReadFileException();
            }
        }
    }

    /**
     * Reads and decodes saved Tasks from the file into a List of Task objects.
     *
     * @return
     * @throws TaskDecodingException
     */
    public List<Task> readTasksFile() throws TaskDecodingException, NotFoundException {
        List<Task> tasks = new ArrayList<>();

        try {
            Scanner sc = new Scanner(this.tasksFile);
            while (sc.hasNextLine()) {
                String encodedTask = sc.nextLine();
                if (!encodedTask.isBlank()) {
                    Task t = TaskDecoder.decodeTask(encodedTask);
                    tasks.add(t);
                }
            }
        } catch (FileNotFoundException e) {
            throw new NotFoundException();
        }

        return tasks;
    }

    /**
     * Writes the encoded list of Tasks into the save-file in the user's hard disk.
     *
     * @param
     * @throws FileException
     */
    public void writeTasksFile(String encodedTasks) throws FileException {
        try {
            FileWriter fw = new FileWriter(filePath.toString());
            fw.write(encodedTasks);
            fw.close();
        } catch (IOException e) {
            throw new WriteFileException();
        }
    }

    private String encodeTask(Task t) {
        return t.encodeTask();
    }
}
