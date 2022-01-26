package duke.task;

import duke.DukeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

/**
 * Saves and loads Task data in String format.
 */
public class Storage {
    private static final String SAVE_DIR = "data";
    private static final String FILE_NAME = SAVE_DIR + "/duke.txt";

    /**
     * Saves the data within a list of Tasks into a .txt file.
     *
     * @param tasks The list of Tasks to be saved.
     */
    public void saveTasks(List<Task> tasks) {
        try {
            Path path = Paths.get(SAVE_DIR);
            Files.createDirectories(path);
            PrintWriter writer = new PrintWriter(FILE_NAME);

            for (Task task : tasks) {
                writer.println(task.toSaveData());
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the Tasks stored in a .txt file into a list of tasks.
     *
     * @param tasks The list of tasks to load data into.
     */
    public void loadTasks(List<Task> tasks) {

        try {
            File file = new File(FILE_NAME);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] lineSplit = line.split(" \\| ");

                boolean isDone = lineSplit[1].equals("1");
                LocalDateTime dateTime;
                String description = lineSplit[2];

                switch (lineSplit[0]) {
                case "T":
                    tasks.add(new ToDo(description, isDone));
                    break;
                case "E":
                    dateTime = LocalDateTime.parse(lineSplit[3]);
                    Duration duration = Duration.parse(lineSplit[4]);
                    tasks.add(new Event(description, dateTime, duration, isDone));
                    break;
                case "D":
                    dateTime = LocalDateTime.parse(lineSplit[3]);
                    tasks.add(new Deadline(description, dateTime, isDone));
                    break;
                default:
                    throw new DukeException("Invalid save data");
                }
            }
        } catch (FileNotFoundException ignored) {
        }
    }
}
