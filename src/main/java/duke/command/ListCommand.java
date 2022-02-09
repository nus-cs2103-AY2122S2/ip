package duke.command;

import duke.task.Task;
import duke.ui.Ui;

public class ListCommand extends Command {
    public ListCommand() {
        super("list");
    }

    /** Executes the list command
     * @return a String containing the current list
     */
    public String execute() {
        String listTask = Task.printTaskList();
        String output = Ui.createLine()
                + listTask + "\n"
                + Ui.createLine();
        return output;
    }

    @Override
    public String toString(){
        return "list";
    }
}
