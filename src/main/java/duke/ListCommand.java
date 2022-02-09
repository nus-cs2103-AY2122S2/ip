package duke;

/**
 * Represents a list command.
 */
public class ListCommand extends Command {

    /**
     * Constructs an instance of the ListCommand class.
     *
     * @param command A string containing the word "list".
     */
    public ListCommand(String command) {
        super(command);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
         if (tasks.isEmpty()) {
            ui.showMessage("Uh-oh! List is empty!");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                ui.showMessage((i + 1) + "." + tasks.get(i));
            }
        }
    }
}
