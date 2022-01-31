package duke.command;

import java.util.ArrayList;
import java.util.List;

import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

public class FindCommand extends Command {
    private static final String MESSAGE_FIND = "Here are the matching tasks in your list:";

    private static final String ERROR_EMPTY_FIND = "OOPS!!! Search term cannot be empty :(";
    private static final String ERROR_FOUND_NOTHING = "OOPS!!! No tasks found with the term :";

    private String searchTerm;

    /**
     * Constructor to the find command.
     *
     * @param searchTerm Text to be searched for
     * @throws DukeException If the text is left empty
     */
    public FindCommand(String searchTerm) throws DukeException {
        if (searchTerm.equals("")) {
            throw new DukeException(ERROR_EMPTY_FIND);
        }
        this.searchTerm = searchTerm;
    }

    /**
     * Execution of the find command to search for tasks that match.
     *
     * @param tasks Task list
     * @param ui UI object
     */
    @Override
    public void execute(List<Task> tasks, Ui ui) {
        List<Integer> foundIndexes = new ArrayList<Integer>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTitle().contains(this.searchTerm)) {
                foundIndexes.add(i);
            }
        }
        if (foundIndexes.size() == 0) {
            ui.printContent(ERROR_FOUND_NOTHING);
        } else {
            String foundList = MESSAGE_FIND + "\n     ";
            for (int i = 0; i < foundIndexes.size(); i++) {
                Task thisTask = tasks.get(foundIndexes.get(i));
                foundList += (i + 1) + ". " + "[" + thisTask.getType() + "]"
                        + "[" + thisTask.getStatusIcon() + "] " + thisTask;
                if (i != foundIndexes.size() - 1) {
                    foundList += "\n     ";
                }
            }
            ui.printContent(foundList);
        }
    }
}
