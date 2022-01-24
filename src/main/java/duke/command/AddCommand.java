package duke.command;

import duke.helptool.DukeException;
import duke.helptool.Storage;
import duke.helptool.TaskList;
import duke.helptool.Ui;
import duke.task.*;

import java.time.LocalDateTime;

/**
 * The type Add command.
 */
public class AddCommand extends Command {
    private final String description;
    private final String TYPE;
    private LocalDateTime dateTime;

    /**
     * Instantiates a new Add command.
     *
     * @param description the Task description
     * @param type        the type
     */
    public AddCommand(String description, String type){
        this.TYPE = type;
        this.description = description;
    }

    /**
     * Instantiates a new Add command.
     *
     * @param description the command description
     * @param type        the type
     * @param dateTime    the date time
     */
    public AddCommand(String description, String type, LocalDateTime dateTime){
        this.TYPE = type;
        this.description = description;
        this.dateTime = dateTime;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        switch (this.TYPE) {
            case "T":
                try {
                    ToDo temp = new ToDo(description);
                    tasks.addTask(temp);
                    ui.showAddTodo(temp.toString(), tasks.getSize());
                    storage.write(tasks);
                } catch (DukeException e) {
                    ui.showExceptionError(e);
                }
                break;
            case "D":
                try {
                    Deadline ddl = new Deadline(description, dateTime);
                    tasks.addTask(ddl);
                    ui.showAddDeadline(ddl.toString(), tasks.getSize());
                    storage.write(tasks);
                } catch (DukeException e) {
                    ui.showExceptionError(e);
                }
                break;
            case "E":
                try {
                    Event event = new Event(description, dateTime);
                    tasks.addTask(event);
                    ui.showAddEvent(event.toString(), tasks.getSize());
                    storage.write(tasks);
                } catch (DukeException e) {
                    ui.showExceptionError(e);
                }
                break;
        }
    }
}
