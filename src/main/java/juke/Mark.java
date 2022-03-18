package juke;

import java.util.ArrayList;

public class Mark {
    public static String addMark(ArrayList<Task> itemList, String reply, Outputs op) {
        assert !itemList.isEmpty() : "List is empty";
        try {
            int index = Integer.valueOf(reply.split(" ")[1]);
            itemList.get(index - 1).markAsDone();
            return op.border +
                    "     Nice! I've marked this task as done: \n" +
                    "       " + itemList.get(index - 1).getDescription() + "\n" +
                    op.instructions +
                    op.border;

        } catch (NumberFormatException n) {
            return "Invalid input, please enter a valid index number instead";
        }
    }

    public static boolean isMark(String[] splittedString) {
        return splittedString[0].equals("mark") && splittedString.length == 2;
    }

}
