package kenobi.command;

public class UnmarkCommand extends Command {
    int toUnmarkIndex;

    public UnmarkCommand(int index) {
        toUnmarkIndex = index - 1;
    }

    @Override
    public String execute() {
        try{
            return "I guess you weren't done with this one:\n" + tasks.markTaskAsUndone(toUnmarkIndex);
        } catch (IndexOutOfBoundsException e) {
            return "The archives contain no such task";
        }
    }

}
