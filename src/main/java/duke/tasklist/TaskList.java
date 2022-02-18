package duke.tasklist;
import java.util.ArrayList;
import java.util.Arrays;

import duke.storage.Storage;
import duke.task.Tasks;
import duke.ui.Ui;

/**
 *
 */
public class TaskList {

    private static final int ONE_BASEDINDEX= 1;
    private static final int ZERO_BASEDINDEX= 1;
    private final ArrayList<Tasks> taskList;

    /**
     *
     * @param taskList
     */
    public TaskList(ArrayList<Tasks> taskList) {
        this.taskList = new ArrayList<Tasks>(taskList);
    }

    /**
     *
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
    // Maybe can abstract out the printing portion
    /**
     *
     * @param query
     * @return
     */
    public String queryTasks (String query) {
        ArrayList<Tasks> filteredData = filterDataSet(query);
        return Ui.returnQueriedTaskRes(listInPrintFormat(filteredData));
    }

    // Delete duke.task -> returns duke.task deleted, then returns string to append
    /**
     * Initiate the main bulk of adding a task from the user input into the database.
     * If this is successful, it will return a boolean value of True.
     *
     * @param task Task to be added to the database
     * @param storage Storage that facilitate writing of Task into the database
     * @return returns a boolean value of the success of adding a task
     */
    public String addsTask(Tasks task, Storage storage) {
        if (storage.hasAppendToDatabase(task.toDatabaseString() + "\n")){
            return Ui.returnAddTaskRes(task.toString(), Boolean.TRUE);
        }
        return Ui.returnAddTaskRes(task.toString(), Boolean.FALSE);
    }

    private String listInDataFormat(ArrayList<Tasks> edittedTasksList){
        StringBuilder tasksToDataFormat = new StringBuilder();
        for (int i = 0; i < edittedTasksList.size(); i++) {
            tasksToDataFormat.append(edittedTasksList.get(i).toDatabaseString()).append("\n");
        }
        return tasksToDataFormat.toString();
    }

    private String listInPrintFormat(ArrayList<Tasks> edittedTasksList){
        StringBuilder tasksToDataFormat = new StringBuilder();
        for (int i = 0; i < edittedTasksList.size(); i++) {
            tasksToDataFormat.append("   ").append(i+ONE_BASEDINDEX).append(". ")
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

        for (int i = arrayCounter(taskIndicesToDelete) - ONE_BASEDINDEX; i>=0; i--) {
            collatedStatus += deletesTask(taskIndicesToDelete[i], duplicateTaskList, storage);
            duplicateTaskList = storage.load();
        }
        return Ui.returnDeleteHandlerRes(collatedStatus);
    }

    public String listsTask() {
        return Ui.returnListTaskRes(listInPrintFormat(taskList));
    }

    private int arrayCounter(int[] array) {
        return array.length;
    }

    private int fileContentCounterZeroed() {
        return taskList.size() - ZERO_BASEDINDEX;
    }
}
