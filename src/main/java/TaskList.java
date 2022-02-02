import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public void delete(int i) throws DukeException {
        if (!this.get(i).isEmptyTask()) {
            this.tasks.remove(i - 1);
        } else {
            throw new DukeException("No such task found!");
        }
    }

    public Task get(int i) throws DukeException {
        if (tasks.isEmpty()) {
            throw new DukeException("TaskList is empty!");
        } else if (i < 1 || i > tasks.size()) {
            throw new DukeException("Requested task does not exist!");
        } else {
            return tasks.get(i - 1);
        }
    }

    public int getNumOfTasks() {
        return tasks.size();
    }

    public String tasksAsString() throws DukeException {
        String taskAsString = "";
        int i = 0;
        while (i < this.tasks.size()) {
            String SPACE = "     ";
            Task curr = this.get(i + 1);
            taskAsString += SPACE + (i + 1) + ". " + curr + "\n";
            i++;
        }
        return taskAsString;
    }

    public String taskAsData() throws DukeException {
        String taskAsData = "";
        int i = 0;
        while (i < this.tasks.size()) {
            Task curr = this.get(i + 1);
            taskAsData += curr.getPrefix() + "/"
                    + curr.isMarked() + "/"
                    + curr.getName() + "/"
                    + curr.getDate() + "/"
                    + curr.getTime() + "\n";
            i++;
        }
        return taskAsData;
    }

    public void mark(int i) throws DukeException {
        if (this.isEmpty()) {
            throw new DukeException("No tasks in TaskList!");
        } else if (get(i).isMarked()) {
            throw new DukeException("Task is already marked!");
        } else {
            get(i).mark();
        }
    }

    public void unmark(int i) throws DukeException {
        if (this.isEmpty()) {
            throw new DukeException("No tasks in TaskList!");
        } else if (get(i).isMarked()) {
            throw new DukeException("Task is already unmarked!");
        } else {
            get(i).unmark();
        }
    }

    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }
}
