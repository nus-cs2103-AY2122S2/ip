package duke;

import java.util.ArrayList;

/**
 * This class contains operations to manipulate the list stored by Duke.
 */

public class TaskList {
    static void add(Task task) {
        Storage.taskList.add(task);
    }

    static void delete(int idx) {
        try {
            Storage.taskList.remove(idx - 1);
            Storage s = new Storage();
            s.save();
            System.out.println("Successfully deleted file.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Item not found in list.");
        }
    }
    static void list() {
        ArrayList<Task> taskArrayList = Storage.taskList;
        System.out.println("Here are the tasks in your list: " + "\n");
        for (int i = 0; i < taskArrayList.size(); i++) {
            System.out.println( (i + 1) + ". "  + taskArrayList.get(i) + "\n");
        }
    }

    static void find(String term){
        boolean hasResults = false;
        for (Task t : Storage.taskList) {
            if (t.getDescription().contains(term)) {
                hasResults = true;
                System.out.println(t);
            }
        }
        if (!hasResults) {
            System.out.println("Sorry, we didn't find anything related to your search terms.");
        }
    }
}
