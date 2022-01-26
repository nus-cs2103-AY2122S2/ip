package duke;

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
}
