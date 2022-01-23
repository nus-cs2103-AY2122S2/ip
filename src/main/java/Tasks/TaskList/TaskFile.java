package Tasks.TaskList;

import SparkExceptions.FileExceptions.FileException;
import SparkExceptions.FileExceptions.ReadFileException;
import SparkExceptions.FileExceptions.TaskDecodingException;
import SparkExceptions.FileExceptions.WriteFileException;
import Tasks.TaskTypes.Task;

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
class TaskFile {
    static final Path defaultFilePath = Paths.get("spark_save_file.txt");
    private final File tasksFile;

    /**
     * Loads data from the file at the default filepath.
     * If no file can be found, creates a new file at the default
     * filepath.
     */
    public TaskFile() throws FileException {
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
    protected void writeTasksFile(List<Task> tasks) throws FileException {
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
