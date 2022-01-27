package duke;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The class representing the list of tasks.
 */
public class TaskList {

    private final ArrayList<Task> items;

    private Storage storage;

    /**
     * Constructor of the class.
     * @param storage Storage object to store the list.
     * @throws DukeException If something wrong happens.
     */
    public TaskList(Storage storage) throws DukeException {
        items = new ArrayList<>();
        this.storage = storage;
        readFromFile();
    }

    /**
     * Deletes an item from the list.
     * @param index The index of the item.
     * @return A success message after deletion.
     * @throws DukeException If something wrong happens.
     */
    public String deleteItem(int index) throws DukeException {
        Task item = items.get(index);
        items.remove(index);
        saveToFile();
        return item.toString();
    }

    /**
     * Marks an item as completed.
     * @param index The index of the item.
     * @return A success message after marking.
     * @throws DukeException If something wrong happens.
     */
    public String markItem(int index) throws DukeException {
        items.get(index).mark();
        saveToFile();
        return items.get(index).toString();
    }

    /**
     * Unmarks an item from completed.
     * @param index The index of the item.
     * @return A success message after marking.
     * @throws DukeException If something wrong happens.
     */
    public String unmarkItem(int index) throws DukeException {
        items.get(index).unmark();
        saveToFile();
        return items.get(index).toString();
    }

    /**
     * Adds a todo task to the list.
     * @param name The name of the task.
     * @return A success message after adding.
     * @throws DukeException If something wrong happens.
     */
    public String addTodo(String name) throws DukeException {
        Task item = new TodoTask(name);
        items.add(item);
        saveToFile();
        return item.toString();
    }

    /**
     * Adds a deadline task to the list.
     * @param name The name of the task.
     * @param date The deadline of the task.
     * @return A success message after adding.
     * @throws DukeException If something wrong happens.
     */
    public String addDeadline(String name, String date) throws DukeException {
        Task item = new DeadlineTask(name, date);
        items.add(item);
        saveToFile();
        return item.toString();
    }

    /**
     * Adds an event task to the list.
     * @param name The name of the task.
     * @param date The event date of the task.
     * @return A success message after adding.
     * @throws DukeException If something wrong happens.
     */
    public String addEvent(String name, String date) throws DukeException {
        Task item = new EventTask(name, date);
        items.add(item);
        saveToFile();
        return item.toString();
    }

    /**
     * Reads from the storage and populate the content as a list.
     * @throws DukeException If something wrong happens.
     */
    private void readFromFile() throws DukeException {
        Scanner scanner = storage.read();
        while (scanner.hasNext()) {
            String[] data = scanner.nextLine().split(" \\| ");
            String type = data[0];
            Task item;
            switch (type) {
            case "T":
                item = new TodoTask(data[2]);
                break;
            case "E":
                item = new EventTask(data[2], data[3]);
                break;
            case "D":
                item = new DeadlineTask(data[2], data[3]);
                break;
            default:
                item = new TodoTask("");
            }
            if (data[1].equals("1")) {
                item.mark();
            }
            items.add(item);
        }
    }

    /**
     * Saves the list to the storage.
     * @throws DukeException If something wrong happens.
     */
    private void saveToFile() throws DukeException{
        String result = "";
        for (Task item : items) {
            result = result.concat(item.toStore() + "\n");
        }
        this.storage.write(result);
    }

    /**
     * Returns a string telling the number of tasks in the list.
     * @return a string telling the number of tasks in the list.
     */
    public String listCount() {
        return String.format("Now you have %d tasks in the list.", items.size());
    }

    /**
     * Returns a string representation of the list.
     * @return a string representation of the list.
     */
    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < items.size(); i++) {
            result = result.concat(String.format("%d.%s\n", i + 1, items.get(i)));
        }
        return result;
    }



}
