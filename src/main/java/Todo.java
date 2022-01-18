public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + this.getSymbol() + " " + this.getName();
    }

    //Formats a line of text into a Todo object
    public static Todo formatInput(String input) throws DukeException {
        String tdTask = input.substring(4); //Grabs all the text after the "todo" command word
        if (tdTask.trim().equals("")) {
            throw new DukeException("Empty description for Todo object");
        }
        return new Todo(tdTask.trim());
    }

}
