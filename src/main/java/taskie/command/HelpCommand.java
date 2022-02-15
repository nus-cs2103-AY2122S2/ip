package taskie.command;

import taskie.storage.Storage;
import taskie.tasklist.TaskList;
import taskie.ui.Ui;

public class HelpCommand extends Command {
    private String keyword;

    public HelpCommand() {
        super("help");
        this.keyword = "";
    }

    public HelpCommand(String keyword) {
        super("help");
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, StringBuilder response) {
        if (hasKeyword()) {
            response.append(ui.getHelpMessage(keyword));
        } else {
            response.append(ui.getHelpMessage());
        }
        assert response.length() > 0; // response should not be empty
    }

    private boolean hasKeyword() {
        return !keyword.equals("");
    }
}
