package jarvis.commands;

import jarvis.tasks.TaskList;

public class TagsCommand extends Command {
    /**
     * Function to execute the command and get the result.
     *
     * @param taskList TaskList object
     */
    @Override
    public String getResult(TaskList taskList) {
        return taskList.getTagList().toString();
    }
}
