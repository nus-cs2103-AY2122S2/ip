package spark.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import spark.exceptions.fileexceptions.FileException;
import spark.exceptions.fileexceptions.NotFoundException;
import spark.exceptions.fileexceptions.ReadFileException;
import spark.exceptions.fileexceptions.TaskDecodingException;
import spark.exceptions.fileexceptions.WriteFileException;
import spark.tasks.tasktypes.Task;

/**
 * Holds methods for loading and saving Tasks to
 * a File in the user's hard disk.
 */
public class Storage {
    /** The location of the save-file on the user's hard-disk. */
    private final Path filePath;
    /** Represents the save-file on the user's hard-disk. */
    private final File tasksFile;

    /**
     * Opens the file at the specified relative file-path.
     * If no file can be found, creates a new file at the
     * specified file-path.
     *
     * @throws ReadFileException if the save-file could not be created or read from.
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
     * @return                       a list of Tasks stored in the save-file.
     * @throws TaskDecodingException if the save-file could not be decoded.
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
     * @param encodedTasks an encoded representation of the task list.
     * @throws FileException if the save-file could not be modified.
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
