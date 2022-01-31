package chatcat.tasklist;

import java.util.ArrayList;

import chatcat.tasks.Deadline;
import chatcat.tasks.Event;
import chatcat.util.UI;
import chatcat.tasks.Task;
import chatcat.tasks.Todo;
import chatcat.util.WriteToFile;
import chatcat.chatcatexception.ChatCatException;

/**
 * Handles writing and reading to list of tasks represented as an {@code ArrayList}.
 * Allows clients to unmark, mark, add and delete tasks. Allows users to view task list.
 */
public class TaskList {
    ArrayList<Task> Tasks = new ArrayList<>();
    WriteToFile writeToFile;
    UI ui;

    /**
     * Creates {@code TaskList} object containing an empty Task List {@code ArrayList}.
     * Creates {@code UI} object.
     */
    public TaskList() {
        ui = new UI();
        writeToFile = new WriteToFile();
    }

    /**
     * Prints out all tasks in Task List.
     *
     * @see WriteToFile
     * @see Task
     */
    public void listTasks() {
        Tasks = writeToFile.toRead();

        if (Tasks.size() == 0) {
            System.out.println("empty list!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < Tasks.size(); i++) {
                System.out.println((i + 1) + ". " + Tasks.get(i));
            }
        }
        System.out.println("");
    }

    /**
     * Marks task at specified location.
     *
     * @param str containing the task to be marked.
     * @see UI
     * @see Task
     * @see WriteToFile
     */
    public void mark(String str) {
        String[] input = str.split(" ");
        int taskID = Integer.parseInt(input[1]) - 1;
        Tasks.get(taskID).setDone();
        writeToFile.toWrite(Tasks);
        ui.printOutPut("Nice! I've marked this task as done:\n" +
                Tasks.get(taskID));
    }

    /**
     * Unmark task at specified location.
     *
     * @param str containing the task to be marked.
     * @see UI
     * @see Task
     * @see WriteToFile
     */
    public void unmark(String str) {
        String[] input = str.split(" ");
        int taskID = Integer.parseInt(input[1]) - 1;
        Tasks.get(taskID).setUnDone();
        writeToFile.toWrite(Tasks);
        ui.printOutPut("OK, I've marked this task as not done yet:\n" +
                Tasks.get(taskID));
    }

    /**
     * Creates a todo {@code todo} object and appends the object at the end of {@code taskList}.
     *
     * @param str containing the task to be added.
     * @throws ChatCatException if description of todo is empty.
     * @see UI
     * @see Todo
     * @see WriteToFile
     */
    public void setTodo(String str) throws ChatCatException {
        String[] input = str.split(" ");
        if (input.length == 1) {
            throw new ChatCatException("OOPS!!! The description of a todo cannot be empty.");
        }

        Todo todo = new Todo(str.substring(5));
        Tasks.add(todo);
        writeToFile.toWrite(Tasks);
        ui.printOutPut("Got it. I've added this task:\n" + todo);
        ui.printOutPut("Now you have " + Tasks.size() + " tasks in the list.");
    }

    /**
     * Creates a deadline {@code Deadline} object and appends the object at the end of {@code taskList}.
     *
     * @param str containing the task to be added.
     * @throws ChatCatException if description of deadline is empty.
     * @see UI
     * @see Deadline
     * @see WriteToFile
     */
    public void setDeadline(String str) throws ChatCatException {
        String[] input = str.split(" ");

        if (input.length == 1) {
            throw new ChatCatException("OOPS!!! The description of a deadline cannot be empty.");
        }

        String[] split = str.split("/by ");
        Deadline deadline = new Deadline(split[0].substring(9), split[1]);
        Tasks.add(deadline);
        writeToFile.toWrite(Tasks);
        ui.printOutPut("Got it. I've added this task:\n" + deadline);
        ui.printOutPut("Now you have " + Tasks.size() + " tasks in the list.");
    }

    /**
     * Creates a deadline {@code Event} object and appends the object at the end of {@code taskList}.
     *
     * @param str containing the task to be added.
     * @throws ChatCatException if description of event is empty.
     * @see UI
     * @see Event
     * @see WriteToFile
     */
    public void setEvent(String str) throws ChatCatException {
        String[] input = str.split(" ");

        if (input.length == 1) {
            throw new ChatCatException("OOPS!!! The description of a event cannot be empty.");
        }

        String[] split = str.split("/at ");
        Event event = new Event(split[0].substring(6), split[1]);
        Tasks.add(event);
        writeToFile.toWrite(Tasks);
        ui.printOutPut("Got it. I've added this task:\n" + event);
        ui.printOutPut("Now you have " + Tasks.size() + " tasks in the list." + "\n");
    }

    /**
     * Deletes a specified task {@code Task} in the tasklist {@code taskList}.
     *
     * @param str containing the task to be deleted.
     * @see UI
     * @see Task
     * @see WriteToFile
     */
    public void delete(String str) {
        Tasks = writeToFile.toRead();
        String[] input = str.split(" ");

        int toDelete = Integer.parseInt(input[1]) - 1;
        Task removed = Tasks.remove(toDelete);
        writeToFile.toWrite(Tasks);
        ui.printOutPut("Noted. I've removed this task:\n" + removed);
        ui.printOutPut("Now you have " + Tasks.size() + " tasks in the list.");
    }
}
