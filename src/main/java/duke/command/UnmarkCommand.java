package duke.command;

import duke.task.Task;

public class UnmarkCommand extends Command {
    int toUnmarkIndex;

    public UnmarkCommand(int index) {
        toUnmarkIndex = index - 1;
    }

    @Override
    public String execute() {
        try{
            Task unmarkedTask = tasks.get(toUnmarkIndex).undone();
            return "I assume you weren't done with this one:\n%s" + unmarkedTask;
        } catch (IndexOutOfBoundsException e) {
            return "The archives contain no such task";
        }
    }

}
