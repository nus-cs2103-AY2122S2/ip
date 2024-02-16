package bob.command;

import bob.Storage;
import bob.TaskList;
import bob.Ui;
import bob.exception.BobException;
import bob.task.Task;

public class FindCommand extends Command {
    private String search;
    public FindCommand(String search) {
        this.search = search;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage store) throws BobException {
        boolean isResultEmpty = true;
        StringBuilder reply = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.getTask(i);
            if (currentTask.toString().contains(search)) {
                isResultEmpty = false;
                reply.append(ui.say(String.format("\t %o . %s\n", i + 1, currentTask.printStatus())));
            }
        }
        if (isResultEmpty) {
            reply.append(ui.noSearchResults());
        } else {
            StringBuilder hasResultsReply = new StringBuilder(ui.searchHasResults() + "\n");
            hasResultsReply.append(reply.toString());
            hasResultsReply.append(ui.postListFace());
            reply = hasResultsReply;
        }
        return reply.toString();
    }
}
