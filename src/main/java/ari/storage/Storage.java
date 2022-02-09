package ari.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import ari.command.Command;
import ari.parser.Parser;
import ari.tasks.Task;
import ari.tasks.TaskList;

/**
 * Deals with loading tasks from file and saving tasks into file
 */
public class Storage {
    protected File filePath;
    private String path;

    public Storage() {
    }

    /**
     * Sets path to save/load file
     *
     * @param filePath file path to save file or load file
     */
    public void setFile(String filePath) {
        path = filePath;

        this.filePath = new File(filePath);

        if (!this.filePath.exists()) {
            initializeFile();
        }
    }

    /**
     * Creates file at specified location
     */
    private void initializeFile() {
        String[] paths = path.split("/");
        String tempPath = "";
        for (String p : paths) {
            tempPath += p + "/";
            File file = new File(tempPath);
            createFile(file);
        }
    }

    private void createFile(File path) {
        if (path.toString().contains(".")) {
            try {
                path.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            path.mkdir();
        }
    }

    /**
     * Saves tasks in file
     *
     * @param taskList list of Tasks to save
     */
    public void save(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getAllTasks();

        try {
            writeTasksInFile(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void writeTasksInFile(ArrayList<Task> tasks) throws IOException {
        FileWriter wFile = new FileWriter(this.filePath);
        for (Task task : tasks) {
            wFile.write(task.writeToFile());
            wFile.write(System.lineSeparator());
        }
        wFile.close();
    }

    /**
     * Loads the task into a list if the file exists
     *
     * @return list of Tasks present in the file
     */
    public TaskList load() {
        File file = new File(path);
        TaskList taskList = new TaskList();

        try {
            Scanner reader = new Scanner(file);
            Parser parser = new Parser();
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                ArrayList<Command> commands = parser.fileParse(line, taskList.getSize() + 1);
                executeCommands(commands, taskList);
            }

            reader.close();
        } catch (FileNotFoundException fileEx) {
            // if file is not found don't do anything
        }

        return taskList;
    }

    private void executeCommands(ArrayList<Command> commands, TaskList taskList) {
        for (Command command : commands) {
            command.setTaskList(taskList);
            command.execute();
        }
    }
}
