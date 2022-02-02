package sana;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;

import sana.exception.IncompleteCommandException;
import sana.exception.OutOfBoundsTaskException;
import sana.exception.UnknownCommandException;
import sana.task.Deadline;
import sana.task.Event;
import sana.task.Task;
import sana.task.ToDo;

/**
 * Sana is a BIG program!
 *
 * @author  Jan Alfenson Tan
 * @version 8.0
 */
public class Sana {

    /** userTasks stores the commands given to Sana from the user */
    private TaskList userTasks;

    /** taskMem stores the tasks given to Sana in a txt file */
    private Memory taskMem;

    /** ui handles the user interface portion of the program */
    private SanaResponse sanaResponse;

    /** parser parses the user command */
    private Parser parser;

    /**
     * Constructor for the Sana class
     */
    public Sana() {
        this.taskMem = new Memory();
        this.userTasks = new TaskList(taskMem.memToList());
        this.sanaResponse = new SanaResponse();
        this.parser = new Parser();
    }

    /**
     * This method controls the flow of logic of Sana depending on the user command
     *
     * @param userCommand   the user command
     */
    public String doCommand(String userCommand) {
        String[] parsedCmd = parser.parseCommand(userCommand);
        if (parsedCmd.length == 0) {
            return new UnknownCommandException().getMessage();
        }
        String command = parsedCmd[0];

        try {
            switch (command) {
            case "bye":
                return sanaResponse.bye();
            case "list":
                return list();
            case "mark":
                int markIndex = Integer.parseInt(parsedCmd[1]) - 1;
                return mark(markIndex, true);
            case "unmark":
                int unmarkIndex = Integer.parseInt(parsedCmd[1]) - 1;
                return mark(unmarkIndex, false);
            case "todo":
                return addToDo(parsedCmd[1]);
            case "event":
                LocalDate eventDate = LocalDate.parse(parsedCmd[2]);
                return addEvent(parsedCmd[1], eventDate);
            case "deadline":
                LocalDate deadlineDate = LocalDate.parse(parsedCmd[2]);
                return addDeadline(parsedCmd[1], deadlineDate);
            case "delete":
                int deleteIndex = Integer.parseInt(parsedCmd[1]) - 1;
                return delete(deleteIndex);
            case "find":
                return findTasks(parsedCmd[1]);
            default:
                throw new UnknownCommandException();
            }
        } catch (UnknownCommandException e) {
            System.out.println(e.getMessage());
        } catch (OutOfBoundsTaskException e) {
            System.out.println(e.getMessage());
        } catch (IncompleteCommandException e) {
            System.out.println(e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("Give your date in YYYY-MM-DD format!");
        } catch (NumberFormatException e) {
            System.out.println("I don't know what sana task you're referring to!");
        }
        taskMem.updateMemory(userTasks.toList());
        return "";
    }

    /**
     * Finds task names that contain the keyword, then print it out
     *
     * @param keyword   keyword to find in task names
     */
    private String findTasks(String keyword) {
        LinkedList<Task> matchingTasks = userTasks.findMatchingTasks(keyword);
        return sanaResponse.printTaskList(matchingTasks, true);
    }

    /**
     * Removes a sana.task from the userTasks list
     *
     * @param taskIndex                     The index of the sana.task to be removed
     * @throws OutOfBoundsTaskException     When task index is not in current task list
     */
    private String delete(int taskIndex) throws OutOfBoundsTaskException {
        if (taskIndex < 0 || taskIndex >= userTasks.taskAmt()) {
            throw new OutOfBoundsTaskException();
        }
        StringBuilder responseText = new StringBuilder();
        responseText.append(sanaResponse.deleteTaskText());
        responseText.append(sanaResponse.printTaskInList(userTasks.getTask(taskIndex)));
        userTasks.removeTask(taskIndex);
        responseText.append(sanaResponse.taskNumberText(userTasks.taskAmt()));
        return responseText.toString();
    }

    /**
     * Adds a Deadline to userTasks
     *
     * @param deadlineName                  name of the deadline
     * @param deadlineDate                  time of the deadline
     * @throws IncompleteCommandException   when command given is incomplete
     */
    private String addDeadline(String deadlineName, LocalDate deadlineDate) throws IncompleteCommandException {
        if (deadlineName.isBlank()) {
            throw new IncompleteCommandException();
        }
        StringBuilder responseText = new StringBuilder();
        responseText.append(sanaResponse.addTaskText());
        Deadline newDeadline = new Deadline(deadlineName, deadlineDate);
        userTasks.addTask(newDeadline);
        responseText.append(sanaResponse.printTaskInList(newDeadline));
        responseText.append(sanaResponse.taskNumberText(userTasks.taskAmt()));
        return responseText.toString();
    }

    /**
     * Adds an Event to userTasks
     *
     * @param eventName                     name of the event
     * @param eventTime                     time of the event
     * @throws IncompleteCommandException   when command given is incomplete
     */
    private String addEvent(String eventName, LocalDate eventTime) throws IncompleteCommandException {
        if (eventName.isBlank()) {
            throw new IncompleteCommandException();
        }
        StringBuilder responseText = new StringBuilder();
        responseText.append(sanaResponse.addTaskText());
        Event newEvent = new Event(eventName, eventTime);
        userTasks.addTask(newEvent);
        responseText.append(sanaResponse.printTaskInList(newEvent));
        responseText.append(sanaResponse.taskNumberText(userTasks.taskAmt()));
        return responseText.toString();
    }

    /**
     * Adds a ToDo to userTasks
     *
     * @param todoName                      the name of the todoTask
     * @throws IncompleteCommandException   when command given is incomplete
     */
    private String addToDo(String todoName) throws IncompleteCommandException {
        if (todoName.isBlank()) {
            throw new IncompleteCommandException();
        }
        StringBuilder responseText = new StringBuilder();
        responseText.append(sanaResponse.addTaskText());
        ToDo newTodo = new ToDo(todoName);
        userTasks.addTask(newTodo);
        responseText.append(sanaResponse.printTaskInList(newTodo));
        responseText.append(sanaResponse.taskNumberText(userTasks.taskAmt()));
        return responseText.toString();
    }

    /**
     * Marks the sana.task located at the index as done or not done
     *
     * @param taskIndex                 the index of the sana.task to be marked done
     * @param isComplete                the completion of the sana.task
     * @throws OutOfBoundsTaskException when task index is not in current task list
     */
    private String mark(int taskIndex, boolean isComplete) throws OutOfBoundsTaskException {
        if (taskIndex < 0 || taskIndex >= userTasks.taskAmt()) {
            throw new OutOfBoundsTaskException();
        }
        userTasks.getTask(taskIndex).setDone(isComplete);
        return sanaResponse.markText(isComplete)
                + sanaResponse.printTaskInList(userTasks.getTask(taskIndex));

    }

    /**
     * Lists the history of user inputs to Sana
     */
    private String list() {
        return sanaResponse.printTaskList(userTasks.toList(), false);
    }


    // GUI code portion

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String response = doCommand(input);
        return "Sana heard: " + response;
    }
}
