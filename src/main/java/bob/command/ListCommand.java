package bob.command;

import bob.exception.BobException;

import bob.TaskList;
import bob.Ui;
import bob.Storage;

public class ListCommand extends Command {

    private boolean isGreetList;

    public ListCommand() {}

    public ListCommand(boolean isGreetList) {
        this.isGreetList = isGreetList;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) throws BobException {
        if (tasks.isEmpty()) {
            ui.free();
        } else {
            if (!isGreetList) {
                ui.preListReply();
            }
            for (int i = 1; i <= tasks.size(); i++) {
                ui.say(String.format("\t %o . %s", i, tasks.getTask(i - 1).printStatus()));
            }
            ui.postListFace();
        }
    }
}
