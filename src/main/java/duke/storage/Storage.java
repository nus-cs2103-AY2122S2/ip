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
            File file = new File(filepath.toString());
            Scanner scanner = new Scanner(file);
            ArrayList<Task> tasks = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String strTask = scanner.nextLine();
                boolean status = (strTask.charAt(4) == 'X');

                if (strTask.startsWith("[T")) {
                    String taskTitle = strTask.substring(7);
                    tasks.add(new Todo(taskTitle, status));

                } else if (strTask.startsWith("[D")) {
                    String[] taskInfo = strTask.substring(7).split("\\(by:");
                    String time = taskInfo[1].substring(0, taskInfo[1].length() - 1);
                    SimpleDateFormat parser = new SimpleDateFormat("d/M/yy HH:mm");
                    try {
                        Date dateTime = parser.parse(time);
                        tasks.add(new Deadline(taskInfo[0], dateTime, status));
                    } catch (ParseException e) {
                        throw new InvalidArgumentException("Invalid time format");
                    }

                } else if (strTask.startsWith("[E")) {
                    String[] taskInfo = strTask.substring(7).split("\\(at:");
                    String time = taskInfo[1].substring(0, taskInfo[1].length() - 1);
                    SimpleDateFormat parser = new SimpleDateFormat("d/M/yy HH:mm");
                    try {
                        Date dateTime = parser.parse(time);
                        tasks.add(new Event(taskInfo[0], dateTime, status));
                    } catch (ParseException e) {
                        throw new InvalidArgumentException("Invalid time format");
                    }
                }
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
}
