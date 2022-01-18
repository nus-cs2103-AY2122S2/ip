import java.util.ArrayList;

public class TaskList {
    /** Array of Activity in the list */
    ArrayList<Task> activities;

    public TaskList() {
        activities = new ArrayList<>();
    }

    /**
     * Adds a new todo task
     * @param activity name of task
     * @return a response string
     */
    public String addToDo(String activity) {
        Task ac = new ToDoTask(activity);
        activities.add(ac);
        return "Got it! I have added a new todo task: \n" + ac
                + "\nYou have " + activities.size() + " tasks in your list.";
    }

    /**
     * Adds a new deadline task
     * @param activity name of task
     * @return a response string
     */
    public String addDeadline(String activity, String date) {
        Task ac = new DeadlineTask(activity, date);
        activities.add(ac);
        return "Got it! I have added a new deadline task: \n" + ac
                + "\nYou have " + activities.size() + " tasks in your list.";
    }

    /**
     * Adds a new event task
     * @param activity name of task
     * @return a response string
     */
    public String addEvent(String activity, String date) {
        Task ac = new EventTask(activity, date);
        activities.add(ac);
        return "Got it! I have added a new event task: \n" + ac
                + "\nYou have " + activities.size() + " tasks in your list.";
    }

    /**
     * Marks task in list as done.
     *
     * @param idx index of the task
     * @return the marked task
     * @throws TaskListException if index is out of bounds of task list size
     */
    public Task markDone(int idx) throws TaskListException {
        if (idx < 0 || idx >= activities.size())
            throw new TaskListException("There is no task with index: " + (idx + 1));
        else {
            Task ac = activities.get(idx);
            ac.done();
            return ac;
        }
    }

    /**
     * Marks task in list as undone.
     *
     * @param idx index of the task
     * @return the unmarked task
     * @throws TaskListException if index is out of bounds of task list size
     */
    public Task markUndone(int idx) throws TaskListException {
        if (idx < 0 || idx >= activities.size())
            throw new TaskListException("There is no task with index: " + (idx + 1));
        else {
            Task ac = activities.get(idx);
            ac.undone();
            return ac;
        }
    }

    /**
     * Returns a string of the activities added to the list,
     * in order of addition.
     *
     * @return the list of activities if list is not empty
     */
    @Override
    public String toString() {
        if (activities.size() == 0) return "Nothing is added yet.";
        StringBuilder sb = new StringBuilder();
        sb.append("You have the following upcoming tasks: \n");
        for (int i = 0; i < activities.size(); i++) {
            sb.append((i + 1) + "." + activities.get(i).toString() + "\n");
        }
        if(sb.length() > 0){
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
