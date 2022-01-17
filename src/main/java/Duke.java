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
        Task[] taskList = new Task[100];
        int taskCount = 0;
        while (!command.equalsIgnoreCase(bye)) { // Checks if user wants to exit or not
            command = sc.nextLine();
            String requestNextCommand = "\tAye, Aye. Your next command: ";
            String reply = "\tAye, Aye. Your command: ";
            String taskCall = "\tAvast ye Matey. Here goes your task list:\n";
            String taskCompleted = "\tTask completed, good job matey!\n";
            String taskUnchecked = "\tAlright matey, hurry up and finish up this task arrr:\n";
            String[] splitCommand = command.split(" ", 2);
            switch (splitCommand[0].toLowerCase()) {
                case "bye":
                    System.out.println("\tFair winds adventurer, till we meet next time yarr. Bye for now.\n" + line);
                    break;
                case "list":
                    System.out.println(line + taskCall);
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println("\t" + String.valueOf(i + 1) + ".[" + taskList[i].getStatusIcon() + "] " + taskList[i].getTaskDescription());
                    }
                    System.out.println(line + requestNextCommand);
                    break;
                case "mark":
                    int taskIndex = Integer.parseInt(splitCommand[1]) - 1;
                    Task curr = taskList[taskIndex];
                    curr.markAsDone();
                    System.out.println(taskCompleted);
                    System.out.println("\t[" + curr.getStatusIcon() + "] " + curr.getTaskDescription());
                    System.out.println(line + requestNextCommand);
                    break;
                case "unmark":
                    taskIndex = Integer.parseInt(splitCommand[1]) - 1;
                    curr = taskList[taskIndex];
                    curr.markAsUndone();
                    System.out.println(taskUnchecked);
                    System.out.println("\t[" + curr.getStatusIcon() + "] " + curr.getTaskDescription());
                    System.out.println(line + requestNextCommand);
                    break;
                default:
                    taskList[taskCount] = new Task(command);
                    taskCount++;
                    System.out.println(reply + "Add '" + command + "' into list\n");
                    System.out.println(line + requestNextCommand);
            }
        }
    }
}
