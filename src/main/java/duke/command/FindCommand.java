package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder searchText = new StringBuilder();
        searchText.append("Here are matching tasks \n");
        for (int i = 0; i < tasks.getSize(); i++) {
            if (tasks.getByIndex(i).getTask().contains(keyword.trim())) {
                searchText.append("    ").append(i + 1).append(". ")
                        .append(tasks.getByIndex(i))
                        .append("\n");
            }
        }
        searchText.delete(searchText.length() - 1, searchText.length());
        ui.showMessage(searchText.toString());
    }
}
