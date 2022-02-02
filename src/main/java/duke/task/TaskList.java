package duke.task;

import duke.exception.DukeException;
import duke.ui.Ui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>(100);
    }

    public int addToDo(String description) {
        return addToDo(description, false);
    }

    public int addToDo(String description, boolean isDone) {
        this.tasks.add(new ToDo(description, isDone));
        return tasks.size() - 1;
    }

    public int addDeadline(String description, boolean isDone, LocalDate date, LocalTime time) {
        this.tasks.add(new Deadline(description, isDone, date, time));
        return tasks.size() - 1;
    }

    public int addDeadline(String description, LocalDate date, LocalTime time) {
        return addDeadline(description, false, date, time);
    }

    public int addEvent(String description, boolean isDone, LocalDate date, LocalTime time) {
        this.tasks.add(new Event(description, isDone, date, time));
        return tasks.size() - 1;
    }

    public int addEvent(String description, LocalDate date, LocalTime time) {
        return addEvent(description, false, date, time);
    }

    public int deleteTask(int task) {
        tasks.remove(task);
        return tasks.size() - 1;
    }

    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    public Task getTask(int i) {
        return this.tasks.get(i);
    }

    public int getTotalTasks() {
        return tasks.size();
    }

    public boolean isEmpty() { return tasks.size() == 0; }

    public boolean isDone (int i) {
        return tasks.get(i).getIsDone();
    }

    public void completeTask(int i) {
        tasks.get(i).markAsDone();
    }

    @Override
    public String toString(){
        return Ui.taskListMsg(this);
    }



}
