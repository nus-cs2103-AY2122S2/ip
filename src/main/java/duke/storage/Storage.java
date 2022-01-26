package duke.storage;

import duke.DukeException;
import duke.InvalidArgumentException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

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

public class Storage {
    private final Path filepath;

    public Storage(String path) {
        this.filepath = Paths.get(path);
    }

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
