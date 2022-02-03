package duke.command;

import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;

import duke.task.Task;

import static duke.constant.Message.FOUND_TASK;
import static duke.constant.Message.LINE_PREFIX;
import static duke.constant.Message.LINE_SEPARATOR;
import static duke.constant.Message.NO_TASK;
import static duke.constant.Message.NO_TASK_MATCHED;

/**
 * A representation of the command for founding tasks in the list.
 */
public class FindCommand extends Command {
    String commandArgument;

    /**
     * Class constructor.
     *
     * @param commandArgument command argument from user input
     */
    public FindCommand(String commandArgument) {
        this.commandArgument = commandArgument;
    }

    /**
     * Executes finding command.
     *
     * @param tasks   TaskList class
     * @param ui      Ui class
     * @param storage Storage class
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.getNumberOfTasks() == 0) {
            return ui.printMessage(NO_TASK);
//            return;
        }

        TaskList foundTasks = new TaskList();
        for (Task task: tasks.getTasks()) {
            if (task.getDescription().contains(commandArgument)) {
                foundTasks.addTask(task);
            }
        }

        if (foundTasks.getNumberOfTasks() == 0) {
            return ui.printMessage(NO_TASK_MATCHED);
        } else {
            String result = "";
            result += FOUND_TASK;
            result += LINE_SEPARATOR;
            for (int i = 0; i < foundTasks.getNumberOfTasks(); i++) {
                result += ui.printMessage(LINE_PREFIX + (i + 1) + "." + foundTasks.getTaskByIndex(i));
            }
            return result;
        }



    }
}
