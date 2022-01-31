package duke.command;


import duke.*;
import duke.dukeexceptions.DukeException;
import duke.dukeexceptions.EventException;
import duke.task.Event;
import duke.task.Task;

import java.io.IOException;

public class EventCommand extends Command {

    public EventCommand(String input) {
        super(input);
    }

    /**
     * Add an event task to the task list, and save it to the data file
     *
     * @param taskList currentTaskList
     * @param ui ui class that helps to print suitable command
     * @param storage storage that manage saving and loading data
     * @throws DukeException an error message
     * @throws IOException error saving to the file
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        String[] parts = input.split(" ");
        if (parts.length == 1) {
            throw new EventException("The format should be: event <description> /at <date>");
        }
        String[] split1 = input.split("/at ");
        if (split1.length == 1) {
            throw new EventException("You need to tell me your event date\n e.g event <description> /at <date>");
        }
        String eventDesription = split1[0].substring(6);
        String eventDate = split1[1];
        Task event = new Event(eventDesription, eventDate);
        taskList.add(event);
        ui.showEventTaskAdded(event, taskList);
        storage.saveFile(taskList);
    }
}
