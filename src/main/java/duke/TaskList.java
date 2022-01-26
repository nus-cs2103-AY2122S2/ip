package duke;

import java.util.ArrayList;

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
}
