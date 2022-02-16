package duke.command;
import duke.task.*;
import duke.util.*;

public class AddCommand extends Command {

    protected String type;
    protected String details;
    protected String time;

    public AddCommand(String type, String details, String time) {
        this.type = type;
        this.details = details;
        this.time = time;
    }

    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        if (type.equals("T")) {
            ToDo item = new ToDo(details);
            tasklist.add(item);
            Ui.add(item, tasklist.size());
        } else if (type.equals("D")) {
            Deadline item = new Deadline(details, type);
            tasklist.add(item);
            Ui.add(item, tasklist.size());
        } else if (type.equals("E")) {
            Event item = new Event(details, type);
            tasklist.add(item);
            Ui.add(item, tasklist.size());
        }
    }
}
