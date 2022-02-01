package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.util.Constants;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Represents Duke chat-bot storage. Handle reading and
 * writing to file. Will create a new storage if the data
 * is not present.
 */
public class Storage {
    private TaskList taskList = new TaskList();

    /**
     * Load the data into tasklist if present, else create a new directory
     * to store the data and return empty tasklist.
     *
     * @return TaskList loaded from existing data
     * @throws IOException if fails to read the data.
     */
    public TaskList load() throws IOException {
        File directory = new File(Constants.DATA_DIRECTORY);
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                throw new IOException("Cannot create new directory");
            }
        }
        File dataFile = new File(directory, Constants.FILE_NAME);
        if (!dataFile.createNewFile()) {
            Scanner sc = new Scanner(dataFile);
            while (sc.hasNext()) {
                String[] line = sc.nextLine().strip().split(" <> ");
                Task currentTask;
                switch (line[0]) {
                case "T":
                    currentTask = new ToDo(line[2], line[1].equals("1"));
                    break;
                case "D":
                    currentTask = new Deadline(line[2], line[1].equals("1"), line[3]);
                    break;
                default:
                    currentTask = new Event(line[2], line[1].equals("1"), line[3]);
                    break;
                }
                taskList.addTask(currentTask);
            }
            sc.close();
        }
        return taskList;
    }

    /**
     * Update the data with the updated taskList. Create a temp file
     * to be written by the new data, replace the old data with temp
     *
     * @param taskList update taskList
     * @throws IOException if fails to write the data
     */
    public void updateStorage(TaskList taskList) throws IOException {

        this.taskList = taskList;

        File directory = new File(Constants.DATA_DIRECTORY);
        File tempFile = new File(directory, Constants.TEMP_FILE_NAME);
        File originalFile = new File(directory, Constants.FILE_NAME);

        PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

        for (Task task : taskList.listTasks()) {
            pw.write(task.encode());
        }

        pw.close();

        if (!originalFile.delete()) {
            throw new IOException("Cannot update file");
        }

        if (!tempFile.renameTo(originalFile)) {
            throw new IOException("Cannot update file");
        }
    }
}
