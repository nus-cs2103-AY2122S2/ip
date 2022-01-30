package bernie.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import bernie.tasks.Task;
import bernie.tasks.TaskList;

/**
 * Storage class handles the loading and saving of tasks in the file
 */
public class Storage {
    private static final String LINE_BREAK = "___________________________________________________________";
    private String root = System.getProperty("user.dir");
    private File tasksFile;
    private File dataDir;

    /**
     * Constructs the Storage class with the File tasksFile and dataDir.
     * tasksFile is the path of where we want to store the text file containing the
     * information of the tasks on our TaskList.
     * dataDir is the directory where the tasksFile is suppose to be in
     */
    public Storage() {
        this.tasksFile = new File(root + "/data", "Bernie.txt");
        this.dataDir = new File(root, "data");
    }

    /**
     * Loads the data when Bernie starts up if it exists and reads. If doesn't
     * exist, creates the required files
     */
    public void loadTasks() {
        try {
            if (tasksFile.exists() && dataDir.exists()) {
                System.out.println("On the list:");
                FileReader fileReader = new FileReader(tasksFile);
                BufferedReader reader = new BufferedReader(fileReader);
                while (true) {
                    String line = reader.readLine();
                    if (line == null) {
                        break;
                    }
                    System.out.println(line);
                }
                reader.close();
                System.out.println(LINE_BREAK);
            } else {
                // create dir and file
                dataDir.mkdir();
                tasksFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println(LINE_BREAK);
        }
    }

    /**
     * Handles the conditions for checking if the File exist before
     * saving the tasks with the save function. If File doesn't exist,
     * the required files will be created before save.
     * @param tasks TaskList, takes in the current tasks
     */
    public void saveTasks(TaskList tasks) {
        try {
            if (dataDir.exists() && tasksFile.exists()) {
                save(tasks);
            } else {
                // create dir and file
                dataDir.mkdir();
                tasksFile.createNewFile();
                save(tasks);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println(LINE_BREAK);
        }
    }

    /**
     * Saves the most updated tasks whenever the tasks changes upon
     * delete or add by writing the file. The file is saved to ./data/Bernie.txt
     * @params tasks TaskList, takes in the current tasks
     * @throws IOException for any IO errors
     */
    void save(TaskList tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(tasksFile);
        BufferedWriter writer = new BufferedWriter(fileWriter);
        if (tasks.isEmpty()) {
            writer.write("NOTHING! :D");
        }
        for (int i = 0; i < tasks.getSize(); i++) {
            Task currentTask = tasks.getTask(i);
            writer.write(String.format("%d. %s\n", i + 1, currentTask));
        }
        writer.close();
    }
}
