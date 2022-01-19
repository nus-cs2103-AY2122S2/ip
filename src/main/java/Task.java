/**
 * Class task that Duke creates
 */
public class Task {

    String deadline;
    String name;
    String type;
    int number;
    static int totalTask = 0;
    boolean done = false;

    /**
     * Constructor of task
     *
     * @param name Descriptor of task
     * @param number Number associated with task
     * @param deadline time associated with task
     * @param type type of task. 'E' for Event, 'T' for Todo and 'D' for Deadline
     */
    public Task(String name, int number, String deadline, String type){
        try {
            if (name.equals("")) {
                throw new EmptyDescriptorExceptions();
            }
            this.name = name;
            this.number = number;
            this.deadline = deadline;
            this.type = type;
            System.out.println("Got it. I've added this task: ");
            if (type.equals("D")){
                System.out.printf(" [D][%s] %s (by: %s) \n", this.getStatus(),  this.name, this.deadline);
            } else if (type.equals("T")){
                System.out.printf(" [T][%s] %s\n", this.getStatus(),  this.name);
            } else {
                System.out.printf(" [E][%s] %s (on: %s) \n", this.getStatus(),  this.name, this.deadline);
            }
            totalTask++;
            System.out.printf("Now you have %d task on the list.\n", Task.totalTask);
        }
        catch (EmptyDescriptorExceptions e){
            System.out.println("☹ OOPS!!! The description of a task cannot be empty.");
        }
    }

    /**
     * Marks tasks as done.
     */
    public void mark(){
        this.done = true;
    }

    /**
     * Unmarks tasks as not done.
     */
    public void unmark(){
        this.done = false;
    }

    /**
     * Gets status of tasks based on if task is done/not done.
     *
     * @return X if task is done and empty string if task is not done
     */
    public String getStatus(){
        if (this.done){
            return "X";
        } else {
            return " ";
        }
    }

    /**
     * Decrements number associated with the task.
     */
    public void decrementNum(){
        this.number--;
    }

    /**
     * Gets string representation of task.
     *
     * @return String representation of task.
     */
    @Override
    public String toString(){
        String s = String.format("%d. [%s] %s\n", number+1, getStatus(), name);
        return s;
    }
}
