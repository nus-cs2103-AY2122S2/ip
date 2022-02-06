package duke.task;

public class Todo extends Task {

    public Todo(String command) {
        super(command);
        System.out.println("added: " + this.toString());
    }

    public String toString(){
        if (this.checked) {
            return "[T][X] " + this.title;
        } else {
            return "[T][ ] " + this.title;
        }
    }

}
