import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    /** Stores the messages sent by the user. */
    private static List<Task> messages = new ArrayList<>();

    public static void main(String[] args) {
        // Initialize a scanner object
        Scanner userInput = new Scanner(System.in);
        System.out.println("Hello master I am Chi \nHow may I serve you today nyan~?");

        // Stores the text input of user
        String echo;
        // Request for user input
        echo = userInput.nextLine();
        // Check if bye has been said
        while (!echo.toLowerCase().equals("bye")) {
            if (echo.toLowerCase().equals("list")) {
                System.out.println("Here are your list items nyan~:");
                int i = 1;
                for (Task t: messages) {
                    // Print task and its status
                    System.out.printf("%d.[%s] %s\n",i ,t.getStatusIcon(), t);
                    ++i;
                }
                echo = userInput.nextLine();
                continue;
            } else if (echo.split(" ")[0].toLowerCase().equals("mark") && echo.split(" ").length == 2) {
                // Retrieve the task from the list
                Task doneTask = messages.get(Integer.parseInt(echo.split(" ")[1]) - 1);
                // Mark as done
                doneTask.markAsDone();
                // Print completion message
                System.out.printf("Great job nyan~!\n[%s] %s\n",doneTask.getStatusIcon(), doneTask);
                echo = userInput.nextLine();
                continue;
            } else if (echo.split(" ")[0].toLowerCase().equals("unmark") && echo.split(" ").length == 2) {
                Task doneTask = messages.get(Integer.parseInt(echo.split(" ")[1]) - 1);
                doneTask.markAsUndone();
                // Print undo message
                System.out.printf("Let's get it done next time nyan~!\n[%s] %s\n",doneTask.getStatusIcon(), doneTask);
                echo = userInput.nextLine();
                continue;
            }
            // Echo message sent by user
            System.out.println(echo);
            // Store message
            messages.add(new Task(echo));
            echo = userInput.nextLine();
        }
        // Ending statement and close scanner
        System.out.println("Sayonara, see you next time nyan~");
        userInput.close();
    }
}
