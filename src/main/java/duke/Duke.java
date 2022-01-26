/*

 To be removed
public class Duke {

    /** Stores the messages sent by the user.
    private static List<Task> messages = new ArrayList<>();

    /**
     * Displays a message based on the user input.
     * @param msg The message inputted by the user.
     * @throws DukeException if messages are invalid.

    public static void respondToMsg(String msg) throws DukeException {
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
            throw new DukeException(msg.trim().toLowerCase());
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
                        throw new DukeException("deadline");
                    }
                    // Create new Deadline object
                    LocalDate d = LocalDate.parse(content[1].trim().split(" ")[0].trim(),
                            DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    LocalTime t;
                    Task newTask1;
                    if (content[1].trim().split(" ").length == 2) {
                        t = LocalTime.parse(content[1].trim().split(" ")[1].trim(),
                                DateTimeFormatter.ofPattern("HH:mm"));
                        newTask1 = new Deadline(content[0].trim(), d, t);
                    } else {
                        newTask1 = new Deadline(content[0].trim(), d);
                    }

                    messages.add(newTask1);
                    System.out.printf("Ok! Chi-san has added:\n%s\nYou have %d tasks nyan~!\n",
                            newTask1,messages.size());
                    break;
                case "event":
                    // Separate task and timing
                    String[] content1 = msg.substring(5).split("/at");
                    if (content1[0].trim().equals("")) {
                        throw new DukeException("event");
                    }
                    LocalDate ds = LocalDate.parse(content1[1].trim().split(" ")[0].trim(),
                            DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    LocalTime t1;
                    LocalTime t2;
                    Task newTask2;
                    if (content1[1].trim().split(" ").length == 2) {
                        t1 = LocalTime.parse(content1[1].trim().split(" ")[1].trim().split("-")[0],
                                DateTimeFormatter.ofPattern("HH:mm"));
                        t2 = LocalTime.parse(content1[1].trim().split(" ")[1].trim().split("-")[1],
                                DateTimeFormatter.ofPattern("HH:mm"));
                       newTask2 = new Event(content1[0].trim(), ds, t1, t2);
                    } else {
                       newTask2 = new Event(content1[0].trim(), ds);
                    }
                    // Create new Event object
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
                    throw new DukeException(msg);
            }
        }

    }

    public static void main(String[] args) {
        // Initialize a scanner object
        Scanner userInput = new Scanner(System.in);
        System.out.println("Hello master I am Chi\nHow may I serve you today nyan~?");

        // Stores the text input of user
        String echo;
        // Request for user input
        echo = userInput.nextLine();
        // Check if bye has been said
        while (!echo.equals("bye")) {
            try {
                respondToMsg(echo);
                echo = userInput.nextLine();
            } catch (DukeException e) {
                // Output error message
                System.out.println(e);
                echo = userInput.nextLine();
            }
        }
        // Ending statement and close scanner
        System.out.println("Sayonara, see you next time nyan~");
        userInput.close();
    }
}
 */