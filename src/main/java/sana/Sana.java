package sana;

import java.time.format.DateTimeParseException;
import java.time.LocalDate;

import sana.exception.*;
import sana.task.*;


/**
 * Sana is a BIG program!
 *
 * @author  Jan Alfenson Tan
 * @version 1.7
 */
public class Sana {

    /** userTasks stores the commands given to Sana from the user */
    private TaskList userTasks;

    /** taskMem stores the tasks given to Sana in a txt file */
    private Memory taskMem;

    /** ui handles the user interface portion of the program */
    private Ui ui;

    /** parser parses the user command */
    private Parser parser;

    /**
     * Constructor for the Sana class
     */
    public Sana() {
        this.taskMem = new Memory();
        this.userTasks = new TaskList(taskMem.memToList());
        this.ui = new Ui();
        this.parser = new Parser();
    }

    /**
     * This method runs Sana
     */
    public void run() {
        ui.greet();

        while (true) {
            String input = ui.nextLine();
            doCommand(input);
            if (input.equals("bye")) {
                break;
            }
        }
    }

    /**
     * This method controls the flow of logic of Sana depending on the user command
     *
     * @param userCommand   the user command
     */
    public void doCommand(String userCommand) {
        ui.border();
        String[] parsedCmd = parser.parseCommand(userCommand);
        if (parsedCmd.length == 0) {
            return;
        }
        String command = parsedCmd[0];
        try {
            switch (command) {
                case "bye":
                    ui.closeScanner();
                    ui.bye();
                    break;
                case "list":
                    list();
                    break;
                case "mark":
                    int markIndex = Integer.parseInt(parsedCmd[1]) - 1;
                    mark(markIndex, true);
                    break;
                case "unmark":
                    int unmarkIndex = Integer.parseInt(parsedCmd[1]) - 1;
                    mark(unmarkIndex, false);
                    break;
                case "todo":
                    addToDo(parsedCmd[1]);
                    break;
                case "event":
                    LocalDate eventDate = LocalDate.parse(parsedCmd[2]);
                    addEvent(parsedCmd[1], eventDate);
                    break;
                case "deadline":
                    LocalDate deadlineDate = LocalDate.parse(parsedCmd[2]);
                    addDeadline(parsedCmd[1], deadlineDate);
                    break;
                case "delete":
                    int deleteIndex = Integer.parseInt(parsedCmd[1]) - 1;
                    delete(deleteIndex);
            }
        } catch (OutOfBoundsTaskException e) {
            System.out.println(e.getMessage());
        } catch (IncompleteCommandException e) {
            System.out.println(e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("Give your date in YYYY-MM-DD format!");
        } catch (NumberFormatException e) {
            System.out.println("I don't know what sana.task you're referring to!");
        }
        ui.border();
    }

    /**
     * This method removes a sana.task from the userTasks list
     *
     * @param taskIndex The index of the sana.task to be removed
     */
    private void delete(int taskIndex) throws OutOfBoundsTaskException {
        if (taskIndex < 0 || taskIndex >= userTasks.taskAmt()) {
            throw new OutOfBoundsTaskException();
        }
        ui.deleteTaskText();
        ui.printTaskInList(userTasks.getTask(taskIndex));
        userTasks.removeTask(taskIndex);
        ui.taskNumberText(userTasks.taskAmt());
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
        userTasks.addTask(newDeadline);
        ui.printTaskInList(newDeadline);
        ui.taskNumberText(userTasks.taskAmt());
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
        userTasks.addTask(newEvent);
        ui.printTaskInList(newEvent);
        ui.taskNumberText(userTasks.taskAmt());
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
        userTasks.addTask(newTodo);
        ui.printTaskInList(newTodo);
        ui.taskNumberText(userTasks.taskAmt());
    }

    /**
     * This method marks the sana.task located at the index as done or not done
     *
     * @param taskIndex     the index of the sana.task to be marked done
     * @param completion    the completion of the sana.task
     */
    private void mark(int taskIndex, boolean completion) throws OutOfBoundsTaskException {
        if (taskIndex < 0 || taskIndex >= userTasks.taskAmt()) {
            throw new OutOfBoundsTaskException();
        }
        userTasks.getTask(taskIndex).setDone(completion);
        ui.markText(completion);
        ui.printTaskInList(userTasks.getTask(taskIndex));
    }

    /**
     * This method lists the history of user inputs to Sana
     */
    private void list() {
        ui.printTaskList(userTasks.toList());
    }

    public static void main(String[] args) {
        Sana sana = new Sana();
        sana.run();
    }
}
