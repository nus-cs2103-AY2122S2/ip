package duke.command;

import duke.List;
import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;



public class FindCommand extends Command {
    private String description;

    public FindCommand(String description) {
        super(false);
        this.description = description;
    }

    @Override
    public void execute(List taskList, Ui ui, Storage storage) throws DukeException {
       List list = taskList.findTask(description);
       ui.printFindTask(list);
    }
}
