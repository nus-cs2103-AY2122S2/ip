package narcibot;
public class Ui {
    public void welcome() {
        System.out.println("I'm Narcibot, the best bot ever created.\n" +
                "Oh it's you, what a bother.\n" +
                "Here's a hello as a formality. What do you want this time?\n");
    }

    public void noFile() {
        System.out.println("You have no prior tasks");
    }

    public void bye() {
        System.out.println("So you finally decided to leave. Goodbye! Not that I really care.");
    }

    public void list() {
        System.out.println("Do I have to remind you again?");
    }

    public void mark() {
        System.out.println("You finally did something? I'll mark it for you then.");
    }

    public void unmark() {
        System.out.println("As expected... another task that wasn't finished at all.");
    }

    public void delete() {
        System.out.println("Removing this task, guess you gave up after all.");
    }

    public void task(int size) {
        System.out.println("I have added this task cause you won't remember it.");
        System.out.println("You now have " + size + " tasks");
    }

    public void unknown() {
        System.out.println("Could you please talk some sense? I can't seem to comprehend what you're saying.");
    }
}
