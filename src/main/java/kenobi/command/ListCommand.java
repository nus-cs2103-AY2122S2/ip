package kenobi.command;

public class ListCommand extends Command {
    @Override
    public String execute() {
        if (tasks.isEmpty()) {
            return "You don't have no tasks in the archives";
        }

        return "Here are all of your tasks in the archives\n" + tasks;
    }
}
