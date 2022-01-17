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
                default:
                    Task currentTask = new Task(userInput);
                    taskArray.add(currentTask);
                    String outputString = "added: " + userInput;
                    System.out.println(outputString);
                    break;
            }
        }
    }
}
