package athena.commands;

import athena.tasks.TaskList;
import athena.ui.Ui;

import java.util.List;

public class FindCommand extends Command {
    private final String searchPhrase;

    public FindCommand(String searchPhrase) {
        this.searchPhrase = searchPhrase;
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        List<Integer> taskNumbers = taskList.getTaskNumbersContainingPhrase(searchPhrase);
        if (taskNumbers.size() > 0) {
            ui.sayText(String.format("Here are the tasks containing the phrase '%s'", searchPhrase));
            ui.showSpecificTasks(taskNumbers);
        } else {
            ui.sayText(String.format("Sorry, no tasks were found containing the phrase '%s'", searchPhrase));
        }
    }
}
