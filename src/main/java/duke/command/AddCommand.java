package duke.command;

import java.time.LocalDateTime;

import duke.helptool.DukeException;
import duke.helptool.Storage;
import duke.helptool.TaskList;
import duke.helptool.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

/**
 * The type AddCommand.
 */
public class AddCommand extends Command {
    private final String description;
    private final String type;
    private LocalDateTime dateTime;

    /**
     * Instantiates a new Add command.
     *
     * @param description the Task description
     * @param type        the type
     */
    public AddCommand(String description, String type) {
        this.type = type;
        this.description = description;
    }

    /**
     * Instantiates a new Add command.
     *
     * @param description the command description
     * @param type        the type
     * @param dateTime    the date time
     */
    public AddCommand(String description, String type, LocalDateTime dateTime) {
        this.type = type;
        this.description = description;
        this.dateTime = dateTime;
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
                ToDo temp = new ToDo(description);
                tasks.addTask(temp);
                tempResult = ui.showAddTodo(temp.toString(), tasks.getSize());
                storage.write(tasks);
            } catch (DukeException e) {
                tempResult = ui.showExceptionError(e);
            }
            break;
        case "D":
            try {
                Deadline ddl = new Deadline(description, dateTime);
                tasks.addTask(ddl);
                tempResult = ui.showAddDeadline(ddl.toString(), tasks.getSize());
                storage.write(tasks);
            } catch (DukeException e) {
                tempResult = ui.showExceptionError(e);
            }
            break;
        case "E":
            try {
                Event event = new Event(description, dateTime);
                tasks.addTask(event);
                tempResult = ui.showAddEvent(event.toString(), tasks.getSize());
                storage.write(tasks);
            } catch (DukeException e) {
                tempResult = ui.showExceptionError(e);
            }
            break;
        default:
            break;
        }
        return tempResult;
    }
}
