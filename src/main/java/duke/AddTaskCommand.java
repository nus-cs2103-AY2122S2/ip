package main.java.duke;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class AddTaskCommand extends Command {

    String action;
    String type;
    String toDoBy;

    public AddTaskCommand(String action, String type) {
        this.action = action;
        this.type = type;
    }

    public AddTaskCommand(String action, String type, String toDoBy) {
        this.action = action;
        this.type = type;
        this.toDoBy = toDoBy;
    }

    @Override
    public void execute(TaskList tasks,Ui ui, Storage storage) {
        try {
            if (type.equals("todo")) {
                tasks.addTask(new ToDo(action));
            } else if (type.equals("deadline")) {
                tasks.addTask(new Deadline(action, toDoBy));
            } else {
                tasks.addTask(new Event(action, toDoBy));
            }
            ui.showSucessfulAdd(tasks.getTask(tasks.size() - 1), tasks.size());
        } catch (DateTimeParseException e) {
            ui.showError("DateTimeParseException");
        } try {
            storage.writeToFile(tasks.getTaskArr());
        } catch (IOException e) {
            ui.showError("IOException");
        }
    }

    @Override
    public boolean isEnd() {
        return false;
    }

}
