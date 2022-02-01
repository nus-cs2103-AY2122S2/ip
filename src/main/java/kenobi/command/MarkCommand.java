package kenobi.command;

public class MarkCommand extends Command {
    int toMarkIndex;

    public MarkCommand(int index) {
        toMarkIndex = index - 1;
    }

    @Override
    public String execute() {
        try {
            return "Here you go:\n" + tasks.markTaskAsDone(toMarkIndex);
        } catch (IndexOutOfBoundsException e) {
            return "The archives contain no such task";
        }
    }
}
