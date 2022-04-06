package duke.command;

import java.util.ArrayList;
import java.util.List;

import duke.Storage;
import duke.exception.BingChillingException;
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
     *
     * @param fullCommand User's input.
     */
    public FindCommand(String fullCommand) {
        this.fullCommand = fullCommand;
        this.splitFullCommand = fullCommand.split(" ", 2);
        this.taskType = splitFullCommand[0];
    }

    @Override
    public String execute(TaskList tasks, Storage storage, MessageUi ui) throws BingChillingException {
        String taskDescription = splitFullCommand[1];
        List<String> taskData = storage.loadFileContents();
        List<Task> foundTasks = new ArrayList<>();
        for (int i = 0; i < taskData.size(); i++) {
            String description = taskData.get(i).split(" , ")[2];
            if (description.contains(taskDescription)) {
                foundTasks.add(tasks.getTask(i + 1));
            }
        }
        if (foundTasks.isEmpty()) {
            return ("No match found!");
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < foundTasks.size(); i++) {
                stringBuilder.append(i + 1 + "." + foundTasks.get(i).toString() + "\n");
            }
            return stringBuilder.toString();
        }
    }
}
