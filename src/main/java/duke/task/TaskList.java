package duke.task;

import duke.exception.DukeException;
import duke.exception.TaskOutOfBoundException;
import duke.ui.Ui;
import duke.utility.Storage;

import java.util.List;


/**
 * Public class for task list.
 */
public class TaskList {

    public static TaskList taskList;
    public Storage storage;
    public List<Task> tasks;

    private TaskList(Storage db) {
        this.storage = db;
        tasks = storage.readAllTasks();
    }

    public static synchronized TaskList getCurrentList(Storage database) {
        if (taskList == null) {
            taskList = new TaskList(database);
        }
        return taskList;
    }

    public String addTasks(Task task) {
        tasks.add(task);
        int size = tasks.size();
        String msg = Ui.printAddTask(task, size);
        storage.update(tasks);
        return msg;
    }

    public String deleteTasks(int index) throws TaskOutOfBoundException {
        try {
            String message = Ui.printDelete(tasks.get(index), tasks.size() - 1);
            tasks.remove(index);
            storage.update(tasks);
            return message;
        } catch (IndexOutOfBoundsException e) {
            throw new TaskOutOfBoundException("Not inside the range of task list!", index + 1);
        }
    }

    public String ListTasks() throws DukeException {
        if (tasks.size() == 0) {
            return Ui.printNoTaskReminder();
        }
        return Ui.printAllTask(tasks, true);
    }

    public String query(String key) throws DukeException {
        List<Task> tasks = storage.query(key);
        return Ui.printAllTask(tasks, false);
    }

    public String markTask(int index) throws TaskOutOfBoundException {
        try {
            Task t = this.tasks.get(index);
            t.setStatus(true);
            String message = Ui.printDoneTask(t);
            storage.update(tasks);
            return message;
        } catch (IndexOutOfBoundsException e) {
            throw new TaskOutOfBoundException("Not inside the range of the task list!", index + 1);
        }
    }

}
