import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.util.*;

public class Duke {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println(chatBox("Hello! I'm Duke\n    What can I do for you?"));

        Task[] tasks = new Task[100];
        String tab = "    ";

        int items = 0;

        String[] inp = sc.nextLine().split(" ");
        String task = inp[0];
        String item = "";

        for (int i = 1; i < inp.length; i++) {
            if (i != inp.length - 1) {
                item += inp[i] + " ";
            } else {
                item += inp[i];
            }

        }

        while (!task.equals("bye")) {
            if (task.equals("todo")) {
                if (!item.equals("")) {
                    items += 1;
                    tasks[items] = new Todo(item);
                    System.out.println(chatBox(addTask(tasks[items], items)));
                } else {
                    System.out.println(chatBox("☹ OOPS!!! The description of a todo cannot be empty."));
                }

            } else if (task.equals("deadline")) {
                if (!item.equals("")) {
                    items += 1;
                    System.out.println(item);
                    String thing = item.split(" /by ")[0];
                    String time = item.split(" /by ")[1];
                    tasks[items] = new Deadline(thing, time);
                    System.out.println(chatBox(addTask(tasks[items], items)));
                } else {
                    System.out.println(chatBox("☹ OOPS!!! The description of a deadline cannot be empty."));
                }

            } else if (task.equals("event")) {
                if (!item.equals("")) {
                    System.out.println(item);
                    items += 1;
                    String thing = item.split(" /at ")[0];
                    String time = item.split(" /at ")[1];
                    tasks[items] = new Event(thing, time);
                    System.out.println(chatBox(addTask(tasks[items], items)));
                } else {
                    System.out.println(chatBox("☹ OOPS!!! The description of an event cannot be empty."));
                }

            } else if (task.equals("list")){
                String lists = "";
                for (int i = 1; i <= items; i++) {
                    if (i != 1) {
                        lists += "\n" + tab;
                    }
                    lists += String.format("%d. %s", i, tasks[i].toString());

                }

                System.out.println(chatBox(lists));

            } else if (task.equals("mark")) {
                int index = Integer.parseInt(item);
                tasks[index].markAsDone();
                System.out.println(chatBox("Nice! I've marked this task as done:\n      "
                        + tasks[index].toString()));
            } else if (task.equals("unmark")) {
                int index = Integer.parseInt(item);
                tasks[index].markAsUndone();
                System.out.println(chatBox("OK, I've marked this task as not done yet:\n      "
                        + tasks[index].toString()));
            } else {
                System.out.println(chatBox("☹ OOPS!!! I'm sorry, but I don't know what that means :-("));
            }

            inp = sc.nextLine().split(" ");
            task = inp[0];
            item = "";

            for (int i = 1; i < inp.length; i++) {
                if (i != inp.length - 1) {
                    item += inp[i] + " ";
                } else {
                    item += inp[i];
                }

            }
        }




        System.out.println(chatBox("Bye. Hope to see you again soon!"));

        sc.close();
    }

    private static String chatBox(String command){
        String tab = "    ";
        String horizontalLines = tab + "___________________________________";

        return horizontalLines + "\n" + tab + command + "\n" + horizontalLines;

    }

    private static String addTask(Task task, int total) {
        String tab = "    ";
        String firstLine = "Got it. I've added this task:\n";
        String secondLine = tab + "  " + task.toString() + "\n";
        String thirdLine;
        if (total == 1) {
            thirdLine = tab + "Now you have " + total + " task in the list.";
        } else {
            thirdLine = tab + "Now you have " + total + " tasks in the list.";
        }

        return firstLine + secondLine + thirdLine;

    }

}



