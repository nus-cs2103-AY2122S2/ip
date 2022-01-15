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
                    System.out.printf("%d. %s\n",i , t);
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
            } else if (echo.split(" ")[0].toLowerCase().equals("todo")) {
                Task newTask = new Todo(echo.substring(4).trim());
                messages.add(newTask);
                System.out.printf("Ok! Chi-san has added this task:\n%s\nYou have %d tasks nyan~!", newTask,messages.size());
                echo = userInput.nextLine();
            } else if (echo.split(" ")[0].toLowerCase().equals("deadline")) {
                //Task newTask = new Deadline(echo.substring(8).split("/by")[0].trim(), )
            } else if (echo.split(" ")[0].toLowerCase().equals("event"))
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
