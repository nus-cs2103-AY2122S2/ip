package chatcat.commands;

import java.util.ArrayList;

import chatcat.chatcatexception.InvalidDateException;
import chatcat.chatcatexception.TaskException;
import chatcat.tasks.Deadline;
import chatcat.tasks.Task;
import chatcat.util.DateTimeUtil;
import chatcat.util.OutputMessage;
import chatcat.util.SplitInput;
import chatcat.util.WriteToFile;

/**
 * The default SetDeadlineCommand class inherited from {@code Command}.
 *
 * @see Command
 */
public class SetDeadlineCommand extends Command {
    final String DEADLINE;
    Deadline deadline;

    /**
     * Creates a default SetDeadlineCommand {@code Command} object.
     *
     * @param tasks the tasklist {@code ArrayList}.
     * @param writeToFile the class to handle writing of .ser files.
     * @param DEADLINE string specify deadline task {@code Task}.
     */
    public SetDeadlineCommand(ArrayList<Task> tasks, WriteToFile writeToFile, String DEADLINE) {
        super(tasks, writeToFile);
        this.DEADLINE = DEADLINE;
    }

    /**
     * Creates a deadline {@code Deadline} object and appends the object at the end of {@code taskList}.
     *
     * @throws TaskException if description of deadline is empty.
     * @see Deadline
     * @see WriteToFile
     * @see DateTimeUtil
     * @see OutputMessage
     */
    public void setDeadline() throws TaskException, InvalidDateException {
        String[] input = DEADLINE.split(" ");

        if (input.length == 1) {
            throw new TaskException(OutputMessage.taskErrorMessage());
        }

        if (!(DEADLINE.contains("/by "))) {
            throw new TaskException(OutputMessage.invalidInputMessage());
        }

        String deadlineStr = SplitInput.getTask(DEADLINE, "/by ", 9);
        String time = SplitInput.getTime(DEADLINE, "/by ");
        DateTimeUtil dateTimeUtil = new DateTimeUtil(time);

        deadline = new Deadline(deadlineStr, dateTimeUtil.getTime());

        if (this.tasks.contains(deadline)) {
            throw new TaskException(OutputMessage.repeatedTaskErrorMessage());
        }

        super.tasks.add(deadline);
        super.writeToFile.toWrite(super.tasks);
    }

    /**
     * Returns created deadline task {@code Task} in String.
     *
     * @return created deadline task {@code Task} in String.
     * @see OutputMessage
     */
    @Override
    public String toString() {
        return OutputMessage.setTaskMessage(deadline, super.tasks.size());
    }
}


