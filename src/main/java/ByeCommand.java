/**
 * Extends Command class
 * When executed, calls Ui method to print output
 */
public class ByeCommand extends Command {
    public String execute(TaskList tasklist, Ui ui, Storage storage) {
        super.changeExit();
        return ui.printBye();
    }
}
