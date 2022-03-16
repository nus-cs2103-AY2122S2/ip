package juke;

import java.util.ArrayList;
import java.util.List;

public class Generalcommands {

    public static String executeList(ArrayList<Task> itemList, Outputs op) {
        String totalString = op.border +
                "    Here are the tasks in your list:\n";
        for (int i = 0; i < itemList.size(); i++) {
            totalString += "    " + (i + 1) + ". " + itemList.get(i).getDescription() + " " +
                    itemList.get(i).tag + "\n";
        }
        totalString += op.instructions +
                op.border;
        return totalString;
    }

    public static String executeFind(ArrayList<Task> itemList, String[] splittedString, Outputs op) {
        String keyword = splittedString[1];
        List<Task> filteredItemList = new ArrayList<>();
        for (Task t : itemList) {
            filteredItemList.add(t);
        }
        filteredItemList.removeIf(s -> !s.getDescription().contains(keyword));
        String relevantTasks = "";
        int count = 1;
        for (Task t : filteredItemList) {
            relevantTasks += "     " + count + "." + t.getDescription() + " " + t.tag + "\n";
            count++;
        }
        if (relevantTasks.equals("")) { //no tasks with relevant keywords
            return op.border
                    + "     Sorry, there are no matching tasks\n"
                    + op.instructions + op.border;
        } else {
            return op.border
                    + "     Here are the matching tasks in your list:\n" + relevantTasks
                    + op.instructions + op.border;
        }
    }
}
