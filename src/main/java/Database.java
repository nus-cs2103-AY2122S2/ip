import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Database {
    private final TaskList taskList;

    public Database(TaskList taskList) {
        this.taskList = taskList;
    }
    public void setup() {
        try {
            File directory = new File(Constants.DATA_DIRECTORY);
            if (!directory.exists()) {
                if (!directory.mkdirs()) {
                    throw new IOException("Cannot create new directory");
                }
            }
            File dataFile = new File(directory, Constants.FILE_NAME);

            if (!dataFile.createNewFile()) {
                Scanner sc = new Scanner(dataFile);
                while (sc.hasNext()) {
                    String[] line = sc.nextLine().strip().split(" <> ");
                    Task currentTask;
                    switch (line[0]) {
                        case "T":
                            currentTask = new ToDo(line[2], line[1].equals("1"));
                            break;
                        case "D":
                            currentTask = new Deadline(line[2], line[1].equals("1"), line[3]);
                            break;
                        default:
                            currentTask = new Event(line[2], line[1].equals("1"), line[3]);
                            break;
                    }
                    taskList.addTask(currentTask);
                }
                sc.close();
            }
        } catch (IOException e) {
            Duke.log("Cannot read data, please restart the application");
            e.printStackTrace();
        }

    }

    public void update() {
        try {
            File directory = new File(Constants.DATA_DIRECTORY);
            File tempFile = new File(directory, Constants.TEMP_FILE_NAME);
            File originalFile = new File(directory, Constants.FILE_NAME);

            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

            for (Task task : taskList.listTasks()) {
                pw.write(task.encode());
            }

            pw.close();

            if (!originalFile.delete()) {
                throw new IOException("Cannot update file");
            }

            if (!tempFile.renameTo(originalFile)) {
                throw new IOException("Cannot update file");
            }
        } catch (Exception e) {
            Duke.log("Failed to update the data");
            e.printStackTrace();
        }
    }
}
