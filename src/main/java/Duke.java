import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    /** Stores the messages sent by the user. */
    private static List<Task> messages = new ArrayList<>();

    public static void main(String[] args) {
        // Initialize a scanner object
        Scanner userInput = new Scanner(System.in);
        System.out.println("Hello master I am Chi\nHow may I serve you today nyan~?");

        // Stores the text input of user
        String echo;
        // Request for user input
        echo = userInput.nextLine();
        // Check if bye has been said
        while (!echo.toLowerCase().equals("bye")) {
            // To handle special messages
            String[] command = echo.split(" ");
            if (command[0].equals("list")) {
                System.out.println("Here are your list items nyan~:");
                int i = 1;
                for (Task t: messages) {
                    // Print task and its status
                    System.out.printf("%d. %s\n",i , t);
                    ++i;
                }
                echo = userInput.nextLine();
                continue;
            } else if (command[0].equals("mark")) {
                // Retrieve the task from the list
                Task doneTask = messages.get(Integer.parseInt(command[1]) - 1);
                // Mark as done
                doneTask.markAsDone();
                // Print completion message
                System.out.printf("Great job nyan~!\n%s\n",doneTask);
                echo = userInput.nextLine();
                continue;
            } else if (command[0].equals("unmark")) {
                Task doneTask = messages.get(Integer.parseInt(command[1]) - 1);
                doneTask.markAsUndone();
                // Print undo message
                System.out.printf("Let's get it done next time nyan~!\n%s\n",doneTask);
                echo = userInput.nextLine();
                continue;
            } else if (command[0].equals("todo")) {
                // Obtain the ToDo
                Task newTask = new Todo(echo.substring(4).trim());
                // Add task to list
                messages.add(newTask);
                System.out.printf("Ok! Chi-san has added:\n%s\nYou have %d tasks nyan~!\n", newTask,messages.size());
                echo = userInput.nextLine();
            } else if (command[0].equals("deadline")) {
                // Separate task and deadline
                String[] content = echo.substring(8).split("/by");
                // Create new Deadline object
                Task newTask = new Deadline(content[0].trim(), content[1].trim());
                messages.add(newTask);
                System.out.printf("Ok! Chi-san has added:\n%s\nYou have %d tasks nyan~!\n", newTask,messages.size());
                echo = userInput.nextLine();
            } else if (command[0].equals("event")) {
                // Separate task and timing
                String[] content = echo.substring(5).split("/at");
                // Create new Event object
                Task newTask = new Event(content[0].trim(), content[1].trim());
                messages.add(newTask);
                System.out.printf("Ok! Chi-san has added:\n%s\nYou have %d tasks nyan~!\n", newTask,messages.size());
                echo = userInput.nextLine();
            } else {
                // Echo message sent by user
                System.out.println(echo);
                // Store message
                messages.add(new Task(echo));
                echo = userInput.nextLine();
            }
        }
        // Ending statement and close scanner
        System.out.println("Sayonara, see you next time nyan~");
        userInput.close();
    }
}
