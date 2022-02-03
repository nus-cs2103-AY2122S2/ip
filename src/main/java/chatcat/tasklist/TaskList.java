package chatcat.tasklist;

import java.util.ArrayList;

import chatcat.tasks.Deadline;
import chatcat.tasks.Event;
import chatcat.tasks.Task;
import chatcat.tasks.Todo;
import chatcat.util.WriteToFile;
import chatcat.util.DateTimeUtil;
import chatcat.chatcatexception.ChatCatException;
import chatcat.commands.Commands;

/**
 * Handles writing and reading to list of tasks represented as an {@code ArrayList}.
 * Allows clients to unmark, mark, add and delete tasks. Allows users to view task list.
 */
public class TaskList {
    ArrayList<Task> Tasks = new ArrayList<>();
    WriteToFile writeToFile;
    Commands commands;
    DateTimeUtil dateTimeUtil;

    /**
     * Creates {@code TaskList} object containing an empty Task List {@code ArrayList}.
     * Creates {@code WriteToFile} object.
     * Creates {@code Commands} object.
     */
    public TaskList() {
        writeToFile = new WriteToFile();
        commands = new Commands();
    }

    /**
     * Prints out all tasks in Task List.
     *
     * @see WriteToFile
     * @see Task
     * @see Commands
     */
    public void listTasks() {
        Tasks = writeToFile.toRead();
        commands.outputList(Tasks);
    }

    /**
     * Prints out exit message.
     *
     * @see Commands
     */
    public void exitChatCat() {
        commands.outputByeMessage();
    }

    /**
     * Marks task at specified location.
     *
     * @param str containing the task to be marked.
     * @see Task
     * @see WriteToFile
     * @see Commands
     */
    public void mark(String str) {
        String[] input = str.split(" ");
        int taskID = Integer.parseInt(input[1]) - 1;

        Tasks.get(taskID).setDone();
        writeToFile.toWrite(Tasks);

        commands.outputMarkMessage(Tasks.get(taskID));
    }

    /**
     * Unmark task at specified location.
     *
     * @param str containing the task to be marked.
     * @see Task
     * @see WriteToFile
     * @see Commands
     */
    public void unmark(String str) {
        String[] input = str.split(" ");
        int taskID = Integer.parseInt(input[1]) - 1;

        Tasks.get(taskID).setUnDone();
        writeToFile.toWrite(Tasks);

        commands.outputUnmarkMessage(Tasks.get(taskID));
    }

    /**
     * Creates a todo {@code todo} object and appends the object at the end of {@code taskList}.
     *
     * @param str containing the task to be added.
     * @throws ChatCatException if description of todo is empty.
     * @see Todo
     * @see WriteToFile
     * @see Commands
     */
    public void setTodo(String str) throws ChatCatException {
        String[] input = str.split(" ");

        if (input.length == 1) {
            throw new ChatCatException(
                    "OOPS!!! The description of a todo cannot be empty.");
        }

        Todo todo = new Todo(str.substring(5));
        Tasks.add(todo);
        writeToFile.toWrite(Tasks);

        commands.outputTaskMessage(todo, Tasks.size());
    }

    /**
     * Creates a deadline {@code Deadline} object and appends the object at the end of {@code taskList}.
     *
     * @param str containing the task to be added.
     * @throws ChatCatException if description of deadline is empty.
     * @see Deadline
     * @see WriteToFile
     * @see Commands
     * @see DateTimeUtil
     */
    public void setDeadline(String str) throws ChatCatException {
        String[] input = str.split(" ");

        if (input.length == 1) {
            throw new ChatCatException("OOPS!!! The description of a deadline cannot be empty.");
        }

        String[] split = str.split("/by ");
        dateTimeUtil = new DateTimeUtil(split[1]);
        Deadline deadline = new Deadline(split[0].substring(9), dateTimeUtil.getTime());

        Tasks.add(deadline);
        writeToFile.toWrite(Tasks);

        commands.outputTaskMessage(deadline, Tasks.size());
    }

    /**
     * Creates a deadline {@code Event} object and appends the object at the end of {@code taskList}.
     *
     * @param str containing the task to be added.
     * @throws ChatCatException if description of event is empty.
     * @see Event
     * @see WriteToFile
     * @see Commands
     * @see DateTimeUtil
     */
    public void setEvent(String str) throws ChatCatException {
        String[] input = str.split(" ");

        if (input.length == 1) {
            throw new ChatCatException("OOPS!!! The description of a event cannot be empty.");
        }

        String[] split = str.split("/at ");
        dateTimeUtil = new DateTimeUtil(split[1]);
        Event event = new Event(split[0].substring(6), dateTimeUtil.getTime());

        Tasks.add(event);
        writeToFile.toWrite(Tasks);

        commands.outputTaskMessage(event, Tasks.size());
    }

    /**
     * Deletes a specified task {@code Task} in the tasklist {@code taskList}.
     *
     * @param str containing the task to be deleted.
     * @see Task
     * @see WriteToFile
     * @see Commands
     */
    public void delete(String str) {
        Tasks = writeToFile.toRead();
        String[] input = str.split(" ");
        int toDelete = Integer.parseInt(input[1]) - 1;

        Task removed = Tasks.remove(toDelete);
        writeToFile.toWrite(Tasks);

        commands.outputDeleteMessage(removed, Tasks.size());
    }

    /**
     * Displays the tasks {@code Task} in tasklist {@code taskList} that includes a specified keyword.
     *
     * @param str containing the keyword.
     * @see Task
     * @see Commands
     */
    public void filter(String str) throws ChatCatException {
        Tasks = writeToFile.toRead();
        ArrayList<Task> filteredList = new ArrayList<>();
        String[] input = str.split(" ");

        Tasks.forEach(task -> {
            if (task.containsKeyWord(input[1])) {
                filteredList.add(task);
            }
        });

        if (filteredList.isEmpty()) {
            throw new ChatCatException("No task with keyword: " + "str");
        }

        commands.outputFilterMessage(filteredList);
    }
}
