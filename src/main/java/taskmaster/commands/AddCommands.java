package taskmaster.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import taskmaster.exception.TaskmasterExceptions;
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

    private String parseCommand(TaskList taskList) throws TaskmasterExceptions {
        //Split the string using the whitespace delimiter to make identifying each component easier.
        String[] stringIntoParts = this.command.split(" ");
        String firstWord = stringIntoParts[0];
        //Handle the case of having no second input
        if (stringIntoParts.length == 1) {
            throw new TaskmasterExceptions("What?! Task description cannot be empty."
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

    private String parseDeadlineEventTasks(TaskList taskList) throws TaskmasterExceptions {
        //Split the string using the whitespace delimiter to make identifying each component easier.
        String[] stringIntoParts = this.command.split(" ");
        String firstWord = stringIntoParts[0];
        String taskName = command.substring(command.indexOf(" "));

        //If task name do not contain "/".
        if (!taskName.contains("/")) {
            throw new TaskmasterExceptions("Deadline and event tasks require /by and "
                    + "/at to specify the deadline or time of occurrence.\n"
                    + " Eg Deadline eat food /by 12pm, event concert /at 8pm");
        }

        //Retrieves the substring before and after the "/" delimiter.
        String temp = taskName.substring(taskName.indexOf("/"));
        //taskNameOnly contains the task description/name only.
        String taskNameOnly = taskName.substring(0, taskName.indexOf("/"));
        //oldDateTime contains the time of occurrence.
        String oldDateTime = temp.substring(temp.indexOf(" ") + 1);

        try {
            DateTimeFormatter oldFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime dateTime = LocalDateTime.parse(oldDateTime, oldFormat);

            //String that states by ... or at....
            if (firstWord.equals("deadline")) {
                //Handle the case of deadline task having no /by
                if (!temp.contains("by")) {
                    throw new TaskmasterExceptions("Deadline tasks require /by specify the deadline."
                            + "Eg deadline eat food /by 12pm\n");
                }

                return addDeadlineTask(taskNameOnly, dateTime, taskList);

            } else {
                //Handle the case of event task having no /at
                if (!temp.contains("at")) {
                    throw new TaskmasterExceptions("Event tasks require /at specify the time of occurrence."
                            + "music concert /at 8pm\n");
                }
                return addEventTask(taskNameOnly, dateTime, taskList);

            }
        } catch (NumberFormatException nfe) {
            throw new TaskmasterExceptions("ERROR! Expected Numbers for date and time!\n");

        } catch (DateTimeParseException dt) {
            throw new TaskmasterExceptions("ERROR! Time or Date is in wrong format! 2/12/2019 1800\n");
        }
    }

    /**
     * Helper function to add a new TodoTask to the Task list.
     *
     * @param taskName Name of the To do Task.
     *
     * @param taskList the task list that contains all the tasks.
     *
     * @return the confirmation of the execution of the task in
     *      String format.
     */

    private String addTodoTask(String taskName, TaskList taskList) {
        //initial size of the task list to check whether task has been successfully deleted.
        int initialSize = taskList.getCurrentSize();
        TodoTask newTask = new TodoTask(taskName);
        taskList.add(newTask);
        assert taskList.getCurrentSize() == initialSize + 1 : "Task list should contain one more than the initial size"
                + " of the existing task list after adding a new task.";
        return printTask(newTask, taskList);
    }

    /**
     * Helper function to add a new Deadline Task to the Task list.
     *
     * @param taskName Name of the Deadline Task.
     *
     * @param dateTime Deadline the task is due by.
     *
     * @return the confirmation of the execution of the task in
     *      String format.
     */

    private String addDeadlineTask(String taskName, LocalDateTime dateTime, TaskList taskList) {
        //initial size of the task list to check whether task has been successfully deleted.
        int initialSize = taskList.getCurrentSize();
        DeadlineTask newTask = new DeadlineTask(taskName, dateTime);
        taskList.add(newTask);
        assert taskList.getCurrentSize() == initialSize + 1 : "Task list should contain one more than the initial size"
                + " of the existing task list after adding a new task.";
        return printTask(newTask, taskList);
    }

    /**
     * Helper function to add a new Event Task to the Task list.
     *
     * @param taskName Name of the Event Task.
     *
     * @param dateTime The date and time the task is occurring at.
     *
     * @return the confirmation of the execution of the task in
     *       String format.
     */

    private String addEventTask(String taskName, LocalDateTime dateTime, TaskList taskList) {
        //initial size of the task list to check whether task has been successfully deleted.
        int initialSize = taskList.getCurrentSize();
        EventTask newTask = new EventTask(taskName, dateTime);
        taskList.add(newTask);
        assert taskList.getCurrentSize() == initialSize + 1 : "Task list should contain one more than the initial size"
                + " of the existing task list after adding a new task.";
        return printTask(newTask, taskList);
    }

    /**
     * Helper function to print out the display message when
     * the task has been successfully added.
     *
     * @param newTask Task that has been successfully added.
     *
     * @param taskList the task list that contains all the task.
     *
     * @return the confirmation of the execution of the task in
     * String format.
     */
    private String printTask(Task newTask, TaskList taskList) {
        return "Quit ordering me around!\n"
                + "I've added this task to our list:"
                + "    \n" + newTask.toString()
                + "\n" + taskList.printCurrentSize();
    }

    /**
     * Execute the command.
     *
     * @param ui The User interface.
     *
     * @param storage The file that is storing the task information.
     *
     * @return Returns a string confirmation that the task has been executed.
     *
     * @throws TaskmasterExceptions Throws an exception if execution fails.
     */

    public String execute(TaskList taskList, UserInterface ui, Storage storage) throws TaskmasterExceptions {
        return parseCommand(taskList);
    }

}
