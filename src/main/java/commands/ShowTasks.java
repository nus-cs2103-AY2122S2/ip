package commands;

import myTasks.Task;

import java.util.List;

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
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + "." + list.get(i).toString());
        }
    }

    /**
     * Method find finds and displays tasks that match the keyword/task the user has input.
     * @param taskCount the number of task currently being tracked.
     * @param keyword the text/task the user wants to find,
     * @param list contains the list of tasks that are currently being tracked.
     */
    public static int find(int taskCount, String keyword, List<Task> list) {
        int count = 0;
        for (int i = 0; i < taskCount; i++) {
            for (String word : list.get(i).description.split(" ")) {
                if (word.equals(keyword)) {
                    count++;
                    System.out.println((i + 1) + "." + list.get(i).toString());
                }
            }
        }
        return count;
    }
}
