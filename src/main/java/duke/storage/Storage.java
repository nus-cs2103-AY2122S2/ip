package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Represents the storage system of the program. A <code>Storage</code> object can be created to
 * save and load the task list data into the hard disk. It allows data to be retained to the
 * next instance of the program.
 */
public class Storage {
    public static final String MESSAGE_READ_FAILURE = "Something went wrong with file read!";
    public static final String MESSAGE_WRITE_FAILURE = "Something went wrong with file write!";
    public static final String MESSAGE_INVALID_FILE = "File Corrupted!";

    private final String filePath;

    /**
     * Creates an instance of a Storage object.
     *
     * @param inputPath path to the storage file.
     * @throws IOException if the creation of directory and text file fails.
     */
    public Storage(String inputPath) throws IOException {
        this.filePath = System.getProperty("user.dir") + inputPath;

        File file = new File(this.filePath);
        file.getParentFile().mkdirs();
        file.createNewFile();
    }

    /**
     * Saves the task list by writing the data of the tasks into
     * the file specified in the Storage object.
     *
     * @param taskList the task list to be saved.
     * @throws DukeException if write to file fails.
     */
    public void saveTaskList(TaskList taskList) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);

            for (int i = 0; i < taskList.size(); i++) {
                Task curTask = taskList.get(i);

                fw.write(curTask.toData());
                fw.write(System.lineSeparator());
            }
            fw.close();

        } catch (IOException e) {
            throw new DukeException(MESSAGE_WRITE_FAILURE);
        }
    }

    /**
     * Returns a task list by reading the data of the file
     * specified in the Storage object.
     *
     * @return a saved task list.
     * @throws DukeException if write to file fails.
     */
    public TaskList loadTaskList() throws DukeException {
        try {
            ArrayList<Task> taskArr = new ArrayList<>();
            File file = new File(filePath);
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                String[] dataArgs = data.split("\\|");

                Task curTask = dataToTask(dataArgs);
                taskArr.add(curTask);
            }

            return new TaskList(taskArr);

        } catch (FileNotFoundException e) {
            throw new DukeException(MESSAGE_READ_FAILURE);
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    private Task dataToTask(String[] dataArgs) throws IOException {
        try {
            int taskDone = Integer.parseInt(dataArgs[1]);
            boolean isMarkValid = taskDone == 1 || taskDone == 0;
            boolean isTaskMark = taskDone == 1;

            if (!isMarkValid) {
                throw new IOException(MESSAGE_INVALID_FILE);
            }

            switch (dataArgs[0]) {
            case "T":
                return new Todo(dataArgs[2], isTaskMark);
            case "D":
                return new Deadline(dataArgs[2], isTaskMark, LocalDate.parse(dataArgs[3]),
                        LocalTime.parse(dataArgs[4]));
            case "E":
                return new Event(dataArgs[2], isTaskMark, LocalDate.parse(dataArgs[3]),
                        LocalTime.parse(dataArgs[4]), LocalTime.parse(dataArgs[5]));
            default:
                throw new IOException(MESSAGE_INVALID_FILE);
            }
        } catch (NumberFormatException e) {
            throw new IOException(MESSAGE_INVALID_FILE);
        }
    }

}
