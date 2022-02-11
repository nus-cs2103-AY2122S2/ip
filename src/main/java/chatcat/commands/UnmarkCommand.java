package chatcat.commands;

import java.util.ArrayList;

import chatcat.tasks.Task;
import chatcat.util.WriteToFile;
import chatcat.util.OutputMessage;

/**
 * The default UnmarkCommand class inherited from {@code Command}.
 *
 * @see Command
 */
public class UnmarkCommand extends Command {
    final String UNMARK;
    int taskID;

    /**
     * Creates a default UnmarkCommand {@code Command} object.
     *
     * @param tasks the tasklist {@code ArrayList}.
     * @param writeToFile the class to handle writing of .ser files.
     * @param UNMARK string specify which task {@code Task} to unmark.
     */
    public UnmarkCommand(ArrayList<Task> tasks, WriteToFile writeToFile, String UNMARK) {
        super(tasks, writeToFile);
        this.UNMARK = UNMARK;
    }

    /**
     * Unmarks task at specified location.
     *
     * @see Task
     * @see WriteToFile
     */
    public void unmark() {
        String[] input = UNMARK.split(" ");
        taskID = Integer.parseInt(input[1]) - 1;

        assert taskID < super.tasks.size() : "Index is larger than task list size";
        super.tasks.get(taskID).setUndone();
        super.writeToFile.toWrite(super.tasks);
    }

    /**
     * Returns unmarked task {@code Task} in String.
     *
     * @return unmarked task {@code Task} in String.
     * @see OutputMessage
     */
    @Override
    public String toString() {
        return OutputMessage.unmarkTaskMessage(super.tasks.get(taskID).toString());
    }
}




