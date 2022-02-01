import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Storage {
    private static String filePath;
    private static File file;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
    }

    public static void saveToFile(TaskList taskList) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        for (int i = 0; i < taskList.size(); i++) {
            fileWriter.write(taskList.get(i).toStorageString() + "\n");
        }
        fileWriter.close();
    }

    public static void readFromFile(TaskList taskList) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String[] taskInfo = scanner.nextLine().split("#");
            Task newTask = null;
            switch (taskInfo[0]) {
                case "T":
                    newTask = new Todo(taskInfo[2]);
                    break;
                case "D":
                    newTask = new Deadline(taskInfo[2], LocalDateTime.parse(taskInfo[3], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                    break;
                case "E":
                    newTask = new Event(taskInfo[2], LocalDateTime.parse(taskInfo[3], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                    break;
            }
            if (taskInfo[1].equals("X")) {
                newTask.markAsDone();
            }
            taskList.add(newTask);
        }
    }
}
