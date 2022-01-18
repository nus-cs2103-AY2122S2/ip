import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String welcomeMessage = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"
                + "Hello! I'm JJ\n"
                + "What do you want? :D \n";

        System.out.println("Hello from\n" + welcomeMessage);

        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();

        while(!userInput.equals("bye")) {
            String output = "   _____________________________________________\n"
                            + "       " + userInput +"\n"
                            + "   _____________________________________________";
            System.out.println(output);
            userInput = sc.nextLine();
        }

        String goodByeMessage = "   _____________________________________________\n"
                + "       " + "Bye. I hope to see you soon." +"\n"
                + "   _____________________________________________";
        System.out.println(goodByeMessage);

    }
}
