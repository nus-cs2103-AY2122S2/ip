package juke;

import java.util.ArrayList;

public class Tag {

    public static String addTag(ArrayList<Task> itemList, String[] splittedString, String reply, Outputs op) {
        assert !itemList.isEmpty() : "List is empty";
        String tag = "#" + splittedString[2];
        try {
            int index = Integer.valueOf(reply.split(" ")[1]);
            itemList.get(index - 1).setTag(tag);
            return op.border +
                    "     Nice! I've tagged " + '"' + itemList.get(index - 1).getDescription() + '"' +
                    " with " + tag + "       " + "\n" + op.instructions + op.border;
        } catch (NumberFormatException n) {
            return "Invalid input, please enter a valid index number instead";
        }
    }

    public static boolean isTag(String[] splittedString) {
        return splittedString[0].equals("tag") && splittedString.length == 3;
    }

}
