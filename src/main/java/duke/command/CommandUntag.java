package duke.command;

import duke.Task;
public class CommandUntag extends Command {
    private Task task;
    private int tagNumber;

    public CommandUntag(Task task, int tagNumber) {
        this.task = task;
        this.tagNumber = tagNumber;
    }

    @Override
    public String execute() {
        task.deleteTag(tagNumber);
        return "deleted Tag " + tagNumber + task.toString();
    }
}
