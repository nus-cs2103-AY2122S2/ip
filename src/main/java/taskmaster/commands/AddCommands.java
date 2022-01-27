package taskmaster.commands;

import taskmaster.exception.DukeExceptions;
import taskmaster.util.TaskList;
import taskmaster.task.Task;
import taskmaster.task.TodoTask;
import taskmaster.task.DeadlineTask;
import taskmaster.task.EventTask;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/*
 * This class inherits from the Command class.
 * It encapsulates Commands that adds a new Task.
 */

public class AddCommands extends Commands {
    /** TaskList contains all the task. **/
    private final TaskList TASKLIST;

    /**
     * Constructor for AddCommands.
     *
     * @param command Type of command.
     * @param taskList Task list that command is going to be added in.
     */

    public AddCommands(String command, TaskList taskList) {
        super(command);
        this.TASKLIST = taskList;
    }

    /**
     * Helper function to help parse the command.
     * Extract the components of the command.
     * Categorise it into either Todo Command or Deadline/Event task.
     */

    private void parseCommand() {
        String[] stringIntoParts = this.command.split(" ");
        String firstWord = stringIntoParts[0];

        try {
            //Handle the case of having no second input
            if (stringIntoParts.length == 1) {
                throw new DukeExceptions("What?! Task description cannot be empty."
                        + "Eg todo eat, deadline eat food /by 12pm,"
                        + "event concert /at 8pm\n");
            }

            String taskName = command.substring(command.indexOf(" "));

            if (firstWord.equals("todo")) {
                addTodoTask(taskName);
            } else {
                parseDeadlineEventTasks();
            }
        } catch (DukeExceptions e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Helper function to help parse Deadline and Event task.
     * Extract the components of the command.
     * Categorise it into either Deadline Command or Event Command.
     */

    private void parseDeadlineEventTasks() {
        String[] stringIntoParts = this.command.split(" ");
        String firstWord = stringIntoParts[0];
        String taskName = command.substring(command.indexOf(" "));
        try {
            //Handle the case of having no "/" to specify deadline or time of occurrences for deadline and event tasks
            if (!taskName.contains("/")) {
                throw new DukeExceptions("Deadline and event tasks require /by and "
                        + "/at to specify the deadline or time of occurrence.\n"
                        + " Eg Deadline eat food /by 12pm, event concert /at 8pm");
            }

            String temp = taskName.substring(taskName.indexOf("/"));
            String taskNameWithoutBack = taskName.substring(0, taskName.indexOf("/"));
            String oldDateTime = temp.substring(temp.indexOf(" ") + 1);

            DateTimeFormatter oldFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime dateTime = LocalDateTime.parse(oldDateTime, oldFormat);

            //String that states by ... or at....
            if (firstWord.equals("deadline")) {
                //Handle the case of deadline task having no /by
                if (!temp.contains("by")) {
                    throw new DukeExceptions("Deadline tasks require /by specify the deadline."
                                            + "Eg deadline eat food /by 12pm\n");
                }
                    addDeadlineTask(taskNameWithoutBack, dateTime);
                } else {
                    //Handle the case of event task having no /at
                    if (!temp.contains("at")) {
                        throw new DukeExceptions("Event tasks require /at specify the time of occurrence."
                                                    + "music concert /at 8pm\n");
                    }
                    addEventTask(taskNameWithoutBack, dateTime);
                }

        } catch (DukeExceptions e) {
            System.out.println(e.getMessage());

        } catch (NumberFormatException e) {
            System.out.println("ERROR! Expected Numbers for date and time!\n");

        } catch (DateTimeParseException e) {
            System.out.println("ERROR! Time or Date is in wrong format! 2/12/2019 1800\n");
        }
    }

    /**
     * Helper function to add a new TodoTask to the Task list.
     *
     * @param taskName Name of the To do Task.
     */

    private void addTodoTask(String taskName) {
        TodoTask newTask = new TodoTask(taskName);
        TASKLIST.add(newTask);
        printTask(newTask);
    }

    /**
     * Helper function to add a new Deadline Task to the Task list.
     *
     * @param taskName Name of the Deadline Task.
     * @param dateTime Deadline the task is due by.
     */

    private void addDeadlineTask(String taskName, LocalDateTime dateTime){
        DeadlineTask newTask = new DeadlineTask(taskName, dateTime);
        TASKLIST.add(newTask);
        printTask(newTask);
    }

    /**
     * Helper function to add a new Event Task to the Task list.
     *
     * @param taskName Name of the Event Task.
     * @param dateTime The date and time the task is occurring at.
     */

    private void addEventTask(String taskName, LocalDateTime dateTime){
        EventTask newTask = new EventTask(taskName, dateTime);
        TASKLIST.add(newTask);
        printTask(newTask);
    }

    /**
     * Helper function to print out the display message when
     * the task has been successfully added.
     *
     * @param newTask Task that has been successfully added.
     */


    private void printTask(Task newTask) {
        System.out.println("Quit ordering me around!");
        System.out.println("I've added this task to our list:");
        System.out.println("    " + newTask.toString());
        TASKLIST.printCurrentSize();
    }

    /**
     * Execute Command.
     */

    public void execute() {
        parseCommand();
    }

}
