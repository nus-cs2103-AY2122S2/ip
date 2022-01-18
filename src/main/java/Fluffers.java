import Tasks.*;

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
    private boolean isAwake;

    /**
     * Constructor to initialise Fluffers.
     */
    public Fluffers() {
        tasks = new TaskList();
        isAwake = true;
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
        return "Meooow~ (Bye bye! See you again next time!)\n" +
                Fluffers.ASLEEP;
    }

    /**
     * Method/Getter to check if Fluffers is awake.
     *
     * @return whether Fluffers is awake.
     */
    public boolean isAwake() {
        return this.isAwake;
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
     * Method to count the number of tasks in the list.
     *
     * @return an integer representing the number of tasks.
     */
    public int countTasks() {
        return tasks.length();
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
     * Method for Fluffers to reply and describe an invalid action that they are asked to do.
     *
     * This is used in place of an exception as Fluffers will be initiated in the main function,
     * and there's no point if an exception is thrown and then caught immediately.
     *
     * @param message the sentence to be included in the reply. Include a fullstop.
     * @return a String representing the invalid action reply.
     */
    public String invalidActionReply(String message) {
        String invalidMsg = String.format(
                "I can't do that! %s\nDid you mean to do something else?", message);
        return this.speak(invalidMsg, true);
    }

    /**
     * Method to store the task and give a reply with fancy formatting.
     *
     * @param task the given task
     * @return the reply with fancy formatting.
     */
    public String storeTaskAndReply(Task task) {
        this.store(task);
        String reply = String.format("Added: %s.\nYou now have %d task(s)!", task, this.countTasks());
        return this.speak(reply);
    }

    /**
     * Overall Method for Fluffers to accept input and give responses.
     *
     * @param input the input String or command to be given to Fluffers.
     * @return the response given with respect to the given input.
     */
    public String feedCommandAndReply(String input) {
        if (!isAwake) {
            return Fluffers.ASLEEP;
        } else {
            if (Objects.equals(input, "bye")) {
                isAwake = false;
                return this.farewell();

            } else if (Objects.equals(input, "list")) {
                return this.listTasks();

            } else if (input.startsWith("mark")) {
                int taskNum = Integer.parseInt(input.split(" ")[1]);
                try {
                    this.markTask(taskNum, true);
                    return this.speak("Okay! I've marked this task as done! " + this.displayTask(taskNum));

                } catch (NoSuchTaskException e) {
                    return this.invalidActionReply(String.format("There's no task %d to mark.", taskNum));
                }

            } else if (input.startsWith("unmark")) {
                int taskNum = Integer.parseInt(input.split(" ")[1]);
                try {
                    this.markTask(taskNum, false);
                    return this.speak("This task now needs to be done! " + this.displayTask(taskNum));

                } catch (NoSuchTaskException e) {
                    return this.invalidActionReply(String.format(
                            "There's no task %d to unmark.", taskNum));
                }

            } else if (input.startsWith("todo")){
                try {
                    ToDoTask task = ToDoTask.parseInput(input);
                    return this.storeTaskAndReply(task);
                } catch (InvalidInputException e) {
                    return this.invalidActionReply(e.getMessage());
                }

            } else if (input.startsWith("deadline")){
                try {
                    DeadlineTask task = DeadlineTask.parseInput(input);
                    return this.storeTaskAndReply(task);
                } catch (InvalidInputException e) {
                    return this.invalidActionReply(e.getMessage());
                }

            } else if (input.startsWith("event")) {
                try {
                    EventTask task = EventTask.parseInput(input);
                    return this.storeTaskAndReply(task);
                } catch (InvalidInputException e) {
                    return this.invalidActionReply(e.getMessage());
                }

            } else {
                return this.invalidActionReply("I don't understand what you said.");
            }
        }
    }




    /**
     * The main method to start the program/wake Fluffers up
     * @param args input CLI arguments.
     */
    public static void main(String[] args) {
        Fluffers<Task> f = new Fluffers<>();

        System.out.println(f.greet());

        Scanner sc = new Scanner(System.in);

        while (f.isAwake()) {
            String input = sc.nextLine();
            System.out.println(f.feedCommandAndReply(input));
        }
    }
}
