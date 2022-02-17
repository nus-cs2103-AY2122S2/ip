package duke.data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.dukeexceptions.DukeException;
import duke.dukeexceptions.ForeignException;
import duke.dukeexceptions.StorageErrorException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;


/**
 * Storage is a class that allows users to access a persist storage
 */
public class Storage {
    private File persistStore;
    private TaskList runningTaskList;

    /**
     * Constructs the contents of the Storage with a TaskList
     * @param runningTaskList that to be added to the Storage
     */
    public Storage(TaskList runningTaskList) {
        this.persistStore = new File("data/command.txt");
        this.runningTaskList = runningTaskList;
    }

    /**
     * Function that is run upon initialisation of the storage.
     * Load the persist storage into a TaskList.
     * @throws DukeException if storage error is encountered.
     */
    public void initialiseStorage() throws DukeException {
        File data = new File("data");
        try {
            if (data.exists()) {
            } else {
                data.mkdirs();
            }
            if (persistStore.exists()) {
            } else {
                persistStore.createNewFile();
            }
        } catch (Exception e) {
            throw new StorageErrorException("");
        }
    }

    private static boolean convertMarkToBoolean(String mark) {
        return mark.equals("1");
    }

    private static Task convertStorageToTask(String taskString) {
        String[] stringCmdUnits = taskString.split(" \\| ");
        switch (stringCmdUnits[0]) {
        case "T":
            return new ToDo(stringCmdUnits[2], convertMarkToBoolean(stringCmdUnits[1]));
        case "D":
            return new Deadline(stringCmdUnits[2], convertMarkToBoolean(stringCmdUnits[1]), stringCmdUnits[3]);
        case "E":
            return new Event(stringCmdUnits[2], convertMarkToBoolean(stringCmdUnits[1]), stringCmdUnits[3]);
        default:
            return null;
        }
    }

    /**
     * Function that loads from permanent storage to the TaskList.
     * @return TaskList from storage.
     * @throws DukeException if storage error is encountered.
     */
    public TaskList loadFromDisk() throws DukeException {
        try {
            Scanner fileReader = null;
            fileReader = new Scanner(persistStore);
            TaskList taskList = new TaskList();
            while (fileReader.hasNext()) {
                String command = fileReader.nextLine();
                taskList.addTask(convertStorageToTask(command));
            }
            return taskList;
        } catch (Exception e) {
            throw new StorageErrorException("");
        }
    }

    /**
     * Function that loads the TaskList into the permanent storage.
     * @param taskList tasklist
     * @throws DukeException exception
    */
    public void loadToDisk(TaskList taskList) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(persistStore);
            for (int i = 0; i < taskList.taskLength(); i++) {
                fileWriter.write(taskList.getTask(i).toStore() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new ForeignException("");
        }
    }
}
