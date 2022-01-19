import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "\nHello! I'm Duke\nWhat can i do for you?\n");

        Scanner input = new Scanner(System.in);
        String intputText = "";
        List<Task> list = new ArrayList<>();

        while (!intputText.equals("bye")) {
            intputText = input.nextLine();
            String[] tempList = intputText.split(" ", 2);
            int tasks = list.size();

            try {
                new DukeException().invalidChecker(tempList);
            } catch (DukeException e) {
                System.err.println(e);
                continue;
            }

            System.out.println("------------------------------------------------------------");
            if (intputText.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
            } else if (intputText.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks; i++) {
                    System.out.println((i + 1) + "." + list.get(i).toString());
                }
            } else {
                if (tempList[0].equals("todo")) {
                    list.add(new Todo(tempList[1]));
                    System.out.println(list.get(tasks).toString());
                    printTaskList(tasks + 1);
                } else if (tempList[0].equals("deadline")){
                    String[] restOfPara  = tempList[1].split("/by ", 2);
                    list.add(new Deadline(restOfPara[0], restOfPara[1]));
                    System.out.println(list.get(tasks).toString());
                    printTaskList(tasks + 1);
                } else if (tempList[0].equals("event")) {
                    String[] restOfPara = tempList[1].split("/at ", 2);
                    list.add(new Event(restOfPara[0], restOfPara[1]));
                    System.out.println(list.get(tasks).toString());
                    printTaskList(tasks + 1);
                } else {
                    int taskNumOrDes = Integer.parseInt(tempList[1]) - 1;
                    if (tempList[0].equals("mark")) {
                        System.out.println(list.get(taskNumOrDes).toString());
                        list.get(taskNumOrDes).markAsDone();
                    } else if (tempList[0].equals("unmark")) {
                        System.out.println(list.get(taskNumOrDes).toString());
                        list.get(taskNumOrDes).markAsNotDone();
                    } else if (tempList[0].equals("delete")) {
                        System.out.println("Noted. I've removed this task: ");
                        System.out.println(list.get(taskNumOrDes).toString());
                        list.remove(taskNumOrDes);
                        printTaskList(tasks - 1);
                    }
                }
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