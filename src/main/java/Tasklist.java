import java.io.IOException;
import java.util.LinkedList;

public class Tasklist {

    private LinkedList<Task> tasks;

    public Tasklist() {
        this.tasks = new LinkedList<Task>();
    }

    public void setTaskList(String input) {
        String[] tasks = input.split("\\r?\\n");
        for (int i = 0; i < tasks.length; i++) {
            String[] taskInfo = tasks[i].split("\\|");
            boolean completed = taskInfo[1].equals("1");
            switch (taskInfo[0]) {
                case "T":
                    this.addTask(new Todo(completed, taskInfo[2]));
                    break;
                case "D":
                    if (taskInfo.length == 4) {
                        this.addTask(new Deadline(completed, taskInfo[2],
                                Time.convertToDate(taskInfo[3]), ""));
                    } else {
                        this.addTask(new Deadline(completed, taskInfo[2],
                                Time.convertToDate(taskInfo[3]), taskInfo[4]));
                    }
                    break;
                case "E":
                    if (taskInfo.length == 4) {
                        this.addTask(new Event(completed, taskInfo[2],
                                Time.convertToDate(taskInfo[3]), ""));
                    }
                    this.addTask(new Event(completed, taskInfo[2],
                            Time.convertToDate(taskInfo[3]), taskInfo[4]));
            }
        }
    }

    public void writeTaskList(String path) throws IOException {
        StringBuilder writeTasks = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            String digit;
            Task t = this.tasks.get(i);
            if (t.isCompleted()) {
                digit = "1|";
            } else {
                digit = "0|";
            }
            if (t instanceof Todo) {
                writeTasks.append("T|").append(digit).append(t.getTaskName()).append("\n");
            } else if (t instanceof Event) {
                writeTasks.append("E|").append(digit).append(t.getTaskName()).append("|")
                        .append(((Event) t).getTime()).append("|")
                        .append(((Event) t).getTime()).append("\n");
            } else {
                writeTasks.append("D|").append(digit).append(t.getTaskName()).append("|")
                        .append(((Deadline) t).getDeadline()).append("|")
                        .append(((Deadline) t).getTime()).append("\n");
            }
        }
        FileCreator.writeToFile(path, writeTasks.toString());
    }

    public void addTask(Task t) {
        this.tasks.add(t);
    }

    public int getTotalTasks() {
        return this.tasks.size();
    }

    public Task mark(int index) {
        Task t = this.tasks.get(index);
        t.mark();
        return t;
    }

    public Task unmark(int index) {
        Task t = this.tasks.get(index);
        t.unmark();
        return t;
    }

    public Task delete(int index) {
        return this.tasks.remove(index);
    }

    @Override
    public String toString() {
        StringBuilder allTasks = new StringBuilder("Fetching all records...\n");
        if (this.tasks.size() == 0) {
            return allTasks.append("No entries found, start by adding one!\n").toString();
        }
        for (int i = 0; i < this.tasks.size(); i++) {
            allTasks.append(i + 1)
                    .append(". ")
                    .append(this.tasks.get(i).toString())
                    .append("\n");
        }
        return allTasks.toString();
    }
}