package chatcat.tasklist;

import java.util.ArrayList;

import chatcat.tasks.Task;
import chatcat.util.WriteToFile;
import chatcat.chatcatexception.ChatCatException;
import chatcat.commands.SetDeadlineCommand;
import chatcat.commands.SetEventCommand;
import chatcat.commands.SetTodoCommand;
import chatcat.commands.ListTaskCommand;
import chatcat.commands.MarkCommand;
import chatcat.commands.UnmarkCommand;
import chatcat.commands.DeleteCommand;
import chatcat.commands.FilterCommand;

/**
 * Handles writing and reading to list of tasks represented as an {@code ArrayList}.
 * Allows clients to unmark, mark, add and delete tasks. Allows users to view task list.
 */
public class TaskList {
    ArrayList<Task> tasks;
    WriteToFile writeToFile;

    /**
     * Creates {@code TaskList} object containing an empty Task List {@code ArrayList}.
     * Creates {@code WriteToFile} object.
     */
    public TaskList() {
        writeToFile = new WriteToFile();
        tasks = writeToFile.toRead();
    }

    /**
     * Prints out all tasks in Task List.
     *
     * @see WriteToFile
     * @see ListTaskCommand
     */
    public String listTasks() {
        ListTaskCommand listTasks = new ListTaskCommand(tasks, writeToFile);
        listTasks.getTaskList();

        return listTasks.toString();
    }

    /**
     * Prints out exit message.
     *
     * @return exit message
     */
    public String exitChatCat() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Marks task at specified location.
     *
     * @param str containing the task to be marked.
     * @return marked task {@code Task} in String.
     * @see WriteToFile
     * @see MarkCommand
     */
    public String mark(String str) {
        MarkCommand markCommand = new MarkCommand(tasks, writeToFile, str);
        markCommand.mark();

        return markCommand.toString();
    }

    /**
     * Unmarks task at specified location.
     *
     * @param str containing the task to be marked.
     * @return unmarked task {@code Task} in String.
     * @see WriteToFile
     * @see UnmarkCommand
     */
    public String unmark(String str) {
        UnmarkCommand unmarkCommand = new UnmarkCommand(tasks, writeToFile, str);
        unmarkCommand.unmark();

        return unmarkCommand.toString();
    }

    /**
     * Creates a todo {@code todo} object and appends the object at the end of {@code taskList}.
     *
     * @param str containing the task to be added.
     * @return created todo task {@code Task} in String.
     * @throws ChatCatException if description of todo is empty.
     * @see WriteToFile
     * @see SetTodoCommand
     */
    public String setTodo(String str) throws ChatCatException {
        SetTodoCommand setTodoCommand = new SetTodoCommand(tasks, writeToFile, str);
        setTodoCommand.setTodo();

        return setTodoCommand.toString();
    }

    /**
     * Creates a deadline {@code Deadline} object and appends the object at the end of {@code taskList}.
     *
     * @param str containing the task to be added.
     * @return created deadline task {@code Task} in String.
     * @throws ChatCatException if description of deadline is empty.
     * @see WriteToFile
     * @see SetDeadlineCommand
     */
    public String setDeadline(String str) throws ChatCatException {
        SetDeadlineCommand setDeadlineCommand = new SetDeadlineCommand(tasks, writeToFile, str);
        setDeadlineCommand.setDeadline();

        return setDeadlineCommand.toString();
    }

    /**
     * Creates a event {@code Event} object and appends the object at the end of {@code taskList}.
     *
     * @param str containing the task to be added.
     * @return created event task {@code Task} in String.
     * @throws ChatCatException if description of event is empty.
     * @see WriteToFile
     * @see SetEventCommand
     */
    public String setEvent(String str) throws ChatCatException {
        SetEventCommand setEventCommand = new SetEventCommand(tasks, writeToFile, str);
        setEventCommand.setEvent();

        return setEventCommand.toString();
    }

    /**
     * Deletes a specified task {@code Task} in the tasklist {@code taskList}.
     *
     * @param str containing the task to be deleted.
     * @return deleted task {@code Task} in String.
     * @see WriteToFile
     * @see DeleteCommand
     */
    public String delete(String str) {
        DeleteCommand deleteCommand = new DeleteCommand(tasks, writeToFile, str);
        deleteCommand.delete();

        return deleteCommand.toString();
    }

    /**
     * Displays the tasks {@code Task} in tasklist {@code taskList} that includes a specified keyword.
     *
     * @param str containing the keyword.
     * @return a representation in string of filtered {@code Task} tasks that includes on keyword.
     * @see FilterCommand
     * @see WriteToFile
     */
    public String filter(String str) throws ChatCatException {
        FilterCommand filterCommand = new FilterCommand(tasks, writeToFile, str);
        filterCommand.filter();

        return filterCommand.toString();
    }
}
