package duke.task;

import duke.DukeException;

/**
 * Represents a task object that needs to be completed.
 */
public class Todo extends Task {

    /**
     * Constructor for a Todo object.
     *
     * @param description The title for the Todo object.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string description of a todo object.
     *
     * @return A string description of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + this.getSymbol() + " " + this.getName();
    }

    /**
     * Creates a new Todo object from a text input.
     *
     * @param input The string input which contains info on the Todo task.
     * @return A new Todo object.
     */
    public static Todo createTodo(String input) throws DukeException {
        String tdTask = input.substring(4); //Grabs all the text after the "todo" command word
        if (tdTask.trim().equals("")) {
            throw new DukeException("Empty description for Todo object");
        }
        Todo td = new Todo(tdTask.trim());
        System.out.println("Got it! I've added this task:");
        System.out.println(td.toString());
        return td;
    }

    /**
     * Creates a new Todo object from a text input and a status value.
     *
     * @param status Indicates if task is marked as completed or not. 1 being
     * complete and 0 being incomplete.
     * @param description The given title of the todo task.
     * @return A new todo object.
     */
    public static Todo createTodo(int status, String description) {
        Todo td = new Todo(description);
        if (status == 1) {
            td.markTask();
        }
        return td;
    }

    /**
     * Formats a Todo object to text.
     *
     * @return A text with information regarding the Todo object.
     */
    @Override
    public String formatText() {
        int status = (this.getStatus()) ? 1 : 0;
        return "T|" + status + "|" + this.getName();
    }
}