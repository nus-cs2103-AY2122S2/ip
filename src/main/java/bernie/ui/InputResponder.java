package bernie.ui;

import bernie.tasks.Task;
import bernie.tasks.TaskList;

/**
 * Class to deal with any interactions with the user. It is responsible for printing out
 * relevant messages to the user.
 */
public class InputResponder {
    /**
     * Returns the message when user starts the program
     * @return String, the message
     */
    public String greet() {
        return "Hello, I'm Bernie your task manager!\n" +
                "In case you forgot, here's how to create tasks:\n" +
                "For tasks to do: todo <description>\n" +
                "For deadlines: deadline <description> /by yyyy-mm-dd\n" +
                "For events: event <description> /at <time>\n" +
                "To mark/unmark a task: mark/unmark <taskNumber>\n" +
                "To delete a task: delete <taskNumber>\n" +
                "To find a task: find <description>\n" +
                "To get deadlines not done, type: remind\n" +
                "To exit, type: bye";
    }

    /**
     * Returns the message when user exits out of the program
     * @return String, the message
     */
    public String showLeaveMsg() {
        return "See ya!";
    }

    /**
     * Returns the error message when faced with any errors
     * @param msg String, the error message
     * @return String, the error message
     */
    public String showErrorMsg(String msg) {
        return msg;
    }

    /**
     * Returns the message whenever the user adds a new task: consists of the number
     * of tasks left and the tasks description
     * @param newTask Task, newTask added by the user
     * @param numTasksLeft int, the number of tasks left not done
     * @return String, the message
     */
    public String showAddedMsg(Task newTask, int numTasksLeft) {
        StringBuilder s = new StringBuilder();
        return s.append(String.format("Got ya. Added:\n%s\nYou got %d tasks waiting to be done ya!\n",
                newTask, numTasksLeft))
                .toString();
    }

    /**
     * Returns the message whenever the user deletes a task
     * @param deletedTask Task, task deleted by the user
     * @param numTasksLeft int, the number of tasks left not done
     * @return String message
     */
    public String showDeleteMsg(Task deletedTask, int numTasksLeft) {
        StringBuilder s = new StringBuilder();
        return s.append(String.format("Got ya. Removed:\n%s\nYou got %d tasks waiting to be done ya!\n",
                deletedTask, numTasksLeft))
                .toString();
    }

    /**
     * Returns the current taskList when the user inputs list into the program
     * @param tasks TaskList, empty if no tasks, length of 1 tasks if tasks contains tasks
     * @return String, the message of the tasks containing in the task list currently
     */
    public String showListTasksMsg(TaskList... tasks) {
        StringBuilder s = new StringBuilder();
        String startingMsg = "Here's your current list buddy:\n";
        s.append(startingMsg);
        if (tasks.length == 0) {
            String noTasksMsg = "NOTHING! :D";
            s.append(noTasksMsg);
            return s.toString();
        } else {
            return s.append(tasks[0].listTasks())
                    .toString();
        }
    }

    /**
     * Returns the message when a user marks a task number
     * @param markedTask Task that is marked done
     * @return String, the message
     */
    public String showDoneMsg(Task markedTask) {
        StringBuilder s = new StringBuilder();
        return s.append(String.format("This is now done:\n%s\n", markedTask))
                .toString();
    }

    /**
     * Returns the message when a user unmarks a task number
     * @param unmarkedTask Task that is marked not done
     * @return String, the message
     */
    public String showUndoneMsg(Task unmarkedTask) {
        StringBuilder s = new StringBuilder();
        return s.append(String.format("This is now undone:\n%s\n", unmarkedTask))
                .toString();
    }

    /**
     * Returns the message for matching tasks in the list
     * @param tasks TaskList, the current tasklist
     * @param description String, the description which we want to find within the tasks
     * @return String, the message
     */
    public String showFoundTasksMsg(TaskList tasks, String description) {
        StringBuilder s = new StringBuilder();
        return s.append("We found these tasks in your list:\n")
                .append(tasks.findTasks(description))
                .toString();
    }

    /**
     * Returns the output message for the list of deadlines that have yet to be done.
     * This is aided by TaskList to help to filter out tasks that are Deadline and
     * not done.
     * @param tasks TaskList
     * @return String, the output message
     */
    public String showDeadlinesMsg(TaskList tasks) {
        StringBuilder s = new StringBuilder();
        String startingMsg = "Got your back! Your deadlines to meet:\n";
        s.append(startingMsg);
        return s.append(tasks.listDeadlines()).toString();
    }
}
