package duke;

import java.util.ArrayList;

/**
 * An ArrayList container for {@code Task} object
 */
public class TaskList {
    private static Ui ui;
    private final DukeException dukeException;
    public ArrayList<Task> taskList;


    /**
     * Default Constructor for {@code TaskList}
     * This constructor will be used when there is no previous TaskList to load from.
     */
    public TaskList() {
        taskList = new ArrayList<>();
        dukeException = new DukeException();
        ui = new Ui();
    }

    /**
     * Default Constructor for {@code TaskList}
     * This constructor will be used when there is an existing taskList to load from.
     *
     * @param taskList ArrayList of {@code Task}
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
        dukeException = new DukeException();
        ui = new Ui();
    }

    /**
     * Returns ArrayList of current tasks.
     *
     * @return An ArrayList of task
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Get the size of current {@code taskList}
     *
     * @return Integer value of {@code taskList} size.
     */
    public int getSize() {
        return taskList.size();
    }


    /**
     * Add task to taskList.
     *
     * @param task Task to be added to the taskList
     * @return Message when adding task into taskList
     */
    public String addTask(Task task) {
        StringBuilder result = new StringBuilder();
        taskList.add(task);
        result.append(ui.addTask(task));
        result.append("\r\n");
        result.append(ui.displayTaskAmount(taskList));
        return result.toString();
    }

    /**
     * Given a Task Index, Set Task to done, and marks the Task.
     *
     * @param index Index of Task
     * @return Message when task is marked, or if selected index is invalid.
     */
    public String mark(int index) {
        try {
            Task selectedTask = taskList.get(index - 1);
            selectedTask.setDone();
            return ui.markTask(selectedTask);
        } catch (Exception e) {
            return "Task is invalid, please select a valid task number.";
        }
    }

    /**
     * Given a Task Index, Set Task to not done, and unchecks the Task.
     *
     * @param index Index of Task
     * @return Message when task is unmarked, or if selected index is invalid.
     */
    public String unmark(int index) {
        try {
            Task selectedTask = taskList.get(index - 1);
            selectedTask.setUndone();
            return ui.unmarkTask(selectedTask);
        } catch (Exception e) {
            return "Task is invalid, please select a valid task number.";
        }
    }

    /**
     * Given a Task Index, Delete the specific Task.
     *
     * @param index Index of Task
     * @return Message to user that specific task has been removed, otherwise throw DukeException handler.
     */
    public String delete(int index) {
        try {
            StringBuilder result = new StringBuilder();
            Task removedTask = taskList.remove(index - 1);
            result.append(ui.deleteTask(removedTask)).append("\r\n");
            result.append(ui.displayTaskAmount(taskList));
            return result.toString();
        } catch (NumberFormatException noTaskNumber) {
            return dukeException.noTaskNumber();
        } catch (IndexOutOfBoundsException invalidTaskNumber) {
            return dukeException.invalidTaskNumber();
        }
    }

    /**
     * Given a Task Index, and  Delete the specific Task.
     *
     * @param index Index of Task
     * @param tagDescription String input of what the task should be tagged with
     * @return Message to user that specific task has been tagged accordingly, otherwise throw DukeException handler.
     */
    public String tag(int index, String tagDescription) {
        try {
            Task selectedTask = taskList.get(index - 1);
            selectedTask.addTag(tagDescription);
            return "Successfully added tag " +
                    tagDescription +
                    " to:\r\n" +
                    selectedTask;
        } catch (NumberFormatException noTaskNumber) {
            return dukeException.noTaskNumber();
        } catch (IndexOutOfBoundsException invalidTaskNumber) {
            return dukeException.invalidTaskNumber();
        }
    }


    /**
     * Given a Task Index, and  Delete the specific Task.
     *
     * @param index Index of Task
     * @param tagIndex String input of what the task should be tagged with
     * @return Message to user that specific task has been tagged accordingly, otherwise throw DukeException handler.
     */
    public String removeTag(int index, int tagIndex) {
        try {
            Task selectedTask = taskList.get(index - 1);
            String removedTag = selectedTask.removeTag(tagIndex);
            return "Successfully removed tag " + removedTag + " from:\r\n" + selectedTask;
        } catch (NumberFormatException noTaskNumber) {
            return dukeException.noTaskNumber();
        } catch (IndexOutOfBoundsException invalidTaskNumber) {
            return dukeException.invalidTaskNumber();
        }
    }

    /**
     * Method is used to scan through current TaskList, searching by supplied keyword.
     *
     * @param matchingDescription Description of Task that User is finding
     * @return List of tasks found with matching description
     */
    public String find(String matchingDescription) {
        if(matchingDescription.startsWith("#")) {
            String tagDescription = matchingDescription.replace("#", "");
            ArrayList<Task> matchingTasks = new ArrayList<>();
            ArrayList<Integer> indexList = new ArrayList<>();
            for(int i = 0; i < taskList.size(); i++) {
                Task currentTask = taskList.get(i);
                ArrayList<String> tagList = currentTask.getTags();
                if (tagList.contains(tagDescription)) {
                    indexList.add(i+1);
                    matchingTasks.add(currentTask);
                }
            }
            return "Here are your task with " +
                    matchingDescription +
                    ui.displayFoundTasks(matchingTasks,indexList);
        } else {
            ArrayList<Task> matchingTasks = new ArrayList<>();
            ArrayList<Integer> indexList = new ArrayList<>();
            for(int i = 0; i < taskList.size(); i++) {
                Task currentTask = taskList.get(i);
                String currentDescription = currentTask.getDescription().toLowerCase();
                matchingDescription = matchingDescription.toLowerCase();
                if (currentDescription.contains(matchingDescription)) {
                    matchingTasks.add(currentTask);
                    indexList.add(i+1);
                }
            }
            return ui.displayFoundTasks(matchingTasks, indexList);
        }
    }
}