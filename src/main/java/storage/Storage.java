package storage;

import duke.DukeException;
import task.TaskList;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Storage class to store and writes tasks from file.
 */
public class Storage implements IStorage{
    private final String saveFilePath;

    /**
     * Constructor for Storage.
     * @param saveFileDirectory directory for save file.
     * @param saveFileName name of the save file.
     * @throws DukeException
     */
    public Storage(String saveFileDirectory, String saveFileName) {
        this.saveFilePath = saveFileDirectory + saveFileName;
        File fileDirectory = new File(saveFileDirectory);
        if (!fileDirectory.exists()) {
            fileDirectory.mkdir();
        }

        File saveFile = new File(this.saveFilePath);
        if (!saveFile.exists()) {
            try {
                saveFile.createNewFile();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    /**
     * Read tasks from file.
     * @return TaskList tasks parsed from the file.
     * @throws DukeException
     */
    public TaskList readFile() throws DukeException {
        TaskList taskList = new TaskList();
        File file = new File(saveFilePath);

        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                taskList.addTaskNoMessage(processLine(sc.nextLine()));
            }
        } catch (FileNotFoundException e) {
            throw DukeException.DukeFileNotFoundException();
        } catch (DukeException d) {
            throw d;
        }
        return taskList;
    }

    /**
     * Write tasks to the file.
     * @param taskList tasks that are supposed to be written to the file.
     * @throws DukeException
     */
    public void writeToFile(TaskList taskList) throws DukeException {
        assert taskList != null;
        StringBuilder msg = new StringBuilder();
        for (Task task : taskList.getTasks()) {
            msg.append(task.getSaveMessage()).append("\n");
        }
        try {
            FileWriter fw = new FileWriter(saveFilePath);
            fw.write(msg.toString());
            fw.close();
        } catch (IOException e) {
            throw DukeException.DukeIOException();
        }
    }

    /**
     * Processes saved tasks from the file.
     * @param line each line from the file.
     * @return Task task corresponding to the line.
     * @throws DukeException
     */
    public Task processLine(String line) throws DukeException { //can add exception when handling the lines for possible corrupted file
        assert line != null;
        String[] description = line.split(" / ");
        try {
            switch (description[0]) {
            case "T":
                if (description[1].equals("1")) {
                    return new Todo(description[2], true);
                } else {
                    return new Todo(description[2], false);
                }
            case "D":
                if (description[1].equals("1")) {
                    return new Deadline(description[2], true, LocalDate.parse(description[3]));
                } else {
                    return new Deadline(description[2], false, LocalDate.parse(description[3]));
                }
            case "E":
                if (description[1].equals("1")) {
                    return new Event(description[2], true, LocalDate.parse(description[3]));
                } else {
                    return new Event(description[2], false, LocalDate.parse(description[3]));
                }
            default:
                throw DukeException.DukeFileIncomplete();
            }
        } catch (Exception e) {
            throw DukeException.DukeFileIncomplete();
        }
    }
}
