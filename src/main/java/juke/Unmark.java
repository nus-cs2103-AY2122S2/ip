package juke;

import java.util.ArrayList;

public class Unmark {
    public static String addUnmark(ArrayList<Task> itemList, String reply, Outputs op) {
        assert !itemList.isEmpty() : "List is empty";
        try {
            int index = Integer.valueOf(reply.split(" ")[1]);
            itemList.get(index - 1).markAsUndone();
            return op.border +
                    "     OK, I've marked this task as not done yet: \n" +
                    "       " + itemList.get(index - 1).getDescription() + "\n" +
                    op.instructions +
                    op.border;
        } catch (NumberFormatException n) {
            return "Invalid input, please enter a valid index number instead";
        }
    }

    public static boolean isUnmark(String[] splittedString) {
        return splittedString[0].equals("unmark") && splittedString.length == 2;
    }

}
