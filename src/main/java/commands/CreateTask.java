package commands;

import myTasks.Deadline;
import myTasks.Event;
import myTasks.Task;
import myTasks.Todo;

import java.util.List;

public class CreateTask {
    public static void todo(String description, List<Task> list) {
        list.add(new Todo(description));
    }

    public static void deadline(String description, List<Task> list) {
        String[] restOfPara  = description.split("/by ", 2);
        list.add(new Deadline(restOfPara[0], restOfPara[1]));
    }

    public static void event(String description, List<Task> list) {
        String[] restOfPara2 = description.split("/at ", 2);
        list.add(new Event(restOfPara2[0], restOfPara2[1]));

    }
}
