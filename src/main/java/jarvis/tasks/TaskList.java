package jarvis.tasks;

import java.util.ArrayList;

import jarvis.exceptions.InvalidTaskException;
import jarvis.exceptions.TagNotFoundException;
import jarvis.tags.Tag;
import jarvis.tags.TagList;
import jarvis.utils.Storage;

public class TaskList {
    private static final int CAPACITY = 100;

    private final Storage storage;
    private final ArrayList<Task> taskList;
    private final TagList tagList;
    private final boolean useStorage;

    /**
     * Constructor for TaskList
     */
    public TaskList(Storage storage) {
        this.storage = storage;

        if (storage.fileExist()) {
            this.taskList = storage.readData();
        } else {
            this.taskList = new ArrayList<>(CAPACITY);
        }
        this.tagList = new TagList();
        this.useStorage = true;
    }

    /**
     * Constructor for TaskList that is non-persistent.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.storage = null;
        this.taskList = taskList;
        this.tagList = new TagList();
        this.useStorage = false;
    }

    /**
     * Update the data in the storage.
     */
    public void updateStorage() {
        if (useStorage) {
            this.storage.writeData(taskList);
        }
    }

    /**
     * Get the task list.
     *
     * @return array of tasks
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Get the tag list.
     *
     * @return array of tasks
     */
    public TagList getTagList() {
        return this.tagList;
    }

    /**
     * Get the task object from the tasklist.
     * @param idx index of the task
     * @return task object
     */
    public Task getTask(int idx) throws IndexOutOfBoundsException {
        return this.taskList.get(idx - 1);
    }

    /**
     * Add a new item to the list.
     *
     * @param taskString string representation of the task
     */
    public String add(String taskString) throws InvalidTaskException {
        if (taskList.size() > CAPACITY) {
            throw new InvalidTaskException("Capacity has been reached");
        }

        Task task = Task.of(taskString);
        this.taskList.add(task);
        updateStorage();

        return "Got it. I've added this task:\n"
                + String.format("\t%s\n", task)
                + String.format("Now you have %d tasks in the list.", this.taskList.size());
    }

    /**
     * Mark the task as completed given a task index.
     *
     * @param idx index of the task in the taskList
     * @return status string
     */
    public String markTask(int idx) throws IndexOutOfBoundsException {
        Task task = this.taskList.get(idx - 1);
        task.markAsCompleted();
        updateStorage();

        return "Nice! I've marked this task as done:\n"
                + String.format("\t%s", task);
    }

    /**
     * Mark the task as uncompleted given a task index.
     *
     * @param idx idx index of the task in the taskList
     * @return status string
     */
    public String unmarkTask(int idx) throws IndexOutOfBoundsException {
        Task task = this.taskList.get(idx - 1);
        task.markAsUncompleted();
        updateStorage();

        return "OK, I've marked this task as not done yet:\n"
                + String.format("\t%s", task);
    }

    /**
     * Delete the task specified.
     *
     * @param idx idx index of the task in the taskList
     * @return status string
     * @throws IndexOutOfBoundsException index given is out of bounds
     */
    public String delete(int idx) throws IndexOutOfBoundsException {
        Task task = this.taskList.remove(idx - 1);
        updateStorage();

        return "Noted. I've removed this task:\n"
                + String.format("\t%s\n", task)
                + String.format("Now you have %d tasks in the list.", this.taskList.size());
    }

    /**
     * Add a tag to a task.
     * @param taskIdx index of a task
     * @param tagName name of the tag
     * @return status string
     * @throws TagNotFoundException tag not found in the list
     */
    public String tagTask(int taskIdx, String tagName) throws TagNotFoundException {
        if (taskIdx > taskList.size()) {
            throw new TagNotFoundException("No such task found");
        }
        Task task = this.taskList.get(taskIdx - 1);
        if (!tagList.contains(tagName)) {
            tagList.add(tagName);
        }
        Tag tag = tagList.getTag(tagName);
        task.tag(tag);

        return String.format("OK, I've tagged this task with %s:\n", tag)
            + String.format("\t%s", task);
    }

    /**
     * Removes a tag to a task.
     * @param taskIdx index of a task
     * @param tagName name of the tag
     * @return status string
     */
    public String untagTask(int taskIdx, String tagName) throws TagNotFoundException {
        Task task = this.taskList.get(taskIdx - 1);
        if (!task.hasTag(tagName)) {
            return String.format("No tags by the name of %s found", tagName);
        }
        Tag tag = task.getTag(tagName);
        task.untag(tag);

        return String.format("OK, I've untagged %s from this task:\n", tag)
            + String.format("\t%s", task);
    }

    /**
     * Find tasks that contain the keyword.
     *
     * @param keywords strings to search for
     * @return an array list of tasks that matches
     */
    public String find(String... keywords) {
        ArrayList<Task> matches = new ArrayList<>();
        for (Task task: taskList) {
            for (String keyword: keywords) {
                if (task.matches(keyword)) {
                    matches.add(task);
                    break;
                }
            }
        }

        StringBuilder result;
        int size = matches.size();
        if (size == 0) {
            result = new StringBuilder("No results found.");
        } else {
            result = new StringBuilder("Here are the matching tasks in your list:\n");
            for (int i = 0; i < size; i++) {
                result.append(String.format("%d. %s\n", i + 1, matches.get(i)));
            }
        }
        return result.toString();
    }

    @Override
    public String toString() {
        int size = this.taskList.size();
        StringBuilder result;

        if (size == 0) {
            result = new StringBuilder("List is empty. Add items to the list.");
        } else {
            result = new StringBuilder();
            for (int i = 0; i < size; i++) {
                String eol = i == size - 1 ? "" : "\n";
                result.append(String.format("%d. %s%s", i + 1, this.taskList.get(i), eol));
            }
        }

        return result.toString();
    }
}
