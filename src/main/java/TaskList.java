import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;

public class TaskList {
    protected static final String INDENT2 = "        ";

    protected List<Task> taskList;
    protected int numOfTasks;

    TaskList() {
        this.taskList = new ArrayList<Task>();
        this.numOfTasks = 0;
    }

    TaskList(List<String> storageMemory) {
        this.taskList = new ArrayList<Task>();
        for (int i = 0; i < storageMemory.size(); i++) {
            String[] task = storageMemory.get(i).split("@", 4); // format: D@1@do something
            Task taskNew;
            if (task.length < 3)
                break;
            switch (task[0]) {
                case "E": //event
                    taskNew = new Event(task[2], task[3]);
                    break;
                case "D": //deadline
                    taskNew = new Deadline(task[2], task[3]);
                    break;
                default: // todo
                    taskNew = new Todo(task[2]);
                    break;
            }
            if (Integer.parseInt(task[1]) == 1) {
                taskNew.markAsDone();
            }
            this.taskList.add(taskNew);
        }
        this.numOfTasks = this.taskList.size();
    }

    TaskList(List<Task> tasks, int numOfTasks) {
        this.taskList = tasks;
        this.numOfTasks = numOfTasks;
    }

    int size() {
        return numOfTasks;
    }

    Task get(int index) {
        return this.taskList.get(index);
    }

    TaskList filterByDate(Date date) {
        List<Task> tasks = new ArrayList<Task>();
        for (Task task : taskList) {
            if (task.isOn(date)) {
                tasks.add(task);
            }
        }
        return new TaskList(tasks, tasks.size());
    }

    void addTask(Task tNew) {
        this.numOfTasks += 1;
        this.taskList.add(tNew);
    }

    /**
     * @param index
     * @return the removed task to be printed out by Ui
     */
    Task deleteTask(int index) {
        this.numOfTasks -= 1;
        return this.taskList.remove(index - 1);
    }

    Task markAsDone(int index) {
        Task done = this.taskList.get(index - 1);
        done.markAsDone();
        return done;
    }

    Task markAsUndone(int index) {
        Task undone = this.taskList.get(index - 1);
        undone.markAsUndone();
        return undone;
    }

    @Override
    public String toString() {
        String msg = "";
        for (int i = 0; i < taskList.size(); i++) {
            msg += INDENT2 + (i + 1) + "." + taskList.get(i).toString() + "\n";
        }
        return msg;
    }
}
