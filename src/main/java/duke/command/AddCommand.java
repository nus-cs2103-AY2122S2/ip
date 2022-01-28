package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class AddCommand extends Command {
    String taskType;
    String taskDetails;

    public AddCommand(String taskType, String taskDetails) {
        this.taskType = taskType.toLowerCase();
        this.taskDetails = taskDetails;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        switch (taskType) {
        case "todo":
            try {
                tasks.addTask(new ToDo(taskDetails));
                ui.showAddition(tasks.size(), tasks.getLast());
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
            break;
        case "event":
            String[] eventDetails = taskDetails.split(" /at ", 2);
            try {
                tasks.addTask(new Event(eventDetails[0], eventDetails[1]));
                ui.showAddition(tasks.size(), tasks.getLast());
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
            break;
        case "deadline":
            String[] deadlineDetails = taskDetails.split(" /by ", 2);
            try {
                tasks.addTask(new Deadline(deadlineDetails[0], deadlineDetails[1]));
                ui.showAddition(tasks.size(), tasks.getLast());
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
            break;
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
