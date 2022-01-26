package duke;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {

    private final ArrayList<Task> items;

    private Storage storage;

    public TaskList(Storage storage) throws DukeException {
        items = new ArrayList<>();
        this.storage = storage;
        readFromFile();
    }

    public String deleteItem(int index) throws DukeException {
        Task item = items.get(index);
        items.remove(index);
        saveToFile();
        return item.toString();
    }

    public String markItem(int index) throws DukeException {
        items.get(index).mark();
        saveToFile();
        return items.get(index).toString();
    }

    public String unmarkItem(int index) throws DukeException {
        items.get(index).unmark();
        saveToFile();
        return items.get(index).toString();
    }

    public String addTodo(String name) throws DukeException {
        Task item = new TodoTask(name);
        items.add(item);
        saveToFile();
        return item.toString();
    }

    public String addDeadline(String name, String date) throws DukeException {
        Task item = new DeadlineTask(name, date);
        items.add(item);
        saveToFile();
        return item.toString();
    }

    public String addEvent(String name, String date) throws DukeException {
        Task item = new EventTask(name, date);
        items.add(item);
        saveToFile();
        return item.toString();
    }

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

    private void saveToFile() throws DukeException{
        String result = "";
        for (Task item : items) {
            result = result.concat(item.toStore() + "\n");
        }
        this.storage.write(result);
    }

    public String listCount() {
        return String.format("Now you have %d tasks in the list.", items.size());
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < items.size(); i++) {
            result = result.concat(String.format("%d.%s\n", i + 1, items.get(i)));
        }
        return result;
    }



}
