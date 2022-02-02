package duke.storage;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import static java.nio.file.StandardOpenOption.WRITE;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import duke.exception.DukeException;

/**
 * This class acts as the interface between the application and the disk storage.
 */
public class Storage {
    private final String filePath;
    private final Path path;

    /**
     * Class constructor.
     */
    public Storage() {
        filePath = "tasks.txt";
        path = Paths.get(filePath);
    }

    /**
     * Gets the data stored in designated disk location.
     *
     * @return an array of task records read from the disk file.
     * @throws DukeException when the disk file cannot be read or its file format is incorrect.
     */
    public String[] loadTasksFromFile() throws DukeException {
        if (!Files.exists(path)) {
            return new String[0];
        }

        try {
            BufferedReader bufferReader = new BufferedReader(Files.newBufferedReader(path));

            int numberOfTasks = Integer.parseInt(bufferReader.readLine());
            String[] data = new String[numberOfTasks];

            for (int i = 0; i < numberOfTasks; i++) {
                data[i] = bufferReader.readLine();
            }

            return data;
        } catch (NumberFormatException e) {
            throw new DukeException("Encounter incorrect file format when reading " + filePath);
        } catch (IOException e) {
            throw new DukeException("Failed to load tasks from " + filePath);
        }
    }

    /**
     * Stores the task data into a designated disk location.
     *
     * @param data an array of task records to be stored in disk.
     * @throws DukeException when unable to create (or write to) the disk file.
     */
    public void saveToFile(String... data) throws DukeException {
        try {
            BufferedWriter bufferedWriter = Files.newBufferedWriter(
                    path, CREATE, TRUNCATE_EXISTING, WRITE);

            bufferedWriter.write(String.valueOf(data.length));
            bufferedWriter.newLine();
            for (int i = 0; i < data.length; i++) {
                bufferedWriter.write(data[i]);
                bufferedWriter.newLine();
            }

            bufferedWriter.flush();
        } catch (IOException e) {
            throw new DukeException("Failed to write tasks to " + filePath);
        }
    }
}
