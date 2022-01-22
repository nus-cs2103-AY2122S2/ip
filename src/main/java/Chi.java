import java.util.ArrayList;
import java.util.List;

public class Chi {
    /** Stores the messages sent by the user. */
    private List<Task> messages;

    public Chi() {
        this.messages = new ArrayList<>();
    }
    /**
     * Displays a message based on the user input.
     * @param msg The message inputted by the user.
     * @throws ChiException if messages are invalid.
     */
    public void respondToMsg(String msg) throws ChiException {
        // Obtain 1st word
        String[] command = msg.trim().split(" ");
        if (command.length == 1) {
            if (command[0].equals("list")) {
                System.out.println("Here are your list items nyan~:");
                int i = 1;
                for (Task t: messages) {
                    // Print task and its status
                    System.out.printf("%d. %s\n",i , t);
                    ++i;
                }
                return;
            }
            // Unknown message, or command lacks task
            throw new ChiException(msg.trim().toLowerCase());
        } else {
            // Check for keywords
            switch(command[0].toLowerCase()) {
                case "mark":
                    // Retrieve the task from the list
                    Task doneTask = messages.get(Integer.parseInt(command[1]) - 1);
                    // Mark as done
                    doneTask.markAsDone();
                    // Print completion message
                    System.out.printf("Great job nyan~!\n%s\n",doneTask);
                    break;
                case "unmark":
                    Task doneTask1 = messages.get(Integer.parseInt(command[1]) - 1);
                    doneTask1.markAsUndone();
                    // Print undo message
                    System.out.printf("Let's get it done next time nyan~!\n%s\n",doneTask1);
                    break;
                case "todo":
                    // Obtain the ToDo
                    Task newTask = new Todo(msg.substring(4).trim());
                    // Add task to list
                    messages.add(newTask);
                    System.out.printf("Ok! Chi-san has added:\n%s\nYou have %d tasks nyan~!\n",
                            newTask,messages.size());
                    break;
                case "deadline":
                    // Separate task and deadline
                    String[] content = msg.substring(8).split("/by");
                    // Create new Deadline object
                    if (content[0].trim().equals("")) {
                        throw new ChiException("deadline");
                    }
                    // Create new Deadline object
                    Task newTask1 = new Deadline(content[0].trim(), content[1].trim());
                    messages.add(newTask1);
                    System.out.printf("Ok! Chi-san has added:\n%s\nYou have %d tasks nyan~!\n",
                            newTask1,messages.size());
                    break;
                case "event":
                    // Separate task and timing
                    String[] content1 = msg.substring(5).split("/at");
                    if (content1[0].trim().equals("")) {
                        throw new ChiException("event");
                    }
                    // Create new Event object
                    Task newTask2 = new Event(content1[0].trim(), content1[1].trim());
                    messages.add(newTask2);
                    System.out.printf("Ok! Chi-san has added:\n%s\nYou have %d tasks nyan~!\n",
                            newTask2,messages.size());
                    break;
                case "delete":
                    Task toDelete = messages.get(Integer.parseInt(command[1]) - 1);
                    messages.remove(toDelete);
                    System.out.printf("Chi-san has removed task:\n %s\nYou now have %d tasks nyan~!\n",
                            toDelete, messages.size());
                    break;
                default:
                    // Some message which does not start with a keyword
                    throw new ChiException(msg);
            }
        }

    }
}
