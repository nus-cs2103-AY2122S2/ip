import java.io.FileNotFoundException;
import java.util.*; // Import java utils

/**
 * Sana is a BIG program!
 *
 * @author  Jan Alfenson Tan
 * @version 1.2
 */
public class Sana {
    // The border for Sana's replies
    private static final String border = "_____________________________________________";

    /**
     * userTasks stores the commands given to Sana from the user
     */
    private LinkedList<Task> userTasks;

    /**
     * taskMem stores the tasks given to Sana in a txt file
     */
    private Memory taskMem;

    /**
     * Constructor for the Sana class
     */
    public Sana() {
        this.taskMem = new Memory();
        this.userTasks = taskMem.memToList();
    }

    /**
     * This method prints to system IO Sana's greeting
     */
    public void greet() {
        border();
        System.out.println("Hi! I'm BEEEEEEEG\nWhats up?");
        border();
    }

    /**
     * This method takes in the user's command and calls the respective Sana commands
     *
     * @param userCommand   the user command
     */
    public void commandParser(String userCommand) {
        border();
        try {
            if (userCommand.equals("bye")) {
                bye();
            } else if (userCommand.equals("list")) {
                list();
            } else if (userCommand.startsWith("mark") || userCommand.startsWith("unmark")) {
                String[] substringArr = userCommand.split(" ", 2);
                if (substringArr.length == 1) {
                    throw new IncompleteCommandException();
                }
                int taskIndex = Integer.parseInt(substringArr[1]) - 1;
                mark(taskIndex, userCommand.startsWith("mark"));
            } else if (userCommand.startsWith("todo ")) {
                String taskName = userCommand.replaceFirst("todo ", "");
                addToDo(taskName);
            } else if (userCommand.startsWith("event ")) {
                String temp = userCommand.replaceFirst("event ", "");
                if (!temp.contains(" /at ")) {
                    throw new IncompleteCommandException();
                }
                String[] subStrings = temp.split(" /at ", 2);
                addEvent(subStrings[0], subStrings[1]);
            } else if (userCommand.startsWith("deadline ")) {
                String temp = userCommand.replaceFirst("deadline ", "");
                if (!temp.contains(" /by ")) {
                    throw new IncompleteCommandException();
                }
                String[] subStrings = temp.split(" /by ", 2);
                addDeadline(subStrings[0], subStrings[1]);
            } else if (userCommand.startsWith("delete ")) {
                String[] substringArr = userCommand.split(" ", 2);
                if (substringArr.length == 1) {
                    throw new IncompleteCommandException();
                }
                int taskIndex = Integer.parseInt(substringArr[1]) - 1;
                delete(taskIndex);
            } else {
                throw new UnknownCommandException();
            }
        } catch (UnknownCommandException e) {
            System.out.println(e.getMessage());
        } catch (IncompleteCommandException e) {
            System.out.println(e.getMessage());
        } catch (OutOfBoundsTaskException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Where's my number!");
        }
        taskMem.updateMemory(userTasks);
        border();
    }

    /**
     * This method removes a task from the userTasks list
     *
     * @param taskIndex The index of the tast to be removed
     */
    private void delete(int taskIndex) throws OutOfBoundsTaskException {
        if (taskIndex < 0 || taskIndex >= userTasks.size()) {
            throw new OutOfBoundsTaskException();
        }
        System.out.println("Yay! I'll take this out!");
        System.out.println(userTasks.get(taskIndex));
        userTasks.remove(taskIndex);
        taskNumberText();
    }

    /**
     * This method adds a Deadline to userTasks
     *
     * @param deadlineName  name of the deadline
     * @param deadlineTime  time of the deadline
     */
    private void addDeadline(String deadlineName, String deadlineTime) throws IncompleteCommandException {
        if (deadlineName.isBlank() || deadlineTime.isBlank()) {
            throw new IncompleteCommandException();
        }
        addTaskText();
        Deadline newDeadline = new Deadline(deadlineName, deadlineTime);
        userTasks.add(newDeadline);
        System.out.println(newDeadline);
        taskNumberText();
    }

    /**
     * This method adds an Event to userTasks
     *
     * @param eventName name of the event
     * @param eventTime time of the event
     */
    private void addEvent(String eventName, String eventTime) throws IncompleteCommandException {
        if (eventName.isBlank() || eventTime.isBlank()) {
            throw new IncompleteCommandException();
        }
        addTaskText();
        Event newEvent = new Event(eventName, eventTime);
        userTasks.add(newEvent);
        System.out.println(newEvent);
        taskNumberText();
    }

    /**
     * This method adds a ToDo to userTasks
     *
     * @param taskName  the name of the todo
     */
    private void addToDo(String taskName) throws IncompleteCommandException {
        if (taskName.isBlank()) {
            throw new IncompleteCommandException();
        }
        addTaskText();
        ToDo newTodo = new ToDo(taskName);
        userTasks.add(newTodo);
        System.out.println(newTodo);
        taskNumberText();
    }

    /**
     * This method marks the task located at the index as done or not done
     *
     * @param taskIndex     the index of the task to be marked done
     * @param completion    the completion of the task
     */
    private void mark(int taskIndex, boolean completion) throws OutOfBoundsTaskException {
        if (taskIndex < 0 || taskIndex >= userTasks.size()) {
            throw new OutOfBoundsTaskException();
        }
        userTasks.get(taskIndex).setDone(completion);
        if (completion) {
            System.out.println("You've done it! Well done!");
        } else {
            System.out.println("Oopsies! I'll change it back!");
        }
        System.out.println(userTasks.get(taskIndex));
    }

    /**
     * This method lists the history of user inputs to Sana
     */
    private void list() {
        int index = 1;
        System.out.println("Here's your tasks!");
        for (Task task : userTasks) {
            String header = Integer.valueOf(index).toString() + ".";
            System.out.println(header + task);
            index++;
        }
    }

    /**
     * This method prints to system IO how many tasks in user tasks
     */
    private void taskNumberText() {
        String taskAmt = Integer.valueOf(userTasks.size()).toString();
        System.out.println("You have " + taskAmt + " things here. Time to get working!");
    }

    /**
     * This method prints to system IO Sana's add task text
     */
    private void addTaskText() {
        System.out.println("I've added your task!");
    }

    /**
     * This method prints to system IO Sana's bye
     */
    private void bye() {
        System.out.println("See you next time!");
    }

    /**
     * This method prints the border
     */
    private void border() {
        System.out.println(border);
    }

    /** Obsolete
     * Adds given task to the list of tasks given to Sana.
     *
     * @param taskName  name of task
     */

    /**
     private void addTask(String taskName) {
     userTasks.add(new Task(taskName));
     System.out.println("added: " + taskName);
     }
     */

    public static void main(String[] args) {
        Sana sana = new Sana();
        sana.greet();

        Scanner userInput = new Scanner(System.in);
        while (true) {
            String input = userInput.nextLine();
            sana.commandParser(input);
            if (input.equals("bye")) {
                break;
            }
        }
    }
}
