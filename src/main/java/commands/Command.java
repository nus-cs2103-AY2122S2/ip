package commands;

import java.util.List;
import javafx.application.Platform;

import duke.DukeException;
import mytasks.Task;
import mytasks.TaskList;


/**
 * Initializes the user interface that interacts with the user.
 */
public class Command {
    private StringBuilder sb;

    /**
     * Initializes the user interface.
     */
    public Command() {
        this.sb = new StringBuilder();
    }

    /**
     * Indicate to the user that their command has been successfully executed or if there is any error along the way.
     */
    public String allTasks(String inputText, TaskList tasks) {
        List<Task> list = tasks.getList();
        int taskCount = list.size();
        String[] tempList = inputText.split(" ", 2);

        assert !(taskCount < 0);
        try {
            new DukeException().invalidChecker(tempList, taskCount);
        } catch (DukeException e) {
            return sb.append(e).toString();
        }

        switch (tempList[0]) {
        case "bye":
            sb.append("Bye. Hope to see you again soon!\n");
            Platform.exit();
            break;
        case "list":
            sb.append(ShowTasks.showList(taskCount, list));
            break;
        case "find":
            sb.append(ShowTasks.find(taskCount, tempList[1], list));
            break;
        case "todo":
            sb.append(CreateTask.createTodo(tempList[1], list, taskCount));
            printAndSave(taskCount + 1);
            break;
        case "deadline":
            sb.append(CreateTask.createDeadline(tempList[1], list, taskCount));
            printAndSave(taskCount + 1);
            break;
        case "event":
            sb.append(CreateTask.createEvent(tempList[1], list, taskCount));
            printAndSave(taskCount + 1);
            break;
        case "mark":
            sb.append(MarkTask.mark(tempList[1], list));
            break;
        case "unmark":
            sb.append(MarkTask.unmark(tempList[1], list));
            break;
        case "delete":
            sb.append(DeleteTask.delete(tempList[1], list));
            printAndSave(taskCount - 1);
            break;
        default:
        }
        return sb.toString();
    }

    /**
     * Shows the number of task being track.
     */
    public StringBuilder printAndSave(int taskCount) {
        if (taskCount + 1 == 1) {
            return sb.append("You have " + (taskCount) + " task in the list.\n");
        } else {
            return sb.append("You have " + (taskCount) + " tasks in the list.\n");
        }
    }
}
