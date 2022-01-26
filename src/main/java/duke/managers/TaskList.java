package duke.managers;

import duke.DukeException;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> userTaskList;
    protected Ui ui;

    /**
     * Constructor for TaskList
     */
    public TaskList(){
        userTaskList = new ArrayList<Task>();
        ui = new Ui();
    }

    /**
     * Returns exact Task of the specified index.
     *
     * @param taskIdx
     * @return Task
     */
    public Task getTask(int taskIdx){
        return this.userTaskList.get(taskIdx);
    }

    /**
     * Returns total size of the TaskList (Number of task the user has)
     *
     * @return Size of TaskList
     */
    public int getSize(){
        return this.userTaskList.size();
    }

    /**
     * Returns TaskList as an ArrayList
     *
     * @return TaskList
     */
    public ArrayList<Task> getArrayList(){
        return this.userTaskList;
    }

    /**
     * Adds a task to the user Task List
     *
     * @param task exact task to be added into users' Task List
     * @param toPrint boolean value to decide if we want to execute print statements
     */
    public void addTask(Task task, Boolean toPrint) {
        this.userTaskList.add(task);
        if (toPrint) {
            if (task.getTag() == "T"){
                ui.print("Fantabulous! You have added this Todo Item:\n" + task);
            } else if (task.getTag() == "D"){
                ui.print("Perfect! You have added this Deadline Item:\n" + task + "\nRemember to stick to your objective date!");
            } else if (task.getTag() == "E"){
                ui.print("Magnifico! You have added this Event Item:\n" + task + "\nRemember to be there 5 minutes early!");
            }
        }
    }

    /**
     * Deletes specified user Task from Task List
     *
     * @param taskIndex exact task index to be deleted
     * @throws DukeException if specified taskIndex is not valid (<=0 or > total size of TaskList)
     */
    public void deleteTask(int taskIndex) throws DukeException {
        if (this.checkValidTask(taskIndex)) {
            Task userTask = this.userTaskList.get(taskIndex);
            this.userTaskList.remove(taskIndex);
            ui.print("I have removed " + userTask);
        }
    }

    /**
     * Checks if a specified task index is value
     *
     * @param taskIndex exact task index to be verified
     * @return true if index is valid
     * @throws DukeException if specified taskIndex is not valid (<=0 or > total size of TaskList)
     */
    public boolean checkValidTask(int taskIndex) throws DukeException {
        if ((taskIndex > this.userTaskList.size()-1) || (taskIndex <0)) {
            ui.throwDukeException("There is no such task! Maybe you entered the wrong task?");
        }
        return true;
    }

    /**
     * Prints all of the users tasks in a nice format
     */
    public void printUserTasks() {
        int numberOfTasks = this.userTaskList.size();
        int counter = 0;
        if (numberOfTasks == 0){
            ui.print("You currently do not have any outstanding tasks! Great job! :D");
        } else {
            ui.println("Here are your tasks:");
            for (int i = 1; i <= numberOfTasks; i++) {
                Task userTask = this.userTaskList.get(i - 1);
                ui.println((String.valueOf(i) + ": " + userTask));
                if (!userTask.checkIsDone()) {
                    counter += 1;
                }
            }
            if (counter == 0) {
                ui.print("You have completed all your tasks! Great job!");
            } else {
                ui.print("These are all your tasks! Only " + String.valueOf(counter) + " more remaining! Keep going!");
            }
        }
    }

    /**
     * Marks tasks as done as specified by the task index
     *
     * @param taskIndex exact task index to be marked
     * @param toPrint boolean value to decide if we want to execute print statements
     * @throws DukeException if specified taskIndex is not valid (<=0 or > total size of TaskList)
     */
    public void markTaskDone(int taskIndex, Boolean toPrint) throws DukeException {
        //This is the actual index for arraylist checking (minus one already)
        if (this.checkValidTask(taskIndex)) {
            Task task = this.userTaskList.get(taskIndex);
            task.markAsDone();
            if (toPrint) {
                ui.print("Congratulations on completing the task!\n" + task);
            }
        }
    }

    /**
     * Marks tasks as not done as specified by the task index
     *
     * @param taskIndex exact task index to be unmarked
     * @param toPrint boolean value to decide if we want to execute print statements
     * @throws DukeException if specified taskIndex is not valid (<=0 or > total size of TaskList)
     */
    public void markTaskNotDone(int taskIndex, Boolean toPrint) throws DukeException {
        //This is the actual index for arraylist checking (minus one already)
        if (this.checkValidTask(taskIndex)) {
            Task task = this.userTaskList.get(taskIndex);
            task.markAsNotDone();
            if (toPrint) {
                ui.print("I have un-marked this task!\n" + task);
            }
        }
    }

}
