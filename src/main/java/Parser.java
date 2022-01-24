import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    public Parser() {
        // Maybe something in the future
    }

    public String processMessage(String msg, TaskList tl) throws ChiException {
        // Obtain 1st word
        String[] command = msg.trim().split(" ");
        if (command.length == 1) {
            if (command[0].equals("list")) {
                /*
                System.out.println("Here are your list items nyan~:");
                int i = 1;
                for (Task t : tl.getTasks()) {
                    // Print task and its status
                    System.out.printf("%d. %s\n", i, t);
                    ++i;
                }
                 */
                return tl.getTasksMsg();
            }
            // Unknown message, or command lacks task
            throw new ChiException(msg.trim().toLowerCase());
        } else {
            // Check for keywords
            switch (command[0].toLowerCase()) {
                case "mark":
                    // Retrieve the task from the list
                    Task doneTask = tl.getTask(Integer.parseInt(command[1]) - 1);
                    // Mark as done
                    doneTask.markAsDone();
                    // Print completion message
                    System.out.printf("Great job nyan~!\n%s\n", doneTask);
                    break;
                case "unmark":
                    Task doneTask1 = tl.getTask(Integer.parseInt(command[1]) - 1);
                    doneTask1.markAsUndone();
                    // Print undo message
                    System.out.printf("Let's get it done next time nyan~!\n%s\n", doneTask1);
                    break;
                case "todo":
                    // Obtain the ToDo
                    Task newTask = new Todo(msg.substring(4).trim(), false);
                    // Add task to list
                    tl.addTask(newTask);
                    System.out.printf("Ok! Chi-san has added:\n%s\nYou have %d tasks nyan~!\n",
                            newTask, tl.getSize());

                    break;
                case "deadline":
                    // Separate task and deadline
                    String[] content = msg.substring(8).split("/by");
                    // Create new Deadline object
                    if (content[0].trim().equals("")) {
                        throw new ChiException("deadline");
                    }
                    // Create new Deadline object
                    LocalDate d = LocalDate.parse(content[1].trim().split(" ")[0].trim(),
                            DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    LocalTime t;
                    Task newTask1;
                    if (content[1].trim().split(" ").length == 2) {
                        t = LocalTime.parse(content[1].trim().split(" ")[1].trim(),
                                DateTimeFormatter.ofPattern("HH:mm"));
                        newTask1 = new Deadline(content[0].trim(), d, t, false);
                    } else {
                        newTask1 = new Deadline(content[0].trim(), d, false);
                    }
                    tl.addTask(newTask1);
                    System.out.printf("Ok! Chi-san has added:\n%s\nYou have %d tasks nyan~!\n",
                            newTask1, tl.getSize());

                    break;
                case "event":
                    // Separate task and timing
                    String[] content1 = msg.substring(5).split("/at");
                    if (content1[0].trim().equals("")) {
                        throw new ChiException("event");
                    }
                    // Create new Event object
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
                        newTask2 = new Event(content1[0].trim(), ds, t1, t2, false);
                    } else {
                        newTask2 = new Event(content1[0].trim(), ds, false);
                    }
                    tl.addTask(newTask2);
                    System.out.printf("Ok! Chi-san has added:\n%s\nYou have %d tasks nyan~!\n",
                            newTask2, tl.getSize());
                    break;
                case "delete":
                    Task toDelete = tl.getTask(Integer.parseInt(command[1]) - 1);
                    tl.deleteTask(toDelete);
                    System.out.printf("Chi-san has removed task:\n %s\nYou now have %d tasks nyan~!\n",
                            toDelete, tl.getSize());
                    break;
                default:
                    // Some message which does not start with a keyword
                    throw new ChiException(msg);
            }
        }
        return "";
    }
}
