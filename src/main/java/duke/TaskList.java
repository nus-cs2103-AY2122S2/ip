package duke;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(String fileName) throws IOException {
        this.list = TaskList.populateList(fileName);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public Task get(int idx) {
        return list.get(idx);
    }

    public int size() {
        return list.size();
    }

    public Task remove(int taskNum) {
        return list.remove(taskNum);
    }

    public Task set(int idx, Task task) {
        return list.set(idx, task);
    }

    public boolean add(Task task) {
        return list.add(task);
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    public static ArrayList<Task> populateList(String fileName) throws IOException {
        ArrayList<Task> list = new ArrayList<Task>();
        BufferedReader file = new BufferedReader(new FileReader(fileName));
        String line = file.readLine();
        while (line != null) {
            boolean isMarked = String.valueOf(line.charAt(4)).equals("X");
            if (String.valueOf(line.charAt(1)).equals("T")) {
                Task task = new Todo(line.substring(7));
                if (isMarked) {
                    task = task.mark();
                }
                list.add(task);
            } else if (String.valueOf(line.charAt(1)).equals("D")) {
                String tempDescription = line.split("by: ")[0].substring(7);
                int tempDescLength = tempDescription.length();
                String description = tempDescription.substring(0, tempDescLength - 2);
                String tempTimeBy = line.split("by: ")[1];
                int endIdx = tempTimeBy.lastIndexOf(")");
                String timeBy = tempTimeBy.substring(0, endIdx);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
                Task task = new Event(description, LocalDate.parse(timeBy, formatter).toString());
                if (isMarked) {
                    task = task.mark();
                }
                list.add(task);
            } else if (String.valueOf(line.charAt(1)).equals("E")) {
                String tempDescription = line.split("at: ")[0].substring(7);
                int tempDescLength = tempDescription.length();
                String description = tempDescription.substring(0, tempDescLength - 2);
                String tempTimeBy = line.split("at: ")[1];
                int endIdx = tempTimeBy.lastIndexOf(")");
                String timeBy = tempTimeBy.substring(0, endIdx);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
                Task task = new Event(description, LocalDate.parse(timeBy, formatter).toString());
                if (isMarked) {
                    task = task.mark();
                }
                list.add(task);
            }
            line = file.readLine();
        }
        file.close();
        return list;
    }
}
