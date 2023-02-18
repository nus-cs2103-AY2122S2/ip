package chatcat.commands;

import java.util.ArrayList;

import chatcat.tasks.Task;
import chatcat.util.WriteToFile;

/**
 * The default Command class.
 */
public class Command {
    ArrayList<Task> tasks;
    final WriteToFile writeToFile;

    /**
     * Creates a {@code Command} object.
     *
     * @param tasks the tasklist {@code ArrayList}.
     * @param writeToFile the class to handle writing of .ser files.
     */
    public Command(ArrayList<Task> tasks, WriteToFile writeToFile) {
        this.tasks = tasks;
        this.writeToFile = writeToFile;
    }
}
