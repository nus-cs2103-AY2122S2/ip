package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    private static final String FILEPATH = "data/duke.txt";
    private static final String DIR_PATH = "data/";

    /**
     * Generate a file at the given directory if it does not exist.
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
        String tempFilePath = FILEPATH + ".new";
        File file = Storage.createFileIfNotExist(tempFilePath, DIR_PATH);
        FileWriter fw = new FileWriter(file, true);
        
        for (Task task : taskList.getTasks()) {
            fw.write(task.toFileFormat());
        }
        fw.close();
        Files.move(Paths.get(tempFilePath), Paths.get(FILEPATH), REPLACE_EXISTING);

    }

    /**
     * Reads and retrieve the contents of the save file in the hard disk.
     * 
     * @return The list of tasks stored in the file.
     * @throws DukeException If the contents of the file cannot be restored correctly.
     */
    public static TaskList readSaveFile() throws DukeException {
        TaskList tasks = new TaskList();
        File f = new File(FILEPATH);
        Scanner s = null;

        try {
            s = new Scanner(f);
            while (s.hasNextLine()) {
                String packet = s.nextLine();
                String[] packetSections = packet.split(" \\| ");
                String taskName = packetSections[2];
                boolean isDone = Integer.parseInt(packetSections[1]) == 1;

                switch (packetSections[0]) {
                case "T":
                    tasks.addTask(new ToDo(taskName, isDone));
                    break;
                case "D":
                    String deadlineString = packetSections[3];
                    tasks.addTask(new Deadline(taskName, isDone, deadlineString));
                    break;
                case "E":
                    String startDateString = packetSections[3];
                    tasks.addTask(new Event(taskName, isDone, startDateString));
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
