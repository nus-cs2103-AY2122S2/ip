package gene.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import gene.component.*;
import gene.exception.BadDeadlineException;
import gene.exception.BadDescriptionException;
import gene.task.DeadlineTask;
import gene.task.EventTask;
import gene.task.Task;
import gene.task.TodoTask;



/**
 * The add command class. This class encapsulates the different kinds of
 * add commands. These include add to do task, add event task and add deadline
 * task.
 *
 * @author Eugene Chia
 * @version 1.0
 * @since 2022-01-12
 */
public class AddCommand extends Command {
    private final String taskBody;
    private final String taskType;

    /**
     * The add command class constructor
     * @param taskBody the body text from the user input to be parsed
     * @param taskType the type of task added
     */
    public AddCommand(String taskBody, String taskType) {
        this.taskBody = taskBody;
        this.taskType = taskType;
    }

    /**
     * The execute method for the add command, which holds all the instructions
     * for when this command is to be executed.
     *
     * @param geneTasks the list of tasks
     * @param geneUi the Ui class task object
     * @param geneTaskStorage the tastorage class object
     * @param geneLocs the list of locations
     * @param geneLocationStorage the location storage class object
     */
    @Override
    public String execute(TaskList geneTasks, Ui geneUi, TaskStorage geneTaskStorage, LocationList geneLocs, LocationStorage geneLocationStorage) {
        switch (this.taskType) {
        case "todo":
            return addTodo(taskBody, geneUi, geneTaskStorage, geneTasks);
        case "deadline":
            return addDeadline(taskBody, geneUi, geneTaskStorage, geneTasks);
        case "event":
            return addEvent(taskBody, geneUi, geneTaskStorage, geneTasks);
        default:
            return "Add command mismatch";
        }
    }

    /**
     * The method to distinguish this command from an exit command.
     *
     * @return must return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * The method that adds a to do task
     * The task is first added to storage
     * Then, the task key is formatted to create a task object for display
     * and added into list
     *
     * @param taskKey un formatted task body
     * @param userInt Gene's Ui
     * @param taskStorage Gene's storage
     * @param itemList Gene's item list
     */
    public String addTodo(String taskKey, Ui userInt, TaskStorage taskStorage, TaskList itemList) {
        taskStorage.writeToFile(taskKey, "T", false);
        String taskTitle = "";
        try {
            taskTitle = descriptionSplit("todo", taskKey);
        } catch (BadDescriptionException e) {
            e.printStackTrace();
        }
        Task newTask = new TodoTask(taskTitle);
        itemList.add(newTask);

        return Ui.showLine() + "\n"
                + "Got it. I've added this task:\n"
                        + "  " + newTask + "\n"
                        + "Now you have " + itemList.size()
                + " tasks in the list."
                        + "\n"
                        + Ui.showLine();
    }

    /**
     * The method that adds an event task
     * First, the task title and date time in String format is extracted.
     * The date time is formatted from d/MM/yyyy HHmm. A task is then created
     * added into Gene's list then into storage.
     *
     * @param taskKey un formatted task body
     * @param userInt Gene's Ui
     * @param taskStorage Gene's storage
     * @param itemList Gene's item list
     */
    public String addEvent(String taskKey, Ui userInt, TaskStorage taskStorage, TaskList itemList) {
        String taskTitle = "";
        try {
            taskTitle = descriptionSplit("event", taskKey);
        } catch (BadDescriptionException e) {
            e.printStackTrace();
        }
        String deadline = "";
        try {
            String[] combinedTask = deadlineSplit(" /at ", taskTitle, "event");
            taskTitle = combinedTask[0];
            deadline = combinedTask[1];
        } catch (BadDeadlineException err) {
            err.printStackTrace();
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
            Task newTask = new EventTask(taskTitle, LocalDateTime.parse(deadline, formatter));
            taskStorage.writeToFile(taskKey, "E", false);
            itemList.add(newTask);
            return Ui.showLine() + "\n"
                    + "Got it. I've added this task:\n"
                            + "  " + newTask + "\n"
                            + "Now you have " + itemList.size() + " tasks in the list."
                            + "\n"
                            + Ui.showLine();
        } catch (DateTimeParseException err) {
            return
                    "Bad date time format used, please use this date time format instead: "
                    + "d/MM/yyyy HHmm.";
        }
    }

    private String[] deadlineSplit(String s, String taskTitle, String taskType) throws BadDeadlineException {
        String[] secondSplit = taskTitle.split(s);
        if (secondSplit.length < 2) {
            throw new BadDeadlineException(taskType);
        }
        return secondSplit;
    }

    private String descriptionSplit(String keySplit, String taskKey) throws BadDescriptionException {
        String[] tokens = taskKey.split(keySplit);
        if (tokens.length < 2) {
            throw new BadDescriptionException(keySplit);
        }
        return tokens[1];
    }

    /**
     * The method that adds a deadline task
     * First, the task title and date time in String format is extracted.
     * The date time is formatted from d/MM/yyyy HHmm. A task is then created
     * added into Gene's list then into storage.
     *
     * @param taskKey un formatted task body
     * @param userInt Gene's Ui
     * @param taskStorage Gene's storage
     * @param itemList Gene's item list
     */
    public String addDeadline(String taskKey, Ui userInt, TaskStorage taskStorage, TaskList itemList) {
        String[] tokens = taskKey.split("deadline ");
        String taskTitle = "";
        try {
            taskTitle = descriptionSplit("deadline", taskKey);
        } catch (BadDescriptionException e) {
            e.printStackTrace();
        }
        String deadline = "";
        try {
            String[] combinedTask = deadlineSplit(" /by ", taskTitle, "deadline");
            taskTitle = combinedTask[0];
            deadline = combinedTask[1];
        } catch (BadDeadlineException err) {
            err.printStackTrace();
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
            Task newTask = new DeadlineTask(taskTitle, LocalDateTime.parse(deadline, formatter));
            taskStorage.writeToFile(taskKey, "D", false);
            itemList.add(newTask);
            return Ui.showLine() + "\n"
                    + "Got it. I've added this task:\n"
                            + "  " + newTask + "\n"
                            + "Now you have " + itemList.size() + " tasks in the list."
                            + "\n"
                            + Ui.showLine();
        } catch (DateTimeParseException err) {
            return err.getMessage();
        }
    }
}
