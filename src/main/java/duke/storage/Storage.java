package duke.storage;

import duke.common.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskType;
import duke.task.ToDo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Class which handles loading and saving of data.
 */
public class Storage {

    /**
     * File used for storage.
     */
    private final File STORAGE_FILE;

    /**
     * Constructor to create Storage object.
     */
    public Storage(String filePath) {
        STORAGE_FILE = new File(filePath);
    }

    /**
     * Create the file and necessary folder to store tasks.
     *
     * @throws FileIOException if unable to create file or folder.
     */
    private void createFile() throws FileIOException {
        try {
            STORAGE_FILE.getParentFile().mkdirs();
            STORAGE_FILE.createNewFile();
        } catch (IOException exception) {
            throw new FileIOException(exception.getMessage());
        }
    }

    /**
     * Parse tokens to identify and mark or unmark tasks.
     *
     * @param task  task to be marked or unmarked.
     * @param tokens tokens to parsed.
     * @throws InvalidFileSyntaxException If syntax is unknown.
     */
    private void parseMarkSyntax(Task task, String[] tokens) throws InvalidFileSyntaxException {
        tokens[1] = tokens[1].trim();
        if (tokens[1].equals("1")) {
            task.markAsDone();
        } else if (tokens[1].equals("0")) {
            task.unmarkAsDone();
        } else {
            throw new InvalidFileSyntaxException("Failed to load task: invalid task mark syntax");
        }
    }

    /**
     * Parse input and create a ToDo task.
     *
     * @param tokens input to be parsed.
     * @return ToDo task.
     */
    private Task parseTodoTask(String[] tokens) {
        return new ToDo(tokens[2].trim());
    }

    /**
     * Parse input and create Event task.
     *
     * @param tokens input to be parsed.
     * @return Event task.
     */
    private Task parseEventTask(String[] tokens) {
        return new Event(tokens[2].trim(),
                LocalDateTime.parse(tokens[3].trim()),
                LocalDateTime.parse(tokens[4].trim()));
    }

    /**
     * Parse input and create Deadline task.
     *
     * @param tokens input to be parsed.
     * @return Deadline task.
     */
    private Task parseDeadlineTask(String[] tokens) {
        return new Deadline(tokens[2].trim(),
                LocalDateTime.parse(tokens[3].trim()));
    }

    /**
     * Read and load the Task from the File.
     * Outputs the list of tasks.
     *
     * @return ArrayList of loaded Tasks.
     * @throws DukeException if there is wrong syntax or I/O error.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(STORAGE_FILE));
            String line;
            String[] tokens;
            Task task;
            while ((line = bufferedReader.readLine()) != null) {
                tokens = line.split("\\|");
                tokens[0] = tokens[0].trim();
                if (tokens[0].equals("T")) {
                    task = parseTodoTask(tokens);
                } else if (tokens[0].equals("D")) {
                    task = parseDeadlineTask(tokens);
                } else if (tokens[0].equals("E")) {
                    task = parseEventTask(tokens);
                } else {
                    throw new InvalidFileSyntaxException("Failed to load task: invalid task type");
                }
                parseMarkSyntax(task, tokens);
                taskList.add(task);
            }
            bufferedReader.close();
        } catch (FileNotFoundException exception) {
            this.createFile();
        } catch (IOException exception) {
            throw new FileIOException(exception.getMessage());
        }
        return taskList;
    }


    /**
     * Saves the tasks from task list to the file stored locally.
     *
     * @throws FileIOException if unable to create or write to file.
     */
    public void saveToFile(TaskList taskList) throws FileIOException {
        try {
            if (!STORAGE_FILE.exists()) {
                createFile();
            }
            FileWriter writer = new FileWriter(STORAGE_FILE);
            Task task;
            TaskType type;
            for (int i = 0; i < taskList.size(); i++) {
                task = taskList.get(i);
                type = task.getType();
                switch (type) {
                case TODO:
                    ToDo toDo = (ToDo) task;
                    writer.write(toDo.getStorageString());
                    break;
                case DEADLINE:
                    Deadline deadline = (Deadline) task;
                    writer.write(deadline.getStorageString());
                    break;
                case EVENT:
                    Event event = (Event) task;
                    writer.write(event.getStorageString());
                    break;
                }
            }
            writer.close();
        } catch (IOException exception) {
            throw new FileIOException(exception.getMessage());
        }
    }
}
