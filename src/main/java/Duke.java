import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.util.*;

public class Duke {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println(chatBox("Hello! I'm Duke\n    What can I do for you?"));

        Task[] tasks = new Task[100];
        String tab = "    ";

        int items = 0;

        String command = sc.nextLine();

        while (!command.equals("bye")) {
            if (!command.equals("list") &&
                    !command.split(" ")[0].equals("mark") &&
                    !command.split(" ")[0].equals("unmark")) {
                items += 1;
                tasks[items] = new Task(command);
                System.out.println(chatBox("added: " + command));
                System.out.println();

            } else {
                if (command.equals("list")){
                    String lists = "";
                    for (int i = 1; i <= items; i++) {
                        if (i != 1) {
                            lists += "\n" + tab;
                        }
                        lists += String.format("%d. %s", i, tasks[i].toString());

                    }

                    System.out.println(chatBox(lists));

                } else if (command.split(" ")[0].equals("mark")) {
                    int task = Integer.parseInt(command.split(" ")[1]);
                    tasks[task].markAsDone();
                    System.out.println(chatBox("Nice! I've marked this task as done:\n      "
                            + tasks[task].toString()));
                } else if (command.split(" ")[0].equals("unmark")) {
                    int task = Integer.parseInt(command.split(" ")[1]);
                    tasks[task].markAsUndone();
                    System.out.println(chatBox("OK, I've marked this task as not done yet:\n      "
                            + tasks[task].toString()));
                }

            }


            command = sc.nextLine();
        }

        System.out.println(chatBox("Bye. Hope to see you again soon!"));

        sc.close();
    }

    private static String chatBox(String command){
        String tab = "    ";
        String horizontalLines = tab + "___________________________________";

        return horizontalLines + "\n" + tab + command + "\n" + horizontalLines;

    }
}



