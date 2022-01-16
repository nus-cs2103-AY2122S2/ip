import java.util.Scanner;
import java.util.ArrayList;

public class Mnsky {
    private ArrayList<String> list;
    private Scanner con;

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

    /**
     * Parses the input and executes the logic depending on the type of input.
     * @return True if the user input "bye" and thus wants to stop talking to the chatbot.
     *          False otherwise.
     */
    public boolean parseInput() {
        String input = this.getInput();

        switch (input) {
            case "bye":
                this.printMnsky("I can help!");
                System.out.println("[MNSKY is shutting itself down...]");
                return false;

            case "list":
                for (int i = 0; i < this.list.size(); i++) {
                    System.out.println(String.format("%d. %s", i, this.list.get(i)));
                }
                break;

            default:
                System.out.println(String.format("[Added %s]", input));
                this.list.add(input);
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
