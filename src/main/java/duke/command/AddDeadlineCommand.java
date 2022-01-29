package duke.command;

import duke.*;
import duke.task.Deadline;
import duke.task.TaskList;

/**
 * Adds a deadline task into the task list.
 */
public class AddDeadlineCommand extends Command {
    private String description;
    private DateHelper datetime;

    private static final String DESC_RESPONSE = "Oops! \\(@.@)/ You have not keyed in a description for the task!\n" +
            "Let's try again ~(^.^)~\n" +
            "Type 'help' if you need to know how to use this command";

    /**
     * Constructor for the addition of deadlines.
     *
     * @param input Description of the task.
     * @param time Due date of the task.
     * @throws DukeException
     */
    public AddDeadlineCommand(String input, String time) throws DukeException {
        description = input;
        if (time.length() == 0) {
            throw new DukeException("Oops! You have not keyed in a due date for the task! ┗(｀Дﾟ┗(｀ﾟДﾟ´)┛ﾟД´)┛\n" +
                    "Let's try again  (☆｀• ω •´)ｂ\n" +
                    "Type 'help' if you need to know how to use this duke.command");
        } else {
            datetime = new DateHelper(time);
        }
    }

    /**
     * Executes the addition of deadline task into the tasklist.
     *
     * @param tasks TaskList that is maintained in Ducky.
     * @param ui Ui that is maintained in Ducky.
     * @param storage Storage that is maintained in Ducky.
     * @throws DukeException thrown when there is no description written.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (description.length() == 0) {
            throw new DukeException(DESC_RESPONSE);
        } else {
            Deadline entry = new Deadline(description, datetime);
            tasks.addTask(entry);
            String message = entry.getTask();
            System.out.println("I have added the following deadline:\n" + message);
            System.out.println("You now have " + tasks.getSize() + " tasks");
        }
    }
}
