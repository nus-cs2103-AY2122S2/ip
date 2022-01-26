import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class TodoList {

    private final ArrayList<Task> items;

    private File file;
    private Scanner scanner;
    private final String filePath = "data.txt";

    TodoList() {
        items = new ArrayList<>();
        file = new File(filePath);
        try {
            file.createNewFile();
            scanner = new Scanner(file);
            readFromFile();
        } catch (DukeException e) {

            try {
                // empty the corrupted file
                FileWriter writer = new FileWriter(filePath, false);
                writer.close();
            } catch (IOException e1) {
                System.out.println(e1.getMessage());
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String deleteItem(int index) {
        Task item = items.get(index);
        items.remove(index);
        saveToFile();
        return item.toString();
    }

    public String markItem(int index) {
        items.get(index).mark();
        saveToFile();
        return items.get(index).toString();
    }

    public String unmarkItem(int index) {
        items.get(index).unmark();
        saveToFile();
        return items.get(index).toString();
    }

    public String addTodo(String name) {
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

    private void saveToFile() {
        try {
            FileWriter writer = new FileWriter(filePath, false);
            String result = "";
            for (Task item : items) {
                result = result.concat(item.toStore() + "\n");
            }

            writer.write(result);
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
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
