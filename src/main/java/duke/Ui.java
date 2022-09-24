package duke;

/**
 * Represenst a user interface.
 */
public class Ui {

    public static final String WELCOME_MSG = "Hi! I'm Duke.\nWhat can I do for you?\n" + Ui.HELP_MSG;
    public static final String BYE_MSG = "Bye. Hope to see you again soon!";
    public static final String INVALID_MSG = "I don't understand your query! Please try again.";
    public static final String HELP_MSG = "Here are the commands you can give to Duke:\n"
            + "1. todo [TO_DO_TASK] (To add a to do task)\n"
            + "2. deadline [DEADLINE] /by [YYYY-MM-DDTHH:mm:ss] (To add a deadline)\n"
            + "3. event [EVENT] /at [YYYY-MM-DDTHH:mm:ss] (To add an event)\n"
            + "4. list (To list all your tasks)\n"
            + "5. find [KEYWORD] (To find tasks with the specified keyword)\n"
            + "6. delete [TASK_NUMBER] (To delete a task by its number)\n"
            + "7. mark [TASK_NUMBER] (To mark a task as done)\n"
            + "8. unmark [TASK_NUMBER] (To mark a task as not done)\n"
            + "9. help (To display this help page)\n"
            + "10. bye (To exit duke)";

    /**
     * Constructor for a Ui object.
     */
    public Ui() {}

}
