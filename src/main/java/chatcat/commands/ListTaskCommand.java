package chatcat.commands;

import java.util.ArrayList;

import chatcat.tasks.Task;
import chatcat.util.OutputMessage;
import chatcat.util.WriteToFile;

/**
 * The default ListTaskCommand class inherited from {@code Command}.
 *
 * @see Command
 */
public class ListTaskCommand extends Command{

    /**
     * Creates a default ListTaskCommand {@code Command} object.
     *
     * @param tasks the tasklist {@code ArrayList}.
     * @param writeToFile the class to handle writing of .ser files.
     */
    public ListTaskCommand(ArrayList<Task> tasks, WriteToFile writeToFile) {
        super(tasks, writeToFile);
    }

    /**
     * Reads saved task list from previous session.
     *
     * @see WriteToFile
     * @see Task
     */
    public void getTaskList() {
        tasks = writeToFile.toRead();
    }

    /**
     * Returns a representation in string of Task in list {@code Task}.
     *
     * @return a representation in string of Task in list {@code Task}.
     * @see OutputMessage
     */
    @Override
    public String toString() {
        return OutputMessage.listTaskMessage(super.tasks);
    }
}
