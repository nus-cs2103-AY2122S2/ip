package duke;

import java.io.IOException;

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
    public String execute(TaskList tasks, PlaceList places, Ui ui,
                          Storage storageTask, Storage storagePlace) throws IOException {
        if (tasks.isEmpty()) {
            return "Uh-oh! List is empty!";
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < tasks.size(); i++) {
                sb.append(i + 1).append(".").append(tasks.get(i)).append("\n");
            }
            return sb.toString();
        }
    }
}
