import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskArray = new ArrayList<Task>();
        while (sc.hasNextLine()) {
            String userInput = sc.nextLine();
            String[] inputArguments = userInput.split(" ");
            String operation = inputArguments[0];
            switch (operation) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 1; i <= taskArray.size(); i++) {
                        System.out.println(i + ". " + taskArray.get(i - 1));
                    }
                    break;
                case "mark":
                    if (inputArguments.length < 2) {
                        System.out.println("Task number is not indicated.");
                        break;
                    }
                    try {
                        int markIndex = Integer.parseInt(inputArguments[1]) - 1;
                        Task taskToMark = taskArray.get(markIndex);
                        taskToMark.mark();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("    " + taskArray.get(markIndex));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Task with this index is not found.");
                    } catch (NumberFormatException e) {
                        System.out.println("Task number is not a valid number");
                    }
                    break;
                case "unmark":
                    if (inputArguments.length < 2) {
                        System.out.println("Task number is not indicated.");
                        break;
                    }
                    try {
                        int unmarkIndex = Integer.parseInt(inputArguments[1]) - 1;
                        Task taskToUnmark = taskArray.get(unmarkIndex);
                        taskToUnmark.unmark();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println("    " + taskArray.get(unmarkIndex));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Task with this index is not found.");
                    } catch (NumberFormatException e) {
                        System.out.println("Task number is not a valid number");
                    }
                    break;
                case "deadline":
                    if (inputArguments.length < 2) {
                        System.out.println("Task is not indicated.");
                        break;
                    }
                    boolean foundDeadlineCommand = false;
                    for (int i = 0; i < inputArguments.length; i++) {
                        if (inputArguments[i].equals("/by")) {
                            foundDeadlineCommand = true;
                        }
                    }
                    if (!foundDeadlineCommand) {
                        System.out.println("Command to insert deadline task is not found.");
                        break;
                    }
                    String[] deadlineDetails = userInput.split("/by", 2);
                    String deadlineTime = deadlineDetails[1].trim();
                    String deadlineName = deadlineDetails[0].substring(operation.length() + 1).trim();
                    if (deadlineTime.isEmpty()) {
                        System.out.println("No deadline found.");
                        break;
                    }
                    if (deadlineName.isEmpty()) {
                        System.out.println("Task name is not found.");
                        break;
                    }
                    Deadline deadlineTask = new Deadline(deadlineName, deadlineTime);
                    taskArray.add(deadlineTask);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("    " + deadlineTask);
                    System.out.println(String.format("Now you have %d tasks in the list.", taskArray.size()));
                    break;
                case "event":
                    if (inputArguments.length < 2) {
                        System.out.println("Task is not indicated.");
                        break;
                    }
                    boolean foundEventCommand = false;
                    for (int i = 0; i < inputArguments.length; i++) {
                        if (inputArguments[i].equals("/at")) {
                            foundEventCommand = true;
                        }
                    }
                    if (!foundEventCommand) {
                        System.out.println("Command to insert event task is not found.");
                        break;
                    }
                    String[] eventDetails = userInput.split("/at", 2);
                    String eventTime = eventDetails[1].trim();
                    String eventName = eventDetails[0].substring(operation.length() + 1).trim();
                    if (eventTime.isEmpty()) {
                        System.out.println("No event time found.");
                        break;
                    }
                    if (eventName.isEmpty()) {
                        System.out.println("Task name is not found.");
                        break;
                    }
                    Event eventTask = new Event(eventName, eventTime);
                    taskArray.add(eventTask);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("    " + eventTask);
                    System.out.println(String.format("Now you have %d tasks in the list.", taskArray.size()));
                    break;
                case "todo":
                    if (inputArguments.length < 2) {
                        System.out.println("Task is not indicated.");
                        break;
                    }
                    String toDoName = userInput.substring(operation.length() + 1);
                    ToDo toDoTask = new ToDo(toDoName);
                    taskArray.add(toDoTask);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("    " + toDoTask);
                    System.out.println(String.format("Now you have %d tasks in the list.", taskArray.size()));
                    break;
                default:
                    System.out.println("No such command found.");
                    break;
            }
        }
    }
}
