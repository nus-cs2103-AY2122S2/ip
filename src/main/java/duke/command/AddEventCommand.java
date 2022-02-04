package duke.command;

import duke.DateHelper;
import duke.DukeException;
import duke.task.Event;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Adds an event to the task list.
 */
public class AddEventCommand extends Command {
    private String description;
    private DateHelper datetime;

    private static final String DESC_RESPONSE = "Oops! \\(@.@)/ You have not keyed in a description for the task!\n"
            + "Let's try again ~(^.^)~\n"
            + "Type 'help' if you need to know how to use this command";

    /**
     * Constructor for AddEventCommand.
     *
     * @param input Description that the user provided for the event.
     * @param time  Date time that the user provided for the event.
     * @throws DukeException thrown when there is no due date for the task.
     */
    public AddEventCommand(String input, String time) throws DukeException {
        description = input;
        if (time.length() == 0) {
            throw new DukeException("Oops! You have not keyed in a due date for the task! ┗(｀Дﾟ┗(｀ﾟДﾟ´)┛ﾟД´)┛\n"
                    + "Let's try again  (☆｀• ω •´)ｂ\n"
                    + "Type 'help' if you need to know how to use this command");
        } else {
            datetime = new DateHelper(time);
        }
    }

    /**
     * Executes addition of the task into the Task List.
     *
     * @param tasks   Tasklist that was declared in the Duke class.
     * @param ui      Ui that was declared in the Duke class.
     * @param storage Storage that was declared in the Duke class.
     * @throws DukeException When there is no description of the task.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (description.length() == 0) {
            throw new DukeException(DESC_RESPONSE);
        } else {
            Event entry = new Event(description, datetime);
            tasks.addTask(entry);
            String message = entry.getTask();
            return ("I have added the following event:\n" + message
                    + "\nYou now have " + tasks.getSize() + " tasks");
        }
    }
}
