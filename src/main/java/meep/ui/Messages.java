package meep.ui;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_LOGO = ""
            + "**       **  ******  ******    *****\n"
            + "* *     * *  *       *         *    *\n"
            + "*  *   *  *  ******  ******    *****\n"
            + "*   * *   *  *       *         *\n"
            + "*    *    *  ******  ******    *\n";

    public static final String MESSAGE_HI = "Hello! I'm Meep\n" + "What can I do for you?";
    public static final String MESSAGE_FILE_MISSING = "Meep was unable to save all the data for the file! "
            + "Please try again.";
    public static final String MESSAGE_INSTRUCTIONS = "Please follow these command instructions:\n"
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
            + "                                                                         \n"
            + "      Note for [ date & time ]: enter it strictly in the following format    \n"
            + "         dd/mm/yyyy [ hh : mm ] | eg: 02/12/2019 18:00                       \n"
            + "\n";

}
