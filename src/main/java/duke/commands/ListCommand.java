package duke.commands;

public class ListCommand extends Command {

    @Override
    public String execute() {
        return taskList.toString();
    }
}
