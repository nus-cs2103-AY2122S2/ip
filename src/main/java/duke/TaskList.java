package duke;

import java.util.ArrayList;

/**
 * A container for Task.
 * Provides methods to manipulate list of Task in it.
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    TaskList() {
        this.tasks = new ArrayList<>();
    }

    TaskList(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a Task into the TaskList and returns a message to indicate success
     *
     * @param t Task to be added
     * @return Message for successfully adding a Task
     */
    public String add(Task t) {
        tasks.add(t);

        return "Got it. BMO has added this task:\n      " + t + count();
    }

    /**
     * Returns a String to represent the current list of Tasks
     *
     * @return Current list of Tasks
     */
    public String list() {
        String tempStr = "Here are the tasks in your list:\n    ";
        for (int i = 0; i < tasks.size(); i++) {
            if (i != 0) {
                tempStr += "\n    ";
            }
            tempStr += (i + 1) + ". " + tasks.get(i);
        }

        return tempStr;
    }

    /**
     * Marks the Task according to the index provided and returns a message for successfully marking the Task
     *
     * @param idx index of the Task to be marked
     * @return Message for successfully marking the Task
     * @throws DukeException If the index specified is out of bounds OR If the TaskList is empty
     */
    public String mark(int idx) throws DukeException {
                
        if (idx < 1 || idx > tasks.size()) {
            throw new DukeIdxOOBException();
        }
        
        tasks.get(idx - 1).mark();
        
        return "Awesome! :D Another task done:\n      " + tasks.get(idx - 1);

    }

    /**
     * Unmasks the Task according to the index provided and returns a message for successfully unmaking the Task
     *
     * @param idx index of the Task to be unmarked
     * @return Message for successfully unmaking the Task
     * @throws DukeException If the index specified is out of bounds OR If the TaskList is empty
     */
    public String unmark(int idx) throws DukeException {
                
        if (idx < 1 || idx > tasks.size()) {
            throw new DukeIdxOOBException();
        }
        
        tasks.get(idx - 1).unmark();
        
        return "T_T BMO can't believe you lied to me before.. I will unmark this task:\n      " + tasks.get(idx - 1);

    }

    /**
     * Deletes the Task according to the index provided and returns a message for successfully deleting the Task
     *
     * @param idx index of the Task to be deleted
     * @return Message for successfully deleting the Task
     * @throws DukeException If the index specified is out of bounds OR If the TaskList is empty
     */
    public String delete(int idx) throws DukeException {

        if (tasks.size() == 0) {
            throw new DukeException("BMO don't think you have anything to delete :\\");
        }

        if (idx < 1 || idx > tasks.size()) {
            throw new DukeIdxOOBException();
        }

        return "BMO deleted the task. Yay!:\n      " + tasks.remove(idx - 1) + count();

    }

    /**
     * Returns a string that describes the number of tasks in the list
     *
     * @return String that describes the number of tasks in the TaskList
     */
    public String count() {
        String isSingular = "s";
        
        if (tasks.size() == 1) {
            isSingular = "";
        }
        
        return "\n    Now you have " + tasks.size() + " task" + isSingular + " in your list.";
    }

    /**
     * Finds tasks that contain the keyword in their description to return a list, else returns a message
     * to inform failure of doing so
     *
     * @param keyword a String or Char sequence to be searched for
     * @return a list of tasks containing the keyword in String format or a message to imply failure
     */
    public String find(String keyword) {
        String resultStr = "";
        boolean keywordFound = false;
        int i = 1;
        String lowerCasedKeyword = keyword.toLowerCase();

        for (Task t : this.tasks) {
            if (t.description.toLowerCase().contains(lowerCasedKeyword)) {
                keywordFound = true;
                resultStr += i++ + ". " + t + "\n      ";
            }
        }

        if (keywordFound) {
            resultStr = "Here are the matching tasks in your list:\n      " + resultStr;
        } else {
            resultStr = "No matching results found in the list.";
        }

        return resultStr;
    }

    /**
     * @return exit message
     */
    public String exit() {
        return "Bye bye. BMO will see you again soon ' v '";
    }

}
