package duke;
/**
 * Extends Command class
 * When executed, calls Ui method to print output
 */
public class ByeCommand extends Command {

    /**
     * Call ui method to print bye statement
     * Set isExit to true and exit program
     * @param tasklist TaskList has all current tasks
     * @param ui Ui handles printing to output
     * @param storage Storage saves tasklist
     * @return void
     */
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        super.changeExit();
        ui.printBye();
    }
}
