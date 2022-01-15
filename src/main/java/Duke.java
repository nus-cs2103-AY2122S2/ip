import java.util.Scanner;

public class Duke {


    public static void main(String[] args) {
        //Program's opening message
        System.out.println("____________________________________________________________");
        System.out.println("    Wassup! I'm Puke! \uD83E\uDD2E \n    What can I do for you?");
        System.out.println("____________________________________________________________");

        //Scanner initialization for user input
        Scanner sc = new Scanner(System.in);
        String userInput = "";

        while (true) {
            //User Input dictates what the program does
            userInput = sc.nextLine();
            //Case 1: Terminate the program
            if (userInput.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("    Bye Bye \uD83D\uDC4B! Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                //Break to exit
                break;
            }
            //Case 2: List the todolist
            else if (userInput.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("    list");
                System.out.println("____________________________________________________________");
            }

            //Case 3: blah
            else if (userInput.equals("blah")) {
                System.out.println("____________________________________________________________");
                System.out.println("    blah");
                System.out.println("____________________________________________________________");
            }

            //Case 4: If its anything but the commands
            else {
                System.out.println("____________________________________________________________");
                System.out.println("    " + userInput + "?");
                System.out.println("    What's that? I dont recognise that command\uD83E\uDD2E!\n    Try again! ");
                System.out.println("____________________________________________________________");
            }

        }





    }
}
