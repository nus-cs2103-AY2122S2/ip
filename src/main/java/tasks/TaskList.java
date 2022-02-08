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
     * Method that takes in a taskId and deletes the task from the tasks arraylist
     * @param taskId id of task
     * @throws DukeException if the task cannot be found in the list
     */
    public static String deleteTask(int taskId) throws DukeException {
        Task preview = tasks.get(taskId - 1);
        // System.out.println(tasks.size());
        tasks.remove(taskId - 1);
        Storage.writeToDukeFile();
        return ui.showDeleteMsg(preview);
    }

    /**
     * Method that takes in a taskId and marks it as done in the tasks arraylist
     * @param taskId id of task
     * @param mark boolean indicating whether to mark the task as done
     * @throws DukeException if the task has yet to be done
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
     * Method that displays the list of tasks to the console
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
     * Method that takes in a list of tasks and displays the list of tasks to the console
     * @param tasks list of tasks
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
     * Method that returns the size/length of tasks
     * @return Integer that indicates the size/length of tasks
     */
    public static Integer getTaskSize() {
        return tasks.size();
    }

    /**
     * Method that takes in a keyword and prints out the list of tasks that contains the keyword
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
     * Method that is used to get tasks stored in Duke
     * @return an arraylist of tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Method that is used to undo a delete command
     * @param taskId id of the task
     * @param removedTask the type of task that is removed
     * @return a message stating the successful undo of a delete command
     * @throws DukeException when there are problems writing to duke file
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
     * Method to undo an add command
     * @return a message stating the successful undo of an add command
     * @throws DukeException when there are problems writing to duke file
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
