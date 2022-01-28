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

    public TaskManager loadTaskManagerFromFile() throws DukeException {
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
                    } catch (DukeException exception) {
                        // UI.showError??
                        continue;
                    }
                }
                return new TaskManager(tasks);
            } else {
                throw new DukeException("Unable to read Task List from file!");
            }
        } catch (SecurityException | IOException exception) {
            throw new DukeException("Unable to read Task List from file!");
        }
    }
    public boolean saveTaskManager(TaskManager taskManager) throws DukeException {

        try {

            file = new File(FILE_PATH);

            if (!dirExists()) {
                boolean dirCreated = createDir();
                if (!dirCreated){
                    throw new IOException("Unable to create directory!");
                }
            }

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
            throw new DukeException("Unable to save to disk!");
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