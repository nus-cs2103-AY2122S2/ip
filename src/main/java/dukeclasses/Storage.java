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
     * Returns the path of the storage file
     *
     * @return String representing the path of the storage file.
     */
    public String getStorageFilePath() {
        return filePath;
    }

    /**
     * Loads data of the storage file into an ArrayList</Task>. Usually called at the start of Duke.
     *
     * @return ArrayList</Task> that represents the data of the storage file given in the file path.
     * @throws DukeException If data in storage file is corrupted and not able to be converted to
     *                       individual tasks.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        File file = new File(filePath);
        if (Files.notExists(Paths.get(filePath))) {
            try {
                file.createNewFile();
            } catch (IOException errorMessage) {
                throw new DukeException();
            }
        }
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException errorMessage) {
            throw new DukeException();
        }
        while (sc.hasNext()) {
            String data = sc.nextLine();
            String[] processedData = data.trim().split("]", 3);

            if (processedData[0].contains("T")) {
                ToDo todo = new ToDo(processedData[2].trim());
                if (processedData[1].contains("X")) {
                    todo.setDone(true);
                }
                tasks.add(todo);
            } else {
                String[] processedDataDescription = processedData[2].split("\\(by:", 2);
                String temp = processedDataDescription[1].replace(")", "");

                LocalDate date = null;
                try {
                    date = LocalDate.parse(temp.trim(), DateTimeFormatter.ofPattern("MMM dd yyyy"));
                } catch (DateTimeParseException errorMessage) {
                    throw new DukeException();
                }

                if (processedData[0].contains("E")) {
                    Event event = new Event(processedDataDescription[0].trim(), date);
                    if (processedData[1].contains("X")) {
                        event.setDone(true);
                    }
                    tasks.add(event);

                } else if (processedData[0].contains("D")) {
                    Deadline deadline = new Deadline(processedDataDescription[0].trim(), date);
                    if (processedData[1].contains("X")) {
                        deadline.setDone(true);
                    }
                    tasks.add(deadline);

                } else {
                    throw new DukeException();
                }
            }
        }
        sc.close();
        return tasks;
    }

    /**
     * Updates the storage file
     *
     * @param dataArrList ArrayList</Task> that represents the updated taskList.
     * @throws DukeException If file is not writeable.
     */
    public void updateStorage(ArrayList<Task> dataArrList) throws DukeException {
        File file = new File(filePath);
        String updatedFileContents = "";

        for (int i = 0; i < dataArrList.size(); i++) {
            updatedFileContents = updatedFileContents.concat(
                String.format("    %s", dataArrList.get(i).identify()));
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
        File file = new File(filePath);
        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(String.format("    %s", task.identify()));
            fw.close();
        } catch (IOException errorMessage) {
            throw new DukeException();
        }
    }

}
