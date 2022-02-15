package duke.command;

import java.util.Arrays;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    private final String[] keywords;
    private boolean isFound;

    /**
     * Returns an error command with error message.
     *
     * @param keywords the keywords to be found.
     */
    public FindCommand(String... keywords) {
        this.keywords = keywords;
        this.isFound = false;
    }

    /**
     * Returns a find command.
     *
     * @param tasks   the entire TaskList.
     * @param ui      the ui interface and messages.
     * @param storage the storage operations.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Arrays.stream(keywords).forEach(k -> handleFind(tasks, ui, k));
    }

    private void handleFind(TaskList tasks, Ui ui, String keyword) {
        StringBuilder searchText = new StringBuilder("Here are matching tasks \n");
        for (int i = 0; i < tasks.getSize(); i++) {
            searchText.append(buildSearchResult(tasks, keyword, i));
        }
        if (isFound) {
            ui.showMessage(searchText.toString());
        } else {
            ui.showMessage("Sorry no result found");
        }
    }

    private String buildSearchResult(TaskList tasks, String keyword, int i) {
        StringBuilder searchText = new StringBuilder();
        if (checkKeyword(tasks, i, keyword)) {
            isFound = true;
            String index = String.valueOf(i + 1);
            searchText.append("    ").append(String.format("%1$3s", index)).append(". ")
                    .append(tasks.getByIndex(i)).append("\n");
        }
        return searchText.toString();
    }

    private boolean checkKeyword(TaskList tasks, int i, String keyword) {
        String targetTask = tasks.getByIndex(i).getTask();
        return targetTask.contains(keyword.trim());
    }
}
