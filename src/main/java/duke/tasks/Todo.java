package duke.tasks;

import java.time.LocalDateTime;

/**
 * Todos are tasks that have no dates involved.
 */
public class Todo extends Task{

    /**
     * Constructor for Todo.
     *
     * @param content Description of Todo.
     */
    public Todo(String content) {
        super(content);
    }

    @Override
    public LocalDateTime getDate() {
        return LocalDateTime.MIN;
    }

    /**
     * Adds Todo indicator to the front of Task string.
     *
     * @return Formatted string representation of Event.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    /**
     * Adds Todo indicator and date time to save data.
     *
     * @return Data to write into save file.
     */
    public String toSaveData() {
        return String.format("T|%s\n",super.toSaveData());
    }

    /**
     * Recreate Todo from save data.
     *
     * @param savedData Todo Data in save file.
     * @return Todo that was saved.
     */
    public static Todo createFromData(String savedData) {
        String[] parsedSavedData = savedData.split("\\|");
        Todo newTodo = new Todo(parsedSavedData[2]);
        if (parsedSavedData[1].equals("1")) {
            newTodo.markAsDone();
        }
        return newTodo;
    }
}
