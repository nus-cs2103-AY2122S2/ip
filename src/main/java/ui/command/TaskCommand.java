package ui.command;

import task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Jiaaa-yang
 *
 * Command which operates on list of task
 * maintained by ChatBot
 */
public abstract class TaskCommand extends Command {
    /**
     * Task list maintained by ChatBot.
     */
    private final ArrayList<Task> taskList;

    /**
     * Data file corresponding to saved task list on disk.
     */
    private final File dataFile;

    public TaskCommand(String name, String args, ArrayList<Task> taskList, File dataFile) {
        super(name, args);
        this.taskList = taskList;
        this.dataFile = dataFile;
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Overwrites content of data file with current
     * state of task list.
     */
    protected void saveTasksToFile() {
        try {
            FileWriter writer = new FileWriter(dataFile);
            for (Task task : taskList) {
                String taskData = task.getSeralisedTaskData();
                writer.write(taskData + "\n");
            }
            writer.close();
        } catch (IOException e) {
            // TODO: Recovery from invalid writing of task data
            System.err.println("Cannot save tasks to file");
        }
    }
}
