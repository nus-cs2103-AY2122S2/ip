package chatcat.commands;

import java.util.ArrayList;

import chatcat.chatcatexception.InvalidDateException;
import chatcat.chatcatexception.TaskException;
import chatcat.tasks.Event;
import chatcat.tasks.Task;
import chatcat.util.DateTimeUtil;
import chatcat.util.OutputMessage;
import chatcat.util.SplitInput;
import chatcat.util.WriteToFile;

/**
 * The default SetEventCommand class inherited from {@code Command}.
 *
 * @see Command
 */
public class SetEventCommand extends Command {
    final String EVENT;
    Event event;
    String output;

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
     * @throws TaskException if description of event is empty.
     * @see Event
     * @see WriteToFile
     * @see DateTimeUtil
     * @see OutputMessage
     */
    public void setEvent() throws TaskException, InvalidDateException {
        String[] input = EVENT.split(" ");

        if (input.length == 1) {
            throw new TaskException(OutputMessage.taskErrorMessage());
        }

        if (!(EVENT.contains("/at "))) {
            throw new TaskException(OutputMessage.invalidInputMessage());
        }

        String eventStr = SplitInput.getTask(EVENT, "/at ", 6);
        String time = SplitInput.getTime(EVENT, "/at ");
        System.out.println(eventStr);
        DateTimeUtil dateTimeUtil = new DateTimeUtil(time);
        event = new Event(eventStr, dateTimeUtil.getTime());

        if (this.tasks.contains(event)) {
            throw new TaskException(OutputMessage.repeatedTaskErrorMessage());
        }

        super.tasks.add(event);
        super.writeToFile.toWrite(super.tasks);
    }

    /**
     * Returns created event task {@code Task} in String.
     *
     * @return created event task {@code Task} in String.
     * @see OutputMessage
     */
    @Override
    public String toString() {
        return OutputMessage.setTaskMessage(event, super.tasks.size());
    }
}


