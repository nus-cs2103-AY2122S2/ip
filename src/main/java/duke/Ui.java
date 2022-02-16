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
                + "list-task\n"
                + "list-tag\n"
                + "todo {task description}\n"
                + "deadline {task description} /by {DATE}\n"
                + "event {task description} /at {DATE}\n"
                + "mark {Task ID}\n"
                + "unmark {Task ID}\n"
                + "find {keyword}\n"
                + "delete {Task ID}\n"
                + "add-tag {tag description}\n"
                + "tag {Task ID} {Tag ID}\n"
                + "bye";
        return str;
    }

    /**
     * Adds tags into our current tag list, save
     * the tag into a file and return a duke comment.
     *
     * @param list a list containing the current tags.
     * @param desc the tag description entered by the user.
     * @return a duke comment.
     * @throws IOException if there's error arising from I/O methods.
     */
    public static String addTag(TagList list, String desc) throws IOException {
        String dukeComment = "";
        if (desc == null) {
            dukeComment += "Please include tag description in your command.";
        } else {
            Tag newTag = new Tag(desc);
            list.addTag(newTag);
            dukeComment = dukeComment + "Got it. I've added this tag:\n"
                    + newTag + "\n"
                    + "Now you have " + list.getSize() + " tags in the list.";
        }
        TagStorage.writeToFile(list);
        return dukeComment;
    }

    /**
     * Adds tags into our current tag list, save
     * the tag into a file and return a duke comment.
     *
     * @param taskList a list containing the current tasks.
     * @param tagList a list containing the current tags.
     * @param taskIndex the position of the task in the list.
     * @param tagIndex the position of the tag in the list.
     * @return a duke comment.
     * @throws IOException if there's error arising from I/O methods.
     */
    public static String tagTask(TaskList taskList, TagList tagList,
                                 int taskIndex, int tagIndex) throws IOException {
        String dukeComment = "";
        if (tagIndex >= tagList.getSize()) {
            dukeComment += "I'm sorry, can't find tag";
        } else if (taskIndex >= taskList.getSize()) {
            dukeComment += "I'm sorry, can't find task";
        } else {
            Tag tag = tagList.getIndex(tagIndex);
            Task task = taskList.getIndex(taskIndex);
            task.tagTask(tag);
            dukeComment = dukeComment + "Nice! I've tagged this task\n"
                    + task;
        }
        TaskStorage.writeToFile(taskList, tagList);
        return dukeComment;
    }

    /**
     * Changes the completion status of the task, save
     * the task into a file and a duke comment.
     *
     * @param taskList a list containing the current task.
     * @param tagList a list containing all tags.
     * @param index position of the task in the list.
     * @return a duke comment.
     * @throws IOException if there's error arising from I/O methods.
     */
    public static String markTask(TaskList taskList,
                                  TagList tagList, int index) throws IOException {
        String dukeComment = "";
        if (index >= taskList.getSize()) {
            dukeComment += "I'm sorry, can't find task";
        } else {
            Task task = taskList.getIndex(index);
            task.setAsDone();
            dukeComment = dukeComment + "Nice! I've marked this task as done\n"
                    + task;
        }
        TaskStorage.writeToFile(taskList, tagList);
        return dukeComment;
    }

    /**
     * Changes the completion status of the task, save
     * the task into a file and a duke comment.
     *
     * @param taskList a list containing the current task.
     * @param tagList a list containing all tags.
     * @return a duke comment.
     * @throws IOException if there's error arising from I/O methods.
     */
    public static String unmarkTask(TaskList taskList,
                                    TagList tagList, int index) throws IOException {
        String dukeComment = "";
        if (index >= taskList.getSize()) {
            dukeComment += "I'm sorry, can't find task";
        } else {
            Task task = taskList.getIndex(index);
            task.setAsNotDone();
            dukeComment = dukeComment + "Okay! I've marked this task as not done\n"
                    + task;
        }
        TaskStorage.writeToFile(taskList, tagList);
        return dukeComment;
    }
    /**
     * Returns a duke comment containing the current list
     * of tasks.
     *
     * @param list a list containing the current tasks.
     * @return a duke comment.
     */
    public static String listTag(TagList list) {
        String dukeComment = "";
        int num = 1;
        dukeComment += "Here are the tags in your list:\n";
        for (Tag tag: list.getList()) {
            dukeComment += num + ". " + tag + "\n";
            num++;
        }
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
     * @param taskList a list containing the current tasks.
     * @param tagList a list containing all tags.
     * @param desc the task description entered by the user.
     * @param isDone the completion status of the task.
     * @return a duke comment.
     * @throws IOException if there's error arising from I/O methods.
     */
    public static String addToDo(TaskList taskList, TagList tagList,
                                 String desc, boolean isDone) throws IOException {
        String dukeComment = "";
        if (desc == null) {
            dukeComment += "Please include task description in your command.";
        } else {
            Task newTask = new ToDo(desc, isDone);
            taskList.addTask(newTask);
            dukeComment = dukeComment + "Got it. I've added this task:\n"
                    + newTask + "\n"
                    + "Now you have " + taskList.getSize() + " tasks in the list.";
        }
        TaskStorage.writeToFile(taskList, tagList);
        return dukeComment;
    }

    /**
     * Adds deadline task into our current list, save
     * the task into a file and return a duke comment.
     *
     * @param taskList a list containing the current tasks.
     * @param tagList a list containing all tags.
     * @param desc the task description entered by the user.
     * @param date the date entered by the user.
     * @param isDone the current status of the task.
     * @return a string indicating that the task is added.
     * @throws IOException if there's error arising from I/O methods.
     */
    public static String addDeadline(TaskList taskList, TagList tagList,
                                     String desc, String date, boolean isDone) throws IOException {
        String dukeComment = "";
        if (desc == null || date == null) {
            return "Missing parameter!";
        }
        try {
            Task newTask = new Deadline(desc, date, isDone);
            taskList.addTask(newTask);
            dukeComment = dukeComment + "Got it. I've added this task:\n"
                    + newTask + "\n"
                    + "Now you have " + taskList.getSize() + " tasks in the list.";
        } catch (DukeException ex) {
            return ex.getMessage();
        }
        TaskStorage.writeToFile(taskList, tagList);
        return dukeComment;
    }

    /**
     * Adds event task into our current list, save
     * the task into a file and return a duke comment.
     *
     * @param taskList a list containing the current tasks.
     * @param tagList a list containing all tags.
     * @param desc the task description entered by the user.
     * @param date the date entered by the user.
     * @param isDone the current status of the task.
     * @return a string indicating that the task is added.
     * @throws IOException if there's error arising from I/O methods.
     */
    public static String addEvent(TaskList taskList, TagList tagList,
                                  String desc, String date, boolean isDone) throws IOException {
        String dukeComment = "";
        if (desc == null || date == null) {
            return "Missing parameter!";
        }
        try {
            Task newTask = new Event(desc, date, isDone);
            taskList.addTask(newTask);
            dukeComment = dukeComment + "Got it. I've added this task:\n"
                    + newTask + "\n"
                    + "Now you have " + taskList.getSize() + " tasks in the list.";
        } catch (DukeException ex) {
            return ex.getMessage();
        }
        TaskStorage.writeToFile(taskList, tagList);
        return dukeComment;
    }

    /**
     * Deletes task from the list, saves the current list
     * and returns a duke comment.
     *
     * @param taskList a list containing the current tasks.
     * @param tagList a list containing all tags.
     * @param index the position of the task.
     * @return a duke comment.
     * @throws IOException if there's error arising from I/O methods.
     */
    public static String deleteTask(TaskList taskList, TagList tagList,
                                    int index) throws IOException {
        String dukeComment = "";
        if (index >= taskList.getSize()) {
            dukeComment += "I'm sorry, can't find task";
        } else {
            Task task = taskList.deleteTask(index);
            dukeComment = dukeComment + "Noted. I've removed this task:\n"
                    + task + "\n"
                    + "Now you have " + taskList.getSize() + " tasks in the list";
        }
        TaskStorage.writeToFile(taskList, tagList);
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
     * @param taskList a list containing the current tasks.
     * @param tagList a list containing all tags.
     * @return a duke comment.
     * @throws IOException if there's error arising from I/O methods.
     */
    public static String endProgram(TaskList taskList, TagList tagList) throws IOException {
        String dukeComment = "Bye. See you again!";
        TaskStorage.writeToFile(taskList, tagList);
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
