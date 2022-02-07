package chatcat.commands;

import java.util.ArrayList;

import chatcat.tasks.Task;
import chatcat.util.WriteToFile;

/**
 * The default MarkCommand class inherited from {@code Command}.
 *
 * @see Command
 */
public class MarkCommand extends Command {
    final String MARK;
    int taskID;

    /**
     * Creates a default MarkCommand {@code Command} object.
     *
     * @param tasks the tasklist {@code ArrayList}.
     * @param writeToFile the class to handle writing of .ser files.
     * @param MARK string specify which task {@code Task} to mark.
     */
    public MarkCommand(ArrayList<Task> tasks, WriteToFile writeToFile, String MARK) {
        super(tasks, writeToFile);
        this.MARK = MARK;
    }

    /**
     * Marks task at specified location.
     *
     * @see Task
     * @see WriteToFile
     * @see Commands
     */
    public void mark() {
        String[] input = MARK.split(" ");
        taskID = Integer.parseInt(input[1]) - 1;

        super.tasks.get(taskID).setDone();
        super.writeToFile.toWrite(super.tasks);
    }

    /**
     * Returns marked task {@code Task} in String.
     *
     * @return marked task {@code Task} in String.
     */
    @Override
    public String toString() {
        return ("Nice! I've marked this task as done:\n" + super.tasks.get(taskID));
    }
}



