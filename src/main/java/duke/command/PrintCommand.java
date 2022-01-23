package duke.command;

import duke.functionality.TaskList;

/**
 * Represents the print command. A <code>PrintCommand</code> object allows users to see the accumulated task in
 * Duke TaskBot.
 */
public class PrintCommand extends Command{

    /**
     * Constructor for PrintCommand class.
     */
    public PrintCommand() {
        super(null, null);
    }

    /**
     * Returns nothing, but prints out all the task in the taskList in TaskList class.
     * @param tasks an Object of TaskList, used to access public methods in TaskList class.
     */
    @Override
    public void execute(TaskList tasks) {
        tasks.printList();
    }

    /**
     * Returns false as the command is not an ExitCommand.
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
