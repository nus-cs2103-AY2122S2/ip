package duke.commands;

import duke.tasks.TaskList;

/**
 * Command that finds a string in Tasks in TaskList.
 */
public class FindCommand extends Command {

    private String searchStr;

    /**
     * Constructor for FindCommand.
     *
     * @param searchStr string to be searched in Task content.
     */
    public FindCommand(String searchStr) {
        this.searchStr = searchStr;
    }

    /**
     * Iterates through each Task in TaskList and searches for given string.
     *
     * @return List of Task found according to search.
     */
    @Override
    public String execute() {
        TaskList foundTasks = new TaskList();
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.getTaskContent(i).toLowerCase().contains(searchStr)) {
                foundTasks.addTask(taskList.getTask(i));
            }
        }

        if (foundTasks.size() == 0) {
            return "Apologies, no task matches what you are looking for";
        }

        return String.format("These are the results of my search:\n%s", foundTasks.toString());
    }
}
