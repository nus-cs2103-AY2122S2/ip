package duke.command;

import duke.task.Event;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * EventCommand class
 */
public class EventCommand extends Command<String>{

    private TaskList list;
    private String description;
    private Storage storage;
    private Ui ui;

    /**
     * Constructor for EventCommand object
     * @param description unformatted string for deadline
     * @param list task list to add this task to
     * @param storage storage of where this task will be added to
     * @param ui ui of subsequent system out to user
     */
    public EventCommand(String description, TaskList list, Storage storage, Ui ui) {
        this.list = list;
        this.description = description;
        this.storage = storage;
        this.ui = ui;
        runCommand();
    }

    /**
     * Splice the string and add to list and display to user
     */
    @Override
    public void runCommand() {
        String[] splicedString = description.split(" /at ");
        String splicedDescription = splicedString[0].substring(6);
        String dueDate = splicedString[1];
        Event freshEvent = new Event(splicedDescription, dueDate, false);
        list.addTask(freshEvent);
        storage.writeToFile(list);
        ui.showAddEvent(freshEvent, list);
    }
}
