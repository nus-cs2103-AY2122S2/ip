import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

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
    public void writeToDataFile(ArrayList<Task> list) {
        FileWriter fileWriter;

        // If directory for data file doesn't exist, try to create it first
        try {
            fileWriter = new FileWriter(this.dataFilePath);
        } catch (IOException e) {
            this.createDataFolder();
        }

        try {
            fileWriter = new FileWriter(this.dataFilePath);
            BufferedWriter bufferedWriter  = new BufferedWriter(fileWriter);

            for (int i = 0; i < list.size(); i++) {
                bufferedWriter.write(list.get(i).getSaveData());
                bufferedWriter.newLine();
            }

            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("[MNSKY had trouble committing the task list to its memory!]");
        }
    }

    /**
     * Retrieves all the tasks from the data file
     * @return A list of all the tasks from the data file in its raw string format,
     *  or an empty list if an error occurred.
     */
    public ArrayList<String> readFromDataFile() {
        try {
            Scanner fileScanner = new Scanner(new File(this.dataFilePath));
            ArrayList<String> taskList = new ArrayList<>();

            while (fileScanner.hasNext()) {
                taskList.add(fileScanner.nextLine());
            }

            return taskList;
        } catch (FileNotFoundException e) {
            this.writeToDataFile(new ArrayList<Task>());
        }

        return new ArrayList<String>();
    }
}
