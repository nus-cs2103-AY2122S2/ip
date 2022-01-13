import java.util.Scanner;

/**
 * Duke class represents a Personal Assistant Chatbot that
 * helps a person to keep track of various things.
 */
public class Duke {
    static String lineBreak = "____________________________________________________________\n";
    static String catFace = " =^_^=\n";
    static Task[] tasks = new Task[100];

    /**
     * Prints the greeting and logo.
     */
    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____      /\\_/\\           ___\n"
                + "| | | | | | | |/ / _      = o_o =_______    \\ \\  -Julie Rhodes-\\\n"
                + "| |_| | |_| |   <  __/     __^      __(  \\.__) )\n"
                + "|____/ \\__,_|_|\\_\\___| (@)<_____>__(_____)____/\n";
        System.out.println(logo + lineBreak + "Meow! I'm Duke" + catFace
                + "What can I do for you?\n" + lineBreak);
    }

    /**
     * Prints the farewell.
     */
    public static void exit() {
        System.out.println(lineBreak + "Bye. Meow!" + catFace + lineBreak);
    }

    /**
     * Processes the command from the user.
     * @param command Command to execute.
     * @param description Task description if any.
     */
    public static void processCommand(String command, String description) {
        int index;
        Task t;

        switch (command) {
        case "list":
            System.out.print(lineBreak);
            for (int i = 0; i < Task.numOfTasks; i++) {
                index = i + 1;
                System.out.println(index + ". " + tasks[i]);
            }
            System.out.print(lineBreak);
            break;
        case "mark":
            index = Integer.parseInt(description.trim());
            tasks[index - 1].markAsDone();
            System.out.println(lineBreak + "Meow! Task is done!" + catFace
                    + tasks[index - 1] + "\n" + lineBreak);
            break;
        case "unmark":
            index = Integer.parseInt(description.trim());
            tasks[index - 1].unmarkAsDone();
            System.out.println(lineBreak + "Meow! Task is not done!" + catFace
                    + tasks[index - 1] + "\n" + lineBreak);
            break;
        case "todo":
            t = new Todo(description);
            tasks[Task.numOfTasks] = t;
            Task.numOfTasks++;
            System.out.println(lineBreak + "added: " + t + "\n" + "Number of tasks in list: " + Task.numOfTasks + catFace + lineBreak);
            break;
        case "deadline":
            String deadline = description.split("/by", 2)[1].trim();
            description = description.split("/by", 2)[0].trim();
            t = new Deadline(description, deadline);
            tasks[Task.numOfTasks] = t;
            Task.numOfTasks++;
            System.out.println(lineBreak + "added: " + t + "\n" + "Number of tasks in list: " + Task.numOfTasks + catFace + lineBreak);
            break;
        case "event":
            String period = description.split("/at", 2)[1].trim();
            description = description.split("/at", 2)[0].trim();
            t = new Event(description, period);
            tasks[Task.numOfTasks] = t;
            Task.numOfTasks++;
            System.out.println(lineBreak + "added: " + t + "\n" + "Number of tasks in list: " + Task.numOfTasks + catFace + lineBreak);
            break;
        default:
            t = new Task(description);
            tasks[Task.numOfTasks] = t;
            Task.numOfTasks++;
            System.out.println(lineBreak + "added: " + description + "\n" + lineBreak);
            break;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        greet();

        String input = sc.nextLine().strip() + " ";
        String command = input.split(" ")[0];
        String description = input.split(" ", 2)[1];

        while (!command.equals("bye")) {
            processCommand(command, description);

            input = sc.nextLine().strip() + " ";
            command = input.split(" ")[0];
            description = input.split(" ", 2)[1];
        }

        exit();
        sc.close();
    }
}