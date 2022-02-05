package duke.command;

import duke.List;
import duke.Storage;
import duke.Ui;

public class FindCommand extends Command {
    private String description;

    /**
     * Constructs a find command.
     * @param description Description of the task.
     */
    public FindCommand(String description) {
        super(false);
        this.description = description;
    }

    /**
     * Executes the find command.
     *
     * @param taskList TaskList of current Tasks.
     * @param ui Ui.
     * @param storage Storage.
     * @return Returns a String reply to the user.
     */
    @Override
    public String execute(List taskList, Ui ui, Storage storage) {
        List list = taskList.findTask(description);
        return ui.showFindTask(list);
    }
}
