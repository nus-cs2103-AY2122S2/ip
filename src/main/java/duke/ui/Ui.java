package duke.ui;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.List;

/**
 * UI for handling the messages returning to a user
 */
public class Ui {
    private Ui() {}

    public static String printAddTask(Task task, int numOfTask) {
        String message = "I've added the following task:\n"
                + "   " + task + '\n' + "Currently there are " + numOfTask
                + " tasks ";
        duke.utils.HelperFunction.outputLimit(message);
        return message;
    }

    public static String printDelete(Task task, int numOfTask) {
        String message = "I have removed the following task:\n" + "   "
                + task + "\n" + "Currently there are " + numOfTask + " duke.tasks ";
        duke.utils.HelperFunction.outputLimit(message);
        return message;
    }

    public static String printNoTaskReminder() {
        String message = "Currently you don't have any task! \n"
                + "Use \"todo/deadline/event\" to add task or \"help\" for assistance";
        duke.utils.HelperFunction.outputLimit(message);
        return message;
    }

    public static String printDoneTask(Task task) {
        String message = "I have marked the following task " + task.getTask() + " as done\n"
                + task + " Well done!";
        duke.utils.HelperFunction.outputLimit(message);
        return message;
    }

    public static String printAllTask(List<Task> tasks, boolean status) throws DukeException {
        String res = "";
        for (int i = 1; i < tasks.size() + 1; i++) {
            res += (status ? (i + ". ") : ("   ")) + tasks.get(i - 1) + "\n";
        }
        duke.utils.HelperFunction.outputLimit(res);
        if (tasks.isEmpty()) {
            throw new DukeException("Cannot find the task! ");
        }
        return res;
    }

    public static String printBye() {
        String message = "Alright bye bye!!";
        System.out.println(message);
        return message;
    }

    public static String printListCommands() {
        String commandList =
                "You can use the following commands for Duke \n"
                 + "\n1, Todo  \"task name\" : \n Create a todo task \n"
                + "\n2, Deadline  \"task name\"  /\"date\": \n Create a deadline task \n"
                + "\n3, Event  \"task name\"  / \"date\" ~ \"date\": \n Create an event task \n"
                + "\n4, Find  \"task index\": \n Find task by index from the list \n"
                + "\n5, Delete  \"task index\": \n Delete task by index from the list \n"
                + "\n6, List: \n List out all existing tasks \n"
                + "\n7, Help: \n List out all commands for Duke \n"
                + "\n8, Done  \"task index\":  \n Mark the task by index as done. \n"
                + "\n9, Bye: \n Close application ";
        return commandList;
    }

}