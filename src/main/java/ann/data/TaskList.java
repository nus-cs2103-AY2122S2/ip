package ann.data;

import java.util.ArrayList;

import ann.data.tasks.Deadline;
import ann.data.tasks.Event;
import ann.data.tasks.Task;

/**
 * Represents the user's list of Tasks.
 *
 * @author Hong Anh
 * @version 0.1
 */
public class TaskList {
    /** Represents the user's list of Tasks.*/
    private final ArrayList<Task> list;
    private int numTasks;

    /**
     * Creates a new, empty TaskList.
     */
    public TaskList() {
        list = new ArrayList<>();
        numTasks = 0;
    }

    /**
     * Creates a new TaskList containing the specified Tasks.
     *
     * @param tasks an ArrayList of Strings representing the user's Tasks.
     */
    public TaskList(ArrayList<String> tasks) {
        this();
        addTasksFromFile(tasks);
        assert list.size() == numTasks : "The field numTasks should correctly keep track of "
                + "the number of tasks in the list";
    }

    /**
     * Adds the given Task to the list.
     *
     * @param task a Task to be added.
     */
    public void addTask(Task task) {
        list.add(task);
        numTasks++;
        assert list.size() == numTasks : "The field numTasks should correctly keep track of "
                + "the number of tasks in the list";
    }

    /**
     * Removes the Task at the specified index from the list.
     *
     * @param index an int representing the index of the Task to be removed.
     * @return a String which is the message to be displayed to the user.
     */
    public String removeTaskAtIndex(int index) {
        if (numTasks == 0) {
            return "Oops! Currently, you have no tasks :)";
        } else if (index < 1 || index > numTasks) {
            if (numTasks == 1) {
                return "Oops! You only have 1 task in your list!";
            } else {
                return "Oops! Please enter a number between 1 and " + numTasks + "!";
            }
        } else {
            Task task = list.get(index - 1);
            list.remove(index - 1);
            numTasks--;
            assert list.size() == numTasks : "The field numTasks should correctly keep track of "
                    + "the number of tasks in the list";
            return "Alright! I've removed this task:\n" + task.toString() + "\n" + getNumTasksString();
        }
    }

    /**
     * Marks as done the Task at the specified index.
     *
     * @param index an int representing the index of the Task to be marked as done.
     * @return a String which is the message to be displayed to the user.
     */
    public String markTaskAtIndex(int index) {
        if (numTasks == 0) {
            return "Oops! Currently, you have no tasks :)";
        } else if (index < 1 || index > numTasks) {
            if (numTasks == 1) {
                return "Oops! You only have 1 task in your list!";
            } else {
                return "Oops! Please enter a number between 1 and " + numTasks + "!";
            }
        } else {
            Task task = this.list.get(index - 1);
            task.mark();
            return "Alright! I've marked this task as done:\n" + task.toString();
        }
    }

    /**
     * Marks as not done the Task at the specified index.
     *
     * @param index an int representing the index of the Task to be marked as not done.
     * @return a String which is the message to be displayed to the user.
     */
    public String unmarkTaskAtIndex(int index) {
        if (numTasks == 0) {
            return "Oops! Currently, you have no tasks :)";
        } else if (index < 1 || index > numTasks) {
            if (numTasks == 1) {
                return "Oops! You only have 1 task in your list!";
            } else {
                return "Oops! Please enter a number between 1 and " + numTasks + "!";
            }
        } else {
            Task task = this.list.get(index - 1);
            task.unmark();
            return "Alright! I've marked this task as not done yet:\n" + task.toString();
        }
    }

    /**
     * Parses the Strings into Tasks which are added to the list.
     *
     * @param tasks an ArrayList containing Strings that represents Tasks.
     */
    public void addTasksFromFile(ArrayList<String> tasks) {
        tasks.forEach(task -> {
            this.list.add(parseFileString(task));
            this.numTasks++;
        });
    }

    /**
     * Returns a Task that is created from its String representation.
     *
     * @param task a String which contains comma-separated information on a Task.
     * @return a Task represented by the given String.
     */
    private Task parseFileString(String task) throws AssertionError {
        String[] components = task.split(", ");
        switch (components[0]) {
        case "T":
            return new Task(components[2], components[1].equals("1"));
        case "D":
            return Deadline.createDeadlineFromStorage(components[2], components[3], components[1].equals("1"));
        case "E":
            return Event.createEventFromStorage(components[2], components[3], components[1].equals("1"));
        default:
            throw new AssertionError("All tasks in storage should have valid type");
        }
    }

    /**
     * Returns a String which describes the number of Tasks in the list.
     *
     * @return a String which describes the number of Tasks in the list.
     */
    public String getNumTasksString() {
        return numTasks == 1 ? "Now there is 1 task in your list."
                : "Now there are " + numTasks + " tasks in your list.";
    }

    /**
     * Returns a String that describes all the tasks in the list.
     *
     * @return a String that describes all the tasks in the list.
     */
    public String getTasksString() {
        StringBuilder tasks;
        if (numTasks == 0) {
            tasks = new StringBuilder("Currently, you have no tasks :)");
        } else {
            tasks = new StringBuilder("Here are the tasks in your list:\n");
            int i = 1;
            for (Task task : this.list) {
                tasks.append(i).append(". ").append(task.toString());
                i++;
                if (i <= numTasks) {
                    tasks.append("\n");
                }
            }
        }
        return tasks.toString();
    }

    /**
     * Returns a String, which describes all the Tasks, to be written to a file.
     *
     * @return a String containing String representations of the Tasks.
     */
    public String getFileString() {
        StringBuilder fileString = new StringBuilder();
        for (int i = 0; i < numTasks; i++) {
            fileString.append(list.get(i).toFileString());
            if (i != this.numTasks - 1) {
                fileString.append("\n");
            }
        }
        return fileString.toString();
    }

    /**
     * Returns a String which contains the Tasks whose description matches the given keyword(s).
     *
     * @param keyWords a String containing the keyword(s).
     * @return a String which contains the Tasks whose description matches the given keyword(s).
     */
    public String findTask(String keyWords) {
        String keyWordsInLowerCase = keyWords.toLowerCase();
        ArrayList<String> matchingTasks = new ArrayList<>();
        switch (keyWordsInLowerCase) {
        case "event":
            for (Task t : list) {
                if (t instanceof Event) {
                    matchingTasks.add(t.toString());
                }
            }
            break;
        case "deadline":
            for (Task t : list) {
                if (t instanceof Deadline) {
                    matchingTasks.add(t.toString());
                }
            }
            break;
        case "todo":
            for (Task t : list) {
                if (!(t instanceof Deadline || t instanceof Event)) {
                    matchingTasks.add(t.toString());
                }
            }
            break;
        default:
            for (Task t : list) {
                if (t.toString().toLowerCase().contains(keyWordsInLowerCase)) {
                    matchingTasks.add(t.toString());
                }
            }
        }
        int numMatchingTasks = matchingTasks.size();
        if (numMatchingTasks == 0) {
            return "There are no matching tasks in your list!";
        } else {
            StringBuilder matchingTasksString = new StringBuilder();
            int i = 1;
            for (String s : matchingTasks) {
                matchingTasksString.append(i).append(". ").append(s);
                if (i < numMatchingTasks) {
                    matchingTasksString.append("\n");
                }
                i++;
            }
            return "Here are the matching tasks in your list:\n" + matchingTasksString;
        }
    }
}
