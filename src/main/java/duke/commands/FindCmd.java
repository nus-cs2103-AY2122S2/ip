package duke.commands;

import duke.Storage;
import duke.TaskList;

public class FindCmd extends Command{
    private String keyword;

    /**
     * Constructor for FindCommand takes in the index of the task to be marked as
     * done.
     *
     * @param keyword the keyword which is to be searched for in TaskList
     */
    public FindCmd(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        try {
            return taskList.searchKeyword(keyword);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
