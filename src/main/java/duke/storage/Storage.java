package duke.storage;

import duke.exceptions.DukeException;
import duke.tasks.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;


/**
 * This class saves and loads data from save file.
 */
public class Storage {

    private static final Path DATA_PATH = Paths.get("data", "duke.txt");

    private static void initialiseSaveFile() throws DukeException {
        try {
            if (Files.notExists(DATA_PATH)) {
                if(Files.notExists(DATA_PATH.getParent())) {
                    Files.createDirectory(DATA_PATH.getParent());
                }
                Files.createFile(DATA_PATH);
            }
        } catch (IOException e) {
            throw new DukeException("Unable to create a save file");
        }
    }

    /**
     * Writes TaskList data to save file.
     *
     * @param taskList TaskList to write to save file.
     * @throws DukeException If there is an error writing to save file.
     */
    public void saveTasklist(TaskList taskList) throws DukeException {
        assert taskList != null : "Null tasklist to save";
        initialiseSaveFile();
        String dataToWrite = taskList.toSaveData();
        try {
            FileWriter saveFileWriter = new FileWriter(DATA_PATH.toString(), false);
            saveFileWriter.write(dataToWrite);
            saveFileWriter.close();
        } catch (IOException e) {
            throw new DukeException("Unable to write into save file");
        }
    }

    /**
     * Loads TaskList data from save file.
     *
     * @return new TaskList recreated from data.
     * @throws DukeException If data cannot be loaded from save file.
     */
    public static TaskList loadTasklist() throws DukeException{
        initialiseSaveFile();
        String strCurrentLine;
        Task currentTask = null;
        TaskList taskList = new TaskList();
        try {
            BufferedReader saveFilereader = new BufferedReader(new FileReader(DATA_PATH.toString()));
            while ((strCurrentLine = saveFilereader.readLine()) != null) {
                switch (strCurrentLine.charAt(0)) {
                case 'T' : {
                    currentTask = Todo.createFromData(strCurrentLine);
                    break;
                }
                case 'E' : {
                    currentTask = Event.createFromData(strCurrentLine);
                    break;
                }
                case 'D' : {
                    currentTask = Deadline.createFromData(strCurrentLine);
                    break;
                }
                }
                taskList.addTask(currentTask);
            }
            saveFilereader.close();
        } catch (IOException e) {
            throw new DukeException("Unable to load save file");
        }
        return taskList;
    }
}

