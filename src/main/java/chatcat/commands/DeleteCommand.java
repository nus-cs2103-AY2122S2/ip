package chatcat.commands;

import java.util.ArrayList;

import chatcat.chatcatexception.TaskEditException;
import chatcat.tasks.Task;
import chatcat.util.OutputMessage;
import chatcat.util.SplitInput;
import chatcat.util.WriteToFile;

/**
 * The default DeleteCommand class inherited from {@code Command}.
 *
 * @see Command
 */
public class DeleteCommand extends Command {
    final String INDEX_TO_DELETE;
    Task removed;

    /**
     * Creates a default DeleteCommand {@code Command} object.
     *
     * @param tasks the tasklist {@code ArrayList}.
     * @param writeToFile the class to handle writing of .ser files.
     * @param INDEX_TO_DELETE the index of task {@code Task} in list to be deleted.
     */
    public DeleteCommand(ArrayList<Task> tasks, WriteToFile writeToFile, String INDEX_TO_DELETE) {
        super(tasks, writeToFile);
        this.INDEX_TO_DELETE = INDEX_TO_DELETE;
    }

    /**
     * Deletes the specified task {@code Task} in tasklist.
     *
     * @see Task
     * @see WriteToFile
     */
    public void delete() throws TaskEditException{
        super.tasks = writeToFile.toRead();

        int indexToDelete = SplitInput.getIndex(INDEX_TO_DELETE, 1);

        if (indexToDelete > super.tasks.size()) {
            throw new TaskEditException(OutputMessage.indexErrorMessage());
        }
        assert indexToDelete < super.tasks.size() : OutputMessage.indexErrorMessage();

        removed = tasks.remove(indexToDelete);
        writeToFile.toWrite(tasks);
    }

    /**
     * Returns deleted task {@code Task} in String.
     *
     * @return deleted task {@code Task} in String.
     * @see OutputMessage
     */
    @Override
    public String toString() {
        return OutputMessage.deleteMessage(
                removed.toString(), super.tasks.size());
    }
}

