package arthur;

import static arthur.commons.Messages.DEADLINE_MISSING_DATE_MESSAGE;
import static arthur.commons.Messages.DELETE_REPLY_TEMPLATE;
import static arthur.commons.Messages.EVENT_MISSING_DATE_TIME_MESSAGE;
import static arthur.commons.Messages.FIND_NO_TASKS_TEMPLATE;
import static arthur.commons.Messages.FIND_REPLY_TEMPLATE;
import static arthur.commons.Messages.INVALID_TASK_NUMBER_MESSAGE;
import static arthur.commons.Messages.LIST_OUT_EMPTY_LIST_TEMPLATE;
import static arthur.commons.Messages.LIST_OUT_REPLY_TEMPLATE;
import static arthur.commons.Messages.MARK_REPLY_TEMPLATE;
import static arthur.commons.Messages.NEW_DEADLINE_REPLY_TEMPLATE;
import static arthur.commons.Messages.NEW_EVENT_REPLY_TEMPLATE;
import static arthur.commons.Messages.NEW_TODO_REPLY_TEMPLATE;
import static arthur.commons.Messages.UNMARK_REPLY_TEMPLATE;
import static arthur.commons.Prefixs.DEADLINE_REPLACE_WITH;
import static arthur.commons.Prefixs.DEADLINE_SPLIT_LOC;
import static arthur.commons.Prefixs.DEADLINE_TO_REPLACE;
import static arthur.commons.Prefixs.EVENT_REPLACE_WITH;
import static arthur.commons.Prefixs.EVENT_SPLIT_LOC;
import static arthur.commons.Prefixs.EVENT_TO_REPLACE;
import static arthur.commons.Prefixs.TASK_INFO_SPLIT_LOC;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import arthur.exceptions.InvalidStoredDataFormat;
import arthur.task.Deadline;
import arthur.task.Event;
import arthur.task.Task;
import arthur.task.Todo;
import arthur.timings.DateTime;


/**
 * Handles the list of tasks and operations on it.
 */
public class TaskList {
    private final ArrayList<Task> taskList;
    private final Storage storage;

    /**
     * Constructs the taskList object.
     *
     * @param storage Storage object to use to store files.
     */
    public TaskList(Storage storage) throws InvalidStoredDataFormat {
        this.taskList = new ArrayList<>();
        this.storage = storage;
        taskFiller();
    }

    /**
     * Lists out the tasks stored in the tasks Arraylist.
     *
     * @return A string version of the list formatted with numbering.
     */
    public String listOut() {
        if (taskList.isEmpty()) {
            return LIST_OUT_EMPTY_LIST_TEMPLATE;
        }
        StringBuilder temp = new StringBuilder(LIST_OUT_REPLY_TEMPLATE);
        int taskNum = 1;

        for (Task a : taskList) {
            temp.append(taskNum).append(". ").append(a).append("\n");
            taskNum++;
        }
        return temp.toString();
    }

    /**
     * Marks or Unmark the indicated task.
     *
     * @param e The instruction to follow with the task number.
     * @return The string result of the instruction.
     */
    public String marker(String e) {
        String[] temp = e.split(" ");

        try {
            Task currTask = taskList.get(Integer.parseInt(temp[1]) - 1);
            if (temp[0].equals("mark")) {
                currTask.mark();
                return MARK_REPLY_TEMPLATE + currTask;
            } else {
                currTask.unmark();
                return UNMARK_REPLY_TEMPLATE + currTask;
            }
        } catch (IndexOutOfBoundsException a) {
            return INVALID_TASK_NUMBER_MESSAGE;
        }
    }

    /**
     * Initialises and adds new Task.Todo task to task list.
     *
     * @param e The description of the todo task to be added.
     * @return String conformation of the input.
     */
    public String todo(String e) {
        Todo temp = new Todo(e);
        taskList.add(temp);
        return NEW_TODO_REPLY_TEMPLATE + temp
                + "\n" + this.outstanding();
    }

    /**
     * Initialises and adds new task with deadline to task list.
     * String after "/by " will be taken as the deadline info.
     *
     * @param e The description of the task with deadline info to be added.
     * @return String conformation of the input.
     */
    public String deadline(String e) {
        Deadline temp;
        try {
            String[] taskInfo = e.split(DEADLINE_SPLIT_LOC);
            String desc = taskInfo[0];
            String date = taskInfo[1];
            temp = new Deadline(desc, date);
        } catch (IndexOutOfBoundsException a) {
            return DEADLINE_MISSING_DATE_MESSAGE;
        }
        taskList.add(temp);
        return NEW_DEADLINE_REPLY_TEMPLATE + temp
                + "\n" + this.outstanding();
    }

    /**
     * Initialises and adds new task with timing to task list.
     * String after "/at " will be taken as the date/time the task
     * would occur.
     *
     * @param e The description of the task with timing info to be added.
     * @return String conformation of the input.
     */
    public String event(String e) {
        Event temp;
        try {
            String[] taskInfo = e.split(EVENT_SPLIT_LOC);
            String desc = taskInfo[0];
            String dateTime = taskInfo[1];
            temp = new Event(desc, dateTime);
        } catch (IndexOutOfBoundsException a) {
            return EVENT_MISSING_DATE_TIME_MESSAGE;
        }

        taskList.add(temp);
        return NEW_EVENT_REPLY_TEMPLATE + temp
                + "\n" + this.outstanding();
    }

    /**
     * Generates a string about the number of tasks left in list.
     *
     * @return String info regarding num of tasks left.
     */
    public String outstanding() {
        return "You have "
                + this.taskList.size()
                + " tasks in list at the moment.";
    }

    /**
     * Deletes the task from the list.
     *
     * @param i The task number to delete.
     * @return String conformation of the task deletion.
     */
    public String deleter(int i) {
        try {
            Task currTask = taskList.remove(i - 1);
            return DELETE_REPLY_TEMPLATE + currTask
                    + "\n" + outstanding();
        } catch (IndexOutOfBoundsException e) {
            return INVALID_TASK_NUMBER_MESSAGE;
        }
    }

    /**
     * Copies over tasks in the data file to the taskList.
     */
    private void taskFiller() throws InvalidStoredDataFormat {
        try {
            Scanner sc = new Scanner(storage.getTasks());
            while (sc.hasNext()) {
                String currTask = sc.nextLine();
                String taskInfo = currTask.split(TASK_INFO_SPLIT_LOC)[1];
                char taskType = currTask.charAt(1);
                char marking = currTask.charAt(4);
                int lastTaskIndex = taskList.size();
                taskCreator(taskInfo, taskType);

                if (marking == 'X') {
                    taskList.get(lastTaskIndex).mark();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates the respective task object using the info from the stored data.
     *
     * @param taskInfo The task description as stated in the stored data file.
     * @param taskType The task logo as states in the stored data file.
     * @throws InvalidStoredDataFormat When the stored data doesn't match the format.
     */
    private void taskCreator(String taskInfo, char taskType) throws InvalidStoredDataFormat {
        switch (taskType) {
        case 'T':
            todo(taskInfo);
            break;
        case 'D':
            taskInfo = deadlineTaskFormat(taskInfo);
            deadline(taskInfo);
            break;
        case 'E':
            taskInfo = eventTaskFormat(taskInfo);
            event(taskInfo);
            break;
        default:
            throw new InvalidStoredDataFormat();
        }
    }

    /**
     * Gets the Task object at the given index from taskList.
     *
     * @param num The index of the Task object to retrieve.
     * @return A Task object.
     * @throws IndexOutOfBoundsException Incorrect index needs to be handled properly.
     */
    public Task getTask(int num) throws IndexOutOfBoundsException {
        return this.taskList.get(num);
    }

    /**
     * Gives the taskList size.
     *
     * @return list size in int.
     */
    public int tasksSize() {
        return this.taskList.size();
    }

    /**
     * Finds tasks matching the given str.
     * Both the input string case and stored task description case is ignored.
     *
     * @param str Used to find task similar to this input.
     * @return A string of all the matching tasks.
     */
    public String find(String str) {
        StringBuilder temp = new StringBuilder(FIND_REPLY_TEMPLATE);
        String refTaskText = str.toLowerCase();
        String currTaskText;
        boolean hasFoundTask = false;
        int taskNum = 1;

        for (Task currTask : taskList) {
            String currTaskString = currTask.toString();
            currTaskText = currTaskString.toLowerCase();
            if (currTaskText.contains(refTaskText)) {
                temp.append(taskNum).append(". ").append(currTask).append("\n");
                taskNum++;
                hasFoundTask = true;
            }
        }
        return hasFoundTask
                ? temp.toString()
                : FIND_NO_TASKS_TEMPLATE;
    }

    /**
     * Formats stored deadline task info to user input format.
     *
     * @param input deadline task info stored in data file.
     * @return Formatted version of the task info.
     */
    public String deadlineTaskFormat(String input) throws InvalidStoredDataFormat {
        return stringConverter(input, DEADLINE_TO_REPLACE, DEADLINE_REPLACE_WITH, DEADLINE_SPLIT_LOC);
    }

    /**
     * Formats stored event task info to user input format.
     *
     * @param input event task info stored in data file.
     * @return Formatted version of the task info.
     */
    public String eventTaskFormat(String input) throws InvalidStoredDataFormat {
        return stringConverter(input, EVENT_TO_REPLACE, EVENT_REPLACE_WITH, EVENT_SPLIT_LOC);
    }

    /**
     * Formats the stored data file info to user input style string.
     *
     * @param input The string stored in the data file to be converted.
     * @param toReplace The format to replace.
     * @param replaceWith The format to replace with.
     * @param splitLoc The location to split to separate description and date/time.
     * @return A user input style format string.
     * @throws InvalidStoredDataFormat When the stored data is in incorrect/invalid format.
     */
    private String stringConverter(String input, String toReplace, String replaceWith, String splitLoc)
            throws InvalidStoredDataFormat {
        String result = input;
        result = result.replaceFirst(toReplace, replaceWith);
        result = result.replaceFirst("\\)", "");
        String[] formatArr = result.split(splitLoc);
        formatArr[1] = new DateTime().stringToDateFormat(formatArr[1]);
        result = String.join(splitLoc, formatArr);
        return result;
    }
}
