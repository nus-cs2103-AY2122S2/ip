import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;  // Import the Scanner class

import java.io.File;
import java.io.FileWriter;

public class Duke {

    private static ArrayList<Task> taskList;
    private static final String FILE_PATH = "duke.txt";
    private static int TASK_COUNT = 0;

    // Helper method, may not use this
    private static void append(String content) {
        try {
            FileWriter writer = new FileWriter(FILE_PATH, true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            System.out.println("Exception thrown:" + e);
        }
    }

    private static void saveToFile() {
        String result = "";
        for (Task task : taskList) {
            result = result.concat(task.toSave() + "\n");
        }

        try {
            FileWriter writer = new FileWriter(FILE_PATH, false);
            writer.write(result);
            writer.close();
        } catch (IOException e) {
            System.out.println("Exception thrown:" + e);
        }
    }


    private static void readFromFile() throws FileNotFoundException {
        // Give inputs into scanner from file
        File file = new File(FILE_PATH);
        Scanner scanner = new Scanner(file);
        taskList = new ArrayList<>();
        while (scanner.hasNext()) {
            String[] savedData = scanner.nextLine().split(" \\| ");
            String taskType = savedData[0];
            Task task;
            TASK_COUNT++;
            switch (taskType) {
            case "T":
                task = new Todo(savedData[2]);
                break;
            case "E":
                task = new Event(savedData[2], savedData[3]);
                break;
            case "D":
                task = new Deadline(savedData[2], savedData[3]);
                break;
            default:
                task = new Todo("");
            }
            if (savedData[1].equals("1")) {
                task.markAsDone();
            }
            System.out.println("Task added.\n");
            taskList.add(task);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        // File check
        try {
            File file = new File(FILE_PATH);
            file.createNewFile();
            taskList = new ArrayList<>();
        } catch (IOException e) { // Read from file if file already exists
            System.out.println("Exception thrown:" + e);
//            readFromFile();
        }
        readFromFile();

        String line = "\t----------------------------------------------------------\n";
        String welcomeMessage = "\tAhoy! Welcome aboard adventurer, Cap'n Dave at your service.\n";
        String welcomeQuestion = "\tWhat can I do for you?\n";
        String help = "\tA 'bye' command will exit the bot, yarr.\n";
        System.out.println(line + welcomeMessage + welcomeQuestion + help + line);

        Scanner sc = new Scanner(System.in);
        System.out.println("\tYour wish is my command. Your command:");
        String command = ""; // Read user input
        String bye = "bye";

        while (!command.equalsIgnoreCase(bye)) { // Checks if user wants to exit or not

            String requestNextCommand = "\tAye, Aye. Your next command:";
            String requestNextCommandAngry = "\tAye Aye, better get it right this time. Your next command:";
            String reply = "\tAye, Aye. Your command:";
            String taskAdded = "\tTask Added, arrgh:\n";
            String taskCall = "\tAvast ye Matey. Here goes your task list:\n";
            String taskCompleted = "\tTask completed, good job matey!\n";
            String taskDeleted = "\tAlright matey, task has been deleted good on ya.\n";
            String taskUnchecked = "\tAlright matey, hurry up and finish up this task arrr:\n";

            command = sc.nextLine();
            String[] splitCommand = command.split(" ", 2);
            switch (splitCommand[0].toLowerCase()) {
            case "bye":
                // Error handling
                System.out.println("\tFair winds adventurer, till we meet next time yarr. Bye for now.\n" + line);
                break;
            case "list":
                // Error handling
                System.out.println(line + taskCall);
                for (int i = 0; i < TASK_COUNT; i++) {
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
                saveToFile();
                System.out.println(taskCompleted);
                System.out.println("\t" + curr);
                System.out.println(line + requestNextCommand);
                break;
            case "unmark":
                // Error handling
                taskIndex = Integer.parseInt(splitCommand[1]) - 1;
                curr = taskList.get(taskIndex);
                curr.markAsUndone();
                saveToFile();
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
                saveToFile();
                TASK_COUNT++;
                System.out.println(taskAdded + "\t" + taskList.get(TASK_COUNT - 1).toString());
                System.out.println("\tNow you have " + String.valueOf(TASK_COUNT) + " tasks in your task list arrr, better get workin' aye!\n");
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
                saveToFile();
                TASK_COUNT++;
                System.out.println(taskAdded + "\t" + taskList.get(TASK_COUNT - 1).toString());
                System.out.println("\tNow you have " + String.valueOf(TASK_COUNT) + " tasks in your task list arrr, better get workin' aye!\n");
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
                saveToFile();
                TASK_COUNT++;
                System.out.println(taskAdded + "\t" + taskList.get(TASK_COUNT - 1).toString());
                System.out.println("\tNow you have " + String.valueOf(TASK_COUNT) + " tasks in your task list arrr, better get workin' aye!\n");
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
