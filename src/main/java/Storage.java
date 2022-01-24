import java.io.File;
import java.nio.file.Files;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadFromFile() throws IOException {
        Scanner sc;
        File directory = new File(Constants.FILE_PATH);
        ArrayList<Task> allTasks = new ArrayList<>();

        if (!directory.exists()) {
            directory.mkdir();
        }

        File file = new File(this.filePath);

        if (!file.exists()) {
            file.createNewFile();
        }

        sc = new Scanner(file);

        while (sc.hasNext()) {
            String task = sc.nextLine();
            String[] taskData = task.split("\\s\\|\\s");
            Boolean completed = Integer.parseInt(taskData[1]) == 1;

            if (task.charAt(0) == 'T') {
                Todo todo = new Todo(taskData[2]);

                todo.setCompleted(completed);
                allTasks.add(todo);
            } else if (task.charAt(0) == 'D') {
                Deadline deadline = new Deadline(
                        taskData[2], LocalDate.parse(taskData[3]));

                deadline.setCompleted(completed);
                allTasks.add(deadline);
            } else if (task.charAt(0) == 'E') {
                Event event = new Event(
                        taskData[2], LocalDate.parse(taskData[3]));

                event.setCompleted(completed);
                allTasks.add(event);
            }
        }

        sc.close();

        return allTasks;
    }

    public void writeToFile(String taskString) throws IOException {
        FileWriter fw = new FileWriter(this.filePath, true);

        fw.write(taskString);
        fw.write(System.getProperty("line.separator"));
        fw.close();
    }

    public void writeToFile(String taskString, int index, boolean isDelete) throws IOException {
        Path filePath = Paths.get(Constants.FILE_PATH, Constants.FILE_NAME);
        ArrayList<String> fileContent = new ArrayList<>(
                Files.readAllLines(filePath, StandardCharsets.UTF_8));

        if (isDelete) {
            fileContent.remove(index);
        } else {
            fileContent.set(index, taskString);
        }

        Files.write(filePath, fileContent);
    }
}
