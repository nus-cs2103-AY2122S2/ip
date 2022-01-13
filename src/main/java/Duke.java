import java.util.Scanner;

/**
 * Task class represents a Personal Assistant Chatbot that
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
     * Processes the input from the user.
     * @param input Full input from user.
     * @param command Command to execute.
     */
    public static void processCommand(String input, String command) {
        int index;

        switch (command) {
        case "list":
            System.out.print(lineBreak);
            for (int i = 0; i < Task.numOfTasks; i++) {
                index = i + 1;
                System.out.println(index + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].description);
            }
            System.out.print(lineBreak);
            break;
        case "mark":
            index = Integer.parseInt(input.split(" ")[1]);
            tasks[index - 1].markAsDone();
            System.out.println(lineBreak + "Meow! Task is done!" + catFace
                    + tasks[index - 1] + "\n" + lineBreak);
            break;
        case "unmark":
            index = Integer.parseInt(input.split(" ")[1]);
            tasks[index - 1].unmarkAsDone();
            System.out.println(lineBreak + "Meow! Task is not done!" + catFace
                    + tasks[index - 1] + "\n" + lineBreak);
            break;
        default:
            Task t = new Task(input);
            tasks[Task.numOfTasks] = t;
            Task.numOfTasks++;
            System.out.println(lineBreak + "added: " + input + catFace + lineBreak);
            break;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        greet();

        String input = sc.nextLine().strip();
        String command = (input + " ").split(" ")[0];

        while (!command.equals("bye")) {
            processCommand(input, command);

            input = sc.nextLine().strip();
            command = (input + " ").split(" ")[0];
        }

        exit();
        sc.close();
    }
}