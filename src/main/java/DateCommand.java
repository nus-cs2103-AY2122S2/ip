import java.io.IOException;

public class DateCommand extends Command{
    private DateTable dateTable;
    private String description;

    public DateCommand(DateTable dateTable, String description) {
        this.dateTable = dateTable;
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage, DateTable dateTable) {
        dateTable.getEventOnDate(description);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
