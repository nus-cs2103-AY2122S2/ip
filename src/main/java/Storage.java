import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    // attributes
    private final String filePath;

    // constructor
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        FileReader fReader = new FileReader(String.valueOf(this.filePath));
        BufferedReader reader = new BufferedReader(fReader);
        String line;
        while ((line = reader.readLine()) != null) {
            String[] strArr;
            Task task = new Task("");
            strArr = line.split(" \\| ");
            switch (strArr[0]) {
                case "T":
                    task = new Todo(strArr[2]);
                    if (strArr[1].equals("1")) {
                        task.setDone();
                    }
                    break;
                case "D":
                    task = new Deadline(strArr[2], strArr[3]);
                    if (strArr[1].equals("X")) {
                        task.setDone();
                    }
                    break;
                case "E":
                    task = new Event(strArr[2], strArr[3]);
                    if (strArr[1].equals("X")) {
                        task.setDone();
                    }
                    break;
            }
            loadedTasks.add(task);
        }
        return loadedTasks;
    }

    public void mkDir() {
        String home = System.getProperty("user.home");
        java.nio.file.Path folderPath = java.nio.file.Paths.get(home, "data");
        File folder = new File(String.valueOf(folderPath));
        folder.mkdir();
        try {
            File file = new File(String.valueOf(filePath));
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file data.txt");
        }
    }

    public void save(TaskList tasks) throws IOException {
        FileWriter fWriter = new FileWriter(String.valueOf(filePath));
        BufferedWriter writer = new BufferedWriter(fWriter);
        StringBuilder output = new StringBuilder();
        for (Task task : tasks.getTaskList()) {
            output.append(task.getType()).append(" | ").append(task.getStatusIcon())
                    .append(" | ").append(task.getDescription());
            if (!task.getType().equals("T")) {
                output.append(" | ").append(task.getBy());
            }
            output.append("\n");
        }
        writer.write(output.toString());
        writer.close();
    }
}
