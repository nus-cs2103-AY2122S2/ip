package duke.command;

import duke.Task;
public class CommandUntag extends Command {
    private String response;
    private Task task;
    private int tagNumber;

    public CommandUntag(Task task, int tagNumber) {
        this.task = task;
        this.tagNumber = tagNumber;
    }

    @Override
    public void execute() {
        task.deleteTag(tagNumber);
        response = "deleted Tag " + tagNumber + task.toString();
    }

    @Override
    public String getResponse() {
        return response;
    }
}
