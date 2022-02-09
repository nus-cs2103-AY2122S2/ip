package seedu.command;

import seedu.duke.DukeException;
import seedu.duke.Storage;
import seedu.duke.TaskList;

import java.io.IOException;

public class FindCommand extends Command{
     public static String run(String keyword, TaskList tasksList) throws DukeException, IOException {
            String result = "Here are the matching tasks in your list:\n";
            result += tasksList.findTasks(keyword).toString();
            return result;
    }
}
