import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String inputText = "";
        List<Task> list = new ArrayList<>();

        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "\nHello! I'm Duke\nWhat can i do for you?\n");

        while (!inputText.equals("bye")) {
            inputText = input.nextLine();
            String[] tempList = inputText.split(" ", 2);
            int tasks = list.size();

            try {
                new DukeException().invalidChecker(tempList, tasks);
            } catch (DukeException e) {
                System.err.println(e);
                continue;
            }

            System.out.println("------------------------------------------------------------");
            switch (tempList[0]) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks; i++) {
                        System.out.println((i + 1) + "." + list.get(i).toString());
                    }
                    break;
                case "todo":
                    list.add(new Todo(tempList[1]));
                    System.out.println(list.get(tasks).toString());
                    printTaskList(tasks + 1);
                    break;
                case "deadline":
                    String[] restOfPara  = tempList[1].split("/by ", 2);
                    list.add(new Deadline(restOfPara[0], restOfPara[1]));
                    System.out.println(list.get(tasks).toString());
                    printTaskList(tasks + 1);
                    break;
                case "event":
                    String[] restOfPara2 = tempList[1].split("/at ", 2);
                    list.add(new Event(restOfPara2[0], restOfPara2[1]));
                    System.out.println(list.get(tasks).toString());
                    printTaskList(tasks + 1);
                    break;
                case "mark":
                    int taskNum = Integer.parseInt(tempList[1]) - 1;
                    list.get(taskNum).markAsDone();
                    System.out.println(list.get(taskNum).toString());
                    break;
                case "unmark":
                    taskNum = Integer.parseInt(tempList[1]) - 1;
                    list.get(taskNum).markAsNotDone();
                    System.out.println(list.get(taskNum).toString());
                    break;
                case "delete":
                    taskNum = Integer.parseInt(tempList[1]) - 1;
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(list.get(taskNum).toString());
                    list.remove(taskNum);
                    printTaskList(tasks - 1);
                    break;
            }
            System.out.println("------------------------------------------------------------");
        }
    }

    public static void printTaskList(int tasks) {
        if (tasks + 1 == 1) {
            System.out.println("Now you have " + (tasks) + " task in the list.");
        } else {
            System.out.println("Now you have " + (tasks) + " tasks in the list.");
        }
    }
}