package main.duke.commands;

import main.duke.DukeException;
import main.duke.TaskList;
import main.duke.Ui;
import main.duke.enums.CommandType;
import main.duke.tasks.Event;
import main.duke.tasks.Task;

import java.time.LocalDateTime;

public class CEvent extends Command {
    protected String description;
    protected LocalDateTime dateTime;

    public CEvent(String description, LocalDateTime dateTime) {
        super(CommandType.EVENT);
        this.description = description;
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    public String getDescription() {
        return this.description;
    }


    @Override
    public String runCommand(Ui ui, TaskList taskList) throws DukeException {
        Task newEvent = new Event(this.getDescription(), this.getDateTime());
        taskList.addTask(newEvent);
        super.runCommand(ui, taskList);
        return ui.respondAddTask(newEvent, taskList);
    }
}
