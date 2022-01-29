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
        todo, deadline, event
    }

    TaskType taskType;
    String command;

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
     * @throws IOException when there is an error opening the file.
     * @throws ParseException when there is an error in recognising the text.
     */
    @Override
    void runCommand(TaskList taskList, Ui ui, Storage storage) throws IOException, ParseException {
        String indentation = "    ";
        DateFormat formatter = new SimpleDateFormat("dd/MM/yy h:mm a");
        String message = null; 
        
        if (taskType == TaskType.todo) {
            try {
                String newString = command.substring(5).trim();


                if (newString.length() == 0) {
                    throw new StringIndexOutOfBoundsException();
                }
                ToDos newToDo = new ToDos(newString);
                taskList.addTask(newToDo);
                message = "Got it. I've added this task:\n" +
                        indentation + "  " + newToDo.toString() +
                        newToDo.getStatus() + " " + newToDo.getDescription() + "\n" +
                        indentation + "Now you have " +
                        String.valueOf(taskList.getSize()) + " tasks in the list.";
                storage.appendTask(newToDo);
            } catch (StringIndexOutOfBoundsException e) {
                ui.showWrongFormat("ToDo");
                return;
            }
        } else if (taskType == TaskType.event) {
            try {
                String description = command.substring(6, command.indexOf('/') - 1).trim();
                String date = command.substring((command.indexOf('/') + 4)).trim();
                Date dueDate = (Date) formatter.parse(date);
                Event newEvent = new Event(description, dueDate);
                taskList.addTask(newEvent);
                message = "Got it. I've added this task:\n" +
                        indentation + "  " + newEvent.toString() +
                        newEvent.getStatus() + " " + newEvent.getDescription() + "\n" +
                        indentation + "Now you have " +
                        String.valueOf(taskList.getSize()) + " tasks in the list.";
                storage.appendTask(newEvent);
            } catch (StringIndexOutOfBoundsException | ParseException e) {
                ui.showWrongFormat("Event");
                return;
            }
        } else if (taskType == TaskType.deadline) {
            try {
                String description = command.substring(9, command.indexOf('/') - 1).trim();
                String date = command.substring((command.indexOf('/') + 4)).trim();
                Date dueDate = (Date) formatter.parse(date);
                Deadline newDeadline = new Deadline(description, dueDate);
                taskList.addTask(newDeadline);
                message = "Got it. I've added this task:\n" +
                        indentation + "  " + newDeadline.toString() +
                        newDeadline.getStatus() + " " + newDeadline.getDescription() + "\n" +
                        indentation + "Now you have " +
                        String.valueOf(taskList.getSize() + " tasks in the list.");
                storage.appendTask(newDeadline);
            } catch (StringIndexOutOfBoundsException | ParseException e) {
                ui.showWrongFormat("Deadline");
                return;
            }
        }

        ui.outputMessage(message);
    }
}
