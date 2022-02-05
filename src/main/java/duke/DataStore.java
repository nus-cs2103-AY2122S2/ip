package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents Storage of the tasks
 */
public class DataStore {

    static final String PATH = "data/tasks.csv";
    static final String FOLDER_NAME = "data";
    static final int TASK_INDEX = 0;
    static final int COMPLETED_INDEX = 1;
    static final int DESCRIPTION_INDEX = 2;
    static final int TIME_INDEX = 3;


    /**
     * Saves tasks given
     *
     * @param tasks TaskList containing all tasks to be saved
     */
    public static void saveData(TaskList tasks) {
        File folder = new File(FOLDER_NAME);

        if (!folder.exists()) {
            folder.mkdir();
        }

        File csvFile = new File(PATH);

        try {
            if (!csvFile.isFile()) {
                csvFile.createNewFile();
            }

            FileWriter csvWriter = new FileWriter(PATH);

            for (int i = 1; i <= tasks.getLength(); i++) {
                Task task = tasks.getTask(i);
                String[] details = task.getDetails();

                csvWriter.write(details[TASK_INDEX]);
                csvWriter.write(',');
                csvWriter.write(details[COMPLETED_INDEX]);
                csvWriter.write(',');
                csvWriter.write(details[DESCRIPTION_INDEX]);
                csvWriter.write(',');
                csvWriter.write(details[TIME_INDEX]);
                csvWriter.write("\n");
            }
            csvWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Loads previously saved data from PATH
     *
     * @return TaskList with the previously saved tasks
     */
    public static TaskList loadData() {
        TaskList tasks = new TaskList();
        File csvFile = new File(PATH);

        if (!csvFile.isFile()) {
            return tasks;
        }

        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(PATH));

            String row = csvReader.readLine();
            while (row != null) {
                tasks.loadTask(row);
                row = csvReader.readLine();
            }
            csvReader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return tasks;
    }


}
