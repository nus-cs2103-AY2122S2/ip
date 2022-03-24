package duke;

import java.io.IOException;

public class EventCommand extends Command {
    EventCommand(String commandArgument) {
        super(commandArgument);
    }

    public void execute(Storage storage, TaskList taskList, Ui ui) throws IOException {
        String[] eventTaskDetails = commandArgument.split("/at");
        String eventTask = eventTaskDetails[0].trim();
        String eventTaskAt = eventTaskDetails[1].trim();
        if (eventTask.isEmpty()) {
            ui.emptyEventDescriptionErrorMessage();
        } else {
            taskList.addTaskToTaskList(new Event(eventTask, eventTaskAt));
            storage.save(taskList);
        }
    }
}
