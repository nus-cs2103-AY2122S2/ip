package duke.command;
import duke.Task;

public class CommandTag extends Command {
    private String response;
    private Task task;
    private String tagMessage;
    public CommandTag(Task task, String tagMessage) {
        this.task = task;
        this.tagMessage = tagMessage;
    }

    @Override
    public void execute() {
        task.addTag(tagMessage);
        response = "Added tag:" + task.toString();
    }

    @Override
    public String getResponse() {
        return response;
    }
}
