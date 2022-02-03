package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.utility.UI;
import duke.task.TaskList;
import duke.utility.Storage;

/**
 * Command for mark and unmark for tasks
 */
public class MarkCommand extends Command {

    boolean isMark;
    int index;
    DukeException dukeException;

    public MarkCommand(String command) {
        super(command);
        String[] stringSplit = command.split(" ");
        String prefix = stringSplit[0];
        if(prefix.equals("mark")){
            this.isMark = true;
        } else if(prefix.equals("unmark")){
            this.isMark = false;
        }
        try {
            this.index = Integer.parseInt(stringSplit[1]);
        } catch (ArrayIndexOutOfBoundsException e){
            this.dukeException = new DukeException("input number within range!");
        }
    }

    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        if (this.dukeException != null) {
            throw this.dukeException;
        } else if (this.index <= 0 || this.index > tasks.getSize()) {
            throw new DukeException("Please only input integers within the range of your tasks");
        }

        Task task = tasks.getByNumber(this.index);
        if (this.isMark) {
            task.setMarked(true);
        } else {
            task.setMarked(false);
        }
        ui.print(String.format("I've marked duke.task %d!", this.index));
        storage.save(tasks);
        return null;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
