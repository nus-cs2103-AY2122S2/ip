package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    private final String[] keywords;

    public FindCommand(String ... keywords) {
        this.keywords = keywords;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder searchText = new StringBuilder();
        searchText.append("Here are matching tasks \n");
        boolean isFound = false;
        for (String keyword: keywords) {
            for (int i = 0; i < tasks.getSize(); i++) {
                if (checkKeyword(tasks, i, keyword)) {
                    isFound = true;
                    searchText.append("    ").append(i + 1).append(". ")
                            .append(tasks.getByIndex(i)).append("\n");
                }
            }
        }
        if (isFound) {
            searchText.delete(searchText.length() - 1, searchText.length());
            ui.showMessage(searchText.toString());
        } else {
            ui.showMessage("Sorry no result found");
        }
    }

    private boolean checkKeyword(TaskList tasks, int i, String keyword) {
        return tasks.getByIndex(i).getTask().contains(keyword.trim());
    }
}
