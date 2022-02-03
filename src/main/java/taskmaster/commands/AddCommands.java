package taskmaster.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import taskmaster.exception.DukeExceptions;
import taskmaster.task.DeadlineTask;
import taskmaster.task.EventTask;
import taskmaster.task.Task;
import taskmaster.task.TodoTask;
import taskmaster.userinterface.UserInterface;
import taskmaster.util.Storage;
import taskmaster.util.TaskList;

/*
 * This class inherits from the Command class.
 * It encapsulates Commands that adds a new Task.
 */

public class AddCommands extends Commands {

    /**
     * Constructor for AddCommands.
     *
     * @param command Type of command.
     */

    public AddCommands(String command) {
        super(command);
    }

    /**
     * Helper function to help parse the command.
     * Extract the components of the command.
     * Categorise it into either Todo Command or Deadline/Event task.
     */

    private String parseCommand(TaskList taskList) throws DukeExceptions {
        String[] stringIntoParts = this.command.split(" ");
        String firstWord = stringIntoParts[0];
        //Handle the case of having no second input
        if (stringIntoParts.length == 1) {
            throw new DukeExceptions("What?! Task description cannot be empty."
                    + "Eg todo eat, deadline eat food /by 12pm,"
                    + "event concert /at 8pm\n");
        }
        String taskName = command.substring(command.indexOf(" "));

        if (firstWord.equals("todo")) {
            return addTodoTask(taskName, taskList);
        } else {
            return parseDeadlineEventTasks(taskList);
        }
    }

    /**
     * Helper function to help parse Deadline and Event task.
     * Extract the components of the command.
     * Categorise it into either Deadline Command or Event Command.
     */

    private String parseDeadlineEventTasks(TaskList taskList) throws DukeExceptions {
        String[] stringIntoParts = this.command.split(" ");
        String firstWord = stringIntoParts[0];
        String taskName = command.substring(command.indexOf(" "));

        if (!taskName.contains("/")) {
            throw new DukeExceptions("Deadline and event tasks require /by and "
                    + "/at to specify the deadline or time of occurrence.\n"
                    + " Eg Deadline eat food /by 12pm, event concert /at 8pm");
        }

        String temp = taskName.substring(taskName.indexOf("/"));
        String taskNameWithoutBack = taskName.substring(0, taskName.indexOf("/"));
        String oldDateTime = temp.substring(temp.indexOf(" ") + 1);

        try {
            DateTimeFormatter oldFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime dateTime = LocalDateTime.parse(oldDateTime, oldFormat);

            //String that states by ... or at....
            if (firstWord.equals("deadline")) {
                //Handle the case of deadline task having no /by
                if (!temp.contains("by")) {
                    throw new DukeExceptions("Deadline tasks require /by specify the deadline."
                            + "Eg deadline eat food /by 12pm\n");
                }

                return addDeadlineTask(taskNameWithoutBack, dateTime, taskList);

            } else {
                //Handle the case of event task having no /at
                if (!temp.contains("at")) {
                    throw new DukeExceptions("Event tasks require /at specify the time of occurrence."
                            + "music concert /at 8pm\n");
                }
                return addEventTask(taskNameWithoutBack, dateTime, taskList);

            }
        } catch (NumberFormatException nfe) {
            throw new DukeExceptions("ERROR! Expected Numbers for date and time!\n");

        } catch (DateTimeParseException dt) {
            throw new DukeExceptions("ERROR! Time or Date is in wrong format! 2/12/2019 1800\n");
        }
    }

    /**
     * Helper function to add a new TodoTask to the Task list.
     *
     * @param taskName Name of the To do Task.
     */

    private String addTodoTask(String taskName, TaskList taskList) {
        TodoTask newTask = new TodoTask(taskName);
        taskList.add(newTask);
        return printTask(newTask, taskList);
    }

    /**
     * Helper function to add a new Deadline Task to the Task list.
     *
     * @param taskName Name of the Deadline Task.
     * @param dateTime Deadline the task is due by.
     */

    private String addDeadlineTask(String taskName, LocalDateTime dateTime, TaskList taskList) {
        DeadlineTask newTask = new DeadlineTask(taskName, dateTime);
        taskList.add(newTask);
        return printTask(newTask, taskList);
    }

    /**
     * Helper function to add a new Event Task to the Task list.
     *
     * @param taskName Name of the Event Task.
     * @param dateTime The date and time the task is occurring at.
     */

    private String addEventTask(String taskName, LocalDateTime dateTime, TaskList taskList) {
        EventTask newTask = new EventTask(taskName, dateTime);
        taskList.add(newTask);
        return printTask(newTask, taskList);
    }

    /**
     * Helper function to print out the display message when
     * the task has been successfully added.
     *
     * @param newTask Task that has been successfully added.
     */


    private String printTask(Task newTask, TaskList taskList) {
        return "Quit ordering me around!\n"
                + "I've added this task to our list:"
                + "    \n" + newTask.toString()
                + "\n" + taskList.returnCurrentSize();
    }

    /**
     * Execute the command.
     * @param ui The User interface.
     * @param storage The file that is storing the task information.
     * @return Returns a string confirmation that the task has been executed.
     * @throws DukeExceptions Throws an exception if execution fails.
     */

    public String execute(TaskList taskList, UserInterface ui, Storage storage) throws DukeExceptions {
        return parseCommand(taskList);
    }

}
