package bob;

import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * The Parser class consists of static methods to handle and execute user input commands.
 */
public class Parser {
    /**
     * Parses user input and executes the desired command.
     *
     * @param userInput String input from user.
     * @param tasks     Current task list.
     * @param storage   Storage instance associated with current Bob program.
     */
    public static void parse(String userInput, List<Task> tasks, Storage storage) {
        try {
            String cmd = userInput.split(" ")[0].strip();
            boolean tasksIsDiff = false;

            switch (cmd) {
            case "list":
                int count = 1;
                System.out.println("Here are the tasks in your list:");
                for (Task entry : tasks) {
                    System.out.println("    " + count++ + "." + entry);
                }
                break;
            case "mark":
                int idxMark = Integer.parseInt(userInput.split(" ")[1]);
                Task currTaskMark = tasks.get(idxMark - 1);
                currTaskMark.toggleDone();

                System.out.println("Nice! I've marked this task as done:\n" + currTaskMark);
                tasksIsDiff = true;
                break;
            case "unmark":
                int idxUnmark = Integer.parseInt(userInput.split(" ")[1]);
                Task currTaskUnmark = tasks.get(idxUnmark - 1);
                currTaskUnmark.toggleNotDone();

                System.out.println("OK, I've marked this task as not done yet:\n" + currTaskUnmark);
                tasksIsDiff = true;
                break;
            case "delete":
                int idxDel = Integer.parseInt(userInput.split(" ")[1]);
                Task currTaskDel = tasks.remove(idxDel - 1);
                tasksIsDiff = true;

                System.out.println("Noted. I've removed this task:\n" + currTaskDel);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                break;
            case "todo":
                Task newTodo = new ToDo(userInput.split(" ", 2)[1]);
                tasks.add(newTodo);
                tasksIsDiff = true;

                System.out.println("Got it. I've added this task:\n" + newTodo);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                break;
            case "deadline":
                String by = userInput.split("/by ")[1];
                String deadlineDesc = userInput.split("/by ")[0];
                deadlineDesc = deadlineDesc.substring(deadlineDesc.indexOf(" ")).strip();
                Task newDeadline = new Deadline(deadlineDesc, by);
                tasks.add(newDeadline);
                tasksIsDiff = true;

                System.out.println("Got it. I've added this task:\n" + newDeadline);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                break;
            case "event":
                String time = userInput.split("/at ")[1];
                String eventDesc = userInput.split("/at ")[0].strip();
                eventDesc = eventDesc.substring(eventDesc.indexOf(" ")).strip();
                Task newEvent = new Event(eventDesc, time);
                tasks.add(newEvent);
                tasksIsDiff = true;

                System.out.println("Got it. I've added this task:\n" + newEvent);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                break;
            default:
                System.out.println("I don't understand :( please repeat!");
            }

            if (tasksIsDiff) {
                storage.writeTaskListToFile(tasks);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Insufficient arguments for this command! :(");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("There is no such item! :0");
        } catch (NumberFormatException e) {
            System.out.println("Please use a valid integer! 12345!");
        } catch (DateTimeParseException e) {
            System.out.println("Please input the time in the correct format! yyyy-mm-ddThh:mm:ss");
        }
    }

    /**
     * Prints a line to format output.
     */
    public static void printLine() {
        System.out.println("----------------------------------------");
    }
}
