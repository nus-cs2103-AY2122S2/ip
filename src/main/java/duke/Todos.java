package duke;

public class Todos extends Task {

    public Todos(String description) {
        super(description);

    }

    @Override
    public String message() {
        return "T | " + "[" +  this.getStatusIcon() + "] " + super.message() ;
    }



}
