package duke.command;

import java.io.IOException;

//import duke.dukeexceptions.DeadlineException;
import duke.dukeexceptions.DukeException;
import duke.extension.CheckDuplicate;
import duke.main.Storage;
import duke.main.TaskList;
import duke.ui.Ui;
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
     * @return a response to user input
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        String result = "";
        String[] parts = input.split(" ");
        if (parts.length == 1) {
            return "Emm, what is your task again? (please insert again)\n";
        }
        String[] split1 = input.split("/by ");
        if (split1.length == 1) {
            return "You need to tell me your deadline date\n "
                                                + "e.g deadline <yourtask> /by <deadline date>\n";
        }
        String deadlineDesription = split1[0].substring(9);
        String deadlineDate = split1[1];
        Task deadlineTask = new Deadline(deadlineDesription, deadlineDate);
        Task duplicated = CheckDuplicate.checkDuplicate(deadlineTask, taskList);
        if (!duplicated.equals(deadlineTask)) {
            result += "This task has been added before!\n";
            result += duplicated.toString();
            assert duplicated.getDescription().equals(deadlineTask.getDescription());
            result += "\n please enter another task";
            return result;
        }
        taskList.add(deadlineTask);
        result += ui.showDeadlineTaskAdded(deadlineTask, taskList);
        storage.saveFile(taskList);
        return result;
    }
}
