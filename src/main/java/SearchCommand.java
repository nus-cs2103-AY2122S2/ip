import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SearchCommand extends Command {
    private final LocalDate date;

    SearchCommand (LocalDate date) {
        super();
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder searchText = new StringBuilder();
        searchText.append("Here are the tasks on ").append(date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))).append(" \n");
        for (int i = 0; i < tasks.getSize(); i++) {
            if (tasks.getByIndex(i).getDate() != null && tasks.getByIndex(i).getDate().equals(date)) {
                searchText.append("    ").append(i + 1).append(". ")
                        .append(tasks.getByIndex(i))
                        .append("\n");
            }
        }
        searchText.delete(searchText.length() - 1, searchText.length());
        ui.showMessage(searchText.toString());
    }
}
