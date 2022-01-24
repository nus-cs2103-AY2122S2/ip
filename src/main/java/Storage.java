import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    private final String path;
    private final String directory;
    private final File database;


    public Storage(String path, String directory) throws IOException {
        this.path = path;
        database = new File(path);
        this.directory = directory;

        if (! database.exists()) {
            File directoryFile = new File(this.directory);
            directoryFile.mkdir();
            database.createNewFile();
        }
    }

    public void readFileContent(ArrayList<Task> storingList) throws IOException {
        FileReader fr = new FileReader(database);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            String type = String.valueOf(line.charAt(2));
            String status = String.valueOf(line.charAt(6));
            String description = line.substring(10, line.length() - 2);
            Task task;
            if (type.equals("D") || type.equals("E")) {
                task = new Task(description.replace("| ", "/"), type);
            } else {
                task = new Task(description, type);
            }
            if (status.equals("1")) {
                task.taskDone();
            }
            storingList.add(task);
        }
    }

    public void changeStatusTask(
            int lineNumber, Task task) throws IOException {
        List<String> lines = Files.readAllLines(database.toPath());
        lines.set(lineNumber - 1, task.toFileText());
        Files.write(database.toPath(), lines);
    }

     public void addTaskToText(Task task) throws IOException {
        FileWriter fileWriter = new FileWriter(path, true);
        fileWriter.write(task.toFileText() + System.lineSeparator());
        fileWriter.close();
    }

    public void deleteTask(int lineNumber) throws IOException {
        List<String> lines = Files.readAllLines(database.toPath());
        lines.remove(lineNumber);
        Files.write(database.toPath(), lines);
    }

    public long getDatabaseLength() {
        return this.database.length();
    }
}
