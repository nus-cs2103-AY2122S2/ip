package meep.commands;

import meep.task.ListTask;

/**
 * Displays command guidance.
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    public static final String MESSAGE_HELP = "Please follow these command instructions:\n"
            + "      COMMANDS                      | FORMAT                               \n"
            + "      1. normal task                      | todo [task title]\n"
            + "      2. task with deadline        | deadline [ task title ] /by [ date & time ]\n"
            + "      3. task on date                    | event [ task title ] /on [ date & time ]\n"
            + "      4. list all tasks                      | list \n"
            + "      5. list tasks before date   | list [ date & time ]\n"
            + "      6. find task                            | find [ keyword ]\n"
            + "      7. mark task as done        | mark [ task id ]\n"
            + "      8. mark task as undone   | unmark [ task id ]\n"
            + "      9. quit application              | bye\n"
            + "      10. print guidance              | help\n"
            + "                                                                         \n"
            + "      Note for [ date & time ]: enter it strictly in the following format    \n"
            + "         dd/mm/yyyy [ hh : mm ] | eg: 02/12/2019 18:00                       \n"
            + "\n";
    public static final int COMMAND_LENGTH = 1;

    /**
     * Returns guidance.
     *
     * @param tasks task list.
     * @return guidance.
     */
    @Override
    public String execute(ListTask tasks) {
        return MESSAGE_HELP;
    }


    /**
     * Returns command word.
     *
     * @return command word.
     */
    @Override
    public String toString() {
        return COMMAND_WORD;
    }
}
