import java.io.*;
import java.util.Arrays;

public class DataStore {
    final public static String PATH = "data/tasks.csv";

    public static void saveData(TaskType task, String[] inputs){
        File csvFile = new File(PATH);
        try {
            if (!csvFile.isFile()) {
                csvFile.createNewFile();
            }
            FileWriter csvWriter = new FileWriter(PATH,true);
            csvWriter.write(task.toString());
            csvWriter.write(',');
            csvWriter.write(inputs[0]);
            csvWriter.write(',');
            csvWriter.write(inputs[1]);
            csvWriter.write("\n");
            csvWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void loadData(TaskList tasks) {
        File csvFile = new File(PATH);
        try {
            if (csvFile.isFile()) {
                BufferedReader csvReader = new BufferedReader(new FileReader(PATH));
                String row = csvReader.readLine();
                while (row != null) {
                    String[] inputs = row.split(",");
                    tasks.add(parseTaskType(inputs[0]), Arrays.copyOfRange(inputs, 1, 2 + 1));
                }
                csvReader.close();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (EmptyDescriptionException e) {
            System.out.println(e.getMessage());
        }
    }

    public static TaskType parseTaskType(String taskType) {
        if (taskType.equals("todo")) {
            return TaskType.TODO;
        } else if (taskType.equals("event")){
            return TaskType.EVENTS;
        } else if (taskType.equals("deadline")){
            return TaskType.DEADLINE;
        }
        return null;
    }
}
