package paggro.lister;

import java.util.ArrayList;
import java.util.HashMap;

import java.time.LocalDate;

import paggro.task.Task;
import paggro.notableDate.NotableDate;

public class Lister {
    public ArrayList<Task> tasks;
    public HashMap<LocalDate, NotableDate> dateMap;

    public Lister() {
        tasks = new ArrayList<>();
        dateMap = new HashMap<>();
    }

    public Lister(ArrayList<Task> tasks, HashMap<LocalDate, NotableDate> dateMap) {
        this.tasks = tasks;
        this.dateMap = dateMap;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void mark(int i) {
        Task task = tasks.get(i - 1);
        task.setDone();
    }

    public void unmark(int i) {
        Task task = tasks.get(i - 1);
        task.setUndone();
    }

    public void delete(int i) {
        tasks.remove(i - 1);
    }

    public NotableDate checkDate(LocalDate lDate) {
        NotableDate nDate;
        if (!dateMap.containsKey(lDate)) { // checks if NotableDate has already been initialised
            nDate = new NotableDate(lDate);
            dateMap.put(lDate, nDate);
        } else  {
            nDate = dateMap.get(lDate);
        }
        return nDate;
    }
}
