package duke;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Runs the command for user to create a new task.
 */

public class TaskCommand extends Command {

    enum TaskType {
        TODO, DEADLINE, EVENT
    }

    private TaskType taskType;
    private String command;
    private TaskList taskList;
    private Storage storage;
    private DateFormat formatter = new SimpleDateFormat("dd/MM/yy h:mm a");


    /**
     * Constructor to create a command to create a new task.
     * @param taskType determine what kind of task to be created.
     * @param command the command entered by the user.
     */
    TaskCommand (TaskType taskType, String command) {
        this.taskType = taskType;
        this.command = command;
    }

    /**
     * Executes the command proceeds to create a new task for the user, and appending it to the list.
     *
     * @param taskList The TaskList of the current user.
     * @param ui The user interface to show messages to users.
     * @param storage The file system for reading and writing into the database.
     */
    @Override
    String runCommand(TaskList taskList, Ui ui, Storage storage) {

        this.taskList = taskList;
        this.storage = storage;

        try {
            String message = null;
            int indexOfPriority = command.indexOf("$");
            String priorityString = command.substring(indexOfPriority);
            Task.Priority priority = parsePriority(priorityString);

            if (taskType == TaskType.TODO) {
                message = newTodoTask(command, priority, indexOfPriority);
            } else if (taskType == TaskType.EVENT) {
                message = newEvent(command, priority, indexOfPriority);
            } else if (taskType == TaskType.DEADLINE) {
                message = newDeadline(command, priority, indexOfPriority);
            }
            return message;
        } catch (StringIndexOutOfBoundsException e) {
            return "Wrong format command, try again. Type /help to see available commands.";
        }
    }

    /**
     * Get the priority level from the command.
     *
     * @param command the string input from user.
     * @return the Priority level of the task.
     */
    private Task.Priority parsePriority(String command) {
        if (command.equals("$HIGH")) {
            return Task.Priority.HIGH;
        } else if (command.equals("$MEDIUM")) {
            return Task.Priority.MEDIUM;
        } else if (command.equals("$LOW")) {
            return Task.Priority.LOW;
        }
        return Task.Priority.HIGH;
    }

    /**
     * Creates a new To-Do task.
     *
     * @param command string command from user.
     * @param priority the priority level of the task.
     * @param indexOfPriority the index of the priority level in the string.
     * @return the return message after running the command.
     */
    private String newTodoTask(String command, Task.Priority priority, int indexOfPriority) {
        String indentation = "    ";
        String message = null;
        try {
            String newString = command.substring(5, indexOfPriority - 1).trim();
            if (newString.length() == 0) {
                throw new StringIndexOutOfBoundsException();
            }
            ToDos newToDo = new ToDos(newString, priority);
            taskList.addTask(newToDo);
            message = "Got it. I've added this task:\n"
                    + indentation + "  " + newToDo.toString()
                    + newToDo.getStatus() + " " + newToDo.getDescription() + "\n"
                    + indentation + "Now you have "
                    + String.valueOf(taskList.getSize()) + " tasks in the list.";
            storage.appendTask(newToDo);
        } catch (StringIndexOutOfBoundsException | IOException e) {
            return "Error";
        }
        return message;
    }

    /**
     * Creates a new Event task.
     *
     * @param command string command from user.
     * @param priority the priority level of the task.
     * @param indexOfPriority the index of the priority level in the string.
     * @return the return message after running the command.
     */
    private String newEvent(String command, Task.Priority priority, int indexOfPriority) {
        String message = null;
        String indentation = "    ";
        try {
            String description = command.substring(6, command.indexOf('/') - 1).trim();
            String date = command.substring((command.indexOf('/') + 4), indexOfPriority - 1).trim();
            Date dueDate = (Date) formatter.parse(date);
            Event newEvent = new Event(description, dueDate, priority);
            taskList.addTask(newEvent);
            message = "Got it. I've added this task:\n"
                    + indentation + "  " + newEvent.toString()
                    + newEvent.getStatus() + " " + newEvent.getDescription() + "\n"
                    + indentation + "Now you have "
                    + String.valueOf(taskList.getSize()) + " tasks in the list.";
            storage.appendTask(newEvent);
        } catch (StringIndexOutOfBoundsException | ParseException | IOException e) {
            return "Error";
        }
        return message;
    }


    /**
     * Creates a new Deadline task.
     *
     * @param command string command from user.
     * @param priority the priority level of the task.
     * @param indexOfPriority the index of the priority level in the string.
     * @return the return message after running the command.
     */
    private String newDeadline(String command, Task.Priority priority, int indexOfPriority) {
        String message = null;
        String indentation = "    ";
        try {
            String description = command.substring(9, command.indexOf('/') - 1).trim();
            String date = command.substring((command.indexOf('/') + 4), indexOfPriority - 1).trim();
            Date dueDate = (Date) formatter.parse(date);
            Deadline newDeadline = new Deadline(description, dueDate, priority);
            taskList.addTask(newDeadline);
            message = "Got it. I've added this task:\n"
                    + indentation + "  " + newDeadline.toString()
                    + newDeadline.getStatus() + " " + newDeadline.getDescription() + "\n"
                    + indentation + "Now you have "
                    + String.valueOf(taskList.getSize() + " tasks in the list.");
            storage.appendTask(newDeadline);
        } catch (StringIndexOutOfBoundsException | ParseException | IOException e) {
            return "Error";
        }
        return message;
    }


}
