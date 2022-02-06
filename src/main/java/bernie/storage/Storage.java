package bernie.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import bernie.commands.CommandHandler;
import bernie.tasks.Task;
import bernie.tasks.TaskList;

/**
 * Storage class handles the loading and saving of tasks in the file
 */
public class Storage {
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
     * @param commandHandler CommandHandler, to initialize the tasks on load
     * @return String, the resulting message
     */
    public String loadTasks(CommandHandler commandHandler) {
        try {
            if (tasksFile.length() == 0) {
                String emptyListMsg = "Nothing on your previous list!";
                return emptyListMsg;
            }
            if (tasksFile.exists() && dataDir.exists()) {
                StringBuilder s = new StringBuilder();
                s.append("Previously on the list:\n");
                FileReader fileReader = new FileReader(tasksFile);
                BufferedReader reader = new BufferedReader(fileReader);
                String outputMsg = loadLines(s, commandHandler, reader);
                reader.close();
                return outputMsg;
            } else {
                // create dir and file
                dataDir.mkdir();
                tasksFile.createNewFile();
                boolean fileExists = tasksFile.exists();
                assert fileExists;
                String noFileMsg = "Didn't find any existing task files, created one for you!";
                return noFileMsg;
            }
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    /**
     * Gets the reader to read each line. Each line will be concatenated
     * via the StringBuilder to display the resulting output message for
     * the user while simultaneously getting commandHandler to initialise
     * these tasks by taking the line as input.
     * @param s StringBuilder, to build output message
     * @param commandHandler CommandHandler, to initialise tasks via the line
     * @param reader BufferedReader, to read the lines of our text file
     * @return String, the resuling output message after reading lines
     * @throws IOException in the case of errors
     */
    String loadLines(StringBuilder s, CommandHandler commandHandler,
                     BufferedReader reader) throws IOException {
        while (true) {
            String line = reader.readLine();
            if (line == null) {
                break;
            }
            commandHandler.initTaskOnLoad(line);
            s.append(line + "\n");
        }
        return s.toString();
    }

    /**
     * Handles the conditions for checking if the File exist before
     * saving the tasks with the save function. If File doesn't exist,
     * the required files will be created before save.
     * @param tasks TaskList, takes in the current tasks
     * @return String, the resulting message
     */
    public String saveTasks(TaskList tasks) {
        try {
            if (!dataDir.exists() || !tasksFile.exists()) {
                // create dir and file
                dataDir.mkdir();
                tasksFile.createNewFile();
                boolean fileExists = tasksFile.exists();
                assert fileExists;
            }
            save(tasks);
            String successMsg = "Successfully saved current tasks.";
            return successMsg;
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    /**
     * Saves the most updated tasks whenever the tasks changes upon
     * delete or add by writing the file. The file is saved to ./data/Bernie.txt
     * @params tasks TaskList, takes in the current tasks
     * @return String, the resulting message
     * @throws IOException for any IO errors
     */
    void save(TaskList tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(tasksFile);
        BufferedWriter writer = new BufferedWriter(fileWriter);
        if (tasks.isEmpty()) {
            String noTasksMsg = "NOTHING! :D";
            writer.write(noTasksMsg);
        }
        for (int i = 0; i < tasks.getSize(); i++) {
            Task currentTask = tasks.getTask(i);
            writer.write(String.format("%d. %s\n", i + 1, currentTask));
        }
        writer.close();
    }
}
