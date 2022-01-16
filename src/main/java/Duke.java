import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Duke {
    public static void main(String[] args) throws IOException {
        String logo = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n";

        String intro = "    Hello! I'm Duke! \n"
                + "    What can I do for you? \n";

        String line = "    ―――――――――――――――――――――――――――――――――― \n";

        String indent = "    ";

        System.out.print(logo + "\n" + intro + line);

        Task[] taskArr = new Task[100];
        int totalTasks = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String[] inputArr = input.split(" ");
        String command = inputArr[0];

        while (!command.equals("bye")) {

            if (command.equals("list")) { // list out all added tasks
                System.out.print(line + indent + "Here are the tasks in your list: \n");
                int index = 1;
                for (int n = 0; n < totalTasks; n++) {
                    Task t = taskArr[n];
                    System.out.println(indent + index + "." + t.toString());
                    index++;
                }
                System.out.print(line);

            } else if (command.equals("mark") || command.equals("unmark")) { // change status of task
                int taskNum = Integer.parseInt(inputArr[1]);
                Task t = taskArr[taskNum - 1];

                if (command.equals("mark")) {
                    System.out.print(line + indent + "Nice! You've completed this task: \n");
                    t.markAsDone();
                } else {
                    System.out.print(line + indent + "Okay, I've marked this task as undone: \n");
                    t.markAsUndone();
                }

                System.out.print(indent + t.toString() + "\n" + line);

            } else { // add task to list
                if (command.equals("todo")) {
                    String str = input.substring(5);
                    taskArr[totalTasks] = new Todo(str);

                } else if (command.equals("deadline")) {
                    String str = input.substring(9);
                    String[] strArr = str.split(" /by ");
                    taskArr[totalTasks] = new Deadline(strArr[0], strArr[1]);

                } else if (command.equals("event")) {
                    String str = input.substring(6);
                    String[] strArr = str.split(" /at ");
                    taskArr[totalTasks] = new Event(strArr[0], strArr[1]);
                }

                totalTasks++;
                System.out.print(line + indent + "Got it! I've added this task: \n"
                        + indent + indent + taskArr[totalTasks - 1].toString() + "\n"
                        + indent + "Now you have " + totalTasks + (totalTasks > 1 ? " tasks" : " task")
                        + " in the list. \n" + line);
            }
            input = br.readLine();
            inputArr = input.split(" ");
            command = inputArr[0];
        }

        System.out.print(line + indent + "Bye! Hope to see you again soon!" + "\n" + line);
    }
}


