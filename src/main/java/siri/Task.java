package siri;
/**
 * Class for the individual items of the task list, with int done to be indicative of whether the task had
 * been completed and String item to be the name of the task.
 */

class Task {
    protected boolean isDone;
    protected String item;

    /**
     * Constructor for Task class.
     *
     * @param item a String to description of the Task.
     * @param done an int to indicate whether the item is done. 0 indicates not completed and 1 indicates completed.
     */
    Task(String item, boolean isDone) {
        this.isDone = isDone;
        this.item = item;
    }

    /**
     * Returns a symbol the doneness of the task.
     * If task is done, return 'X', else return empty space to indicate task not done.
     *
     * @param done integer to indicate whether done or undone. 1 represents the done and 0 represents undone.
     * @return a string " " to indicate the task had not been completed and "X" to indicate the task had be completed.
     */
    private String doneSymbol(boolean isDone) {
        if (isDone) {
            return "X";
        } else {
            return " ";
        }
    }

    /**
     * Changes isDone to true (mark task as done).
     */
    public String markTaskDone() {
        isDone = true;
        return "Great job for completing task:\n" + this.getTaskDetails() + "\n";
    }

    /**
     * Changes isDone to false (mark task as undone).
     */
    public String markTaskUndone() {
        isDone = false;
        return "Task marked as uncompleted:\n" + this.getTaskDetails() + "\n";
    }

    /**
     * Returns the String consisting details of Task.
     *
     * @return String representation of the task details, including whether it is done and task name.
     */
    public String getTaskDetails() {
        String taskDetails = "[" + doneSymbol(this.isDone) + "] " + this.item;
        return taskDetails;
    }

    /**
     * Returns the String representation of the data for saving.
     *
     * @return a string representation of the task for saving.
     */
    public String saveData() {
        String dataString = String.valueOf(this.isDone) + " " + this.item;
        return dataString;
    }
}
