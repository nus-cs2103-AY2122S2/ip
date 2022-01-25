package duke.task;

import duke.DukeException;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + this.getSymbol() + " " + this.getName();
    }

    //Formats a line of text into a Todo object
    public static Todo createTodo(String input) throws DukeException {
        String tdTask = input.substring(4); //Grabs all the text after the "todo" command word
        if (tdTask.trim().equals("")) {
            throw new DukeException("Empty description for Todo object");
        }
        Todo td = new Todo(tdTask.trim());
        System.out.println("Got it! I've added this task:");
        System.out.println(td.toString());
        return td;
    }

    public static Todo createTodo(int status, String description) {
        Todo td = new Todo(description);
        if (status == 1) {
            td.markTask();
        }
        return td;
    }

    @Override
    public String formatText() {
        int status = (this.getStatus()) ? 1 : 0;
        return "T|" + status + "|" + this.getName();
    }
}
