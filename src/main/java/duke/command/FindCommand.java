package duke.command;

import duke.helpTool.Storage;
import duke.helpTool.TaskList;
import duke.helpTool.Ui;

public class FindCommand extends Command{
    String findDetail;

    public FindCommand(String findDetail){
        this.findDetail = findDetail;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchList = new TaskList();
        for (int i = 0; i < tasks.getSize(); i++) {
            if (tasks.getTask(i).getDescription().contains(findDetail)){
                matchList.addTask(tasks.getTask(i));
            }
        }
        ui.showMatchedTask(matchList);
    }
}
