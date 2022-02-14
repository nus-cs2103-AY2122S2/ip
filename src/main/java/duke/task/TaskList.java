package duke.task;

import duke.exception.InvalidTaskNumberException;
import duke.task.tasks.ITask;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents tasks in a form of list. Handle adding new tasks,
 * removing tasks, updating tasks
 */
public class TaskList {
    private List<ITask> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<ITask> tasks) {
        this.tasks = tasks;
    }

    /**
     * Add a new task into the tasks list. will return the string to be printed
     * by the Ui
     *
     * @param newTask new task to be added into the list
     * @return String to be printed by the Ui containing information of the task.
     */
    public String addTask(ITask newTask) {
        assert newTask != null;

        tasks.add(newTask);
        String output = "Got it. I've added this task:\n  " + newTask
                + "\nnow you have " + tasks.size() + " tasks in the list";
        return output;
    }

    /**
     * return the current number of tasks inside the list
     *
     * @return number of tasks inside the list
     */
    public int getNumberOfTasks() {
        return tasks.size();
    }

    /**
     * Returns the string representation of all the tasks
     * inside the list.
     *
     * @return String representation of the tasks.
     */
    public String getTasks() {
        String output = "Here are the tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            output += String.format("\n%d.%s", i + 1, tasks.get(i));
        }
        return output;
    }

    public List<ITask> listTasks() {
        return tasks;
    }

    /**
     * Change the state of specified task, from checked to unchecked and
     * vice versa.
     *
     * @param id specified task to be modified
     * @param instr the instruction to be done, either mark or unmark
     * @return  String contains the information of the modified task
     * @throws InvalidTaskNumberException If id > index or id <= 0 (can't modify)
     */
    public String mark(int id, String instr) throws InvalidTaskNumberException {
        if (id <= 0 || id > tasks.size()) {
            throw new InvalidTaskNumberException();
        }

        String out = tasks.get(id - 1).switchMark(instr);
        return out;
    }

    public TaskList cloneSelf() {
        ArrayList<ITask> copyArray = new ArrayList<>();
        for (ITask task: tasks) {
            copyArray.add(task.cloneSelf());
        }
        return new TaskList(copyArray);
    }

    /**
     * Remove the specified task from the list.
     *
     * @param id id of the task to be removed
     * @return String contains the information of the removed task
     * @throws InvalidTaskNumberException If id > index or id <= 0 (can't remove)
     */
    public String remove(int id) throws InvalidTaskNumberException {
        if (id <= 0 || id > tasks.size()) {
            throw new InvalidTaskNumberException();
        }

        ITask removed = tasks.remove(id - 1);
        String out = "Noted, I have removed this task:\n  "
                + removed + "\nnow you have " + tasks.size() + " tasks in the list.";
        return out;
    }

    public String findKeyWord(String word) {
        assert word != null;

        String output = "Here are the matching tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).hasWord(word)) {
                output += String.format("\n%d.%s", i + 1, tasks.get(i));
            }
        }
        return output;
    }

    public String copyStateFrom(TaskList lastState) {
        if (lastState == null) {
            return "You have not done any commands yet";
        }
        this.tasks = lastState.tasks;
        return "Undo'd, now " + getTasks();
    }
}
