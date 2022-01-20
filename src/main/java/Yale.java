import yalebot.Deadline;
import yalebot.TaskList;
import yalebot.Event;
import yalebot.ToDo;

import java.util.Scanner;

public class Yale {
    public static void main(String[] args) {
        String logo = "\n" +
                " ____  ____     _       _____     ________  \n" +
                "|_  _||_  _|   / \\     |_   _|   |_   __  | \n" +
                "  \\ \\  / /    / _ \\      | |       | |_ \\_| \n" +
                "   \\ \\/ /    / ___ \\     | |   _   |  _| _  \n" +
                "   _|  |_  _/ /   \\ \\_  _| |__/ | _| |__/ | \n" +
                "  |______||____| |____||________||________| \n" +
                "                                            \n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hi, I'm Yale!\n" );
        Scanner scanner = new Scanner(System.in);
        TaskList list = new TaskList();
        while (true) {
            String command = receiveInput(scanner);
            performAction(command, list);
            if (checkExit(command)) {
                break;
            }
        }
    }

    public static String receiveInput(Scanner scanner) {
        System.out.println("Enter command below:");
        String input = scanner.nextLine();
        return input;
    }

    public static void performAction(String command, TaskList list) {
        // Exit Feature
        if (command.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        }
        // List Feature
        else if (command.equals("list")) {
            System.out.println("Here are the tasks in your list\n"
                    + list.listOut());
        }
        // Mark & Unmark Feature
        else if (command.contains("mark") || command.contains("unmark")) {
                String[] commandArray = command.split(" ");
                String markStatus = commandArray[0];
                int itemNo = Integer.parseInt(commandArray[1]);
                // Edge cases
                if (itemNo > list.getSize() || itemNo < 1) {
                    System.out.println("Error: That task number does not exist!");
                }
                // Mark
                else if (markStatus.equals("mark")) {
                    list.getItem(itemNo-1).markItem();
                }
                // Unmark
                else {
                    list.getItem(itemNo-1).unmarkItem();
                }
            }
        // Deadline feature
        else if (command.contains("deadline")) {
            String filterDeadline = command.split("deadline ", 2)[1]; // Remove deadline word
            String task = command.split(" /by ", 2)[0]; // Retrieve task name
            String date = command.split(" /by ", 2)[1]; // Retrieve date
            Deadline newDeadline = new Deadline(task, false, date);
            list.addTo(newDeadline);
            System.out.println("Got it! I've added this task: \n    " +
                    newDeadline.toString() + "\n" +
                    "Now you have " + list.getSize() + " tasks in the list.");
        }
        // Event feature
        else if (command.contains("event")){
            String filterEvent = command.split("event ", 2)[1]; // Remove Event word
            String task = command.split(" /at ", 2)[0]; // Retrieve task name
            String date = command.split(" /at ", 2)[1]; // Retrieve date
            Event newEvent = new Event(task, false, date);
            list.addTo(newEvent);
            System.out.println("Got it! I've added this task: \n    " +
                    newEvent.toString() + "\n" +
                    "Now you have " + list.getSize() + " tasks in the list.");
        }
        else if (command.contains("todo")){
            String task = command.split("todo ", 2)[1]; // Remove word
            ToDo newToDo = new ToDo(task, false);
            list.addTo(newToDo);
            System.out.println("Got it! I've added this task: \n    " +
                    newToDo.toString() + "\n" +
                    "Now you have " + list.getSize() + " tasks in the list.");
        }
        else {
            System.out.println(command + " is not a valid command. Please try again.");
            }

    }

    public static boolean checkExit(String input) {
        return input.equals("bye");
    }
}
