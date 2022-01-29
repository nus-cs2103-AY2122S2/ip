package duke.task;

public class ToDos extends Task {

    /**
     * Creates a ToDos instance
     * @param s Description of the ToDos task
     */
    public ToDos(String s){
        super(s);
    }

    /**
     * Returns String representation of the Todos
     * @return String representation of this Todos
     */
    @Override
    public String show(){
        if(super.done){
            return "[T][X] " + super.s;
        }
        else{
            return "[T][ ] " + super.s;
        }
    }

    /**
     * Returns String format of this Todos to store
     * @return String format of this Todos to store
     */
    @Override
    public String storeFormat(){
        return "T" + "|" + super.done + "|" + super.s + "\n";
    }

}
