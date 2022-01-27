package commands;

import myTasks.Task;

import java.util.List;

public class DeleteTask {
    public static void delete(String number, List<Task> list) {
        int taskNum = Integer.parseInt(number) - 1;
        System.out.println(list.get(taskNum).toString());
        list.remove(taskNum);
    }
}
