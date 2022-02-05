package duke;

import java.util.LinkedList;

public class TaskList {
    protected LinkedList<Task> tasks;

    public TaskList() {
        this.tasks = new LinkedList<>();
    }

    public TaskList(LinkedList<Task> tasks) throws DukeException {
        this.tasks = tasks;
    }

    public void list() {
        Ui.list();
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(i + ". " + get(i - 1));
        }
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task get(int id) {
        return tasks.get(id);
    }

    public void update(int id, String command) {
        if (id > tasks.size()) {
            return;
        }
        switch (command) {
        case "mark":
            if (tasks.get(id - 1).isDone()) {
                Ui.alreadyDone(tasks.get(id - 1));
            } else {
                tasks.get(id - 1).setDone();
                Ui.done(tasks.get(id - 1).toString());
            }
            break;
        case "unmark":
            if (!tasks.get(id - 1).isDone()) {
                Ui.alreadyNotDone(tasks.get(id - 1));
            } else {
                tasks.get(id - 1).setNotDone();
                Ui.notDone(tasks.get(id - 1).toString());
            }
            break;
        case "remove":
            Ui.remove(tasks.remove(id - 1).toString());
            break;
        default:
            Ui.unknownCommand(command);
            break;
        }
    }
}
