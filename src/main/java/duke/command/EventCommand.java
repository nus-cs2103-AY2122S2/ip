package duke.command;

import java.io.IOException;

import duke.dukeexceptions.DukeException;
//import duke.dukeexceptions.EventException;
import duke.extension.CheckDuplicate;
import duke.main.Storage;
import duke.main.TaskList;
import duke.ui.Ui;
import duke.task.Event;
import duke.task.Task;

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
     * @return a respinse to user input
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        String result = "";
        String[] parts = input.split(" ");
        if (parts.length == 1) {
            return "The format should be: eventTask <description> /at <date>\n";
        }
        String[] split1 = input.split("/at ");
        if (split1.length == 1) {
            return "You need to tell me your eventTask date\n e.g eventTask <description> /at <date>\n";
        }
        String eventDesription = split1[0].substring(6);
        String eventDate = split1[1];
        Task eventTask = new Event(eventDesription, eventDate);
        Task duplicated = CheckDuplicate.checkDuplicate(eventTask, taskList);
        if (!duplicated.equals(eventTask)) {
            result += "This task has been added before!\n";
            result += duplicated.toString();
            result += "\n please enter another task";
            return result;
        }
        taskList.add(eventTask);
        result += ui.showEventTaskAdded(eventTask, taskList);
        storage.saveFile(taskList);
        return result;
    }
}
