package stevie;

import java.util.Scanner;

/**
 * This class interacts with the user by receiving inputs from the user, as well as, returning the
 * response to the user.
 */
public class StevieUi {
    private Scanner sc;

    public StevieUi() {
        sc = new Scanner(System.in);
    }

    /**
     * Receives a non-empty user input. If user's input is "help", output a message to inform
     * user on the commands to properly operate Stevie.
     *
     * @return user's input
     */
    public String getUserInput() {
        String userIn = "";
        while ((userIn = sc.nextLine()).length() == 0 || userIn.equals("help")) {
            outputMessage(help());
        }
        return userIn;
    }

    /**
     * Close the scanner object.
     */
    public void terminate() {
        sc.close();
    }

    private static String help() {
        return "\"list\": to display your activities.\n" +
                "\"bye\": to end our session.\n" +
                "\"mark <i>\" to mark the i-th task as done.\n" +
                "\"unmark <i>\" to unmark the i-th task as done.\n" +
                "\"delete <i>\" to delete the i-th task.\n" +
                "\"todo <task_name>\" to add a todo task.\n" +
                "\"deadline <task_name> /by <date>\" to add a deadline.\n" +
                "\"event <event_name> /at <date>\" to add an event.\n" +
                "Date should in format of dd/mm/yyyy HH:mm";
    }

    /**
     * Prints the response string from Stevie.
     *
     * @param text response from Stevie
     */
    public void outputMessage(String text) {
        System.out.println("____________________________");
        System.out.println(text);
        System.out.println("____________________________");
    }

    /**
     * Greets the user when Stevie starts.
     */
    public void greet() {
        // Logo generated from:
        // https://patorjk.com/software/taag/#p=display&f=Graffiti&t=Type%20Something%20
        String logo = "  .--.--.       ___\n" +
                " /  /    '.   ,--.'|_                        ,--,\n" +
                "|  :  /`. /   |  | :,'                     ,--.'|\n" +
                ";  |  |--`    :  : ' :                .---.|  |,\n" +
                "|  :  ;_    .;__,'  /     ,---.     /.  ./|`--'_       ,---.\n" +
                " \\  \\    `. |  |   |     /     \\  .-' . ' |,' ,'|     /     \\\n" +
                "  `----.   \\:__,'| :    /    /  |/___/ \\: |'  | |    /    /  |\n" +
                "  __ \\  \\  |  '  : |__ .    ' / |.   \\  ' .|  | :   .    ' / |\n" +
                " /  /`--'  /  |  | '.'|'   ;   /| \\   \\   ''  : |__ '   ;   /|\n" +
                "'--'.     /   ;  :    ;'   |  / |  \\   \\   |  | '.'|'   |  / |\n" +
                "  `--'---'    |  ,   / |   :    |   \\   \\ |;  :    ;|   :    |\n" +
                "               ---`-'   \\   \\  /     '---\" |  ,   /  \\   \\  /\n" +
                "                         `----'             ---`-'    `----'   ";
        System.out.println("Hello I'm\n" + logo);
        System.out.println("Tell me about your upcoming activities!");
        System.out.println("Input \"help\" for instructions.");
    }
}
