package duke.command;

import duke.functionality.TaskList;

/**
 * Represents the find command. A <code>FindCommand</code> object corresponds to finding similar tasks
 * in the taskList of TaskList class.
 */
public class FindCommand extends Command {

    /**
     * Constructor of FindCommand.
     * @param word keyword used to find similar tasks in taskList of TaskList class.
     */
    public FindCommand(String word) {
        super(null, null, word);
    }

    /**
     * Returns a string which contains all the task after the execution of findWord in the TaskList class.
     * @param tasks an object of TaskList, used to access public methods in TaskList class.
     * @return crafted message after calling findword in the TaskList class.
     */
    @Override
    public String execute(TaskList tasks) {
        TaskList newTaskList = tasks.findWord(super.word);
        String message = "Here are the matching tasks in your list:\n";
        int counter = 1;
        for (int i = 0; i < newTaskList.getListSize(); i++) {
            String output = counter + "." + newTaskList.getTask(i);
            counter++;
            message += output + "\n";
        }
        if (counter == 1) {
            message = "OOPS!, there are no matching task with the word provided." + "\n";
        }
        return message;
    }

    /**
     * Returns false as the Command is not an ExitCommand.
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
