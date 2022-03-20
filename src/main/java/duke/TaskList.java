package duke;

import java.util.ArrayList;

/**
 * This class contains operations to manipulate the list stored by Duke.
 */

public class TaskList {
    static void assertTaskListNotNull() {
        assert Storage.taskList != null : "The list does not exist in the first place.";
    }

    static void add(Task task) {
        assertTaskListNotNull();
        Storage.taskList.add(task);
    }

    static String delete(int idx) {
        assertTaskListNotNull();
        try {
            Storage.taskList.remove(idx - 1);
            Storage s = new Storage();
            s.save();
            return "Successfully deleted task.";
        } catch (IndexOutOfBoundsException e) {
            return "Item not found in list.";
        }
    }

    /**
     * Concat each task in the task list into a single string.
     * @return String which displays the list of all the tasks stored by the application so far.
     */

    static String list() {
        assertTaskListNotNull();
        ArrayList<Task> taskArrayList = Storage.taskList;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the tasks in your list: " + "\n");
        for (int i = 0; i < taskArrayList.size(); i++) {
            stringBuilder.append(i + 1).append(". ").append(taskArrayList.get(i)).append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Concat each task that was found in the task list into a single string.
     * @return String which displays the list of all the tasks found.
     */

    static String find(String term){
        assertTaskListNotNull();
        StringBuilder stringBuilder = new StringBuilder();
        boolean hasResults = false;
        if (term.trim().isEmpty()) {
            return "Sorry, the search term cannot be empty.";
        }
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
