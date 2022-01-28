import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TaskCommand extends Command {

    enum TaskType {
        todo, deadline, event
    }

    TaskType taskType;
    String command; 
    
    TaskCommand (TaskType taskType, String command) {
        this.taskType = taskType;
        this.command = command; 
    }

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
                        indentation + "  " + newToDo.toString() + newToDo.getStatus() + " " + newToDo.getDescription() + "\n" +
                        indentation + "Now you have " + String.valueOf(taskList.getSize()) + " tasks in the list.";
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
                        indentation + "  " + newEvent.toString() + newEvent.getStatus() + " " + newEvent.getDescription() + "\n" +
                        indentation + "Now you have " + String.valueOf(taskList.getSize()) + " tasks in the list.";
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
                        indentation + "  " + newDeadline.toString() + newDeadline.getStatus() + " " + newDeadline.getDescription() + "\n" +
                        indentation + "Now you have " + String.valueOf(taskList.getSize() + " tasks in the list.");
                storage.appendTask(newDeadline);
            } catch (StringIndexOutOfBoundsException | ParseException e) {
                ui.showWrongFormat("Deadline");
                return;
            }
        }
        
        ui.outputMessage(message);
    }
}
