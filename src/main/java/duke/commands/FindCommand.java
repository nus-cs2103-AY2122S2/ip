package duke.commands;

import java.util.ArrayList;
import java.util.Locale;
import java.util.stream.Collectors;

import duke.exceptions.DukeException;
import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

public class FindCommand extends Command {

    private String keyword;
    private String userInput;
    /**
     * Find the list of tasks with matching keywords
     *
     * @param userInput user input
     * @throws ArrayIndexOutOfBoundsException if no tasks found with given keyword
     */
    public FindCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public String execute(TaskList taskList, Ui ui) throws DukeException {
        try {
            String currKeyword = userInput.split(" ")[1];
            ArrayList<Task> foundTasks = (ArrayList<Task>) taskList.getTasks().stream()
                    .filter(t -> t.getDescription().toLowerCase(Locale.ROOT).contains(currKeyword))
                    .collect(Collectors.toList());

            boolean noMatch = foundTasks.isEmpty();

            if (noMatch) {
                Ui.printNoTaskFound();
                return null;
            }

            keyword = currKeyword;

            return Ui.printFindResultHeader(keyword, foundTasks);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please enter keyword of task to search!");
        }
    }
}
