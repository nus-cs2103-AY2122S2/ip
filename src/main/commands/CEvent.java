package main.commands;

import main.TaskList;
import main.Ui;
import main.enums.CommandType;
import main.tasks.Event;
import main.tasks.Task;

public class CEvent extends Command {
    protected String description;
    protected String dateTime;

    public CEvent(String description, String dateTime) {
        super(CommandType.EVENT);
        this.description = description;
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        return this.dateTime;
    }

    public String getDescription() {
        return this.description;
    }


    @Override
    public void runCommand(Ui ui, TaskList taskList) {
        Task newEvent = new Event(this.getDescription(), this.getDateTime());
        taskList.addTask(newEvent);
        ui.respondAddTask(newEvent, taskList);
    }
}
