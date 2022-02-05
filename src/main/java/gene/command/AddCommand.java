package gene.command;

import gene.component.Storage;
import gene.component.TaskList;
import gene.component.Ui;
import gene.exception.BadDeadlineException;
import gene.exception.BadDescriptionException;
import gene.task.DeadlineTask;
import gene.task.EventTask;
import gene.task.Task;
import gene.task.TodoTask;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The add command class. This class encapsulates the different kinds of
 * add commands. These include add to do task, add event task and add deadline
 * task.
 *
 * @author Eugene Chia
 * @version 1.0
 * @since 2022-01-12
 */
public class AddCommand extends Command{
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
     * @param tasks the list of tasks
     * @param userInt the Ui class object
     * @param storage the storage class object
     */
    @Override
    public void execute(TaskList tasks, Ui userInt, Storage storage) {
        switch (this.taskType) {
        case "todo":
            addTodo(taskBody, userInt, storage, tasks);
            break;
        case "deadline":
            addDeadline(taskBody, userInt, storage, tasks);
            break;
        case "event":
            addEvent(taskBody, userInt, storage, tasks);
            break;
        }
    }

    /**
     * The method to distinguish this command from an exit command
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
     * @param storage Gene's storage
     * @param itemList Gene's item list
     */
    public void addTodo(String taskKey, Ui userInt, Storage storage, TaskList itemList) {
        storage.writeToFile(taskKey, "T", false);
        String[] tokens = taskKey.split("todo ");
        String taskTitle = "";
        try {
            if (tokens.length < 2) {
                throw new BadDescriptionException("todo"); //type
            } else {
                taskTitle = tokens[1];
            }
        } catch (BadDescriptionException err) {
            System.out.println(err.getMessage());
            return;
        }
        Task newTask = new TodoTask(taskTitle);
        itemList.add(newTask);

        userInt.print("----------------------------" +
                        "----------------------------\n" +
                        "Got it. I've added this task:\n"
                        + "  " + newTask + "\n"
                        + "Now you have " + itemList.size() + " tasks in the list."
                        + "\n"
                        + "--------------------------------------------------------\n"
        );
    }

    /**
     * The method that adds an event task
     * First, the task title and date time in String format is extracted.
     * The date time is formatted from d/MM/yyyy HHmm. A task is then created
     * added into Gene's list then into storage.
     *
     * @param taskKey un formatted task body
     * @param userInt Gene's Ui
     * @param storage Gene's storage
     * @param itemList Gene's item list
     */
    public void addEvent(String taskKey, Ui userInt, Storage storage, TaskList itemList) {
        String[] tokens = taskKey.split("event ");
        String taskTitle = "";
        try {
            if (tokens.length < 2) {
                throw new BadDescriptionException("event");

            } else {
                taskTitle = tokens[1];
            }
        } catch (BadDescriptionException err) {
            System.out.println(err.getMessage());
            return;
        }
        String[] secondSplit = taskTitle.split(" /at ");
        taskTitle = secondSplit[0];
        String deadline = "";
        try {
            if (secondSplit.length < 2) {
                throw new BadDeadlineException("event");
            } else {
                deadline = secondSplit[1];
            }
        } catch (BadDeadlineException err) {
            System.out.println(err.getMessage());
            return;
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
            Task newTask = new EventTask(taskTitle, LocalDateTime.parse(deadline, formatter));
            storage.writeToFile(taskKey, "E", false);
            itemList.add(newTask);
            userInt.print(
                    "----------------------------" +
                            "----------------------------\n" +
                            "Got it. I've added this task:\n"
                            + "  " + newTask + "\n"
                            + "Now you have " + itemList.size() + " tasks in the list."
                            + "\n"
                            + "--------------------------------------------------------\n"
            );
        } catch(DateTimeParseException err) {
            userInt.print(
                    "Bad date time format used, please use this date time format instead: "
                    + "d/MM/yyyy HHmm."
            );
        }
    }

    /**
     * The method that adds a deadline task
     * First, the task title and date time in String format is extracted.
     * The date time is formatted from d/MM/yyyy HHmm. A task is then created
     * added into Gene's list then into storage.
     *
     * @param taskKey un formatted task body
     * @param userInt Gene's Ui
     * @param storage Gene's storage
     * @param itemList Gene's item list
     */
    public void addDeadline(String taskKey, Ui userInt, Storage storage, TaskList itemList) {
        String[] tokens = taskKey.split("deadline ");
        String taskTitle = "";
        try {
            if (tokens.length < 2) {
                throw new BadDescriptionException("deadline");
            } else {
                taskTitle = tokens[1];
            }
        } catch (BadDescriptionException err) {
            System.out.println(err.getMessage());
            return;
        }
        String[] secondSplit = taskTitle.split(" /by ");
        taskTitle = secondSplit[0];
        String deadline = "";
        try {
            if (secondSplit.length < 2) {
                throw new BadDeadlineException("deadline");
            } else {
                deadline = secondSplit[1];
            }
        } catch (BadDeadlineException err) {
            System.out.println(err.getMessage());
            return;
        }

        try {
            //Accept string
            //Parse string to datetime, use formatter here to change reading style
            //when printing, check date time and print format.
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
            Task newTask = new DeadlineTask(taskTitle, LocalDateTime.parse(deadline, formatter));
            storage.writeToFile(taskKey, "D", false);
            itemList.add(newTask);
            userInt.print(
                    "----------------------------" +
                            "----------------------------\n" +
                            "Got it. I've added this task:\n"
                            + "  " + newTask + "\n"
                            + "Now you have " + itemList.size() + " tasks in the list."
                            + "\n"
                            + "--------------------------------------------------------\n"
            );
        } catch(DateTimeParseException err) {
            System.out.println(err.getMessage());
        }
    }
}
