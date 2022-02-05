package duke.command;

import java.time.LocalDateTime;

import duke.helptool.DukeException;
import duke.helptool.Storage;
import duke.helptool.TaskList;
import duke.helptool.Ui;
import duke.tag.Tag;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

/**
 * The type AddCommand.
 */
public class AddCommand extends Command {
    private final String description;
    private final Tag tag;
    private final String type;
    private LocalDateTime dateTime;

    /**
     * Instantiates a new Add command.
     *
     * @param description the Task description
     * @param type        the type
     */
    public AddCommand(String description, String type, Tag tag) {
        this.type = type;
        this.description = description;
        this.tag = tag;
    }

    /**
     * Instantiates a new Add command.
     *
     * @param description the command description
     * @param type        the type
     * @param dateTime    the date time
     */
    public AddCommand(String description, String type, LocalDateTime dateTime, Tag tag) {
        this.type = type;
        this.description = description;
        this.dateTime = dateTime;
        this.tag = tag;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String tempResult = "";
        switch (this.type) {
        case "T":
            try {
                ToDo temp = new ToDo(description, tag);
                tasks.addTask(temp);
                tempResult = ui.showAddTodo(temp.toString(), tasks.getSize());
                storage.write(tasks);
            } catch (DukeException e) {
                tempResult = ui.showExceptionError(e);
                assert tempResult != null : "DukeException is not null";
            }
            break;
        case "D":
            try {
                Deadline ddl = new Deadline(description, dateTime, tag);
                tasks.addTask(ddl);
                tempResult = ui.showAddDeadline(ddl.toString(), tasks.getSize());
                storage.write(tasks);
            } catch (DukeException e) {
                tempResult = ui.showExceptionError(e);
                assert tempResult != null : "DukeException is not null";
            }
            break;
        case "E":
            try {
                Event event = new Event(description, dateTime, tag);
                tasks.addTask(event);
                tempResult = ui.showAddEvent(event.toString(), tasks.getSize());
                storage.write(tasks);
            } catch (DukeException e) {
                tempResult = ui.showExceptionError(e);
                assert tempResult != null : "DukeException is not null";
            }
            break;
        default:
            break;
        }
        return tempResult;
    }
}
