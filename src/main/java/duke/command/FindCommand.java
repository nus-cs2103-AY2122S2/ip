package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.utility.Storage;
import duke.utility.UI;

public class FindCommand extends Command{

    String keyword;
    DukeException dukeException;

    public FindCommand(String command) {
        super(command);

        String[] commandParsed = command.split(" ");
        try {
            this.keyword = commandParsed[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.dukeException = new DukeException(" input keyword to search");
        }
    }

    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        if(this.dukeException != null) {
            throw this.dukeException;
        }
        TaskList findTasks = tasks.filterByKeyword(this.keyword);
        findTasks.printTasks(ui);
        return null;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
