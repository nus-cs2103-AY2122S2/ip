package duke.commands;

import duke.commands.Command;
import duke.exception.DukeException;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command<String> {
    private final TaskList list;

    public ListCommand(TaskList list) throws DukeException {
        this.list = list;
        execute();
    }

    private void execute() throws DukeException {
        System.out.print(Ui.lineDivider + "Here are the tasks in your list:\n");
        for (int i = 0; i < list.getSize(); i++) {
            System.out.printf("%d." + list.getTask(i).toString() + "\n", i+1);
        }
        System.out.print(Ui.lineDivider);
    }

    public boolean isExit() {
        return false;
    }
}
