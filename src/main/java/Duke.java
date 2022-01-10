import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String splitter = "______________________________________";

        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        System.out.println("\tHow should I address you?");
        String userName = sc.next();
        sc.nextLine();
        System.out.println(String.format("\tSplendid! My pleasure to serve you %s \n\t%s", userName, splitter));

        while(true) {
            try {
                System.out.println(String.format("\tWhat may I assist you with today, %s? \n\t%s", userName, splitter));

                String userInput = sc.nextLine();
                if (userInput.toLowerCase().equals("bye")) {
                    System.out.println("\tGoodbye for now. \n");
                    break;
                }
                
                System.out.println(String.format("\t%s\n\t%s", splitter, userInput));
            }
            // TODO - catching all Exceptions
            catch(Exception e) {
                System.out.println("\tException: " + e.getMessage());
            }
        }

        sc.close();
    }
}
