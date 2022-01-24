package spark.storage;

import spark.exceptions.fileexceptions.FileException;
import spark.exceptions.fileexceptions.ReadFileException;
import spark.exceptions.fileexceptions.TaskDecodingException;
import spark.exceptions.fileexceptions.WriteFileException;
import spark.tasks.TaskDecoder;
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
    static final Path defaultFilePath = Paths.get("spark_save_file.txt");
    private final File tasksFile;

    /**
     * Loads data from the file at the default filepath.
     * If no file can be found, creates a new file at the default
     * filepath.
     */
    public Storage() throws FileException {
        tasksFile = new File(defaultFilePath.toString());
        if (!tasksFile.exists()) {
            try {
                tasksFile.createNewFile();
            } catch (IOException e) {
                throw new ReadFileException();
            }
        }
    }

    /**
     * Reads the encoded list of Tasks from the save-file.
     *
     * @return
     * @throws TaskDecodingException
     */
    public List<Task> readTasksFile() throws TaskDecodingException {
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
            // do nothing
        }

        return tasks;
    }

    /**
     * Writes the encoded list of Tasks into the save-file in the user's hard disk.
     *
     * @param tasks
     * @throws FileException
     */
    public void writeTasksFile(List<Task> tasks) throws FileException {
        String encodedTasks =  encodeTaskList(tasks);

        try {
            FileWriter fw = new FileWriter(defaultFilePath.toString());
            fw.write(encodedTasks);
            fw.close();
        } catch (IOException e) {
            throw new WriteFileException();
        }
    }

    private String encodeTaskList(List<Task> tasks) {
        StringBuilder encodedTasks = new StringBuilder();
        for (Task t : tasks) {
            encodedTasks.append(encodeTask(t) + System.lineSeparator());
        }

        return encodedTasks.toString();
    }

    private String encodeTask(Task t) {
        return t.encodeTask();
    }
}
