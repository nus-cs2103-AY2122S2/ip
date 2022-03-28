package duke;

import java.util.ArrayList;

import java.io.File;

/**
 * Class {@code Storage} for storing task into TaskList
 */
public class Storage {
    ArrayList<Task> taskList = new ArrayList<>();
    private final FileHandler fileHandler;

    /**
     * Overloaded Constructor with filepath
     *
     * @param filePath filePath to File for storing
     */
    public Storage(String filePath) {
        this.fileHandler = new FileHandler();
        loadTask(taskList, filePath);
        loadTags(taskList);
    }

    /**
     * Method to load all tags from File
     *
     * @param taskList Current session's TaskList
     */
    public void loadTags(ArrayList<Task> taskList) {
        String currentDirectory = System.getProperty("user.dir");
        String path = currentDirectory + "\\src\\main\\java\\duke\\data\\tagList.txt";
        path = path.replace("\\", File.separator);
        fileHandler.handleLoadTags(taskList,path);
    }
    /**
     * Return all task stored within File.
     *
     * @return ArrayList of current stored Task
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Method to load all task from File, by supplied filePath.
     *
     * @param taskList Current session's TaskList
     * @param filePath FilePath for where File is stored.
     */
    public void loadTask(ArrayList<Task> taskList, String filePath) {
        String currentDirectory = System.getProperty("user.dir");
        String path = currentDirectory + "\\src\\main\\java\\duke\\data\\duke.txt";
        path = path.replace("\\", File.separator);
        fileHandler.handleLoadTask(taskList, path);
    }

    /**
     * Method to save all Task in given ArrayList of Tasks to File.
     *
     * @param taskList ArrayList of current session's Task.
     */
    // Reusable code for writing into duke.txt task list
    public void saveToTaskList(ArrayList<Task> taskList) {
        String currentDir = System.getProperty("user.dir");
        String tasksPath = currentDir + "\\src\\main\\java\\duke\\data\\duke.txt";
        tasksPath = tasksPath.replace("\\", File.separator);

        String tagsPath = currentDir + "\\src\\main\\java\\duke\\data\\tagList.txt";
        tagsPath = tagsPath.replace("\\", File.separator);
        fileHandler.handleSave(taskList, tasksPath, tagsPath);
    }
}



