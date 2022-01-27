package tasks;

import exceptions.DukeException;
import storage.Storage;

import java.util.ArrayList;

public class Tasklist {

    public Storage s = new Storage();
    public ArrayList<Task> allTasks = new ArrayList<>();

    public void loadTasks() throws DukeException {
        allTasks = s.loadTasks();
    }

    public void saveTasks() throws DukeException {
        s.saveTasks(allTasks);
    }

    public int getNumTasks() {
        return this.allTasks.size();
    }

    public Task getTask(int taskNum) {
        return this.allTasks.get(taskNum);
    }

    public void addTask(Task t) {
        this.allTasks.add(t);
    }

    public String[] markTask(int taskNum) throws DukeException {
        Task thisTask = allTasks.get(taskNum);
        if (thisTask.isDone()) {
            throw new DukeException("This task is already done!");
        } else {
            thisTask.setDone(true);
            return new String[] {"Nice! I've marked this task as done:", thisTask.toString()};
        }
    }

    public String[] unmarkTask(int taskNum) throws DukeException {
        Task thisTask = allTasks.get(taskNum);
        if (!thisTask.isDone()) {
            throw new DukeException("This task has not been done yet!");
        } else {
            thisTask.setDone(false);
            return new String[] {"Ok, I've marked this task as not done yet:", thisTask.toString()};
        }
    }

    public String[] deleteTask(int taskNum) {
        Task thisTask = allTasks.get(taskNum);
        allTasks.remove(taskNum);
        return new String[] {
                "Noted. I've removed this task:",
                thisTask.toString(),
                String.format("Now you have %d tasks in the list.", allTasks.size())};
    }
}