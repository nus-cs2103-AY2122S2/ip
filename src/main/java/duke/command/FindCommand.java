package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class FindCommand extends Command {
    private String findDetail;

    private static final String DESC_RESPONSE = "Oops! (・へ・) You have not keyed in a description for the task!\n"
            + "Let's try again (๑•̀ㅁ•́๑)✧\n"
            + "Type 'help' if you need to know how to use this command";

    public FindCommand(String findString) {
        findDetail = findString;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (findDetail.length() == 0) {
            throw new DukeException(DESC_RESPONSE);
        } else {
            String foundTasks = tasks.findTasks(findDetail);
            if (foundTasks == "") {
                return ("Oops! (・へ・) I could not find anything that matches " + "'" + findDetail + "'\n"
                        + "Please try to search for another word ∑(ﾟ∇ﾟ|||)");
            } else {
                return ("Here are the tasks that contains " + findDetail + foundTasks);
            }
        }
    }
}
