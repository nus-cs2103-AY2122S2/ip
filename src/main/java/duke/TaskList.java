package duke;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents list of user tasks.
 */
public class TaskList {
    protected ArrayList<Task> taskLists;

    /**
     * Constructs a TaskList object.
     *
     * @param taskList an array of Task.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskLists = taskList;
    }

    /**
     * Returns a size integer of TaskList object.
     *
     * @return size of TaskList object.
     */
    public int size() {
        return taskLists.size();
    }

    /**
     * Returns a Task object at that TaskList position.
     *
     * @param num position to get task.
     * @return
     */
    public Task get(int num) {
        return taskLists.get(num);
    }

    /**
     * Lists and print the all the Task in the TaskList.
     *
     * @return string of tasks.
     */
    public String list() {
        String output = "Here are the tasks in your list: \n";
        for (int i = 0; i < taskLists.size(); i++) {
            String display = String.format("%d.%s", i + 1, taskLists.get(i).toString());
            output += display;
        }
        return output;
    }

    /**
     * Removes task from TaskList.
     *
     * @param num position to remove task.
     */
    public String removeTask(int num, Storage storage) throws IOException {
        String output = "    Noted. I've removed this task:\n";
        String taskRemove = "        " + taskLists.get(num - 1).toString() + "\n";

        int initialTaskListsLength = taskLists.size();
        taskLists.remove(num - 1);
        assert taskLists.size() == initialTaskListsLength - 1 : "length of current tasklist should "
                + "decrease by one";

        TaskList tempTaskList = new TaskList(taskLists);
        storage.save(tempTaskList);
        String taskRemainingString = String.format("    Now you have %d tasks in the list.", taskLists.size());
        return output + taskRemove + taskRemainingString;
    }

    /**
     * Adds Task to TaskList.
     *
     * @param task a Task to be added.
     */
    public String addTask(Task task) {
        String displayTaskAmount = String.format("Now you have %d tasks in the list.", taskLists.size());

        // display to do task
        String output = "    Got it. I've added this task:\n";
        String addOn = "        " + task.toString() + "\n";
        String taskAmount = "    " + displayTaskAmount;

        return output + addOn + taskAmount;
    }

    /**
     * Adds Task to TaskList without printing the Task.
     *
     * @param task Task to add.
     */
    public void addWithoutPrint(Task task) {
        taskLists.add(task);
    }

    /**
     * Sets the specified Task as done.
     *
     * @param taskToMark an integer rank of Task in TaskList.
     */
    public String setTaskAsDone(int taskToMark) {
        taskLists.get(taskToMark - 1).markAsDone();

        String output = "    Nice! I've marked this task as done:\n";

        String taskMarkString = String.format("%s", taskLists.get(taskToMark - 1).toString());
        return output + "    " + taskMarkString;
    }

    /**
     * Sets the specified Task as undone.
     *
     * @param taskToUnmark an integer rank of task to mark as undone.
     */
    public String setTaskAsUnDone(int taskToUnmark) {
        taskLists.get(taskToUnmark - 1).markAsNotDone();
        String output = "    OK, I've marked this task as not done yet:\n";

        String taskString = String.format("%s", taskLists.get(taskToUnmark - 1).toString());
        return output + "    " + taskString;
    }

    /**
     * Tags specified task in the list.
     *
     * @param taskToTag index of task to be tagged starting from 1.
     * @param tag description of tag.
     * @param storage storage of task list.
     * @throws IOException
     */
    public TaskList tagTask(int taskToTag, String tag, Storage storage) throws IOException {
        Task taskToBeTagged = taskLists.get(taskToTag - 1);
        Tag taskTag = new Tag(tag);
        Task taggedTask = taskToBeTagged.tag(taskTag);

        // replace the original task to tagged task
        taskLists.set(taskToTag - 1, taggedTask);

        for (int i = 0; i < taskLists.size(); i++) {
            System.out.println(taskLists.get(i).toString());
        }

        // save the updated tasklist to disk
        TaskList tempTaskLists = new TaskList(taskLists);
        return tempTaskLists;
    }

    /**
     * Sets task at a particular index in a tasklist to another task.
     * @param taskToSet an index of a task to be set.
     * @param task new task to replace that position.
     */
    public void set(int taskToSet, Task task) {
        taskLists.set(taskToSet - 1, task);
    }
}
