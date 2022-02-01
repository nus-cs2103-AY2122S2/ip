package bobby.command;

import java.util.ArrayList;

import bobby.Storage;
import bobby.Ui;
import bobby.exception.BobbyException;
import bobby.exception.FindException;
import bobby.task.Task;
import bobby.task.TaskList;


public class FindCommand extends Command {
    private String fullCommand;

    public FindCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BobbyException {
        ui.printLongLine();
        if (fullCommand.substring(4).isBlank()) {
            throw new FindException("empty_command");
        } else if (tasks.isEmpty()) {
            throw new FindException("empty_tasks");
        }
        String keyword = fullCommand.substring(4).trim().toLowerCase();
        ArrayList<Task> tempTasks = new ArrayList<Task>();
        for (Task t : tasks.getTaskList()) {
            if ((t.getTaskName().toLowerCase()).contains(keyword)) {
                tempTasks.add(t);
            }
        }
        TaskList tempTaskList = new TaskList(tempTasks);
        ui.printFindTaskList(tempTaskList);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof FindCommand;
    }
}
