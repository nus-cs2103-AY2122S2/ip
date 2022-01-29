import java.util.ArrayList;
import java.util.Arrays;

/**
 * Handles input in main in Duke.java. Receives String from Scanner in main
 * Processes the input into 7 categories: Todo, Event, Deadline, list, mark, unmark, bye and throws error
 */
public class InputHandler {
    private ArrayList<Task> arr;
    public InputHandler() {
        this.arr = new ArrayList<>();
    }


    /**
     *
     * @param input String input from main in Duke.java, from user input
     * @return boolean representing isChatEnded variable in main. If "bye" command is given, a true boolean is returned, else false is returned
     * @throws DukeException Invalid input types, or unrecognisable commands
     */
    public boolean handleInput(String input) throws DukeException {
        String[] splitInput = input.split(" ");
        String inputCommand = splitInput[0];
        switch (inputCommand) {
            case "todo":
                if (splitInput.length > 1) {
                    String[] nameArray = Arrays.copyOfRange(splitInput, 1, splitInput.length);
                    String name = String.join(" ", nameArray);
                    Todo newTodo = new Todo(name);
                    arr.add(newTodo);
                    printMessage(newTodo);
                    return false;
                } else {
                    throw new DukeException(":( OOPS!!! The description of a todo cannot be empty.");
                }
            case "event":
                if (splitInput.length > 3) {
                    String[] stringArrayExcludingEvent = Arrays.copyOfRange(splitInput, 1, splitInput.length);
                    String stringExcludingEvent = String.join(" ", stringArrayExcludingEvent);
                    String[] nameAndTimeArray = stringExcludingEvent.split("/at");
                    String name = nameAndTimeArray[0];
                    String time = nameAndTimeArray[1];
                    Event newEvent = new Event(name, time);
                    arr.add(newEvent);
                    printMessage(newEvent);
                    return false;
                } else {
                    throw new DukeException(":( OOPS!!! The description of a todo cannot be empty.");
                }
            case "deadline":
                if (splitInput.length > 3) {
                    String[] stringArrayExcludingDeadline = Arrays.copyOfRange(splitInput, 1, splitInput.length);
                    String stringExcludingDeadline = String.join(" ", stringArrayExcludingDeadline);
                    String[] nameAndTimeArray = stringExcludingDeadline.split("/by");
                    String name = nameAndTimeArray[0];
                    String time = nameAndTimeArray[1];
                    Deadline newDeadline = new Deadline(name, time);
                    arr.add(newDeadline);
                    printMessage(newDeadline);
                    return false;
                } else {
                    throw new DukeException(":( OOPS!!! The description of a todo cannot be empty.");
                }
            case "list":
                if (splitInput.length == 1) {
                    System.out.println("Here are the tasks in your list:");
                    int i = 0;
                    for (Task item : arr) {
                        i += 1;
                        if (item.isMark()) {
                            System.out.println(i + ". " + item);
                        } else {
                            System.out.println(i + ". " + item);
                        }
                    }
                    return false;
                } else {
                    throw new DukeException("Wrong usage of list!");
                }
            case "mark":
                if (splitInput.length == 2) {
                    System.out.println("Nice! I've marked this task as done:\n");
                    int idx = Integer.parseInt(splitInput[1]) - 1;
                    Task taskToBeMarked = arr.get(idx);
                    taskToBeMarked.markTask();
                    return false;
                } else {
                    throw new DukeException("Wrong usage of mark!");
                }
            case "unmark":
                if (splitInput.length == 2) {
                    System.out.println("OK, I've marked this task as not done yet:\n");
                    int idx = Integer.parseInt(splitInput[1]) - 1;
                    Task taskToBeUnmarked = arr.get(idx);
                    taskToBeUnmarked.unmarkTask();
                    return false;
                } else {
                    throw new DukeException("Wrong usage of unmark!");
                }
            case "bye":
                return true;
            default:
                throw new DukeException(":( OOPS!!! I'm sorry, but I don't know what that means :(");
        }

    }

    /**
     * Prints out the task name that has been added as well as the number of tasks in the list
     * @param task The task that has been added
     */
    public void printMessage(Task task) {
        System.out.println("Got it. I've added this task:\n" + task + "\nNow you have " + arr.size() + " tasks in the list." );
    }
}

