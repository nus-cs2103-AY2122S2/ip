import java.util.Scanner;  // Import the Scanner class

public class Duke {

    public static void main(String[] args) {
        String line = "\t----------------------------------------------------------\n";
        String welcomeMessage = "\tAhoy! Welcome aboard adventurer, Cap'n Dave at your service.\n";
        String welcomeQuestion = "\tWhat can I do for you?\n";
        String help = "\tA 'bye' command will exit the bot, yarr.\n";
        System.out.println(line + welcomeMessage + welcomeQuestion + help + line);

        Scanner sc= new Scanner(System.in);
        System.out.println("\tYour wish is my command. Your command: ");
        String command = ""; // Read user input
        String bye = "bye";
        String[] tasks = new String[100];
        int taskCount = 0;
        while (!command.equalsIgnoreCase(bye)) { // Checks if user wants to exit or not
            command = sc.nextLine();
            String requestNextCommand = "\tAye, Aye. Your next command: ";
            String reply = "\tAye, Aye. Your command: ";
            String taskCall = "\tAvast ye Matey. Here goes your task list:\n";
            switch (command.toLowerCase()) {
                case "bye":
                    System.out.println("\tFair winds adventurer, till we meet next time yarr. Bye for now.\n" + line);
                    break;
                case "list":
                    System.out.println(line + taskCall);
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println("\t" + String.valueOf(i + 1) + ". " + tasks[i]);
                    }
                    System.out.println(line + requestNextCommand);
                    break;
                default:
                    tasks[taskCount] = command;
                    taskCount++;
                    System.out.println(reply + "Add '" + command + "' into list\n");
                    System.out.println(line + requestNextCommand);
            }
        }
    }
}
