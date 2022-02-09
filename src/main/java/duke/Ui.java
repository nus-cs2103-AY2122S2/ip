package duke;

import java.io.IOException;

import javafx.application.Platform;

/**
 * This class deals with interactions from the user.
 * @author Sim Jun Heng.
 * @version CS2103T AY21/22 Sem 2.
 */
public class Ui {

    /**
     *  Returns a list of helpful commands.
     */
    public static String getHelp() {
        String str = "";
        str = str + "List of Available Commands\n"
                + "list\n"
                + "todo {task description}\n"
                + "deadline {task description} /by {DATE}\n"
                + "event {task description} /at {DATE}\n"
                + "mark {Task ID}\n"
                + "find {keyword}\n"
                + "delete {Task ID}\n"
                + "bye";
        return str;
    }

    /**
     * Changes the completion status of the task, save
     * the task into a file and a duke comment.
     *
     * @param list a list containing the current task.
     * @param index position of the task in the list.
     * @return a duke comment.
     * @throws IOException if there's error arising from I/O methods.
     */
    public static String markTask(TaskList list, int index) throws IOException {
        String dukeComment = "";
        if (index >= list.getSize()) {
            dukeComment += "I'm sorry, can't find task";
        } else {
            Task task = list.getIndex(index);
            task.setAsDone();
            dukeComment = dukeComment + "Nice! I've marked this task as done\n"
                    + task;
        }
        Storage.writeToFile(list);
        return dukeComment;
    }

    /**
     * Changes the completion status of the task, save
     * the task into a file and a duke comment.
     *
     * @param list a list containing the current tasks.
     * @param index position of the task in the list.
     * @return a duke comment.
     * @throws IOException if there's error arising from I/O methods.
     */
    public static String unmarkTask(TaskList list, int index) throws IOException {
        String dukeComment = "";
        if (index >= list.getSize()) {
            dukeComment += "I'm sorry, can't find task";
        } else {
            Task task = list.getIndex(index);
            task.setAsNotDone();
            dukeComment = dukeComment + "Okay! I've marked this task as not done\n"
                    + task;
        }
        Storage.writeToFile(list);
        return dukeComment;
    }

    /**
     * Returns a duke comment containing the current list
     * of tasks.
     *
     * @param list a list containing the current tasks.
     * @return a duke comment.
     */
    public static String listTask(TaskList list) {
        String dukeComment = "";
        int num = 1;
        dukeComment += "Here are the tasks in your list:\n";
        for (Task task: list.getList()) {
            dukeComment += num + ". " + task + "\n";
            num++;
        }
        return dukeComment;
    }

    /**
     * Adds to do task into our current list , save
     * the task into a file and a duke comment.
     *
     * @param list a list containing the current tasks.
     * @param desc the task description entered by the user.
     * @param isDone the completion status of the task.
     * @return a duke comment.
     * @throws IOException if there's error arising from I/O methods.
     */
    public static String addToDo(TaskList list, String desc, boolean isDone) throws IOException {
        String dukeComment = "";
        if (desc == null) {
            dukeComment += "Please include task description in your command.";
        } else {
            Task newTask = new ToDo(desc, isDone);
            list.addTask(newTask);
            dukeComment = dukeComment + "Got it. I've added this task:\n"
                    + newTask + "\n"
                    + "Now you have " + list.getSize() + " tasks in the list.";
        }
        Storage.writeToFile(list);
        return dukeComment;
    }

    /**
     * Adds deadline task into our current list, save
     * the task into a file and a duke comment.
     *
     * @param list a list containing the current tasks.
     * @param desc the task description entered by the user.
     * @param date the date entered by the user.
     * @param isDone the current status of the task.
     * @return a string indicating that the task is added.
     * @throws IOException if there's error arising from I/O methods.
     */
    public static String addDeadline(TaskList list, String desc, String date, boolean isDone)
            throws IOException {
        String dukeComment = "";
        if (desc == null || date == null) {
            return "Missing parameter!";
        }
        try {
            Task newTask = new Deadline(desc, date, isDone);
            list.addTask(newTask);
            dukeComment = dukeComment + "Got it. I've added this task:\n"
                    + newTask + "\n"
                    + "Now you have " + list.getSize() + " tasks in the list.";
        } catch (DukeException ex) {
            return ex.getMessage();
        }
        Storage.writeToFile(list);
        return dukeComment;
    }

    /**
     * Adds event task into our current list, save
     * the task into a file and a duke comment.
     *
     * @param list a list containing the current tasks.
     * @param desc the task description entered by the user.
     * @param date the date entered by the user.
     * @param isDone the current status of the task.
     * @return a string indicating that the task is added.
     * @throws IOException if there's error arising from I/O methods.
     */
    public static String addEvent(TaskList list, String desc, String date, boolean isDone)
            throws IOException {
        String dukeComment = "";
        if (desc == null || date == null) {
            return "Missing parameter!";
        }
        try {
            Task newTask = new Event(desc, date, isDone);
            list.addTask(newTask);
            dukeComment = dukeComment + "Got it. I've added this task:\n"
                    + newTask + "\n"
                    + "Now you have " + list.getSize() + " tasks in the list.";
        } catch (DukeException ex) {
            return ex.getMessage();
        }
        Storage.writeToFile(list);
        return dukeComment;
    }

    /**
     * Deletes task from the list, saves the current list
     * and returns a duke comment.
     *
     * @param list a list containing the current tasks.
     * @param index the position of the task.
     * @return a duke comment.
     * @throws IOException if there's error arising from I/O methods.
     */
    public static String deleteTask(TaskList list, int index) throws IOException {
        String dukeComment = "";
        if (index >= list.getSize()) {
            dukeComment += "I'm sorry, can't find task";
        } else {
            Task task = list.deleteTask(index);
            dukeComment = dukeComment + "Noted. I've removed this task:\n"
                    + task + "\n"
                    + "Now you have " + list.getSize() + " tasks in the list";
        }
        Storage.writeToFile(list);
        return dukeComment;
    }

    /**
     * Returns a list of tasks with a keyword specified by the user.
     *
     * @param list a list containing the current tasks.
     * @param desc the keyword entered by the user.
     * @return a duke comment.
     */
    public static String findTask(TaskList list, String desc) {
        String dukeComment = "Here are the matching tasks in your list:\n";
        int num = 1;
        int matchNum = 0;
        for (Task task : list.getList()) {
            String temp = task.getDesc();
            if (desc == null) {
                break;
            } else if (temp.contains(desc)) {
                dukeComment += num + ". " + task + "\n";
                matchNum++;
            }
            num++;
        }
        dukeComment += "Match Found: " + matchNum;
        return dukeComment;
    }

    /**
     * Ends program and returns a duke comment.
     *
     * @param list a list containing the current tasks.
     * @return a duke comment.
     * @throws IOException if there's error arising from I/O methods.
     */
    public static String endProgram(TaskList list) throws IOException {
        String dukeComment = "Bye. See you again!";
        Storage.writeToFile(list);
        Platform.exit();
        return dukeComment;
    }

    /**
     * Returns an error message.
     *
     * @return a duke comment.
     */
    public static String getErrorMsg() {
        String dukeComment = "The command doesn't exist. Please try again.";
        return dukeComment;
    }
}
