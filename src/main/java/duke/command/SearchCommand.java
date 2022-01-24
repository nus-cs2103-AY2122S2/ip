package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;


/**
 * Represents a search command to display all the matches
 */
public class SearchCommand extends Command {
    private final LocalDate date;

    /**
     * Returns a search command with match date.
     *
     * @param date date of search.
     */
    public SearchCommand(LocalDate date) {
        super();
        this.date = date;
    }

    /**
     * Filters and formats the string out put of list by the date.
     *
     * @param tasks   the entire TaskList.
     * @param ui      the ui interface and messages.
     * @param storage the storage operations.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder searchText = new StringBuilder();
        searchText.append("Here are the tasks on ")
                .append(date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))).append(" \n");
        for (int i = 0; i < tasks.getSize(); i++) {
            LocalDate currDate = tasks.getByIndex(i).getDate();
            if (currDate != null && currDate.equals(date)) {
                String index = String.valueOf(i + 1);
                searchText.append("    ").append(String.format("%1$3s", index)).append(". ")
                        .append(tasks.getByIndex(i))
                        .append("\n");
            }
        }
        searchText.delete(searchText.length() - 1, searchText.length());
        ui.showMessage(searchText.toString());
    }
}
