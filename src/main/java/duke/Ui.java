package duke;

import myTasks.TaskList;
import myTasks.Task;
import commands.*;

import java.util.List;

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
            return;
        }

        System.out.println("------------------------------------------------------------");
        switch (tempList[0]) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                break;
            case "list":
                System.out.println("Here are the tasks in your list:");
                ShowTasks.list(taskCount, list);
                break;
            case "find":
                System.out.println("Here are the matching tasks in your list:");
                ShowTasks.find(taskCount, tempList[1], list);
                break;
            case "todo":
                CreateTask.todo(tempList[1], list);
                System.out.println(list.get(taskCount).toString());
                System.out.println("Got it. I've added this task:");
                printAndSave(taskCount + 1, list);
                break;
            case "deadline":
                CreateTask.deadline(tempList[1], list);
                System.out.println(list.get(taskCount).toString());
                printAndSave(taskCount + 1, list);
                System.out.println("Got it. I've added this task:");
                break;
            case "event":
                CreateTask.event(tempList[1], list);
                System.out.println(list.get(taskCount).toString());
                printAndSave(taskCount + 1, list);
                System.out.println("Got it. I've added this task:");
                break;
            case "mark":
                MarkTask.mark(tempList[1], list);
                printAndSave(taskCount, list);
                System.out.println("Nice! I've marked this task as done:");
                break;
            case "unmark":
                MarkTask.unmark(tempList[1], list);
                printAndSave(taskCount, list);
                System.out.println("OK, I've marked this task as not done yet:");
                break;
            case "delete":
                System.out.println("Noted. I've removed this task:");
                DeleteTask.delete(tempList[1], list);
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
