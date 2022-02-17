package duke.commands;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

import duke.exceptions.DukeException;
import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;
import duke.validation.TaskValidator;

public class ReminderCommand extends Command {

    private LocalDate keyword;
    private String userInput;
    /**
     * Find the list of tasks with matching keywords
     *
     * @param userInput user input
     * @throws ArrayIndexOutOfBoundsException if no tasks found with given keyword
     */
    public ReminderCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public String execute(TaskList taskList, Ui ui) throws DukeException {
        try {
            LocalDate currKeyword = TaskValidator.convertDate(userInput.split(" ")[1]);
            System.out.println(currKeyword);
            ArrayList<Task> foundTasks = (ArrayList<Task>) taskList.getTasks().stream()
                    .filter(t -> t.getDate() != null && t.getDate().isEqual(currKeyword))
                    .collect(Collectors.toList());

            boolean noMatch = foundTasks.isEmpty();

            if (noMatch) {
                Ui.printNoTaskFound();
                return null;
            }

            keyword = currKeyword;

            return Ui.printReminderResultHeader(keyword, foundTasks);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please enter the date of the task you wish to search!");
        }
    }
}
