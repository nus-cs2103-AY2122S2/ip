package chatcat.commands;

import java.util.ArrayList;

import chatcat.chatcatexception.ChatCatException;
import chatcat.tasks.Task;
import chatcat.tasks.Todo;
import chatcat.util.DateTimeUtil;
import chatcat.util.WriteToFile;

/**
 * The default SetTodoCommand class inherited from {@code Command}.
 *
 * @see Command
 */
public class SetTodoCommand extends Command {
    final String TODO;
    Todo todo;

    /**
     * Creates a default SetTodoCommand {@code Command} object.
     *
     * @param tasks the tasklist {@code ArrayList}.
     * @param writeToFile the class to handle writing of .ser files.
     * @param TODO string specify todo task {@code Task}.
     */
    public SetTodoCommand(ArrayList<Task> tasks, WriteToFile writeToFile, String TODO) {
        super(tasks, writeToFile);
        this.TODO = TODO;
    }

    /**
     * Creates a todo {@code Todo} object and appends the object at the end of {@code taskList}.
     *
     * @throws ChatCatException if description of deadline is empty.
     * @see Todo
     * @see WriteToFile
     * @see DateTimeUtil
     */
    public void setTodo() throws ChatCatException {
        String[] input = TODO.split(" ");

        if (input.length == 1) {
            throw new ChatCatException(
                    "OOPS!!! The description of a todo cannot be empty.");
        }
      
        String todoStr = TODO.substring(5);
        todo = new Todo(todoStr);

        super.tasks.add(todo);
        super.writeToFile.toWrite(super.tasks);
    }

    /**
     * Returns created todo task {@code Task} in String.
     *
     * @return created todo task {@code Task} in String.
     */
    @Override
    public String toString() {
        return "Got it. I've added this task:\n" + todo + "\n" +
                "Now you have " + super.tasks.size() + " tasks in the list.";
    }
}



