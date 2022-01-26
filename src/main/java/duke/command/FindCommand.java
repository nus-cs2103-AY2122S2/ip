package duke.command;

import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;

import duke.task.Task;

import static duke.constant.Message.FOUND_TASK;
import static duke.constant.Message.LINE_PREFIX;
import static duke.constant.Message.NO_TASK;
import static duke.constant.Message.NO_TASK_MATCHED;

public class FindCommand extends Command {
    String commandArgument;

    public FindCommand(String commandArgument) {
        this.commandArgument = commandArgument;
    }


    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.getNumberOfTasks() == 0) {
            ui.printMessage(NO_TASK);
            return;
        }

        TaskList foundTasks = new TaskList();
        for (Task task: tasks.getTasks()) {
            if (task.getDescription().contains(commandArgument)) {
                foundTasks.addTask(task);
            }
        }

        if (foundTasks.getNumberOfTasks() == 0) {
            ui.printMessage(NO_TASK_MATCHED);
        } else {
            ui.printLine();
            System.out.println(FOUND_TASK);
            for (int i = 0; i < foundTasks.getNumberOfTasks(); i++) {
                ui.printMessageWithoutLine(LINE_PREFIX + (i + 1) + "." + foundTasks.getTaskByIndex(i));
            }
            ui.printLine();
        }



    }
}
