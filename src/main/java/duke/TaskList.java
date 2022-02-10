package duke;

import java.util.ArrayList;

/**
 * This class contains operations to manipulate the list stored by Duke.
 */

public class TaskList {
    static void add(Task task) {
        assert Storage.taskList != null : "The list does not exist in the first place.";
        Storage.taskList.add(task);
    }

    static String delete(int idx) {
        assert Storage.taskList != null : "The list does not exist in the first place.";
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
        assert Storage.taskList != null : "The list does not exist in the first place.";
        ArrayList<Task> taskArrayList = Storage.taskList;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the tasks in your list: " + "\n");
        for (int i = 0; i < taskArrayList.size(); i++) {
            stringBuilder.append(i + 1).append(". ").append(taskArrayList.get(i)).append("\n");
        }
        return stringBuilder.toString();
    }

    static String find(String term){
        assert Storage.taskList != null : "The list does not exist in the first place.";
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
