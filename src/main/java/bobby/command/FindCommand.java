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

    /**
     * {@inheritDoc}
     *
     * @param tasks   TaskList object containing a list of Tasks.
     * @param ui      Ui object to allow for Bobby to print messages.
     * @param storage Storage object that handles the reading/writing of TaskList into a specified file.
     * @return Bobby's reply to the command.
     * @throws BobbyException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BobbyException {
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
        return ui.printFindTaskList(tempTaskList);
    }

    /**
     * {@inheritDoc}
     *
     * @param obj The other Command object to compare with.
     * @return True if both objects are FindCommand objects. False otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof FindCommand;
    }
}
