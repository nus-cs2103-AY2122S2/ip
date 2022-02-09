package bobby.command;

import java.util.ArrayList;

import bobby.Storage;
import bobby.Ui;
import bobby.exception.BobbyException;
import bobby.exception.FindException;
import bobby.task.Task;
import bobby.task.TaskList;


public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
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
        if (tasks.isEmpty()) {
            throw new FindException("empty_tasks");
        }
        TaskList tempTaskList = extractTasksWithKeyword(tasks);
        return ui.printFindTaskList(tempTaskList);
    }

    /**
     * Extracts the tasks containing the keyword and put into a new TaskList.
     *
     * @param tasks TaskList object containing a list of Tasks.
     * @returns TaskList containing only tasks containing the keyword.
     */
    private TaskList extractTasksWithKeyword(TaskList tasks) {
        ArrayList<Task> tempTasks = new ArrayList<>();
        for (Task t : tasks.getTaskList()) {
            if ((t.getTaskName().toLowerCase()).contains(keyword)) {
                tempTasks.add(t);
            }
        }
        return new TaskList(tempTasks);
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
