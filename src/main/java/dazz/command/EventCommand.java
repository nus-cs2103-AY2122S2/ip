package dazz.command;

import dazz.Storage;
import dazz.TaskList;
import dazz.Ui;
import dazz.task.Event;

import java.time.LocalDateTime;

public class EventCommand extends Command{
    Event event;

    public EventCommand(String description, LocalDateTime dateTime) {
        this.event = new Event(description, dateTime);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(this.event);
        ui.showAdd(event, taskList);
        storage.updateList(taskList);
    }

}
