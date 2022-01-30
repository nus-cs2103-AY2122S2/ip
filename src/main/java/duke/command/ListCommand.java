package duke.command;

public class ListCommand extends Command {
    @Override
    public String execute() {
        return tasks.toString();
    }
}
