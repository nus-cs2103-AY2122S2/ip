import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static String line = "\n_______________________^_^__________________________________\n";

    public static void addList() {
        Scanner sc = new Scanner(System.in);
        String input;
        ArrayList<Task> taskList = new ArrayList<>();

        while(true) {
            input = sc.nextLine();
            String inputArr[] = input.split(" ",2);

            if (inputArr[0].equals("bye")) {
                System.out.println(line + "Bye. Hope to see you again soon!\n" + line);
                break;
            } else if(inputArr[0].equals("list")) {
                System.out.println(line + "Here are the tasks in your list:\n");
                for (int i = 1; i <= taskList.size(); i++) {
                    System.out.println(i + ".[" + taskList.get(i - 1).getStatusIcon() + "] "
                            + taskList.get(i - 1).description);
                }
                System.out.println(line);

            } else if(inputArr[0].equals("mark") && Integer.parseInt(inputArr[1]) > 0
                    && Integer.parseInt(inputArr[1]) <= taskList.size()) {
                int taskNum = Integer.parseInt(inputArr[1]) - 1;
                taskList.get(taskNum).markAsDone();
                System.out.println(line + "Nice! I've marked this task as done:\n");
                System.out.println("[" + taskList.get(taskNum).getStatusIcon() + "] "
                        + taskList.get(taskNum).description + line);

            } else if(inputArr[0].equals("unmark") && Integer.parseInt(inputArr[1]) > 0
                    && Integer.parseInt(inputArr[1]) <= taskList.size()) {
                    int taskNum = Integer.parseInt(inputArr[1]) - 1;
                    taskList.get(taskNum).markAsNotDone();
                    System.out.println(line + "OK, I've marked this task as not done yet:\n");
                    System.out.println("[" + taskList.get(taskNum).getStatusIcon() + "] "
                            + taskList.get(taskNum).description + line);

            } else {
                taskList.add(new Task(input));
                System.out.println(line + "added: " + input + line);
            }
        }
    }

    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(line + "Hello! I'm Duke\n" +
                "What can I do for you?\n" + line);

        Duke.addList();//level-3


    }

}
