package dazz.command;

import java.time.LocalDateTime;

import dazz.Storage;
import dazz.TaskList;
import dazz.Ui;
import dazz.task.Event;

/**
 * Represents the logic of adding an <code>Event</code>.
 */
public class EventCommand extends Command {
    private Event event;

    /**
     * Constructs a <code>EventCommand</code> based on the description and the
     * datetime of the event.
     * @param description
     * @param dateTime
     */
    public EventCommand(String description, LocalDateTime dateTime) {
        this.event = new Event(description, dateTime);
    }

    /**
     * Executes the adding of an <code>Event</code>,
     * updates the text file and returns the execution message.
     * @param taskList The <code>TaskList</code> that <code>Event</code> is added to.
     * @param ui The <code>Ui</code> of Dazz.
     * @param storage The <code>Storage</code> of Dazz.
     * @return The execution message.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(this.event);
        //ui.showAdd(event, taskList);
        String message = ui.messageForAdd(event, taskList);
        storage.updateList(taskList);
        return message;
    }

}
