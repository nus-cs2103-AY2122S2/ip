package duke.task;

/**
 * Represents an instance of a ToDo
 * type of Task
 */
public class ToDo extends Task {

    /**
     * Constructor method for ToDo
     *
     * @param desc Description of ToDo
     * @param isComp Completion Status of ToDo
     */
    public ToDo(String desc, boolean isComp){
        super(desc, isComp);
    }

    /**
     * toString method to provide String representation
     * of ToDo
     *
     * @return String representation of the ToDo
     */
    @Override
    public String toString(){
        String temp = "[T] " + super.toString();
        return temp;
    }
}
