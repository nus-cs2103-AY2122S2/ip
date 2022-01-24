import java.time.format.DateTimeParseException;
import java.time.LocalDate;

import java.util.LinkedList;

/**
 * Sana is a BIG program!
 *
 * @author  Jan Alfenson Tan
 * @version 1.7
 */
public class Sana {

    /** userTasks stores the commands given to Sana from the user */
    private LinkedList<Task> userTasks;

    /** taskMem stores the tasks given to Sana in a txt file */
    private Memory taskMem;

    /** ui handles the user interface portion of the program */
    private Ui ui;

    /**
     * Constructor for the Sana class
     */
    public Sana() {
        this.taskMem = new Memory();
        this.userTasks = taskMem.memToList();
        this.ui = new Ui();
    }

    /**
     * This method runs Sana
     */
    public void run() {
        ui.greet();

        while (true) {
            String input = ui.nextLine();
            commandParser(input);
            if (input.equals("bye")) {
                break;
            }
        }
    }

    /**
     * This method takes in the user's command and calls the respective Sana commands
     *
     * @param userCommand   the user command
     */
    public void commandParser(String userCommand) {
        ui.border();
        try {
            if (userCommand.equals("bye")) {
                ui.closeScanner();
                ui.bye();

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
                LocalDate eventDate = LocalDate.parse(subStrings[1]);
                addEvent(subStrings[0], eventDate);

            } else if (userCommand.startsWith("deadline ")) {
                String temp = userCommand.replaceFirst("deadline ", "");
                if (!temp.contains(" /by ")) {
                    throw new IncompleteCommandException();
                }
                String[] subStrings = temp.split(" /by ", 2);
                LocalDate deadlineDate = LocalDate.parse(subStrings[1]);
                addDeadline(subStrings[0], deadlineDate);

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
        } catch (DateTimeParseException e) {
            System.out.println(e);
        }
        taskMem.updateMemory(userTasks);
        ui.border();
    }

    /**
     * This method removes a task from the userTasks list
     *
     * @param taskIndex The index of the task to be removed
     */
    private void delete(int taskIndex) throws OutOfBoundsTaskException {
        if (taskIndex < 0 || taskIndex >= userTasks.size()) {
            throw new OutOfBoundsTaskException();
        }
        ui.deleteTaskText();
        ui.printTaskInList(userTasks.get(taskIndex));
        userTasks.remove(taskIndex);
        ui.taskNumberText(userTasks.size());
    }

    /**
     * This method adds a Deadline to userTasks
     *
     * @param deadlineName  name of the deadline
     * @param deadlineDate  time of the deadline
     */
    private void addDeadline(String deadlineName, LocalDate deadlineDate) throws IncompleteCommandException {
        if (deadlineName.isBlank()) {
            throw new IncompleteCommandException();
        }
        ui.addTaskText();
        Deadline newDeadline = new Deadline(deadlineName, deadlineDate);
        userTasks.add(newDeadline);
        ui.printTaskInList(newDeadline);
        ui.taskNumberText(userTasks.size());
    }

    /**
     * This method adds an Event to userTasks
     *
     * @param eventName name of the event
     * @param eventTime time of the event
     */
    private void addEvent(String eventName, LocalDate eventTime) throws IncompleteCommandException {
        if (eventName.isBlank()) {
            throw new IncompleteCommandException();
        }
        ui.addTaskText();
        Event newEvent = new Event(eventName, eventTime);
        userTasks.add(newEvent);
        ui.printTaskInList(newEvent);
        ui.taskNumberText(userTasks.size());
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
        ui.addTaskText();
        ToDo newTodo = new ToDo(taskName);
        userTasks.add(newTodo);
        ui.printTaskInList(newTodo);
        ui.taskNumberText(userTasks.size());
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
        ui.markText(completion);
        ui.printTaskInList(userTasks.get(taskIndex));
    }

    /**
     * This method lists the history of user inputs to Sana
     */
    private void list() {
        ui.printTaskList(userTasks);
    }

    public static void main(String[] args) {
        Sana sana = new Sana();
        sana.run();
    }
}
