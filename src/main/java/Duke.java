import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskArray = new ArrayList<Task>();
        while (sc.hasNextLine()) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            if (userInput.equals("list")) {
                System.out.println("Tasks: ");
                for (int i = 1; i <= taskArray.size(); i++) {
                    System.out.println(i + ". " + taskArray.get(i - 1));
                }
            } else {
                Task currentTask = new Task(userInput);
                taskArray.add(currentTask);
                String outputString = "added: " + currentTask;
                System.out.println(outputString);
            }
        }
    }
}
