package duke.storage;

import duke.DukeException;
import duke.parser.Parser;
import duke.task.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;

public class Storage {
    public static final String DEFAULT_STORAGE_DIRECTORY = "./data/";
    public static final String DEFAULT_STORAGE_FILEPATH = DEFAULT_STORAGE_DIRECTORY + "tasks.txt";

    public final Path path;

    public Storage() throws DukeException {
        this(DEFAULT_STORAGE_FILEPATH);
    }

    public Storage(String fileName) throws DukeException {
        path = Paths.get(DEFAULT_STORAGE_DIRECTORY + fileName);
        if (isValidPath(path)) {
            throw new DukeException("duke.storage.Storage file should end with '.txt'");
        }
    }

    public ArrayList<Task> loadALlTasks() throws DukeException  {
        ArrayList<Task> taskList = new ArrayList<Task>();
        if (isValidPath(path)) {
            return taskList;
        }
        try(BufferedReader reader = new BufferedReader(new FileReader(path.toString()))) {
            String strCurrentLine;
            Task currentTask;

            while ((strCurrentLine = reader.readLine()) != null) {
                String[] taskInput = strCurrentLine.split(" \\| ");
                switch (taskInput[0]) {
                    case "T":
                        currentTask = new Todo(taskInput[2]);
                        break;
                    case "D":
                        currentTask = new Deadline(taskInput[2], Parser.parseDateTime(taskInput[3]));
                        break;
                    case "E":
                        currentTask = new Event(taskInput[2], Parser.parseDateTime(taskInput[3]));
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
        } catch (FileNotFoundException e) {
            throw new DukeException("No file in storage.");
        } catch (IOException ioe) {
            throw new DukeException("Error writing to file: " + path);
        }
    }

    public void saveAllTasks(TaskList tasks) throws DukeException {
        try {
            if (Files.notExists(path)) {
                Files.createDirectories(Paths.get(DEFAULT_STORAGE_DIRECTORY));
                Files.createFile(path);
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(path.toString()));

            for (int i = 0; i < tasks.getSize(); i++) {
                writer.append(tasks.getTask(i).getSaveFormat());
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

    public String getPath() {
        return path.toString();
    }
}
