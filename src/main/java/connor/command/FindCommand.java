package connor.command;

import connor.task.TaskList;

public class FindCommand extends Command {
    private String desc;

    public FindCommand(String desc) {
        this.desc = desc;
    }

    @Override
    public String activate() {
        return TaskList.findTasks(desc);
    }
}
