import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String splitter = "______________________________________";
        ArrayList<String> list = new ArrayList<>();

        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        System.out.println("\tHow should I address you?");
        String userName = sc.nextLine();
        System.out.println(String.format("\tSplendid! My pleasure to serve you %s \n\t%s", userName, splitter));

        while(true) {
            try {
                System.out.println(String.format("\tWhat may I assist you with today, %s? \n\t%s", userName, splitter));

                String userInput = sc.nextLine();

                // Start of Duke's text block 
                System.out.println(String.format("\t%s", splitter));

                // Exit - "bye", exits the program 
                if (userInput.toLowerCase().equals("bye")) {
                    System.out.println("\tGoodbye for now. \n");
                    break;
                }

                // List - "list", lists all of the user's inputs 
                else if (userInput.toLowerCase().equals("list")) {
                    if (list.size() == 0) System.out.println(String.format("\tYour list is empty %s", userName));
                    else {
                        for (int i = 0; i < list.size(); i++) {
                            System.out.println(String.format("\t%d: %s", i+1, list.get(i)));
                        }
                    }
                }
                
                // Add - adds user input to a list 
                else {
                    list.add(userInput);
                    System.out.println(String.format("\tadded: %s", userInput));
                }

            }
            // TODO - catching all Exceptions
            catch(Exception e) {
                System.out.println("\tException: " + e.getMessage());
            }
        }

        sc.close();
    }
}
