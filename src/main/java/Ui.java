import java.util.Random;

public class Ui {

    private static final String BORDER =
        "**********************************************************************************************************************";
    private static final String[] GREETING_QUOTES = {
        "Welcome to my inn",
        "Pull up a chair by the hearth!",
        "Come in, and shut the door, it's cold out there!",
        "Don't be scared. Come in, have a seat!",
    };
    private static final String[] GUIDE = {
        "list                                                            View your task list",
        "get <date*>                                                     View the tasks that you have on the specified date",
        "todo <name of task>                                             Add a todo to your task list",
        "deadline <name of task> /by <date* or timestamp* of task>       Add a deadline to your task list",
        "event <name of task> /at <date* or timestamp* of task>          Add an event to your task list",
        "mark <index of task>                                            Mark a task as completed in your task list",
        "unmark <index of task>                                          Unmark a task in your task list",
        "bye                                                             Exit the program",
        "* date format is dd/MM/YYYY                                        eg; 24/04/2022",
        "* timestamp format is dd/MM/YYYY HHmm                              eg; 23/03/2022 1800",
    };

    private final Random random;

    public Ui() {
        this.random = new Random();
    }

    public void greet() {
        System.out.println(BORDER + "\n");
        chat("Greetings, traveller!");
        // chat(getRandomGreetingQuote());
        chat(
            "I'm the innkeeper and im here to help you with whatever you need."
        );
    }

    public void bye() {
        chat("Goodbye traveller! Hope to see you again soon!");
        System.out.println(BORDER);
    }

    public void chat(String message) {
        System.out.print("Innkeeper: ");
        System.out.println(message + "\n");
    }

    public void prompt() {
        System.out.println(BORDER + "\n");
        chat("What can I do for you today?");
        System.out.println(BORDER);
        System.out.print("\nYou: ");
    }

    public void error(String errorMessage) {
        chat(errorMessage);
        chat("You can type guide for a list of valid commands to use!");
    }

    public String getRandomGreetingQuote() {
        int randomIndex = random.nextInt(GREETING_QUOTES.length);
        return GREETING_QUOTES[randomIndex];
    }

    public void printNumTasks(int numTasks) {
        chat(
            String.format(
                "You now have %d %s!",
                numTasks,
                numTasks == 1 ? "task" : "tasks"
            )
        );
    }

    public void printGuide() {
        chat("Here is a list of commands that you can use traveller!");
        for (int i = 0; i < GUIDE.length - 2; i++) {
            System.out.println(
                String.format("             %d. %s", i + 1, GUIDE[i])
            );
        }
        System.out.println();
        for (int i = GUIDE.length - 2; i < GUIDE.length; i++) {
            System.out.println(String.format("             %s", GUIDE[i]));
        }
        System.out.println();
    }
}
