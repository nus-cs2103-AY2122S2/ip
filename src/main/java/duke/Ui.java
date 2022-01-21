package duke;

import java.util.Scanner;

/**
 * <code>Ui</code> class which encapsulates and drives
 * the display of the user interface when the code is run.
 */


public class Ui {

    Parser parser = new Parser();

    /**
     * Prints a fixed line separator.
     */
    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the Welcome message.
     */
    public void printWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
    }

    /**
     * Begins displaying the User Interface in Terminal.
     * @param tasklist
     */
    public void start(TaskList tasklist) {
        Scanner scan = new Scanner(System.in);
        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLine();
        while (true) {
            String input = scan.nextLine();
            CommandType commandType = parser.parse(input);
            boolean exit = false;
            switch (commandType) {
            case BYE:
                exit = true;
                break;
            case LIST:
                System.out.println("Here are the tasks in your list:");
                tasklist.listTasks();
                printLine();
                break;
            case MARK:
                try {
                    String[] splitted = input.split(" ");
                    int index = Integer.valueOf(splitted[1]);
                    Task set = tasklist.setDone(index - 1);
                    printLine();
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(set);
                    printLine();
                     break;
                } catch (IndexOutOfBoundsException e) {
                     System.out.println("The index you have provided is invalid");
                     printLine();
                     break;
                }
            case UNMARK:
                try {
                    String[] splitted = input.split(" ");
                    int index = Integer.valueOf(splitted[1]);
                    Task unset = tasklist.setUndone(index - 1);
                    printLine();
                    System.out.println("OK, I've marked this task as not done yet:  ");
                    System.out.println(unset);
                    printLine();
                    break;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("The index you have provided is invalid");
                    printLine();
                    break;
                }
            case TODO:
                try {
                    Task toAdd = new Todo(input.split("todo")[1].trim());
                    tasklist.addTask(toAdd);
                    printLine();
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(toAdd);
                    System.out.println("Now you have " + tasklist.getSize() + " tasks in the list.");
                    printLine();
                    break;
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    printLine();
                    break;
                }
            case DEADLINE:
                try {
                    String[] splitted = input.split("deadline")[1].split("/by");
                    Deadline toAdd = new Deadline(splitted[0].trim(), splitted[1].trim());
                    tasklist.addTask(toAdd);
                    printLine();
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(toAdd);
                    System.out.println("Now you have " + tasklist.getSize() + " tasks in the list.");
                    printLine();
                    break;
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Oops! The correct format is : `deadline /by <type time here>`");
                     break;
                }
            case EVENT:
                try {
                    String[] splitted = input.split("event")[1].split("/at");
                    Event toAdd = new Event(splitted[0].trim(), splitted[1].trim());
                    tasklist.addTask(toAdd);
                    printLine();
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(toAdd);
                    System.out.println("Now you have " + tasklist.getSize() + " tasks in the list.");
                    printLine();
                    break;
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Oops! The correct format is : `event /at <type time here>`");
                    break;
                }
            case DELETE:
                try {
                    String[] splitted = input.split(" ");
                    int index = Integer.valueOf(splitted[1]);
                    Task removedTask = tasklist.deleteTask(index - 1);
                    printLine();
                    System.out.println("Noted. I've removed this task: ");
                    System.out.println(removedTask);
                    System.out.println("Now you have " + tasklist.getSize() + " tasks in the list.");
                    printLine();
                    break;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("The index you have provided is invalid");
                    printLine();
                    break;
                }
            case FIND:
                String[] splitted = input.split(" ");
                printLine();
                System.out.println("Here are the matching tasks in your list:");
                tasklist.findAndPrintTasks(splitted[1]);
                printLine();
                break;

            default:
                System.out.println("OOPS!!! I'm sorry, but I do not know what that means :-(");
                printLine();
                break;
            }
            if (exit) break;
        }

        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();


    }
}
