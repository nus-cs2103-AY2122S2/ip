package dukeclasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Modifies the storage file that is used in permenant storage of data.
 */
public class Storage {
    private String filePath;

    /**
     * Constructor for Storage.
     *
     * @param filePath that indicates the path of the storage file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads data of the storage file into an ArrayList</Task>. Usually called at the start of Duke.
     *
     * @return ArrayList</Task> that represents the data of the storage file given in the file path.
     * @throws DukeException If data in storage file is corrupted and not able to be converted to
     *                       individual tasks.
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            ArrayList<Task> tasks = new ArrayList<Task>();
            File openedFile = openFile(filePath);
            tasks = scanFileAndAddTask(openedFile, tasks);
            return tasks;
        } catch (DukeException error) {
            throw new DukeException();
        }
    }

    /**
     * Creates Task from the data given as a String.
     *
     * @param data String that represents the Task.
     * @return Task that represents the String argument.
     * @throws DukeException If data is an event or deadline and does not have a valid date.
     */
    private Task createTaskFromStorageFile(String data) throws DukeException {
        String[] processedData = data.trim().split("]", 3);
        Task task;
        if (processedData[0].contains("T")) {
            task = new ToDo(processedData[2].trim());
            if (processedData[1].contains("X")) {
                task.setDone(true);
            }
            return task;
        }

        String[] processedDataDescription = processedData[2].split("\\(by:", 2);
        String temp = processedDataDescription[1].replace(")", "");

        LocalDate date;
        try {
            date = LocalDate.parse(temp.trim(), DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException errorMessage) {
            throw new DukeException();
        }

        if (processedData[0].contains("E")) {
            task = new Event(processedDataDescription[0].trim(), date);
        } else if (processedData[0].contains("D")) {
            task = new Deadline(processedDataDescription[0].trim(), date);
        } else {
            throw new DukeException();
        }

        if (processedData[1].contains("X")) {
            task.setDone(true);
        }
        return task;
    }

    /**
     * Creates a File object based on the given path.
     *
     * @param path String that represents path of data file.
     * @return File object that represents the data file.
     * @throws DukeException If an I/O error occurs.
     */
    private File openFile(String path) throws DukeException {
        File file = new File(path);
        if (Files.notExists(Paths.get(path))) {
            try {
                file.createNewFile();
            } catch (IOException errorMessage) {
                throw new DukeException();
            }
        }
        return file;
    }

    /**
     * Scans data in File and add them as tasks into a TaskList that is returned.
     *
     * @param file File that represents the storage file.
     * @param tasks ArrayList that represent the tasks in the storage file.
     * @return ArrayList</Task> that represents the tasks in the storage file.
     * @throws DukeException If file object given to Scanner class does not exist or if
     *                       error occur during processing of storage file.
     */
    private ArrayList<Task> scanFileAndAddTask(File file, ArrayList<Task> tasks) throws DukeException {
        assert file != null: "command should not be null.";
        assert tasks != null: "command Task should not be null.";
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                Task task = createTaskFromStorageFile(sc.nextLine());
                tasks.add(task);
            }
            sc.close();
            return tasks;
        } catch (DukeException|FileNotFoundException error) {
            throw new DukeException();
        }
    }

    /**
     * Updates the storage file.
     *
     * @param dataArrList ArrayList</Task> that represents the updated taskList.
     * @throws DukeException If file is not writeable.
     */
    public void updateStorageFile(ArrayList<Task> dataArrList) throws DukeException {
        String updatedFileContents = "";

        for (int i = 0; i < dataArrList.size(); i++) {
            updatedFileContents = updatedFileContents.concat(
                String.format("    %s", dataArrList.get(i).toString()));
        }

        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(updatedFileContents);
            fw.close();
        } catch (IOException errorMessage) {
            throw new DukeException();
        }
    }

    /**
     * Adds a new task to the storage file.
     *
     * @param task Task that is to be added to the end of the storage file.
     * @throws DukeException If file is not writeable.
     */
    public void appendToStorage(Task task) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(String.format("    %s", task.toString()));
            fw.close();
        } catch (IOException errorMessage) {
            throw new DukeException();
        }
    }

}
