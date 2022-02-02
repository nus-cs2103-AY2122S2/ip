package duke.command;

import java.util.ArrayList;
import java.util.List;

import duke.Storage;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.MessageUi;

/**
 * Represents commands which find tasks in the task list. A FindCommand
 * object corresponds a valid Ekud command, which can then be executed.
 */

public class FindCommand implements Command {

    private String fullCommand;
    private String[] splitFullCommand;
    private String taskType;

    /**
     * Constructor for this class.
     * @param fullCommand User's input.
     */
    public FindCommand(String fullCommand) {
        this.fullCommand = fullCommand;
        this.splitFullCommand = fullCommand.split(" ", 2);
        this.taskType = splitFullCommand[0];
    }

    @Override
    public String execute(TaskList tasks, Storage storage, MessageUi ui) throws DukeException {
        String find = splitFullCommand[1];
        List<Task> taskList = tasks.getTaskList();
        List<Task> l = new ArrayList<>();
        for (Task t : taskList) {
            if (t.toString().contains(find)) {
                l.add(t);
            }
        }
        if (l.isEmpty()) {
            return ("No match found!");
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < l.size(); i++) {
                stringBuilder.append(i + 1 + "." + l.get(i).toString() + "\n");
            }
            return stringBuilder.toString();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
