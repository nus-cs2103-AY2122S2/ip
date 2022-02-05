package duke.command;

import java.io.IOException;

import duke.dukeexceptions.DeadlineException;
import duke.dukeexceptions.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Deadline;
import duke.task.Task;



public class DeadlineCommand extends Command {

    public DeadlineCommand(String input) {
        super(input);
    }

    /**
     * Add a deadline command to the task list, and save it to the file
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
            throw new DeadlineException("Emm, what is your task again? (please insert again)");
        }
        String[] split1 = input.split("/by ");
        if (split1.length == 1) {
            throw new DeadlineException("You need to tell me your deadline date\n "
                                                + "e.g deadline <yourtask> /by <deadline date>");
        }
        String deadlineDesription = split1[0].substring(9);
        String deadlineDate = split1[1];
        Task deadline = new Deadline(deadlineDesription, deadlineDate);
        taskList.add(deadline);
        ui.showDeadlineTaskAdded(deadline, taskList);
        storage.saveFile(taskList);
    }
}
