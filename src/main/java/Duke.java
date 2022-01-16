import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
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

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.print(line + indent + "Here are the tasks in your list: \n");
                int index = 1;
                for (int n = 0; n < totalTasks; n++) {
                    Task cur = taskArr[n];
                    System.out.println(indent + index + ". " + cur.getDescription());
                    index++;
                }
                System.out.print(line);
            } else {
                taskArr[totalTasks] = new Task(input);
                totalTasks++;
                System.out.print(line + indent + "added: " + input + "\n" + line);
            }
            input = scanner.nextLine();
        }

        System.out.print(line + indent + "Bye. Hope to see you again soon!" + "\n" + line);
    }
}
