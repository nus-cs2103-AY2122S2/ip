package duke;

import java.util.List;

import commands.CreateTask;
import commands.DeleteTask;
import commands.MarkTask;
import commands.ShowTasks;
import mytasks.Task;
import mytasks.TaskList;

/**
 * Initializes the user interface that interacts with the user.
 */
public class Ui {
    /**
     * Initializes the user interface.
     */
    public Ui() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "\nHello! I'm Duke\nWhat can i do for you?");
    }

    /**
     * Indicate to the user that their command has been successfully executed or if there is any error along the way.
     */
    public void allTasks(String inputText, TaskList tasks) {
        List<Task> list = tasks.getList();
        int taskCount = list.size();
        String[] tempList = inputText.split(" ", 2);

        try {
            new DukeException().invalidChecker(tempList, taskCount);
        } catch (DukeException e) {
            System.err.println(e);
            return;
        }

        System.out.println("------------------------------------------------------------");
        switch (tempList[0]) {
        case "bye":
            System.out.println("Bye. Hope to see you again soon!");
            break;
        case "list":
            ShowTasks.showList(taskCount, list);
            break;
        case "find":
            ShowTasks.find(taskCount, tempList[1], list);
            break;
        case "todo":
            CreateTask.todo(tempList[1], list, taskCount);
            break;
        case "deadline":
            CreateTask.deadline(tempList[1], list, taskCount);
            break;
        case "event":
            CreateTask.event(tempList[1], list, taskCount);
            break;
        case "mark":
            MarkTask.mark(tempList[1], list, taskCount);
            break;
        case "unmark":
            MarkTask.unmark(tempList[1], list, taskCount);
            break;
        case "delete":
            DeleteTask.delete(tempList[1], list, taskCount);
            break;
        default:
        }
        System.out.println("------------------------------------------------------------");
    }

    /**
     * Shows the number of task being track.
     */
    public static void printAndSave(int taskCount) {
        if (taskCount + 1 == 1) {
            System.out.println("You have " + (taskCount) + " task in the list.");
        } else {
            System.out.println("You have " + (taskCount) + " tasks in the list.");
        }
    }
}
