import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;

public class Storage {
    public static final String DEFAULT_STORAGE_DIRECTORY = "./data";
    public static final String DEFAULT_STORAGE_FILEPATH = DEFAULT_STORAGE_DIRECTORY + "/data.txt";

    public final Path path;

    public Storage() throws DukeException {
        this(DEFAULT_STORAGE_FILEPATH);
    }

    public Storage(String filePath) throws DukeException {
        path = Paths.get(filePath);
        if (isValidPath(path)) {
            throw new DukeException("Storage file should end with '.txt'");
        }
    }

    public ArrayList<Task> loadALlTasks() throws DukeException  {
        ArrayList<Task> taskList = new ArrayList<Task>();
        if (isValidPath(path)) {
            return taskList;
        }
        try {
            String strCurrentLine;
            Task currentTask;
            FileReader fileReader = new FileReader(DEFAULT_STORAGE_FILEPATH);
            BufferedReader reader = new BufferedReader(fileReader);

            while ((strCurrentLine = reader.readLine()) != null) {
                String[] taskInput = strCurrentLine.split(" \\| ");
                switch (taskInput[0]) {
                    case "T":
                        currentTask = new Todo(taskInput[2]);
                        break;
                    case "D":
                        currentTask = new Deadline(taskInput[2], taskInput[3]);
                        break;
                    case "E":
                        currentTask = new Event(taskInput[2], taskInput[3]);
                        break;
                    default:
                        throw new DukeException("Invalid task type");
                }

                if(Integer.parseInt(taskInput[1]) == 1) {
                    currentTask.markAsDone();
                }

                taskList.add(currentTask);
            }

            reader.close();

            return taskList;
        } catch (IOException ioe) {
            throw new DukeException("Error writing to file: " + path);
        }
    }

    public void saveAllTasks(ArrayList<Task> tasks) throws DukeException {
        try {
            if (Files.notExists(path)) {
                Files.createDirectories(Paths.get(DEFAULT_STORAGE_DIRECTORY));
                Files.createFile(path);
            }
            FileWriter fileWriter = new FileWriter(DEFAULT_STORAGE_FILEPATH);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            for (Task task : tasks) {
                writer.append(task.getSaveFormat());
                writer.append('\n');
            }

            writer.close();

        } catch (IOException ioe) {
            throw new DukeException("Error writing to file: " + path);
        }
    }

    private static boolean isValidPath(Path filePath) {
        return !filePath.toString().endsWith(".txt");
    }
}
