package duke;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import duke.exceptions.CorruptedSaveException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    private static final String FILE_PATH = "data/duke.txt";
    private static final String DIR_PATH = "data/";

    /**
     * Generates a file at the given directory if it does not exist.
     *
     * @param filePath The file to be created.
     * @param dirPath The directory where the file should be.
     * @return The created file.
     * @throws IOException If an I/O error occurs.
     */
    private static File createFileIfNotExist(String filePath, String dirPath) throws IOException {
        File dir = new File(dirPath);
        File file = new File(filePath);
        dir.mkdir();
        file.createNewFile();
        return file;
    }

    /**
     * Stores the list of tasks in the hard drive.
     *
     * @param taskList The list of tasks to be written.
     * @throws IOException If an I/O error occurs.
     */
    public static void updateTaskFile (TaskList taskList) throws IOException {
        String tempFilePath = FILE_PATH + ".new";
        File file = Storage.createFileIfNotExist(tempFilePath, DIR_PATH);
        FileWriter fw = new FileWriter(file, true);

        for (Task task : taskList.getTasks()) {
            fw.write(task.toFileFormat());
        }
        fw.close();
        Files.move(Paths.get(tempFilePath), Paths.get(FILE_PATH), REPLACE_EXISTING);

    }

    /**
     * Reads and retrieve the contents of the save file in the hard disk.
     *
     * @return The list of tasks stored in the file.
     * @throws CorruptedSaveException If the contents of the file cannot be restored correctly.
     */
    public static TaskList readSaveFile() throws CorruptedSaveException {
        TaskList tasks = new TaskList();
        File f = new File(FILE_PATH);
        Scanner s = null;
        try {
            s = new Scanner(f);
            while (s.hasNextLine()) {
                String packet = s.nextLine();
                String[] packetSections = packet.split(" \\| ");
                switch (packetSections[0]) {
                case "T":
                    tasks.addTask(ToDo.fromFileFormat(packet));
                    break;
                case "D":
                    tasks.addTask(Deadline.fromFileFormat(packet));
                    break;
                case "E":
                    tasks.addTask(Event.fromFileFormat(packet));
                    break;
                default:
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        } finally {
            if (s != null) {
                s.close();
            }
        }
        return tasks;

    }
}
