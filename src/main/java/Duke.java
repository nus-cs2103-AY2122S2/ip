import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static ArrayList<Task> taskList;
    public static void main(String[] args) {
        // Declare Task ArrayList to keep tasks and starting Task number
        taskList = new ArrayList<>();
        // Print welcome message
        System.out.println("Hello, this is Belvedere, your virtual assistant!\nHow may i assist you?");
        printLine();
        // Scanner object to detect input from CLI
        Scanner scanner = new Scanner(System.in);
        // Loop to keep getting input until bye
        while (true) {
            String input = scanner.nextLine();
            // for debugging
            //System.out.println(input);
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                printLine();
                break;
            }
            if (input.equals("list")) {
                printTaskList();
                continue;
            }
            if (input.contains("unmark")) {
                int selectedTaskNum = Character.getNumericValue(input.charAt(input.length()-1));
                Task selectedTask = taskList.get(selectedTaskNum-1);
                selectedTask.markAsUndone();
                System.out.println(String.format("Ok, I've marked this task as not done yet:\n%s",selectedTask.toString()));
                printLine();
                continue;
            }
            if (input.contains("mark")) {
                int selectedTaskNum = Character.getNumericValue(input.charAt(input.length()-1));
                Task selectedTask = taskList.get(selectedTaskNum-1);
                selectedTask.markAsDone();
                System.out.println(String.format("Ok, I've marked this task as done:\n%s",selectedTask.toString()));
                printLine();
                continue;
            } else {
                System.out.println("added: " + input);
                Task newTask = new Task(input);
                taskList.add(newTask);
                printLine();
            }
        }
    }
    // Static method to print a line in the terminal to separate call and response
    public static void printLine() {
        System.out.println("--------------------------------------------------");
    }
    public static void printTaskList() {
        for (int i = 1; i < taskList.size()+1; i++) {
            System.out.print(String.format("%d. %s \n", i, taskList.get(i-1).toString()));
        }
        printLine();
    }
}
