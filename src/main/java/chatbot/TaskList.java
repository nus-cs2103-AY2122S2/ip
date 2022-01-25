package chatbot;
import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> taskList;

    private static final String NO_SUCH_TASK = "I'm very sorry Sir, there is no such task you mentioned.";

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    // returns array list
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }
    
    // add new Task to the task list
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    // get Task from a given index
    public Task getTaskFromIndex(int taskIndex) throws DukeException {
        if (taskIndex >= this.taskList.size()) { // invalid integer input
            throw new DukeException(NO_SUCH_TASK);
        } 
        return this.taskList.get(taskIndex);
    }

    // insert new Task
    public String insertNewTask(Task newTask) throws DukeException {
        String reply = "";
        addTask(newTask);
        String taskListLength = Integer.toString(this.taskList.size());
        reply += "Very well Sir. I have added this task:";
        reply += "\n   " + newTask.toString();
        reply += "\n You now have " + taskListLength + " tasks in the agenda Sir.";
        return reply;
    }

    // returns task list in a message
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

    // mark / unmark task
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

    // delete command
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
}
