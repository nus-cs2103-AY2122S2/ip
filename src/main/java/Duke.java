import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);

        List<Task> tasks = new ArrayList<Task>();

        while (true) {
            String[] instruction = sc.nextLine().split(" ");
            switch(instruction[0]){
                case "bye": {
                    System.out.println("Bye. Hope to see you again soon!\n");
                    return;
                }

                case "list": {
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(i + 1 + ". " + tasks.get(i));
                    }
                    break;
                }

                case "mark": {
                    int taskNum = Integer.parseInt(instruction[1]);
                    tasks.get(taskNum - 1).markAsDone();
                    System.out.println(String.format("Nice!, I have marked this task as done: \n %s", tasks.get(taskNum - 1)));
                    break;
                }

                case "unmark": {
                    int taskNum = Integer.parseInt(instruction[1]);
                    tasks.get(taskNum - 1).markAsNotDone();
                    System.out.println(String.format("Ok, I have marked this task as not done: \n %s", tasks.get(taskNum - 1)));
                    break;
                }

                default: {
                    String instructionName = String.join(" ", instruction);
                    tasks.add(new Task(instructionName));
                    System.out.println("added: " + instructionName);
                }
            }
            System.out.println("");
        }
    }
}
