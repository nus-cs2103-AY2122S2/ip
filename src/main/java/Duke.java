import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.util.*;

public class Duke {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println(chatBox("Yawn... You woke me up! Urgh\n    What do you need?"));

        List<Task> tasks = new ArrayList<Task>();
        String tab = "    ";

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
                    tasks.add(new Todo(item));
                    System.out.println(chatBox(addTask(tasks.get(tasks.size() - 1), tasks.size())));
                } else {
                    System.out.println(chatBox("Can read instructions or not? Todo cannot be empty :/"));
                }

            } else if (task.equals("deadline")) {
                if (!item.equals("")) {
                    String thing = item.split(" /by ")[0];
                    String time = item.split(" /by ")[1];
                    tasks.add(new Deadline(thing, time));
                    System.out.println(chatBox(addTask(tasks.get(tasks.size() - 1), tasks.size())));
                } else {
                    System.out.println(chatBox("Can read instructions or not? Deadline cannot be empty :/"));
                }

            } else if (task.equals("event")) {
                if (!item.equals("")) {
                    String thing = item.split(" /at ")[0];
                    String time = item.split(" /at ")[1];
                    tasks.add(new Event(thing, time));
                    System.out.println(chatBox(addTask(tasks.get(tasks.size() - 1), tasks.size())));
                } else {
                    System.out.println(chatBox("Can read instructions or not? Event cannot be empty :/"));
                }

            } else if (task.equals("list")){
                String lists = "";
                for (int i = 0; i < tasks.size(); i++) {
                    if (i != 0) {
                        lists += "\n" + tab;
                    }
                    lists += String.format("%d. %s", i + 1, tasks.get(i).toString());

                }

                System.out.println(chatBox(lists));

            } else if (task.equals("mark")) {
                int index = Integer.parseInt(item);
                tasks.get(index - 1).markAsDone();
                System.out.println(chatBox("Good job for accomplishing something today! I've marked this task as done:\n      "
                        + tasks.get(index - 1).toString()));
            } else if (task.equals("unmark")) {
                int index = Integer.parseInt(item);
                tasks.get(index - 1).markAsUndone();
                System.out.println(chatBox("Stop procrastinating you lazy prick! I've marked this task as not done yet:\n      "
                        + tasks.get(index - 1).toString()));
            } else if (task.equals("delete")) {
                int index = Integer.parseInt(item);
                Task t = tasks.get(index - 1);
                tasks.remove(index - 1);
                System.out.println(chatBox(removeTask(t, tasks.size())));

            } else {
                System.out.println(chatBox("What is this? Can you read English?"));
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


        System.out.println(chatBox("Bye. I don't hope to see you again soon :D"));

        sc.close();
    }

    private static String chatBox(String command){
        String tab = "    ";
        String horizontalLines = tab + "___________________________________";

        return horizontalLines + "\n" + tab + command + "\n" + horizontalLines;

    }

    private static String addTask(Task task, int total) {
        String tab = "    ";
        String firstLine = "Ah sure. I've added this task:\n";
        String secondLine = tab + "  " + task.toString() + "\n";
        String thirdLine;
        if (total == 1) {
            thirdLine = tab + "Now you have " + total + " task in the list.";
        } else {
            thirdLine = tab + "Now you have " + total + " tasks in the list.";
        }

        return firstLine + secondLine + thirdLine;

    }

    private static String removeTask(Task task, int total) {
        String tab = "    ";
        String firstLine = "Less work for you then less work for me then. I've removed this task:\n";
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



