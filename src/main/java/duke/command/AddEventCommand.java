package duke.command;

import duke.*;
import duke.task.Deadline;
import duke.task.TaskList;

public class AddEventCommand extends Command {
    private String description;
    private DateHelper datetime;

    private static final String DESC_RESPONSE = "Oops! \\(@.@)/ You have not keyed in a description for the duke.task!\n" +
            "Let's try again ~(^.^)~\n" +
            "Type 'help' if you need to know how to use this duke.command";

    public AddEventCommand(String input, String time) throws DukeException {
        description = input;
        if (time.length() == 0) {
            throw new DukeException("Oops! You have not keyed in a due date for the duke.task! ┗(｀Дﾟ┗(｀ﾟДﾟ´)┛ﾟД´)┛\n" +
                    "Let's try again  (☆｀• ω •´)ｂ\n" +
                    "Type 'help' if you need to know how to use this duke.command");
        } else {
            datetime = new DateHelper(time);
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (description.length() == 0) {
            throw new DukeException(DESC_RESPONSE);
        }
        else {
            Deadline entry = new Deadline(description, datetime);
            tasks.addTasks(entry);
            String message = entry.getTask();
            System.out.println("I have added the following deadline:\n" + message);
            System.out.println("You now have " + tasks.getSize() + " tasks");
        }
    }
}
