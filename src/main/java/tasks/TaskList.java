package tasks;

import java.util.ArrayList;
import java.util.Locale;

import duke.Storage;
import duke.TextUi;
import duke.exceptions.DukeException;
import duke.exceptions.TaskException;
import duke.exceptions.UndoException;


public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static final TextUi ui = new TextUi();

    /**
     * Instantiates a taskList with a list of tasks
     * @param tasks list of tasks
     *
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Instantiates a taskList
     */
    public TaskList() { }

    /**
     * Returns a success string after a successful deletion of a task from the taskList
     * If the taskId is invalid, an exception will be thrown.
     * @param taskId Index of task.
     * @throws DukeException If the task cannot be found in the list.
     */
    public static String deleteTask(int taskId) throws DukeException {
        Task preview = tasks.get(taskId - 1);
        tasks.remove(taskId - 1);
        Storage.writeToDukeFile();
        return ui.showDeleteMsg(preview);
    }

    /**
     * Returns a success string after marking a particular task in the task list.
     * If the taskId provided to the function is invalid, a TaskException will be thrown.
     * @param taskId Index of task.
     * @param mark Boolean indicating whether to mark the task as done.
     * @throws DukeException If the task has yet to be done.
     */
    public static String markTask(int taskId, boolean mark) throws DukeException {
        Task toSet = tasks.get(taskId - 1);
        if (mark) {
            if (!toSet.isDone) {
                toSet.markIsDone();
                tasks.set(taskId - 1, toSet);
                Storage.writeToDukeFile();
                return ui.showMarkDoneMsg(toSet);
            } else {
                throw new TaskException("DONE");
            }
        } else {
            if (toSet.isDone) {
                toSet.markUndone();
                tasks.set(taskId - 1, toSet);
                Storage.writeToDukeFile();
                return ui.showMarkUndoneMsg(toSet);
            } else {
                throw new TaskException("UNDONE");
            }
        }
    }

    /**
     * Method that takes in a task and adds it to tasks
     * @param task task object
     * @throws DukeException if unable to add task to tasks
     */
    public static String addTask(Task task) throws DukeException {
        tasks.add(task);
        Storage.writeToDukeFile();
        return ui.showAddMsg(tasks.size());
    }

    /**
     * Returns a string displaying all the current tasks in the task list.
     * Will return an empty message if there are no tasks in the task list.
     */
    public static String listTasks() {
        if (tasks.size() == 0) {
            return ui.showEmptyMsg();
        }
        StringBuilder listOfTasks = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            listOfTasks.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return listOfTasks.toString();
    }

    /**
     * Returns a string displaying all the tasks provided by the tasks arraylist.
     * Will return an empty message if there are no tasks in the tasks list.
     * @param tasks An arraylist of tasks.
     */
    public static String listTasks(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            return ui.showEmptyMsg();
        }
        StringBuilder listOfTasks = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            listOfTasks.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return listOfTasks.toString();
    }

    /**
     * Returns the size of the task list that is saved in the system.
     * @return Integer that indicates the size/length of tasks.
     */
    public static Integer getTaskSize() {
        return tasks.size();
    }

    /**
     * Returns a string of tasks that matches the keyword that the user has inputted into the system.
     * Returns an empty message if no matching tasks can be found.
     * @param keyWord task keyword
     */
    public static String findTask(String keyWord) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            String taskDescription = task.getDescription().toLowerCase(Locale.ROOT);
            String keyWordDescription = keyWord.toLowerCase(Locale.ROOT);
            if (taskDescription.matches("\\b" + keyWordDescription + "\\b")) {
                matchingTasks.add(task);
            }
        }
        return ui.showFindTaskMsg(listTasks(matchingTasks));
    }

    /**
     * Returns the task list that is stored in the system.
     * @return an arraylist of tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns a success message after the successful undo of a delete command.
     * If there are no more commands to undo, it will throw an UndoException.
     * Additionally, it will throw a DukeException if there are problems writing to the Duke file.
     * @param taskId Index of the task.
     * @param removedTask The type of task that is removed.
     * @return A message stating the successful undo of a delete command.
     * @throws DukeException When there are problems writing to duke file or there are
     * issues with undoing the previous command.
     */
    public static String addTaskBack(Integer taskId, Task removedTask) throws DukeException {
        try {
            tasks.add(taskId - 1, removedTask);
            Storage.writeToDukeFile();
            return ui.showUndoMsg("Delete");
        } catch (IndexOutOfBoundsException e) {
            throw new UndoException("EMPTY_PREVIOUS");
        }
    }

    /**
     * Returns a success message after the successful undo of a todo/event/deadline command.
     *  f there are no more commands to undo, it will throw an UndoException.
     * Additionally, it will throw a DukeException if there are problems writing to the Duke file.
     * @return a message stating the successful undo of an add command.
     * @throws DukeException when there are problems writing to duke file or there are issues with
     * undoing the previous command.
     */
    public String deleteLastTask() throws DukeException {
        try {
            tasks.remove(tasks.size() - 1);
            Storage.writeToDukeFile();
            return ui.showUndoMsg("Add");
        } catch (IndexOutOfBoundsException e) {
            throw new UndoException("EMPTY_PREVIOUS");
        }
    }

}
