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


public class Storage {
    private static final Path DATA_PATH = Paths.get("data", "duke.txt");

    private static void initialiseSaveFile() throws DukeException {
        try {
            if (Files.notExists(DATA_PATH)) {
                if(Files.notExists(DATA_PATH.getParent())) {
                    Files.createDirectory(DATA_PATH.getParent());
                    System.out.println("Creating");
                }
                Files.createFile(DATA_PATH);
            }
        } catch (IOException e) {
            throw new DukeException("Unable to create a save file");
        }
    }

    public void saveTasklist(TaskList taskList) throws DukeException {
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

    public static TaskList loadTasklist() throws DukeException{
        initialiseSaveFile();
        String strCurrentLine;
        Task currentTask;
        TaskList taskList = new TaskList();
        try {
            BufferedReader saveFilereader = new BufferedReader(new FileReader(DATA_PATH.toString()));
            while ((strCurrentLine = saveFilereader.readLine()) != null) {
                switch (strCurrentLine.charAt(0)) {
                case 'T' -> {
                    currentTask = Todo.createFromData(strCurrentLine);
                    taskList.addTask(currentTask);
                }
                case 'E' -> {
                    currentTask = Event.createFromData(strCurrentLine);
                    taskList.addTask(currentTask);
                }
                case 'D' -> {
                    currentTask = Deadline.createFromData(strCurrentLine);
                    taskList.addTask(currentTask);
                }
                }
            }
            saveFilereader.close();
        } catch (IOException e) {
            throw new DukeException("Unable to load save file");
        }

        return taskList;
    }



}

