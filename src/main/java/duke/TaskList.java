package duke;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    ArrayList<Task> tasks;
    public int items;

    public TaskList(ArrayList<Task> t, int number) {
        tasks = t;
        items = number;
    }
    public TaskList() {
        tasks = new ArrayList<Task>();
    }
    public TaskList add(Task task) {
        this.tasks.add(task);
        items++;
        return this;
    }
    public int getNumberOfTasks() {
        return items;
    }
    public void remove(int n) {
        items--;
        this.tasks.remove(n);
    }
    public Task get(int n) {
        return this.tasks.get(n);
    }
    public void set(int n, Task t) {
        this.tasks.set(n, t);
    }
    public void updateTask(int lineNumber, String data) throws IOException {
        Path path = Paths.get("data/duke.txt");
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        lines.set(lineNumber - 1, data);
        Files.write(path, lines, StandardCharsets.UTF_8);
    }
    public  void deleteTask(int lineNumber) throws IOException {
        Path path = Paths.get("data/duke.txt");
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        lines.remove(lineNumber - 1);
        Files.write(path, lines, StandardCharsets.UTF_8);
    }
}
