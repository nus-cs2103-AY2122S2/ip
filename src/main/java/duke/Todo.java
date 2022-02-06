package duke;

/**
 * Todo Class that is a subclass of task, no additional attributes
 */
public class Todo extends Task {

    /**
     * Constructor for Todo class
     *
     * @param taskName the details/name of the task
     */
    public Todo(String taskName) {
        super(taskName);
    }

    /**
     * Method to print the Todo task out, overrides the method in the superclass
     */
    @Override
    public void printTask() {
        System.out.print(this.toString());
    }

    /**
     * Overrides the toString method, used for JUnit testing ensuring the correct output is printed out
     * @return String that goes into the output
     */
    @Override
    public String toString() {
        String result = "";
        result += "[T]";
        if (this.getDone()) {
            result += "[X]";
        } else {
            result += "[ ]";
        }
        result += " " + this.getTaskName();
        return result;
    }
}
