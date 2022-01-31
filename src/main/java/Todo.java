public class Todo extends Task{
    private final String title;

    public Todo(String command) throws DukeException {
        if (command.isEmpty()) {
            throw new DukeException("Please use this format: Todo <Activity>");
        }
        this.title = command;
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
