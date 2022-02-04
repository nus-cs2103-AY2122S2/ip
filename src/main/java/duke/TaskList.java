package duke;

import java.util.ArrayList;

/**
 * This class contains operations to manipulate the list stored by Duke.
 */

public class TaskList {
    static void add(Task task) {
        Storage.taskList.add(task);
    }

    static String delete(int idx) {
        try {
            Storage.taskList.remove(idx - 1);
            Storage s = new Storage();
            s.save();
            return "Successfully deleted file.";
        } catch (IndexOutOfBoundsException e) {
            return "Item not found in list.";
        }
    }

    static String list() {
        ArrayList<Task> taskArrayList = Storage.taskList;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the tasks in your list: " + "\n");
        for (int i = 0; i < taskArrayList.size(); i++) {
            stringBuilder.append(i + 1).append(". ").append(taskArrayList.get(i)).append("\n");
        }
        return stringBuilder.toString();
    }

    static String find(String term){
        StringBuilder stringBuilder = new StringBuilder();
        boolean hasResults = false;
        for (Task t : Storage.taskList) {
            if (t.getDescription().contains(term)) {
                hasResults = true;
                stringBuilder.append(t).append('\n');
            }
        }
        if (!hasResults) {
            return "Sorry, we didn't find anything related to your search terms.";
        } else {
            return stringBuilder.toString();
        }
    }
}
