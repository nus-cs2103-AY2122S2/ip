package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class Storage {

    private Path directory;
    private Path filePath;

    public Storage(Path directory, Path filePath) {
        this.directory = directory;
        this.filePath = filePath;
    }

    /**
     * Loads list of tasks from file.
     *
     * @return List of tasks.
     * @throws DukeException If file not found.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            File dir = directory.toFile();
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = filePath.toFile();
            file.createNewFile();
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String taskString = fileScanner.nextLine();
                String[] data = taskString.split(",");
                String type = data[0];
                Boolean status = Boolean.parseBoolean(data[1]);
                String text = data[2];
                if (data[0].equals("T")) {
                    tasks.add(new Todo(text, status));
                } else if (type.equals("D")) {
                    LocalDate date = LocalDate.parse(data[3]);
                    tasks.add(new Deadline(text, status, date));
                } else if (type.equals("E")) {
                    LocalDate date = LocalDate.parse(data[3]);
                    tasks.add(new Event(text, status, date));
                }
            }
            fileScanner.close();
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
        return tasks;
    }

    /**
     * Writes task list to file.
     *
     * @throws DukeException If couldn't write to file.
     */
    public void save(TaskList tasks) throws DukeException {
        try {
            File file = filePath.toFile();
            file.createNewFile();
            FileWriter myWriter = new FileWriter(file);
            myWriter.write(tasks.toDataString());
            myWriter.close();
        } catch (IOException e) {
            throw new DukeException("Couldn't write to file");
        }
    }

}
