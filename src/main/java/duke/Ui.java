package duke;

import duke.task.Task;
import duke.task.TaskList;

import java.util.List;

public class Ui {
    private static final String LINES = "____________________________________________________________";

    Ui() {
    }

    public void showWelcome() {
        System.out.println(LINES);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(LINES);
    }

    public void sayGoodbye() {
        System.out.println(LINES + "\nBye. Hope to see you again soon!\n" + LINES);
    }

    public void sayMessage(String s) {
        System.out.println(LINES + "\n" + s + "\n" + LINES);
    }

    public void listTasks(TaskList tasks) {
        System.out.println(LINES);
        System.out.println("Here are the tasks in your list:");
        List<Task> list = tasks.getTasks();
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + "." + list.get(i).toString());
        }
        System.out.println(LINES);
    }

    public void listTasks(List<Task> tasks) {
        System.out.println(LINES);
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
        System.out.println(LINES);
    }
}
