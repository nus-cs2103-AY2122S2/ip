package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 * Represents Storage of the tasks
 */
public class DataStore {

    /**
     * Path to store data
     */
    static final String PATH = "data/tasks.csv";


    /**
     * Saves tasks given
     *
     * @param tasks TaskList containing all tasks to be saved
     */
    public static void saveData(TaskList tasks) {
        File folder = new File("data");
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

                csvWriter.write(details[0]);
                csvWriter.write(',');
                csvWriter.write(details[1]);
                csvWriter.write(',');
                csvWriter.write(details[2]);
                csvWriter.write(',');
                csvWriter.write(details[3]);
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
        try {
            if (csvFile.isFile()) {
                BufferedReader csvReader = new BufferedReader(new FileReader(PATH));
                String row = csvReader.readLine();
                while (row != null) {
                    String[] inputs = row.split(",");
                    TaskType type = parseTaskType(inputs[0]);
                    assert type != null : "Task type cannot be null";
                    tasks.loadTasks(type, Arrays.copyOfRange(inputs, 1, 4));
                    row = csvReader.readLine();
                }
                csvReader.close();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return tasks;
    }

    /**
     * Parses string into TaskType enum
     *
     * @param taskType String indicating type of task
     * @return TaskType enum indicating type of task
     */
    private static TaskType parseTaskType(String taskType) {
        taskType = taskType.toLowerCase();
        switch (taskType) {
        case "todo":
            return TaskType.TODO;
        case "event":
            return TaskType.EVENT;
        case "deadline":
            return TaskType.DEADLINE;
        default:
            return null;
        }
    }
}
