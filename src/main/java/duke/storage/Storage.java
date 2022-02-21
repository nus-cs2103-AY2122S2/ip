package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import duke.DukeException;
import duke.InvalidArgumentException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Responsible for loading the list of tasks from an existing and saving changes in the list to that file.
 */
public class Storage {
    private final Path filepath;

    /**
     * Constructs a Storage object with a filepath that it will reference for retrieving/storing data.
     *
     * @param path The filepath for data.
     */
    public Storage(String path) {
        this.filepath = Paths.get(path);
    }

    /**
     * Loads the task list from the filepath provided during Storage construction.
     *
     * @return The ArrayList of tasks in the provided file.
     * @throws DukeException If the file cannot be parsed or does not exist.
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            assert filepath != null;
            File file = new File(filepath.toString());
            Scanner scanner = new Scanner(file);
            ArrayList<Task> tasks = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String taskAsString = scanner.nextLine();
                assert !taskAsString.isBlank();
                Task task = parseTaskFromString(taskAsString);
                tasks.add(task);
            }
            return tasks;
        } catch (FileNotFoundException e) {
            throw new NoExistingDataException("No existing file");
        }
    }

    /**
     * Writes all tasks in the given list to filepath.
     *
     * @param tasks The ArrayList of tasks.
     * @throws FileSaveException If the tasks cannot be stored in the respective file.
     */
    public void write(ArrayList<Task> tasks) throws FileSaveException {
        assert filepath != null;
        File directory = new File(filepath.toString()).getParentFile();

        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                throw new FileSaveException("Could not create the necessary directory for file save");
            }
        }

        try {
            StringBuilder strBuilder = new StringBuilder();
            for (Task task : tasks) {
                strBuilder.append(task.toString()).append("\n");
            }
            Files.writeString(filepath, strBuilder.toString());
        } catch (IOException e) {
            throw new FileSaveException("Could not save file to the directory.");
        }
    }

    /**
     * Gets the completion status of a task in String form.
     *
     * @param task The given task.
     * @return The completion status.
     */
    public boolean getTaskStatus(String task) {
        return task.charAt(4) == 'X';
    }

    /**
     * Gets the full description of the given task in String form.
     *
     * @param task The given task.
     * @return The task's description.
     */
    public String getTaskDescription(String task) {
        return task.substring(7);
    }

    /**
     * Parses time in String form into a Date object.
     *
     * @param timeAsString The time in String form.
     * @return The time as a Date object.
     * @throws ParseException If the time cannot be parsed.
     */
    public Date parseTimeFromString(String timeAsString) throws ParseException {
        SimpleDateFormat parser = new SimpleDateFormat("d/M/yy HH:mm");
        return parser.parse(timeAsString);
    }

    /**
     * Parses a Todo task from a String.
     *
     * @param task The task in String form.
     * @return The corresponding Todo.
     */
    public Todo parseTodoFromString(String task) {
        boolean isComplete = getTaskStatus(task);
        String title = getTaskDescription(task);

        return new Todo(title, isComplete);
    }

    /**
     * Parses a Deadline task from a String.
     *
     * @param task The task in String form.
     * @return The corresponding Deadline.
     * @throws ParseException If the time cannot be parsed.
     */
    public Deadline parseDeadlineFromString(String task) throws ParseException {
        boolean isComplete = getTaskStatus(task);
        String description = getTaskDescription(task);

        String[] segments = description.split(" \\(by:");
        String title = segments[0];
        Date time = parseTimeFromString(segments[1].substring(0, segments[1].length() - 1));

        return new Deadline(title, time, isComplete);
    }

    /**
     * Parses an Event task from a String.
     *
     * @param task The task in String form.
     * @return The corresponding Event.
     * @throws ParseException If the time cannot be parsed.
     */
    public Event parseEventFromString(String task) throws ParseException {
        boolean isComplete = getTaskStatus(task);
        String description = getTaskDescription(task);

        String[] segments = description.split(" \\(at:");
        String title = segments[0];
        Date time = parseTimeFromString(segments[1].substring(0, segments[1].length() - 1));

        return new Event(title, time, isComplete);
    }

    /**
     * Parses a line from the data file to a task.
     *
     * @param taskAsString The task in String form.
     * @return The corresponding Task.
     * @throws DukeException If the task cannot be parsed.
     */
    public Task parseTaskFromString(String taskAsString) throws DukeException {
        Task task;

        if (taskAsString.startsWith("[T")) {
            task = parseTodoFromString(taskAsString);
        } else if (taskAsString.startsWith("[D")) {
            try {
                task = parseDeadlineFromString(taskAsString);
            } catch (ParseException e) {
                throw new InvalidArgumentException("Invalid time format");
            }
        } else if (taskAsString.startsWith("[E")) {
            try {
                task = parseEventFromString(taskAsString);
            } catch (ParseException e) {
                throw new InvalidArgumentException("Invalid time format");
            }
        } else {
            throw new DukeException("Cannot parse task in file");
        }

        return task;
    }

}
