import java.util.ArrayList;
import java.util.Scanner;  // Import the Scanner class

public class Duke {

    public static void main(String[] args) {
        String line = "\t----------------------------------------------------------\n";
        String welcomeMessage = "\tAhoy! Welcome aboard adventurer, Cap'n Dave at your service.\n";
        String welcomeQuestion = "\tWhat can I do for you?\n";
        String help = "\tA 'bye' command will exit the bot, yarr.\n";
        System.out.println(line + welcomeMessage + welcomeQuestion + help + line);

        Scanner sc= new Scanner(System.in);
        System.out.println("\tYour wish is my command. Your command:");
        String command = ""; // Read user input
        String bye = "bye";
//        Task[] taskList = new Task[100];
        ArrayList<Task> taskList = new ArrayList<Task>();
        int taskCount = 0;
        while (!command.equalsIgnoreCase(bye)) { // Checks if user wants to exit or not
            command = sc.nextLine();
            String requestNextCommand = "\tAye, Aye. Your next command:";
            String requestNextCommandAngry = "\tAye Aye, better get it right this time. Your next command:";
            String reply = "\tAye, Aye. Your command:";
            String taskAdded = "\tTask Added, arrgh:\n";
            String taskCall = "\tAvast ye Matey. Here goes your task list:\n";
            String taskCompleted = "\tTask completed, good job matey!\n";
            String taskDeleted = "\tAlright matey, task has been deleted good on ya.\n";
            String taskUnchecked = "\tAlright matey, hurry up and finish up this task arrr:\n";
            String[] splitCommand = command.split(" ", 2);
            switch (splitCommand[0].toLowerCase()) {
                case "bye":
                    // Error handling
                    System.out.println("\tFair winds adventurer, till we meet next time yarr. Bye for now.\n" + line);
                    break;
                case "list":
                    // Error handling
                    System.out.println(line + taskCall);
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println("\t" + (i + 1) + "." + taskList.get(i).toString());
                    }
                    System.out.println(line + requestNextCommand);
                    break;
                case "delete":
                    // Error handling
                    int taskIndex = Integer.parseInt(splitCommand[1]) - 1;
                    Task curr = taskList.get(taskIndex);
                    taskList.remove(taskIndex);
                    System.out.println(line + taskDeleted);
                    System.out.println("\t" + curr);
                    System.out.println(line + requestNextCommand);
                    break;
                case "mark":
                    // Error handling
                    taskIndex = Integer.parseInt(splitCommand[1]) - 1;
                    curr = taskList.get(taskIndex);
                    curr.markAsDone();
                    System.out.println(taskCompleted);
                    System.out.println("\t" + curr);
                    System.out.println(line + requestNextCommand);
                    break;
                case "unmark":
                    // Error handling
                    taskIndex = Integer.parseInt(splitCommand[1]) - 1;
                    curr = taskList.get(taskIndex);
                    curr.markAsUndone();
                    System.out.println(taskUnchecked);
                    System.out.println("\t" + curr);
                    System.out.println(line + requestNextCommand);
                    break;
                case "deadline":
                    // Error handling
                    if (splitCommand.length == 1) {
                        System.out.println(line + "\tAaaarrrrgggghhhh deadline description can't be empty matey!\n");
                        System.out.println("\tEnter something like this: deadline return book /by Sunday\n" + line);
                        System.out.println(requestNextCommandAngry);
                        break;
                    }
                    String[] furtherSplitCommand = splitCommand[1].split(" /by ", 2);
                    taskList.add(new Deadline(furtherSplitCommand[0], furtherSplitCommand[1]));
                    taskCount++;
                    System.out.println(taskAdded + "\t" + taskList.get(taskCount - 1).toString());
                    System.out.println("\tNow you have " + String.valueOf(taskCount) + " tasks in your task list arrr, better get workin' aye!\n");
                    System.out.println(line + requestNextCommand);
                    break;
                case "event":
                    // Error handling
                    if (splitCommand.length == 1) {
                        System.out.println(line + "\tAaaarrrrgggghhhh event description can't be empty matey!\n");
                        System.out.println("\tEnter something like this: event project meeting /at Monday 2pm\n" + line);
                        System.out.println(requestNextCommandAngry);
                        break;
                    }
                    furtherSplitCommand = splitCommand[1].split(" /at ", 2);
                    taskList.add(new Event(furtherSplitCommand[0], furtherSplitCommand[1]));
                    taskCount++;
                    System.out.println(taskAdded + "\t" + taskList.get(taskCount - 1).toString());
                    System.out.println("\tNow you have " + String.valueOf(taskCount) + " tasks in your task list arrr, better get workin' aye!\n");
                    System.out.println(line + requestNextCommand);
                    break;
                case "todo":
                    // Error handling
                    if (splitCommand.length == 1) {
                        System.out.println(line + "\tAaaarrrrgggghhhh todo description can't be empty matey!\n");
                        System.out.println("\tEnter something like this: todo return book\n" + line);
                        System.out.println(requestNextCommandAngry);
                        break;
                    }
                    taskList.add(new Todo(splitCommand[1]));
                    taskCount++;
                    System.out.println(taskAdded + "\t" + taskList.get(taskCount - 1).toString());
                    System.out.println("\tNow you have " + String.valueOf(taskCount) + " tasks in your task list arrr, better get workin' aye!\n");
                    System.out.println(line + requestNextCommand);
                    break;
                default:
                    // Error handling
                    System.out.println(line + "\tAaaarrrrgggghhhh this ain't no command matey!!\n" + line);
                    System.out.println(requestNextCommandAngry);
                    break;
            }
        }
    }
}
