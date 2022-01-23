package spike.command;

import spike.task.TaskList;

public class IncorrectCommand extends Command{
    private String errorMsg;

    public IncorrectCommand(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String execute(TaskList tasks) {
        return errorMsg;
    }

}
