import java.io.File;
import java.nio.file.FileAlreadyExistsException;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private File file;

    public Storage() {
        try {
            Path path = Paths.get("data/");
            if (!Files.exists(path)) { Files.createDirectory(path); }
            this.file = new File(path + "/list.txt");
            this.file.createNewFile();
        } catch (FileAlreadyExistsException e) {
            e.getStackTrace();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    private Task parseInput(String input) {
        String[] arr = input.split(" === ");
        String type = arr[0];
        boolean isDone = arr[1].equals("1");
        Task task = null;
        switch (type) {
        case "D":
            task = new Deadline(arr[0], isDone, arr[2], arr[3]);
            break;
        case "E":
            task = new Event(arr[0], isDone, arr[2], arr[3]);
            break;
        case "T":
            task = new Todo(arr[0], isDone, arr[2]);
            break;
        default:
            break;
        }
        return task;
    }

    public void loadList(TaskList taskList) {
        try {
            Scanner s = new Scanner(this.file);
            while (s.hasNext()) {
                Task task = parseInput(s.nextLine());
                taskList.add(task, false);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public String processTaskToOutput(Task task) {
        String separator = " === ";
        int isDone = task.getIsDone() ? 1 : 0;
        return task.getTaskType() + separator + isDone + separator +
                task.getDescription() + separator + task.getDate();
    }

    public void updateList(TaskList taskList)  {
            try {
                String filePath = file.getPath();
                FileWriter fw = new FileWriter(filePath);
                List<Task> list = taskList.getReminders();
                for (Task task : list) {
                    String textToAdd = processTaskToOutput(task);
                    fw.write(textToAdd + System.lineSeparator());
                }
                fw.close();
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }

    }

}