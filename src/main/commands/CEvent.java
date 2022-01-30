package main.commands;

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
    public void runCommand() {
        Task newEvent = new Event(this.getDescription(), this.getDateTime());
        Task.addTask(newEvent);
        System.out.printf("Got it. I've added this task:\n" + "%s\n" + "%s\n",
                newEvent, Task.taskCountToString());
    }
}
