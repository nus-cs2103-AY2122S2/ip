package meep.TextUi;

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
    public static final String MESSAGE_BYE = "Bye. Hope to see you again soon!";
    public static final String MESSAGE_INSTRUCTIONS = "\n"
                    + "     **********************************************************************\n"
                    + "     * COMMANDS              | FORMAT                                     *\n"
                    + "     * normal task           | todo [task title]                          *\n"
                    + "     * task with deadline    | deadline [task title] /by [date&time]      *\n"
                    + "     * task on date          | event [task title] /on [date&time]         *\n"
                    + "     * list all tasks        | list                                       *\n"
                    + "     * list tasks before date| list [date&time]                           *\n"
                    + "     * find task             | find [keyword]                             *\n"
                    + "     * mark task as done     | mark [task id]                             *\n"
                    + "     * mark task as undone   | unmark [task id]                           *\n"
                    + "     * quit application      | bye                                        *\n"
                    + "     *                                                                    *\n"
                    + "     * Note for [date&time]: enter it strictly in the following format    *\n"
                    + "     *    dd/mm/yyyy [hh:mm] | eg: 02/12/2019 18:00                       *\n"
                    + "     **********************************************************************\n"
                    + "\n";

}
