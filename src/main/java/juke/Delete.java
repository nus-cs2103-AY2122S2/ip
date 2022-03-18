package juke;

import java.util.ArrayList;

public class Delete {
    public static boolean isDelete(String[] splittedString) {
        return splittedString[0].equals("delete");
    }

    public static String executeDelete(ArrayList<Task> itemList, String[] splittedString, Outputs op) {
        assert !itemList.isEmpty() : "List is empty";
        try {
            int index = Integer.valueOf(splittedString[1]);

            String toRemove = itemList.remove(index - 1).getDescription();
            return op.border +
                    "     Noted. I've removed this task: \n" +
                    "       " + toRemove + "\n" +
                    "     Now you have " + itemList.size() + " tasks in the list.\n" + op.instructions +
                    op.border;
        } catch (NumberFormatException n) {
            return "Invalid input, please enter a valid index number instead";
        }
    }

}
