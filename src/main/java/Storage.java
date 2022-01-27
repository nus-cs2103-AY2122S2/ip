import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Storage {
    private final String filePath;
    private final File file;

    public Storage(String s) {
        filePath = s;
        file = new File(s);
    }

    public void loadTasks(TaskList tasks) throws PukeException {
        try {
            file.getParentFile().mkdir();
            file.createNewFile();
            Scanner fileSc = new Scanner(file);
            populateTasks(tasks, fileSc);
        } catch (IOException e) {
            throw new PukeException("Data file cannot be created!");
        }
    }

    private void populateTasks(TaskList tasks, Scanner s) {
        while (s.hasNext()) {
            String[] taskInfo = s.nextLine().split("@@");
            Task t = null;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            switch (taskInfo[0]) {
            case "T":
                t = new Todo(taskInfo[2]);
                break;
            case "D":
                t = new Deadline(taskInfo[2], LocalDateTime.parse(taskInfo[3], formatter));
                break;
            case "E":
                t = new Event(taskInfo[2], LocalDateTime.parse(taskInfo[3], formatter));
                break;
            }

            if (taskInfo[1].equals("1")) {
                t.mark();
            }

            tasks.addTaskToList(t);
        }
    }

    public void saveTasks(TaskList tasks) throws PukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(tasks.generateStorageData());
            fw.close();
        } catch (IOException e) {
            throw new PukeException("Tasks cannot be saved to file");
        }

    }
}
