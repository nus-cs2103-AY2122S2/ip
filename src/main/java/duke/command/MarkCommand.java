package duke.command;

import duke.task.Task;

public class MarkCommand extends Command {
    int toMarkIndex;

    public MarkCommand(int index) {
        toMarkIndex = index - 1;
    }

    @Override
    public String execute() {
        try {
            Task markedTask = tasks.get(toMarkIndex).done();
            return "Here you go:\n%s" + markedTask;
        } catch (IndexOutOfBoundsException e) {
            return "The archives contain no such task";
        }
    }
}
