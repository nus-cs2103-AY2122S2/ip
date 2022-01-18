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

            String commandLine[] = cleanCommand(input);
            Command c = Command.getCommand(commandLine[0]);

            // main
            switch (c) {
                case BYE:
                    output += "don't leave me don't leave me.";
                    break;
                case LIST:
                    output += "Here are the tasks in your list.\n" + notebook;
                    break;
                case MARK:
                    output += "I have marked this as done.\n";
                    output += notebook.markTask(Integer.parseInt(commandLine[1])) + "\n";
                    break;
                case UNMARK:
                    output += "I have unmarked this task.\n";
                    output += notebook.unmarkTask(Integer.parseInt(commandLine[1])) + "\n";
                    break;
                case TODO:
                    output += "Got it. I have added this task-\n";
                    output += notebook.addToDo(commandLine[1]) + "\n";
                    output += "Now you have " + notebook.size() + " tasks.\n";
                    break;
                case DEADLINE:
                    output += "Got it. I have added this task-\n";
                    output += notebook.addDeadline(commandLine[1], commandLine[2]) + "\n";
                    output += "Now you have " + notebook.size() + " tasks.\n";
                    break;
                case EVENT:
                    output += "Got it. I have added this task-\n";
                    output += notebook.addEvent(commandLine[1], commandLine[2]) + "\n";
                    output += "Now you have " + notebook.size() + " tasks.\n";
                    break;
            }
            System.out.println(output);
        } while (!input.equals("bye"));
        sc.close();
    }

    public static String[] cleanCommand(String input) {
        String[] commandLine = input.trim().split("\\s+", 2);
        if (commandLine.length == 2) {
            String commandType = commandLine[0];
            String commandInfo = commandLine[1];

            if (commandType.equals("deadline") || commandType.equals("event")) {
                return new String[]{commandType, commandInfo.split("/")[0],
                        commandInfo.split("/")[1]};
            }
        }
        return commandLine;
    }


    public static void start() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String msg = "Hello! I am Duke.\n"
                + "Your wish is my command.\n\n";
        System.out.print(logo + msg);
    }
}
