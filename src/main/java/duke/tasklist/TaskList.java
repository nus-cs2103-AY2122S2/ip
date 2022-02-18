package duke.tasklist;
import java.util.ArrayList;
import java.util.Arrays;

import duke.storage.Storage;
import duke.task.Tasks;
import duke.ui.Ui;

/**
 * The TaskList class is a class that help facilitate the CRUD function for the PokeTasks. The process is
 * facilitated with an ArrayList of Tasks loaded from the Storage.
 */
public class TaskList {

    private static final int ONE_BASEDINDEX = 1;
    private static final int ZERO_BASEDINDEX = 1;
    private final ArrayList<Tasks> taskList;

    /**
     * One of the two constructors available to create a TaskList instance.
     *
     * @param taskList An array that contains all the tasks.
     */
    public TaskList(ArrayList<Tasks> taskList) {
        this.taskList = new ArrayList<Tasks>(taskList);
    }

    /**
     * One of the two constructors available to create a TaskList instance.
     */
    public TaskList() {
        this.taskList = new ArrayList<Tasks>();
    }

    private boolean filterDataKeyword(String query, String[] taskNameSplit) {
        for (int j = 0; j < taskNameSplit.length; j++) {
            if (taskNameSplit[j].equals(query)) {
                return true;
            }
        }
        return false;
    }

    private ArrayList<Tasks> filterDataSet(String query) {
        ArrayList<Tasks> unfilteredTasks = new ArrayList<Tasks>(taskList);
        ArrayList<Tasks> filteredTasks = new ArrayList<Tasks>();
        for (int i = 0; i < unfilteredTasks.size(); i++) {
            String taskName = unfilteredTasks.get(i).getName();
            String[] taskNameSplit = taskName.split(" ");
            if (filterDataKeyword(query, taskNameSplit)) {
                filteredTasks.add(unfilteredTasks.get(i));
            }
        }
        return filteredTasks;
    }

    /**
     * A helper that facilitate the querying of tasks that contains a keyword.
     *
     * @param query The keyword to query the database of tasks.
     * @return A String value denoting the tasks containing the query keyword.
     */
    public String queryTasks (String query) {
        ArrayList<Tasks> filteredData = filterDataSet(query);
        return Ui.returnQueriedTaskRes(listInPrintFormat(filteredData));
    }

    /**
     * Add a task into the database.
     *
     * @param task Task to be added to the database
     * @param storage Storage of the program.
     * @return returns a String denoting the status of the program.
     */
    public String addsTask(Tasks task, Storage storage) {
        if (storage.hasAppendToDatabase(task.toDatabaseString() + "\n")) {
            return Ui.returnAddTaskRes(task.toString(), Boolean.TRUE);
        }
        return Ui.returnAddTaskRes(task.toString(), Boolean.FALSE);
    }

    private String listInDataFormat(ArrayList<Tasks> edittedTasksList) {
        StringBuilder tasksToDataFormat = new StringBuilder();
        for (int i = 0; i < edittedTasksList.size(); i++) {
            tasksToDataFormat.append(edittedTasksList.get(i).toDatabaseString()).append("\n");
        }
        return tasksToDataFormat.toString();
    }

    private String listInPrintFormat(ArrayList<Tasks> edittedTasksList) {
        StringBuilder tasksToDataFormat = new StringBuilder();
        for (int i = 0; i < edittedTasksList.size(); i++) {
            tasksToDataFormat.append("   ").append(i + ONE_BASEDINDEX).append(". ")
                    .append(edittedTasksList.get(i).toString()).append("\n");
        }
        return tasksToDataFormat.toString();
    }
    /**
     * This method helps to facilitate the marking of a task as completed. If this is
     * success in marking the task, a boolean value of true will be returned. Else,
     * it returns false.
     *
     * @param storage Storage that facilitate writing of Task into the database
     * @param taskIndexToMark Index of task to be marked in the database
     * @param taskCompletion A boolean value indicating whether to mark it as completed or not
     * @return returns a boolean value indicative of the success of marking a task
     */
    public String marksTask(Storage storage, int taskIndexToMark, boolean taskCompletion) {
        boolean isIndexOutOfBound = taskIndexToMark > fileContentCounterZeroed();
        if (isIndexOutOfBound) {
            return Ui.returnIndexErrorRes();
        }

        ArrayList<Tasks> newList = new ArrayList<>(taskList);
        Tasks editTask = newList.get(taskIndexToMark);
        if (taskCompletion) {
            newList.set(taskIndexToMark, editTask.completeTask());
        } else {
            newList.set(taskIndexToMark, editTask.uncompleteTask());
        }
        System.out.println(listInPrintFormat(newList));
        System.out.println(listInDataFormat(newList));
        if (storage.hasWriteToDatabase(listInDataFormat(newList))) {
            return Ui.returnEditTaskRes(newList.get(taskIndexToMark).toString(), Boolean.TRUE);
        }
        return Ui.returnEditTaskRes("", Boolean.FALSE);
    }

    private ArrayList<Tasks> removeTaskFromList(ArrayList<Tasks> taskListToEdit, int taskIndex) {
        ArrayList<Tasks> duplicateList = new ArrayList<>(taskListToEdit);
        duplicateList.remove(taskIndex);
        return duplicateList;
    }
    /**
     * This method helps to facilitate the deletion of a task. If this is
     * successful in deleting the task, a boolean value of true will be returned. Else,
     * false is returned.
     *
     * @param taskIndexToDelete Index of task to be deleted in the database
     * @param storage Storage that facilitate writing of Task into the database
     * @return a boolean value indicative of the success of marking a task
     */
    private String deletesTask(int taskIndexToDelete, ArrayList<Tasks> taskListToEdit, Storage storage) {
        boolean isIndexOutOfBound = taskIndexToDelete > fileContentCounterZeroed();
        if (isIndexOutOfBound) {
            return Ui.returnDeleteTaskError(taskIndexToDelete);
        }

        ArrayList<Tasks> duplicateList = new ArrayList<>(taskListToEdit);
        Tasks deletedTask = duplicateList.get(taskIndexToDelete);
        ArrayList<Tasks> edittedTaskList = removeTaskFromList(duplicateList, taskIndexToDelete);

        if (storage.hasWriteToDatabase(listInDataFormat(edittedTaskList))) {
            return Ui.returnDeleteTaskRes(deletedTask.toString(), Boolean.TRUE);
        }
        return Ui.returnDeleteTaskRes("", Boolean.FALSE);
    }

    /**
     * This is a handler that help facilitate the single or mass deletion of tasks.
     *
     * @param taskIndicesToDelete An array of indices that indicate tasks to be deleted.
     * @param storage Represents the storage of the program.
     * @return A String representing the status of the completion of the method.
     */
    public String deleteTaskHandler(int[] taskIndicesToDelete, Storage storage) {
        if (arrayCounter(taskIndicesToDelete) <= 0) {
            return Ui.returnNoTaskRes();
        }

        ArrayList<Tasks> duplicateTaskList = new ArrayList<>(taskList);
        Arrays.sort(taskIndicesToDelete);
        String collatedStatus = "";
        if (arrayCounter(taskIndicesToDelete) == 1) {
            collatedStatus += deletesTask(taskIndicesToDelete[0], duplicateTaskList, storage);
            return Ui.returnDeleteHandlerRes(collatedStatus);
        }

        for (int i = arrayCounter(taskIndicesToDelete) - ONE_BASEDINDEX; i >= 0; i--) {
            collatedStatus += deletesTask(taskIndicesToDelete[i], duplicateTaskList, storage);
            duplicateTaskList = storage.load();
        }
        return Ui.returnDeleteHandlerRes(collatedStatus);
    }

    /**
     * A helper that faciltiate the listing of all tasks in the storage.
     *
     * @return A String of all the tasks in the storage.
     */
    public String listsTask() {
        return Ui.returnListTaskRes(listInPrintFormat(taskList));
    }

    int arrayCounter(int[] array) {
        return array.length;
    }

    int fileContentCounterZeroed() {
        return taskList.size() - ZERO_BASEDINDEX;
    }
}
