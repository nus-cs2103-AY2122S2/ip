package jarvis.commands;

import jarvis.tasks.TaskList;

public class HelpCommand extends Command {
    private static final String HELP_MSG = "List of commands:\n"
        + "* add tasks - [todo|deadline|event] desc\n"
        + "* delete task - delete idx\n"
        + "* find task - find idx\n"
        + "* list tasks - list\n"
        + "* mark task - mark idx\n"
        + "* unmark task - unmark idx\n"
        + "* list tags - tags\n"
        + "* list task tags - tasktags idx\n"
        + "* tag task - tag idx tagName\n"
        + "* untag task - untag idx tagName\n"
        + "* quit - bye";

    /**
     * Function to execute the command and get the result.
     *
     * @param dukeList dukeList object
     */
    @Override
    public String getResult(TaskList dukeList) {
        return HELP_MSG;
    }
}
