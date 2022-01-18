import java.util.Objects;
import java.util.Scanner;

/**
 * This class encapsulates the chatbot Fluffers for CS2103T's Individual Project.
 *
 * ASCII art credit: All ASCII art was found on https://www.asciiart.eu/animals/cats .
 *
 *
 * @author Ong Han Yang
 */
public class Fluffers<T> {
    /** ASCII art for when Fluffers just wakes up*/
    private static String AWAKE =
            "    /\\_____/\\\n" +
            "   /  o   o  \\\n" +
            "  ( ==  ^  == )\n" +
            "   )         (\n" +
            "  (           )\n" +
            " ( (  )   (  ) )\n" +
            "(__(__)___(__)__)";

    /** ASCII art for when Fluffers goes back to sleep*/
    private static String ASLEEP =
            "      |\\      _,,,---,,_\n" +
            "ZZZzz /,`.-'`'    -.  ;-;;,_\n" +
            "     |,4-  ) )-,_. ,\\ (  `'-'\n" +
            "    '---''(_/--'  `-'\\_)";

    /** ASCII art for when Fluffers is displaying a list */
    private static String LIST_TOP =
            "    |\\__/,|   (`\\\n" +
            "  _.|o o  |_   ) )\n" +
            "-(((---(((--------";

    /** The private task list that each Fluffers object will keep. */
    private TaskList tasks;

    /**
     * Constructor to initialise Fluffers.
     */
    public Fluffers() {
        tasks = new TaskList();
    }

    /**
     * Asks Fluffers to speak with fancy formatting.
     *
     * @param input the text that Fluffers is asked to speak.
     * @return the formatted String that Fluffers is asked to speak.
     */
    private String speak(String input) {
        return String.format("Meow! (%s)\n", input);
    }

    /**
     * Overloaded speak method to include a isQuestion prompt, to ask Fluffers
     * to speak with fancy formatting.
     *
     * @param input the text that Fluffers is asked to speak.
     * @param isQuestion whether the text is meant to be a question or not.
     * @return the formatted String that Fluffers is asked to speak.
     */
    private String speak(String input, boolean isQuestion) {
        return String.format("Meow%s (%s)\n", isQuestion ? "?" : "!", input);
    }

    /**
     * Initial greeting from Fluffers, with fancy formatting.
     *
     * @return String representation of the greeting.
     */
    private String greet() {
        return "Activating Cat Translator 2000...\n" +
                "Waking Fluffers up...\n\n" +
                "Meow! (Hello!)\n" +
                Fluffers.AWAKE;
    }

    /**
     * Farewell from Fluffers, with fancy formatting.
     *
     * @return String representation of the Farewell.
     */
    private String farewell() {
        return "Bye bye!\n" +
                Fluffers.ASLEEP;
    }

    /**
     * Asks Fluffers to keep track of an item.
     *
     * @param toStore the item to be tracked.
     */
    private void store(Task toStore) {
        tasks.add(toStore);
    }

    /**
     * Asks Fluffers to display what they have kept track of.
     *
     * @return the String representation of the tasks, with fancy formatting.
     */
    private String listTasks() {
        return String.format("%s\n%s\n------------------", Fluffers.LIST_TOP, tasks.toString());
    }

    /**
     * Marks a task as done or undone.
     *
     * @param taskNum the task number to be marked (starts from 1).
     * @param isDone whether the task is done or not.
     * @throws NoSuchTaskException when there is no such task with number taskNum
     */
    private void markTask(int taskNum, boolean isDone) throws NoSuchTaskException {
        this.tasks.markTask(taskNum - 1, isDone);
    }

    /**
     * Displays a task as a String.
     *
     * @param taskNum which task to be displayed (starts from 1).
     * @return the String representation of the task.
     * @throws NoSuchTaskException when there is no such task with number taskNum
     */
    private String displayTask(int taskNum) throws NoSuchTaskException {
        return this.tasks.displayTask(taskNum - 1);
    }



    /**
     * The main method to start the program/wake Fluffers up
     * @param args input CLI arguments.
     */
    public static void main(String[] args) {
        Fluffers<Task> f = new Fluffers<>();

        System.out.println(f.greet());

        Scanner sc = new Scanner(System.in);
        boolean isAwake = true;

        while (isAwake) {
            String input = sc.nextLine();

            if (Objects.equals(input, "bye")) {
                System.out.println(f.farewell());
                isAwake = false;

            } else if (Objects.equals(input, "list")) {
                System.out.println(f.listTasks());

            } else if (input.startsWith("mark")) {
                int taskNum = Integer.parseInt(input.split(" ")[1]);
                try {
                    f.markTask(taskNum, true);
                    System.out.println(f.speak(
                            "Okay! I've marked this task as done! " + f.displayTask(taskNum)
                    ));
                } catch (NoSuchTaskException e) {
                    String reply = String.format(
                            "There's no task %d to mark. Did you mean to do something else?",
                            taskNum);
                    System.out.println(f.speak(reply, true));
                }

            } else if (input.startsWith("unmark")) {
                int taskNum = Integer.parseInt(input.split(" ")[1]);
                try {
                    f.markTask(taskNum, false);
                    System.out.println(f.speak(
                            "This task now needs to be done! " + f.displayTask(taskNum)
                    ));
                } catch (NoSuchTaskException e) {
                    String reply = String.format(
                            "There's no task %d to unmark. Did you mean to do something else?",
                            taskNum);
                    System.out.println(f.speak(reply, true));
                }

            } else {
                f.store(new Task(input));
                System.out.println(f.speak("Added: " + input));
            }
        }
    }
}
