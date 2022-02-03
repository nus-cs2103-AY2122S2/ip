package duke.commands;

import duke.tasks.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;
import java.util.Locale;
import java.util.stream.Collectors;

public class FindCommand extends Command {
    /**
     * Find the list of tasks with matching keywords
     *
     * @param taskList list of task
     * @param userInput user input
     * @throws ArrayIndexOutOfBoundsException if no tasks found with given keyword
     */
    public FindCommand(TaskList taskList, String userInput) {
        try {
            String keyword = userInput.split(" ")[1];
            ArrayList<Task> foundTasks = (ArrayList<Task>) taskList.getTasks().stream()
                    .filter(t -> t.getDescription().toLowerCase(Locale.ROOT).contains(keyword))
                    .collect(Collectors.toList());

            boolean noMatch = foundTasks.isEmpty();

            if (noMatch) {
                Ui.printNoTaskFound();
                return;
            }

            Ui.printFindResultHeader(keyword);
            Ui.printTasks(foundTasks);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please enter keyword of task to search!");
        }
    }
}
