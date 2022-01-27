package commands;

import myTasks.Task;

import java.util.List;

public class ShowTasks {
    public static void list(int taskCount, List<Task> list) {
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + "." + list.get(i).toString());
        }
    }

    public static int find(int taskCount, String text, List<Task> list) {
        int count = 0;
        for (int i = 0; i < taskCount; i++) {
            for (String word : list.get(i).description.split(" ")) {
                if (word.equals(text)) {
                    count++;
                    System.out.println((i + 1) + "." + list.get(i).toString());
                }
            }
        }
        return count;
    }
}
