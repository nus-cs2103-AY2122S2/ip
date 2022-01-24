package duke.exception;

public class BotException {
    public void emptyDescription(String description) {
        System.out.println("____________________________________________________________");
        System.out.println("  ☹ OOPS!!! The description of a " + description + " cannot be empty.");
        System.out.println("____________________________________________________________");
    }

    public void notNumeric(String description) {
        System.out.println("____________________________________________________________");
        System.out.println("  ☹ OOPS!!! The description of a " + description + " must be a number.");
        System.out.println("____________________________________________________________");
    }

    public void wrongSyntax() {
        System.out.println("____________________________________________________________");
        System.out.println("  ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        System.out.println("____________________________________________________________");
    }

    public void dateNotFound() {
        System.out.println("____________________________________________________________");
        System.out.println("  ☹ OOPS!!! You don't have any deadlines/events on this day :-(");
        System.out.println("____________________________________________________________");
    }
}
