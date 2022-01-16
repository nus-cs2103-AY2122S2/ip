import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Mnsky {
    private ArrayList<Task> list;
    private Scanner con;

    /**
     * Constructor for the Mnsky object.
     */
    public Mnsky() {
         this.list = new ArrayList<>();
         this.con = new Scanner(System.in);
    }

    /**
     * Prints the passed message with "MNSKY: " before it.
     * @param msg The message that should be printed after "MNSKY: "
     */
    private void printMnsky(String msg) {
        System.out.print("MNSKY: ");
        System.out.println(msg);
    }

    /**
     * Prints the greeting message for the chatbot.
     */
    public void greeting() {
        this.printMnsky("Hi, I'm");
        this.printMnsky("MM      MM  NN      NN   SSSSSSS   KK     KK  YY      YY");
        this.printMnsky("MMMM  MMMM  NNNN    NN  SSSS       KK   KK      YY  YY");
        this.printMnsky("MM  MM  MM  NN  NN  NN    SSSSS    KKKKK          YY");
        this.printMnsky("MM      MM  NN    NNNN       SSSS  KK   KK        YY");
        this.printMnsky("MM      MM  NN      NN  SSSSSSS    KK     KK      YY");
        this.printMnsky("I can help!");
    }

    /**
     * Retrieves user input from stdin.
     * @return The user's input
     */
    private String getInput() {
        System.out.println();
        System.out.print("> ");
        return this.con.nextLine();
    }

    private void bye() {
        this.printMnsky("I can help!");
        System.out.println("[MNSKY has shut itself down]");
    }

    private void printList() {
        if (this.list.size() == 0) {
            System.out.println("[MNSKY presents an empty task list.]");
        } else {
            for (int i = 0; i < this.list.size(); i++) {
                System.out.println(String.format("%d. %s", i + 1, this.list.get(i)));
            }
        }
    }

    private void mark(int index) {
        this.list.get(index).mark();
        System.out.println(this.list.get(index));
    }

    private void unmark(int index) {
        this.list.get(index).unmark();
        System.out.println(this.list.get(index));
    }

    private void addTask(String input) {
        // TODO: Throw and handle errors for invalid input
        String taskName = input.split(" ", 2)[1];
        this.list.add(new Task(taskName));
        System.out.println(String.format("[MNSKY added task %s to their list]", taskName));
    }

    private void addDeadline(String[] input_split) {
        // TODO: Throw and handle errors for invalid input
        int by_index = 1;
        for (; by_index < input_split.length; by_index++) {
            if (input_split[by_index].equals("/by")) {
                break;
            }
        }

        String deadlineName = String.join(" ", Arrays.copyOfRange(input_split, 1, by_index));
        String by = String.join(" ", Arrays.copyOfRange(input_split, by_index + 1, input_split.length));
        this.list.add(new Deadline(deadlineName, by));
        System.out.println(String.format("[MNSKY added deadline %s to their list]", deadlineName));
    }

    private void addEvent(String[] input_split) {
        int at_index = 1;
        for (; at_index < input_split.length; at_index++) {
            if (input_split[at_index].equals("/at")) {
                break;
            }
        }

        String eventName = String.join(" ", Arrays.copyOfRange(input_split, 1, at_index));
        String at = String.join(" ", Arrays.copyOfRange(input_split, at_index + 1, input_split.length));
        this.list.add(new Event(eventName, at));
        System.out.println(String.format("[MNSKY added event %s to their list]", eventName));
    }

    /**
     * Parses the input and executes the logic depending on the type of input.
     * @return True if the user input "bye" and thus wants to stop talking to the chatbot.
     *          False otherwise.
     */
    public boolean parseInput() {
        String input = this.getInput();
        String[] input_split = input.split(" ");

        switch (input_split[0]) {
            case "bye":
                this.bye();
                return false;

            case "list":
                this.printList();
                break;

            case "mark":
                // TODO: Throw and handle error if no number or invalid number given
                this.mark(Integer.parseInt(input_split[1]) - 1);
                break;

            case "unmark":
                // TODO: Throw and handle error if no number or invalid number given
                this.unmark(Integer.parseInt(input_split[1]) - 1);
                break;

            case "todo":
                this.addTask(input);
                break;

            case "event":
                this.addEvent(input_split);
                break;

            case "deadline":
                this.addDeadline(input_split);
                break;

            default:
                this.printMnsky("...");
        }

        this.printMnsky("I can help!");
        return true;
    }

    public static void main(String[] args) {
        Mnsky mnsky = new Mnsky();
        mnsky.greeting();

        while (mnsky.parseInput()) {}
    }
}
