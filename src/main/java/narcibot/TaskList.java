package narcibot;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList(BufferedReader bufferedReader) throws IOException {
        list = new ArrayList<>();
        String line = bufferedReader.readLine();
        while (line != null) {
            String[] tokens = line.split("\\|", 4);
            boolean done = tokens[1].equals("1");
            switch(tokens[0]) {
                case "T":
                    list.add(new ToDo(tokens[2], done));
                    break;
                case "D":
                    list.add(new Deadline(tokens[2], tokens[3], done));
                    break;
                case "E":
                    list.add(new Event(tokens[2], tokens[3], done));
                    break;
            }
            line = bufferedReader.readLine();
        }
    }

    public void store(FileWriter fileWriter) throws IOException {
        for (Task task: list) {
            fileWriter.write(task.save() + "\n");
        }
    }

    public void list() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + "." + list.get(i).getStatus());
        }
    }

    public void mark(String indexString) throws IncorrectFormatException {
        list.get(checkValid(indexString) - 1).markDone();
    }

    public void unmark(String indexString) throws IncorrectFormatException {
        list.get(checkValid(indexString) - 1).markNotDone();
    }

    public void delete(String indexString) throws IncorrectFormatException {
        list.remove(checkValid(indexString) - 1);
    }

    public int checkValid(String indexString) throws IncorrectFormatException {
        int index = Integer.parseInt(indexString);
        if (index < 1 || index > list.size()) {
            throw new IncorrectFormatException("Please enter a valid task number.");
        }
        return index;
    }

    public int todo(String task) {
        list.add(new ToDo(task));
        return list.size();
    }

    public int deadline(String task, String time) {
        list.add(new Deadline(task, time));
        return list.size();
    }

    public int event(String task, String time) {
        list.add(new Event(task, time));
        return list.size();
    }
}
