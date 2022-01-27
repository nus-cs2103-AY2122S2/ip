import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ui {
    public Ui() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "\nHello! I'm Duke\nWhat can i do for you?");
    }

    public void allTasks(String inputText, TaskList tasks) {
        int taskCount = tasks.list.size();
        List<Task> list = tasks.list;
        String[] tempList = inputText.split(" ", 2);

        try {
            new DukeException().invalidChecker(tempList, taskCount);
        } catch (DukeException e) {
            System.err.println(e);
        }

        System.out.println("------------------------------------------------------------");
        switch (tempList[0]) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                break;
            case "list":
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + "." + list.get(i).toString());
                }
                break;
            case "todo":
                tasks.todo(tempList[1], list);
                System.out.println(list.get(taskCount).toString());
                System.out.println("Got it. I've added this task:");
                printAndSave(taskCount + 1, list);
                break;
            case "deadline":
                tasks.deadline(tempList[1], list);
                System.out.println(list.get(taskCount).toString());
                printAndSave(taskCount + 1, list);
                System.out.println("Got it. I've added this task:");
                break;
            case "event":
                tasks.event(tempList[1], list);
                System.out.println(list.get(taskCount).toString());
                printAndSave(taskCount + 1, list);
                System.out.println("Got it. I've added this task:");
                break;
            case "mark":
                tasks.mark(tempList[1], list);
                printAndSave(taskCount, list);
                System.out.println("Nice! I've marked this task as done:");
                break;
            case "unmark":
                tasks.unmark(tempList[1], list);
                printAndSave(taskCount, list);
                System.out.println("OK, I've marked this task as not done yet:");
                break;
            case "delete":
                System.out.println("Noted. I've removed this task:");
                tasks.delete(tempList[1], list);
                printAndSave(taskCount - 1, list);
                break;
        }
        System.out.println("------------------------------------------------------------");
    }

    public static void printAndSave(int taskCount, List<Task> list) {
        if (taskCount + 1 == 1) {
            System.out.println("You have " + (taskCount) + " task in the list.");
        } else {
            System.out.println("You have " + (taskCount) + " tasks in the list.");
        }
    }
}
