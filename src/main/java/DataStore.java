import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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

    public static void loadData() {

    }
}
