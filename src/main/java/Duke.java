import java.util.Scanner; //Import the Scanner class

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        //Greetings
        System.out.println("Greetings! I'm Duke" + "\n" + "What can I do for you today?\n" + logo);

        //create a scanner object
        Scanner scanned = new Scanner(System.in);
        System.out.println("");

        //read the user input
        String input = scanned.nextLine();

        //continue the scanning if user does not say bye, else continue repeating what the user says
        while (!input.equals("bye")) {
            System.out.println(input);

            //create a scanner object which contains user input
            scanned = new Scanner(System.in);
            System.out.println("");

            //read the new user input
            input = scanned.nextLine();
        }

        //goodbye msg
        System.out.println("Sad to see you leave, come back soon!");
    }
}
