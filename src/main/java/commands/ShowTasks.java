package commands;

import java.util.List;

import mytasks.Task;

/**
 * ShowTasks class displays the tasks that are currently being tracked.
 */
public class ShowTasks {
    /**
     * Method showList displays all currently tracked tasks.
     * @param taskCount the number of task currently being tracked.
     * @param list contains the list of tasks that are currently being tracked.
     */
    public static void showList(int taskCount, List<Task> list) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + "." + list.get(i).toString());
        }
    }

    /**
     * Finds and displays tasks that match the keyword/task the user has input.
     * @param taskCount the number of task currently being tracked.
     * @param keyword the text/task the user wants to find,
     * @param list contains the list of tasks that are currently being tracked.
     */
    public static int find(int taskCount, String keyword, List<Task> list) {
        System.out.println("Here are the matching tasks in your list:");
        int count = 0;
        for (int i = 0; i < taskCount; i++) {
            for (String word : list.get(i).getDescription().split(" ")) {
                if (word.equals(keyword)) {
                    count++;
                    System.out.println((i + 1) + "." + list.get(i).toString());
                }
            }
        }
        return count;
    }
}
