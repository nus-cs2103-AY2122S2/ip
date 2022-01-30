package duke.command;

import duke.functionality.TaskList;

/**
 * Represents the print command. A <code>PrintCommand</code> object allows users to see all task in Duke TaskBot.
 */
public class PrintCommand extends Command {

    /**
     * Constructor for PrintCommand class.
     */
    public PrintCommand() {
        super(null, null, null);
    }

    /**
     * Returns a string which contains all the task after the execution of printList in the TaskList class.
     * @param tasks an object of TaskList, used to access public methods in TaskList class.
     * @return crafted message after calling printList in the TaskList class.
     */
    @Override
    public String execute(TaskList tasks) {
        return tasks.printList();
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
