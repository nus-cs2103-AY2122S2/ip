package duke.helptool;

/**
 * The type Ui.
 */
public class Ui {
    /**
     * Instantiates a new Ui.
     */
    public Ui() {
    }

    /**
     * Show exception error.
     *
     * @param e the e
     */
    public String showExceptionError(DukeException e) {
        return e.getMessage();
    }

    /**
     * Show success mark.
     *
     * @param input the input
     */
    public String showSuccessMark(String input) {
        return "Nice! I've marked this task as done:\n" + input + "\n";
    }

    /**
     * Show success unmark.
     *
     * @param input the input
     */
    public String showSuccessUnmark(String input) {
        return "OK, I've marked this task as not done yet:\n" + input + "\n";
    }

    /**
     * Show delete.
     *
     * @param input           the input
     * @param sizeAfterDelete the size after delete
     */
    public String showDelete(String input, int sizeAfterDelete) {
        return String.format("OK, I've removed this task:\n\t%s\nNow you have %d tasks in the list.\n",
                    input, sizeAfterDelete);
    }

    /**
     * Show list.
     *
     * @param tasks the tasks
     */
    public String showList(TaskList tasks) {
        StringBuilder temp = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.getSize(); i++) {
            if (tasks.getTask(i) != null) {
                temp.append(String.format("%s. %s\n", i + 1, tasks.getTask(i).toString()));
            }
        }
        return temp.toString();
    }

    /**
     * Show matched task.
     *
     * @param tasks the tasks
     */
    public String showMatchedTask(TaskList tasks) {
        StringBuilder temp = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.getSize(); i++) {
            temp.append(String.format("%s. %s\n", i + 1, tasks.getTask(i).toString()));
        }
        return temp.toString();
    }

    /**
     * Show add todo.
     *
     * @param todo the todo
     * @param size the size
     */
    public String showAddTodo(String todo, int size) {
        return String.format("Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list.\n",
                todo, size);
    }

    /**
     * Show add deadline.
     *
     * @param ddl  the ddl
     * @param size the size
     */
    public String showAddDeadline(String ddl, int size) {
        return String.format("Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list.\n",
                ddl, size);
    }

    /**
     * Show add event.
     *
     * @param event the event
     * @param size  the size
     */
    public String showAddEvent(String event, int size) {
        return String.format("Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list.\n",
                event, size);
    }

    /**
     * Bye.
     */
    public String showBye() {
        // print result
        return "Bye. Hope to see you again soon!\nThe application will close automatically in 3 seconds...\n";
    }

}
