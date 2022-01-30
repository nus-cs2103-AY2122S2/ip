package chatbot;
import java.util.ArrayList;

import tasks.Task;
/**
* TaskList class for the bot, handling and storing the list of Task objects locally.
*/
public class TaskList {
    private static final String NO_SUCH_TASK = "I'm very sorry Sir, there is no such task you mentioned.";

    private ArrayList<Task> taskList;

    /**
    * Class constructor.
    * <p>
    * Creates a new ArrayList of Task objects.
    */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
    * Returns the ArrayList of Task objects
    */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }
    /**
    * Adds a Task into the ArrayList of Task objects.
    * <p>
    * This method adds a Task without giving the user any reply.
    *
    * @param    task    the Task object to be added
    * @see      Task
    */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
    * Returns the Task object at the specified ArrayList index.
    *
    * @param    taskIndex    the index of the Task object
    * @return                the Task object
    * @see      Task
    */
    public Task getTaskFromIndex(int taskIndex) throws DukeException {
        if (taskIndex >= this.taskList.size()) { // invalid integer input
            throw new DukeException(NO_SUCH_TASK);
        }
        return this.taskList.get(taskIndex);
    }

    /**
    * Returns the String of the reply to the user, after adding a Task
    * newTask into the ArrayList.
    *
    * @param    newTask    the Task object to be added
    * @return              the String of the reply to the user
    * @see      Task
    */
    public String insertNewTask(Task newTask) throws DukeException {
        String reply = "";
        addTask(newTask);
        String taskListLength = Integer.toString(this.taskList.size());
        reply += "Very well Sir. I have added this task:";
        reply += "\n   " + newTask.toString();
        reply += "\n You now have " + taskListLength + " tasks in the agenda Sir.";
        return reply;
    }

    /**
    * Returns the String of the reply to the user showing all the tasks
    * that they currently have.
    *
    * @return           the String of the reply to the user
    * @see      Task
    */
    public String getTaskListMessage() {
        Integer counter = 1;
        String reply = "";
        for (Task task : this.taskList) {
            String taskInfo = task.toString();
            reply += "\n " + counter.toString() + "." + taskInfo; // note: new line is at the start
            counter++;
        }
        String listHeader = "This is your agenda of tasks Sir:";
        return listHeader + reply;
    }

    /**
    * Returns the String of the reply to the user, after marking a Task
    * in the ArrayList as done or not done depending on String type.
    * <p>
    * String type requires "mark" or "unmark" to mark a task as done or not done.
    *
    * @param    taskIndex    the index of the Task object to be marked
    * @param    type         used to indicate if a task should be marked as done or not.
    * @return                the String of the reply to the user
    * @see      Task
    */
    public String markTask(int taskIndex, String type) throws DukeException {
        String reply = "";
        // check command
        boolean markAsDone = false; // default is "unmark"
        if (type.equals("mark")) {
            markAsDone = true;
        }
        if (taskIndex >= this.taskList.size()) { // invalid integer input
            throw new DukeException(NO_SUCH_TASK);
        }

        Task task = this.taskList.get(taskIndex);
        task.markTask(markAsDone);

        if (markAsDone) {
            reply += "Very well Sir, I have marked this task as complete: ";
        } else {
            reply += "Very well Sir, I have marked this task as incomplete: ";
        }
        reply += "\n   " + task.toString();

        return reply;
    }

    /**
    * Returns the String of the reply to the user, after deleting a Task
    * in the ArrayList.
    *
    * @param    taskIndex    the index of the Task object to be deleted
    * @return                the String of the reply to the user
    * @see      Task
    */
    public String deleteTask(int taskIndex) throws DukeException {
        String reply = "";
        Task taskToRemove = getTaskFromIndex(taskIndex);

        this.taskList.remove(taskToRemove);
        String taskListLength = Integer.toString(taskList.size());

        reply += "Very well Sir. I have removed this task:";
        reply += "\n   " + taskToRemove.toString();
        reply += "\n You now have " + taskListLength + " tasks in the agenda Sir.";
        return reply;
    }

    /**
    * Returns the String of the reply to the user, after finding tasks
    * that containing the input taskName String, if any were found.
    *
    * @param    taskName     the name of the Task object to be found
    * @return                the String of the reply to the user
    * @see      Task
    */
    public String findTaskName(String taskName) {
        String reply = "";
        String listHeader = "These are the tasks that match your description Sir:";
        String noTasksFound = "Sorry Sir, there are no tasks matching your description.";
        Integer taskCounter = 1;
        for (Task task : this.taskList) {
            if (task.getTaskName().contains(taskName)) { // matching task
                reply += "\n " + taskCounter.toString() + "." + task.toString();
                taskCounter++;
            }
        }
        if (taskCounter == 1) { // no tasks were found
            return noTasksFound;
        }
        return listHeader + reply;
    }
}
