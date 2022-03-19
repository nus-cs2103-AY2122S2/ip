package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class FindCommand extends Command {
    private String findDetail;

    private static final String MISSING_ID_RESPONSE = "Oops! (・へ・) You have not keyed in a task ID to be found!\n"
            + "Let's try again (๑•̀ㅁ•́๑)✧\n"
            + "Type 'help' if you need to know how to use this command";

    /**
     * Constructor of the FindCommand class.
     *
     * @param findString string provided by the user that is to be found.
     */
    public FindCommand(String findString) {
        assert findString != null : "No string was provided to be found";
        findDetail = findString;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        boolean isEmptyId = findDetail.length() == 0;
        if (isEmptyId) {
            throw new DukeException(MISSING_ID_RESPONSE);
        } else {
            String foundTasks = tasks.findTasks(findDetail);
            if (foundTasks == "") {
                return ("Oops! (・へ・) I could not find anything that matches " + "'" + findDetail + "'\n"
                        + "Please try to search for another word ∑(ﾟ∇ﾟ|||)");
            } else {
                return ("Here are the tasks that contains " + "\"" + findDetail + "\":\n" + foundTasks);
            }
        }
    }
}
