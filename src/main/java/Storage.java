import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String FILE_PATH = "";
    private String DIR_PATH = "";
    private final String FILE_SEPERATOR = "/";
    File file;

    public Storage(){}
    public Storage(String path) {
        FILE_PATH = System.getProperty("user.home") + FILE_SEPERATOR + path;
        String dir = path.substring(0,path.lastIndexOf("/"));
        DIR_PATH = System.getProperty("user.home") + FILE_SEPERATOR + dir;
    }

    public TaskManager loadTaskManagerFromFile() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        file = new File(FILE_PATH);
        try {
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                while(scanner.hasNext()){
                    String fileInput = scanner.nextLine();
                    try {
                        Task t = Parser.parseToTaskFromFile(fileInput);
                        tasks.add(t);
                    } catch (FailedTaskParseException exception) {
                        // UI.showError??
                        continue;
                    }
                }
                return new TaskManager(tasks);
            } else {
                return new TaskManager();
            }
        } catch (SecurityException | IOException exception) {
            return new TaskManager();
        }
    }
    public boolean saveTaskManager(TaskManager taskManager) {
        file = new File(FILE_PATH);
        try {
            FileWriter writer = new FileWriter(file);

            for ( Task task: taskManager.getTaskList()) {
                String date ="None";
                if (task.getDate() != null) {
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                    date = task.getDate().format(format).toString();
                }
                writer.write(String.format("%c\t%c\t%s\t%s\n",task.getType(),task.getDone(),task.getTaskName(),date));
            }

            writer.close();
            return true;
        } catch (IOException exception){
            return false;
        }
    }

    public boolean dirExists() {
        File dir = new File(DIR_PATH);
        return dir.exists();
    }
    public boolean createDir() {
        File dir = new File(DIR_PATH);
        return dir.mkdir();
    }
}