package chatcat.commands;

import java.util.ArrayList;

import chatcat.chatcatexception.ChatCatException;
import chatcat.tasks.Event;
import chatcat.tasks.Task;
import chatcat.util.DateTimeUtil;
import chatcat.util.WriteToFile;

/**
 * The default SetEventCommand class inherited from {@code Command}.
 *
 * @see Command
 */
public class SetEventCommand extends Command {
    final String EVENT;
    Event event;

    /**
     * Creates a default SetEventCommand {@code Command} object.
     *
     * @param tasks the tasklist {@code ArrayList}.
     * @param writeToFile the class to handle writing of .ser files.
     * @param EVENT string specify event task {@code Task}.
     */
    public SetEventCommand(ArrayList<Task> tasks, WriteToFile writeToFile, String EVENT) {
        super(tasks, writeToFile);
        this.EVENT = EVENT;
    }

    /**
     * Creates a event {@code Event} object and appends the object at the end of {@code taskList}.
     *
     * @throws ChatCatException if description of event is empty.
     * @see Event
     * @see WriteToFile
     * @see Commands
     * @see DateTimeUtil
     */
    public void setEvent() throws ChatCatException {
        String[] input = EVENT.split(" ");

        if (input.length == 1) {
            throw new ChatCatException("OOPS!!! The description of a event cannot be empty.");
        }

        String[] split = EVENT.split("/at ");
        DateTimeUtil dateTimeUtil = new DateTimeUtil(split[1]);
        event = new Event(split[0].substring(6), dateTimeUtil.getTime());

        super.tasks.add(event);
        super.writeToFile.toWrite(super.tasks);
    }

    /**
     * Returns created event task {@code Task} in String.
     *
     * @return created event task {@code Task} in String.
     */
    @Override
    public String toString() {
        return "Got it. I've added this task:\n" + event + "\n" +
                "Now you have " + super.tasks.size() + " tasks in the list.";
    }
}


