package duke.command;
import duke.Task;

public class CommandTag extends Command {
    private Task task;
    private String tagMessage;
    public CommandTag(Task task, String tagMessage) {
        this.task = task;
        this.tagMessage = tagMessage;
    }

    @Override
    public String execute() {
        task.addTag(tagMessage);
        return "Added tag:" + task.toString();
    }
}
