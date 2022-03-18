package juke;

import java.util.ArrayList;

/**
 * Represents the todo tasks input by the user
 */
public class Todo extends Task {

    /**
     * Super constructor to the task class
     *
     * @param description the description of the todo task
     * @return
     * @throws
     */
    public Todo(String description, String tag) {
        super(description, tag);
    }

    /**
     * Returns todo task in string format
     *
     * @param
     * @return todo task in a string format
     * @throws
     */
    public String getDescription() {
        String newReply = super.description.replace("todo ", "");
        return "[T]" + "[" + super.getStatusIcon() + "] " + newReply;
    }

    public static boolean isTodo(String[] splittedString) {
        return splittedString[0].equals("todo");
    }

    public static String executeTodo(ArrayList<Task> itemList, String[] splittedString, String reply, Outputs op) {
        if (splittedString.length == 1) { //invalid todo command
            return op.border +
                    "     ☹ OOPS!!! The description of a todo cannot be empty.\n" +
                    op.instructions +
                    op.border;
        } else { //valid todo command
            Task todoTask = new Todo(reply, "");
            itemList.add(todoTask);
            return op.border +
                    "     Got it. I've added this task: \n" +
                    "       " + todoTask.getDescription() + "\n" +
                    "     Now you have " + itemList.size() + " tasks in the list.\n" +
                    op.instructions +
                    op.border;
        }
    }

}
