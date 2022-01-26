import yale.task.Deadline;
import yale.task.TaskList;
import yale.task.Event;
import yale.task.ToDo;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.NoSuchFileException;
import java.util.Scanner;

public class Yale {
    public static void main(String[] args) {
        String logo = "-----YALE-----";
        System.out.println("Allow me to introduce myself\n" + logo);
        System.out.println("The name's Yale.\n" );
        Scanner scanner = new Scanner(System.in);
        TaskList list = new TaskList();
        String fileData = FileRead.loadFileContents("data/yale.txt");
        list.importIn(fileData);
        while (true) {
            String command = receiveInput(scanner);
            performAction(command, list);
            writeActionTo("data/yale.txt", list);
            if (checkExit(command)) {
                break;
            }
        }
    }

    /**
     * Method to receive input from the scanner and
     * returns that input in a String format
     * @param scanner
     * @return Input
     */
    public static String receiveInput(Scanner scanner) {
        System.out.println("\nEnter command below:");
        String input = scanner.nextLine();
        return input;
    }

    /**
     * Considers input cases and
     * performs action in response to the information
     * input by the user
     * @param command
     * @param list
     */
    public static void performAction(String command, TaskList list) {
        // Exit Feature
        if (command.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        }
        // List Feature
        else if (command.equals("list")) {
            if (list.getSize() == 0) {
                System.out.println("You have no tasks at the moment!");
            } else {
                System.out.println("Here are the tasks in your list\n"
                        + list.listOut());
            }
        }
        // Delete Feature
        else if (command.contains("delete")) {
            try{
                String[] commandArray = command.split(" ");
                int itemNo = Integer.parseInt(commandArray[1]);
                // Edge cases
                if (itemNo > list.getSize() || itemNo < 1) {
                    System.out.println("Error: That task does not exist!");
                } else {
                    System.out.println("Noted. I've removed this task:\n   " +
                            list.getItem(itemNo-1).toString());
                    list.deleteItem(itemNo-1);
                    System.out.println("Now you have " + list.getSize() + " tasks in the list.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid task number");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Error: You forgot to indicate the task number!");
            }
        }
        // Mark & Unmark Feature
        else if (command.contains("mark") || command.contains("unmark")) {
            try {
                String[] commandArray = command.split(" ");
                String markStatus = commandArray[0];
                int itemNo = Integer.parseInt(commandArray[1]);
                // Edge cases
                if (itemNo > list.getSize() || itemNo < 1) {
                    System.out.println("Error: That task does not exist!");
                }
                // Mark
                else if (markStatus.equals("mark")) {
                    list.getItem(itemNo-1).markItem();
                }
                // Unmark
                else {
                    list.getItem(itemNo-1).unmarkItem();
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid task number");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Error: You forgot to indicate the task number!");
            }
            }
        // Deadline feature
        else if (command.contains("deadline")) {
            try {
                String removeDeadline = command.split("deadline ", 2)[1]; // Remove Deadline word
                String task = removeDeadline.split(" /by ", 2)[0]; // Retrieve task name
                String date = removeDeadline.split(" /by ", 2)[1]; // Retrieve date
                Deadline newDeadline = new Deadline(task, false, date);
                list.addTo(newDeadline);
                System.out.println("Got it! I've added this task:\n    " +
                        newDeadline.toString() + "\n" +
                        "Now you have " + list.getSize() + " tasks in the list.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Error: The description of an event cannot be empty.");
            }
        }
        // Event feature
        else if (command.contains("event")){
            try {
                String removeEvent = command.split("event ", 2)[1]; // Remove Event word
                String task = removeEvent.split(" /at ", 2)[0]; // Retrieve task name
                String date = removeEvent.split(" /at ", 2)[1]; // Retrieve date
                Event newEvent = new Event(task, false, date);
                list.addTo(newEvent);
                System.out.println("Got it! I've added this task:\n    " +
                        newEvent.toString() + "\n" +
                        "Now you have " + list.getSize() + " tasks in the list.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Error: The description of an event cannot be empty.");
            }
        }
        else if (command.contains("todo")){
            try{
                String task = command.split("todo ", 2)[1]; // Remove word
                ToDo newToDo = new ToDo(task, false);
                list.addTo(newToDo);
                System.out.println("Got it! I've added this task:\n    " +
                        newToDo.toString() + "\n" +
                        "Now you have " + list.getSize() + " tasks in the list.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Error: The description of a todo cannot be empty.");
            }
        }
        else {
            System.out.println("Error: " + command
                    + " is not a valid command. Please try again.");
            }
    }

    /**
     * Method to check if user input
     * is equal to "bye"
     * @param input
     * @return
     */
    public static boolean checkExit(String input) {
        return input.equals("bye");
    }

    /**
     * Writes String from list into specified file
     * @param filePath
     * @param list
     */
    public static void writeActionTo(String filePath, TaskList list) {
        String file2 = filePath;
        try {
            FileWrite.writeToFile(file2, list.exportOut());
        }
        catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}

