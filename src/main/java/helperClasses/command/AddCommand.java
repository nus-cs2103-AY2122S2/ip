package helperClasses.command;

import helperClasses.*;

import java.time.LocalDateTime;

public class AddCommand extends Command {
    private final String description;
    private final String TYPE;
    private LocalDateTime dateTime;

    public AddCommand(String description, String type){
        this.TYPE = type;
        this.description = description;
    }

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
