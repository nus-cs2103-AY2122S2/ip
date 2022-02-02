package narcibot;
public class Ui {
    /**
     * Print welcome message.
     */
    public String welcome() {
        return "I'm Narcibot, the best bot ever created.\n" +
                "Oh it's you, what a bother.\n" +
                "Here's a hello as a formality. What do you want this time?\n";
    }

    /**
     * Print no file message.
     */
    public void noFile() {
        System.out.println("You have no prior tasks");
    }

    /**
     * Print exit message.
     */
    public void bye() {
        System.out.println("So you finally decided to leave. Goodbye! Not that I really care.");
    }

    /**
     * Print list message.
     */
    public void list() {
        System.out.println("Do I have to remind you again?");
    }

    /**
     * Print mark message.
     */
    public void mark() {
        System.out.println("You finally did something? I'll mark it for you then.");
    }

    /**
     * Print unmark message.
     */
    public void unmark() {
        System.out.println("As expected... another task that wasn't finished at all.");
    }

    /**
     * Print delete message.
     */
    public void delete() {
        System.out.println("Removing this task, guess you gave up after all.");
    }

    /**
     * Print message when adding a task.
     * @param size
     */
    public void task(int size) {
        System.out.println("I have added this task cause you won't remember it.");
        System.out.println("You now have " + size + " tasks");
    }

    /**
     * Print message for unknown command.
     */
    public void unknown() {
        System.out.println("Could you please talk some sense? I can't seem to comprehend what you're saying.");
    }

    /**
     * Print message for find command.
     */
    public void find() {
        System.out.println("Finding tasks that match your keyword");
    }
}
