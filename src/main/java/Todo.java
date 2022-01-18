public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + this.getSymbol() + this.getName();
    }

    public static Todo formatInput(String input) {
        String tdTask = input.substring(4); //Grabs all the text after the "todo" command word
        return new Todo(tdTask);
    }

}
