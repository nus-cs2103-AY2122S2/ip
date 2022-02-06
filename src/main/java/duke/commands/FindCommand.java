package duke.commands;

import java.util.ArrayList;
import java.util.Locale;
import java.util.stream.Collectors;

import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

public class FindCommand extends Command {

    private String keyword;
    private ArrayList<Task> tasks;

    /**
     * Find the list of tasks with matching keywords
     *
     * @param taskList list of task
     * @param userInput user input
     * @throws ArrayIndexOutOfBoundsException if no tasks found with given keyword
     */
    public FindCommand(TaskList taskList, String userInput) {
        try {
            String currKeyword = userInput.split(" ")[1];
            ArrayList<Task> foundTasks = (ArrayList<Task>) taskList.getTasks().stream()
                    .filter(t -> t.getDescription().toLowerCase(Locale.ROOT).contains(currKeyword))
                    .collect(Collectors.toList());

            boolean noMatch = foundTasks.isEmpty();

            if (noMatch) {
                Ui.printNoTaskFound();
                return;
            }

            keyword = currKeyword;
            tasks = foundTasks;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please enter keyword of task to search!");
        }
    }

    @Override
    public String execute(TaskList tasks, Ui ui) {
        return Ui.printFindResultHeader(keyword, this.tasks);
    }
}
