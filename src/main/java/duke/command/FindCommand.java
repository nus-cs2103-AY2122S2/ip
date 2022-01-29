package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;
import duke.task.Todo;

public class FindCommand extends Command {
    String findDetail;
    private static final String DESC_RESPONSE = "Oops! (・へ・) You have not keyed in a description for the duke.task!\n" +
            "Let's try again (๑•̀ㅁ•́๑)✧\n" +
            "Type 'help' if you need to know how to use this duke.command";

    public FindCommand(String findString){
        findDetail = findString;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (findDetail.length() == 0) {
            throw new DukeException(DESC_RESPONSE);
        } else {
            String foundTasks = tasks.findTasks(findDetail);
            if (foundTasks == "") {
                System.out.println("Oops! (・へ・) I could not find anything that matches " + "'"+ findDetail +"'\n"
                + "Please try to search for another word ∑(ﾟ∇ﾟ|||)");
            } else {
                System.out.println("Here are the tasks that contains " + findDetail);
                System.out.println(foundTasks);
            }
        }
    }
}
