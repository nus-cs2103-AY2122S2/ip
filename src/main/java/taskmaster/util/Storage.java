package taskmaster.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

import taskmaster.exception.TaskmasterExceptions;
import taskmaster.task.Task;

/**
 * This class encapsulates Storage which handles
 * reading and writing of information to the text
 * file.
 */

public class Storage {
    /** The file storing the task data. **/
    private final File DATA_FILE;
    /** The path in data file is located **/
    private final String FILE_PATH;

    /**
     * Constructor of Storage.
     * @param filePath The path for the data file.
     */
    public Storage(String filePath) {
        this.DATA_FILE = new File(filePath);
        this.FILE_PATH = filePath;
    }

    /**
     * Helper function to create directory if it does not exist.
     * Might throw SecurityException if unable to write at directory.
     * Not necessary to catch since it's a RuntimeException.
     */

    private void createDirectory() {
        String path = Paths.get("").toAbsolutePath() + "/data/";
        File dir = new File(path);
        //Create Directory.
        dir.mkdir();
    }

    /**
     * Helper function to create file if the file does not exist
     */

    private void createFile() throws TaskmasterExceptions {
        try {
            //Create new file.
            DATA_FILE.createNewFile();
        } catch (IOException e) {
            throw new TaskmasterExceptions("Unable to create new file.");
        }
    }

    /**
     *  Parse the information from the text file and add
     *  the events parsed to the task list.
     *
     * @param taskList task list the tasks will be added to.
     *
     * @exception TaskmasterExceptions if unable to load file.
     */

    public void loadFile(TaskList taskList) throws TaskmasterExceptions {
        String dir = Paths.get("").toAbsolutePath() + "/data/";
        File directory = new File(dir);
        boolean doesDirExist = directory.exists();
        boolean doesDataFileExist = DATA_FILE.exists();
        if (!doesDirExist) {
            createDirectory();
        }
        try {
            if (!doesDataFileExist) {
                createFile();
            }
            Scanner reader = new Scanner(DATA_FILE);
            ParseFiles parser = new ParseFiles();
            //Read File line by line using Scanner.
            while (reader.hasNextLine()) {
                Task currentTask = parser.parseTask(reader.nextLine());
                taskList.add(currentTask);
            }
            reader.close();
        } catch (IOException e) {
            throw new TaskmasterExceptions("Unable to read or write to storage!");
        }
    }

    /**
     * Updates the list of events on the text file based on the
     * list of events that is currently present in the task list.
     *
     * @param taskList Current Task List.
     */
    public void updateList(TaskList taskList) {
        try {
            //Delete file if it exists to ensure that the existing tasks do not duplicate on
            //the new data file.
            if (DATA_FILE.exists()) {
                DATA_FILE.delete();
            }
            FileWriter writer = new FileWriter(FILE_PATH);
            //Write the task list's tasks to the data file.
            writer.write(taskList.listTasksInTextFormat());
            writer.close();
        } catch (IOException e) {
            System.out.println("ERROR: Writing task lists to file");
        }
    }

}
