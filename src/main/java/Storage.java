import java.io.File;
import java.nio.file.FileAlreadyExistsException;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private File file;

    public Storage(String filePath) {
        try {
            Path path = Paths.get("data/");
            if (!Files.exists(path)) { Files.createDirectory(path); }
            this.file = new File(filePath);
            this.file.createNewFile();
        } catch (FileAlreadyExistsException e) {
            e.getStackTrace();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    public List<Task> loadList() {
        List<Task> list = null;
        try {
            Scanner scanner = new Scanner(this.file);
            list = new ArrayList<>();
            while (scanner.hasNext()) {
                list.add(parseInput(scanner.nextLine()));
            }
            return list;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return new ArrayList<>();
        } catch (InvalidDateFormatException e) {
            return list;
        }
    }

    private Task parseInput(String input) throws InvalidDateFormatException {
        String[] arr = input.split("===");
        String taskType = arr[0].trim();
        boolean isDone = arr[1].trim().equals("1");
        Task task = null;
        switch (taskType) {
        case "D":
            task = new Deadline(arr[2].trim(), isDone, Ui.toLocalDateTime(arr[3].trim()));
            break;
        case "E":
            task = new Event(arr[2].trim(), isDone, Ui.toLocalDateTime(arr[3].trim()));
            break;
        case "T":
            task = new Todo(arr[2].trim(), isDone);
            break;
        default:
            break;
        }
        return task;
    }

    public void updateList(TaskList taskList)  {
        try {
            String filePath = file.getPath();
            FileWriter fw = new FileWriter(filePath);
            List<Task> list = taskList.getTaskList();
            for (Task task : list) {
                fw.write(task.writeToFile() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

}