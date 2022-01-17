import java.util.Scanner;

public class Duke {
    private static final String INPUT_NAME = "You: ";
    private static final String OUTPUT_NAME = "Duke: ";

    public static void main(String[] args) {
        start();
        Scanner sc = new Scanner(System.in);
        String input;
        TaskManager notebook = new TaskManager();

        do {
            System.out.print(INPUT_NAME);
            input = sc.nextLine();
            String output = OUTPUT_NAME;
            Command c = Command.getCommand(input.split(" ")[0]);

            // main
            switch (c) {
                case BYE:
                    output += "don't leave me don't leave me.";
                    break;
                case LIST:
                    output += "\n" + notebook;
                    break;
                case MARK:
                    output += "I have marked this as done. \n";
                    output += notebook.markTask(Integer.parseInt(input.split(" ")[1]));
                    break;
                case UNMARK:
                    output += "I have unmarked this task. \n";
                    output += notebook.unmarkTask(Integer.parseInt(input.split(" ")[1]));
                    break;
                default:
                    output += notebook.addTask(input);
            }
            System.out.println(output);
        } while (!input.equals("bye"));
        sc.close();
    }

    public static void start() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String msg = "Hello! I am Duke. \n"
                + "Your wish is my command.\n\n";
        System.out.print(logo + msg);
    }
}
