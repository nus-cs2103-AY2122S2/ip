package paggro.notableDate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;

import paggro.task.Task;

public class NotableDate {
    public LocalDate localDate;
    public ArrayList<Task> tasks;

    public NotableDate(LocalDate date) {
        this.localDate = date;
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    @Override
    public String toString() {
        return localDate.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
    }
}
