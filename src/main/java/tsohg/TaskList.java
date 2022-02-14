package tsohg;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The class representing the list of tasks.
 */
public class TaskList {

    private final ArrayList<Task> items;

    private final Storage storage;

    /**
     * Constructor of the class.
     * @param storage Storage object to store the list.
     * @throws TsohgException If something wrong happens.
     */
    public TaskList(Storage storage) throws TsohgException {
        items = new ArrayList<>();
        this.storage = storage;
        readFromFile();
    }

    /**
     * Deletes an item from the list.
     * @param index The index of the item.
     * @return A success message after deletion.
     * @throws TsohgException If something wrong happens.
     */
    public String deleteItem(int index) throws TsohgException {
        assert 0 <= index && index < size();
        Task item = items.get(index);
        items.remove(index);
        saveToFile();
        return item.toString();
    }

    /**
     * Marks an item as completed.
     * @param index The index of the item.
     * @return A success message after marking.
     * @throws TsohgException If something wrong happens.
     */
    public String markItem(int index) throws TsohgException {
        assert 0 <= index && index < size();
        items.get(index).mark();
        saveToFile();
        return items.get(index).toString();
    }

    /**
     * Unmarks an item from completed.
     * @param index The index of the item.
     * @return A success message after marking.
     * @throws TsohgException If something wrong happens.
     */
    public String unmarkItem(int index) throws TsohgException {
        assert 0 <= index && index < size();
        items.get(index).unmark();
        saveToFile();
        return items.get(index).toString();
    }

    /**
     * Adds a todo task to the list.
     * @param name The name of the task.
     * @return A success message after adding.
     * @throws TsohgException If something wrong happens.
     */
    public String addTodo(String name) throws TsohgException {
        Task item = new TodoTask(name, false);
        items.add(item);
        saveToFile();
        return item.toString();
    }

    /**
     * Adds a deadline task to the list.
     * @param name The name of the task.
     * @param date The deadline of the task.
     * @return A success message after adding.
     * @throws TsohgException If something wrong happens.
     */
    public String addDeadline(String name, String date) throws TsohgException {
        Task item = new DeadlineTask(name, date, false);
        items.add(item);
        saveToFile();
        return item.toString();
    }

    /**
     * Adds an event task to the list.
     * @param name The name of the task.
     * @param date The event date of the task.
     * @return A success message after adding.
     * @throws TsohgException If something wrong happens.
     */
    public String addEvent(String name, String date) throws TsohgException {
        Task item = new EventTask(name, date, false);
        items.add(item);
        saveToFile();
        return item.toString();
    }

    /**
     * Finds list of tasks that matches a keyword.
     * @param keyword The keyword to be searched for.
     * @return A string representing of list of tasks that matches the keyword.
     */
    public String find(String keyword) {
        String result = "";
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).contains(keyword)) {
                result = result.concat(formatItemOutput(i));
            }
        }
        return result;
    }

    /**
     * Switch the priority of the task.
     * @param index The index of the item.
     * @return A success message after switching.
     * @throws TsohgException If something wrong happens.
     */
    public String switchPriority(int index) throws TsohgException {
        assert 0 <= index && index < size();
        items.get(index).switchPriority();
        saveToFile();
        return items.get(index).toString();
    }

    /**
     * Reads from the storage and populate the content as a list.
     * @throws TsohgException If something wrong happens.
     */
    private void readFromFile() throws TsohgException {
        Scanner scanner = storage.read();
        while (scanner.hasNext()) {
            String[] data = parseLine(scanner.nextLine());
            String type = data[0];
            Task item = null;
            switch (type) {
            case "T":
                item = new TodoTask(data[3], false);
                break;
            case "E":
                item = new EventTask(data[3], data[4], false);
                break;
            case "D":
                item = new DeadlineTask(data[3], data[4], false);
                break;
            default:
                assert false;
            }
            if (data[1].equals("1")) {
                item.switchPriority();
            }
            if (data[2].equals("1")) {
                item.mark();
            }
            items.add(item);
        }
    }

    private String[] parseLine(String line) {
        String[] data = line.split("\\|", -1);
        for (int i = 0; i < data.length; i++) {
            data[i] = data[i].strip();
        }
        return data;
    }

    /**
     * Saves the list to the storage.
     * @throws TsohgException If something wrong happens.
     */
    private void saveToFile() throws TsohgException {
        String result = "";
        for (Task item : items) {
            result = result.concat(item.toStore() + "\n");
        }
        this.storage.write(result);
    }

    /**
     * Returns the size of the item list
     * @return return the size of the item list
     */
    public int size() {
        return items.size();
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
            result = result.concat(formatItemOutput(i));
        }
        return result;
    }

    private String formatItemOutput(int index) {
        return String.format("%d.%s\n", index + 1, items.get(index));
    }
}
