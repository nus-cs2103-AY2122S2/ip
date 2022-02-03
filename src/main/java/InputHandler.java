import java.util.Arrays;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Handles input in main in Duke.java. Receives String from Scanner in main
 * Processes the input into 7 categories: Todo, Event, Deadline, list, mark, unmark, bye and throws error
 */
public class InputHandler {
    private Storage storage;

    /**
     * Constructs an InputHandler for Duke.java to handle inputs from user
     * @throws IOException If Storage class fails to initialise
     */
    public InputHandler() throws IOException {
        this.storage = new Storage();
    }


    /**
     *
     * @param input String input from main in Duke.java, from user input
     * @return boolean representing isChatEnded variable in main. If "bye" command is given, a true boolean is returned, else false is returned
     * @throws DukeException Invalid input types, or unrecognisable commands
     */
    public boolean handleInput(String input) throws DukeException, IOException {
        String[] splitInput = input.split(" ");
        String inputCommand = splitInput[0];
        switch (inputCommand) {
        case "todo":
            if (splitInput.length > 1) {
                String[] nameArray = Arrays.copyOfRange(splitInput, 1, splitInput.length);
                String name = String.join(" ", nameArray);
                Todo newTodo = new Todo(name);
                this.storage.writeData(newTodo);
                printAddTaskMessage(newTodo);
                return false;
            } else {
                throw new DukeException(":( OOPS!!! The description of a todo cannot be empty. Correct usage: todo [task]");
            }
        case "event":
            if (splitInput.length > 3) {
                String[] stringArrayExcludingEvent = Arrays.copyOfRange(splitInput, 1, splitInput.length);
                String stringExcludingEvent = String.join(" ", stringArrayExcludingEvent);
                String[] nameAndTimeArray = stringExcludingEvent.split("/at ");
                String name = nameAndTimeArray[0];
                String time = nameAndTimeArray[1];
                String[] timeArray = time.split(" ");
                try {
                    Event newEvent = (timeArray.length > 1) ? new Event(name, timeArray[0], timeArray[1]) : new Event(name, timeArray[0]);
                    this.storage.writeData(newEvent);
                    printAddTaskMessage(newEvent);
                    return false;
                } catch (DateTimeParseException e) {
                    System.out.println(":( OOPS!!! The correct format for date and time is yyyy-mm-dd and hh:mm");
                }
            } else {
                throw new DukeException(":( OOPS!!! The description of a event cannot be empty. Correct usage: event [task] /at [time]");
            }
        case "deadline":
            if (splitInput.length > 3) {
                String[] stringArrayExcludingDeadline = Arrays.copyOfRange(splitInput, 1, splitInput.length);
                String stringExcludingDeadline = String.join(" ", stringArrayExcludingDeadline);
                String[] nameAndTimeArray = stringExcludingDeadline.split("/by ");
                String name = nameAndTimeArray[0];
                String time = nameAndTimeArray[1];
                String[] timeArray = time.split(" ");
                try {
                    Deadline newDeadline = (timeArray.length > 1) ? new Deadline(name, timeArray[0], timeArray[1]) : new Deadline(name, timeArray[0]);
                    this.storage.writeData(newDeadline);
                    printAddTaskMessage(newDeadline);
                    return false;
                } catch (DateTimeParseException e) {
                    System.out.println(":( OOPS!!! The correct format for date and time is yyyy-mm-dd and hh:mm");
                }
            } else {
                throw new DukeException(":( OOPS!!! The description of a deadline cannot be empty. Correct usage: deadline [task] /by [time]");
            }
        case "list":
            if (splitInput.length == 1) {
                System.out.println("Here are the tasks in your list:");
                int i = 0;
                System.out.println(this.storage.list());
                return false;
            } else {
                throw new DukeException("Wrong usage of list! Correct usage: list");
            }
        case "mark":
            if (splitInput.length == 2) {
                System.out.println("Nice! I've marked this task as done:\n");
                int idx = Integer.parseInt(splitInput[1]) - 1;
                Task taskToBeMarked = this.storage.get(idx);
                taskToBeMarked.setMarkedTask();
                return false;
            } else {
                throw new DukeException("Wrong usage of mark! Correct usage: mark [index]");
            }
        case "unmark":
            if (splitInput.length == 2) {
                System.out.println("OK, I've marked this task as not done yet:\n");
                int idx = Integer.parseInt(splitInput[1]) - 1;
                Task taskToBeUnmarked = this.storage.get(idx);
                taskToBeUnmarked.setUnmarkedTask();
                return false;
            } else {
                throw new DukeException("Wrong usage of unmark! Correct usage: unmark [index]");
            }
        case "delete":
            if (splitInput.length == 2) {
                int idx = Integer.parseInt(splitInput[1]) - 1;
                Task taskToBeDeleted = storage.get(idx);
                this.storage.deleteData(idx);
                System.out.println("Noted. I've removed this task:\n" + taskToBeDeleted + "\nNow you have " + this.storage.taskListSize() + " tasks in the list");
                return false;
            } else {
                throw new DukeException("Wrong usage of delete! Correct usage: delete [index]");
            }
        case "bye":
            return true;
        default:
            throw new DukeException(":( OOPS!!! I'm sorry, but I don't know what that means! Possible commands: todo [task], event [task] /at [time],"
            + " deadline [task] /by [time], mark [index], unmark [index], delete [index], bye");
        }

    }

    /**
     * Prints out the task name that has been added as well as the number of tasks in the list
     * @param task The task that has been added
     */
    public void printAddTaskMessage(Task task) {
        System.out.println("Got it. I've added this task:\n" + task + "\nNow you have " + this.storage.taskListSize() + " tasks in the list." );
    }
}

