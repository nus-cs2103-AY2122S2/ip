package main.duke.commands;

import main.duke.TaskList;
import main.duke.Ui;
import main.duke.enums.CommandType;
import main.duke.tasks.Event;
import main.duke.tasks.Task;

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
    public String runCommand(Ui ui, TaskList taskList) {
        Task newEvent = new Event(this.getDescription(), this.getDateTime());
        taskList.addTask(newEvent);
        return ui.respondAddTask(newEvent, taskList);
    }
}
