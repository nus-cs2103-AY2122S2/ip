package chatcat.commands;

import java.util.ArrayList;

import chatcat.chatcatexception.ChatCatException;
import chatcat.tasks.Deadline;
import chatcat.tasks.Task;
import chatcat.util.DateTimeUtil;
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
     * @throws ChatCatException if description of deadline is empty.
     * @see Deadline
     * @see WriteToFile
     * @see DateTimeUtil
     */
    public void setDeadline() throws ChatCatException {
        String[] input = DEADLINE.split(" ");

        if (input.length == 1) {
            throw new ChatCatException("OOPS!!! The description of a deadline cannot be empty.");
        }

        String[] split = DEADLINE.split("/by ");
        DateTimeUtil dateTimeUtil = new DateTimeUtil(split[1]);
        deadline = new Deadline(split[0].substring(9), dateTimeUtil.getTime());

        super.tasks.add(deadline);
        super.writeToFile.toWrite(super.tasks);
    }

    /**
     * Returns created deadline task {@code Task} in String.
     *
     * @return created deadline task {@code Task} in String.
     */
    @Override
    public String toString() {
        return "Got it. I've added this task:\n" + deadline + "\n" +
                "Now you have " + super.tasks.size() + " tasks in the list.";
    }
}


