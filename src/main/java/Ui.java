public class Ui {
    public static void greet() {
        String logo =
                " ------    -------    -------   ---        ---   ---------    -------\n" +
                        "|____  |  |   _   |  |   _   |  \\  \\      /  /  |___   ___|  |   ____|\n" +
                        "     | |  |  |_|  |  |  |_|  |   \\  \\    /  /       | |      |  |____ \n" +
                        " _   | |  |   _   |  |      _|    \\  \\  /  /        | |      |_____  |\n" +
                        "| |__| |  |  | |  |  |  |\\  \\      \\  \\/  /      ___| |___    ____|  |\n" +
                        "|______|  |__| |__|  |__| \\__\\      \\____/      |_________|  |_______|\n"
                ;
        System.out.println("Welcome home, sir.\n" + logo);
    }

    public static void showLine() {
        System.out.println("___________________________________________________________");
    }

    public static void showLoadingError() {
        System.out.println("Unable to load file, sir.");
    }

    public static void list() {
        System.out.println("Here are the tasks on your list, sir.");
    }

    public static void alreadyDone(Task task) {
        System.out.println("This task was already done to begin with, sir:");
        print(task);
    }

    public static void alreadyNotDone(Task task) {
        System.out.println("This task was never done to begin with, sir:");
        print(task);
    }

    public static void done(String description) {
        System.out.println("Good job, sir. This task is marked done: ");
        System.out.println("  " + description);
    }

    public static void notDone(String description) {
        System.out.println("Slacking off, sir? This task is marked not done: ");
        System.out.println("  " + description);
    }

    public static void remove(String description) {
        System.out.println("Too weak to handle it? This task have been removed sir: ");
        System.out.println("  " + description);
    }

    public static void unknownCommand(String command) {
        System.out.println("I apologize, sir. This command: " + command + " is not recognized in my database.");
    }

    public static void create(Task task) {
        System.out.println("I've added this to your tasks sir: ");
        print(task);
    }

    public static void print(Task task) {
        System.out.println("  " + task);
    }

    public static void bye() {
        Ui.showLine();
        System.out.println("Good bye, sir.");
        Ui.showLine();
    }
}
