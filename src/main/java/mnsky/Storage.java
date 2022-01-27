package mnsky;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

import mnsky.exceptions.MnskyException;
import mnsky.task.Task;

public class Storage {
    private String dataFilePath;

    public Storage(String dataFilePath) {
        this.dataFilePath = dataFilePath;
    }

    /**
     * Creates the data folder.
     */
    private void createDataFolder() {
        File newFolder = new File("data/");
        newFolder.mkdir();
    }

    /**
     * Saves the current state of the list to the data file.
     */
    public void writeToDataFile(TaskList taskList) throws MnskyException {
        FileWriter fileWriter;

        // If directory for data file doesn't exist, try to create it first
        try {
            fileWriter = new FileWriter(dataFilePath);
        } catch (IOException e) {
            createDataFolder();
        }

        try {
            fileWriter = new FileWriter(dataFilePath);
            BufferedWriter bufferedWriter  = new BufferedWriter(fileWriter);
            ArrayList<String> storageDatas = taskList.getStorageDatas();

            for (String storageData : storageDatas) {
                bufferedWriter.write(storageData);
                bufferedWriter.newLine();
            }

            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            throw new MnskyException("[MNSKY had trouble committing the task list to its memory!]");
        }
    }

    /**
     * Retrieves all the tasks from the data file
     * @return A list of all the tasks from the data file in its raw string format,
     *  or an empty list if an error occurred.
     */
    public ArrayList<String> readFromDataFile() throws MnskyException {
        try {
            Scanner fileScanner = new Scanner(new File(dataFilePath));
            ArrayList<String> tasks = new ArrayList<>();

            while (fileScanner.hasNext()) {
                tasks.add(fileScanner.nextLine());
            }

            return tasks;
        } catch (FileNotFoundException e) {
            throw new MnskyException("[MNSKY is having trouble remembering the previous task list...]\n");
        }
    }
}
