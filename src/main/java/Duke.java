import java.util.*;


public class Duke {

    private static boolean isBye(String s) {
        return !s.equals("bye");
    }

    private static void welcome() {
        String welcomeMessage = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"
                + "Hello! I'm JJ\n"
                + "What do you want? :D \n";

        System.out.println("Hello from\n" + welcomeMessage);
    }

    private static void exit() {
        String goodByeMessage = "   _____________________________________________\n"
                + "       " + "Bye. I hope to see you soon." +"\n"
                + "   _____________________________________________";
        System.out.println(goodByeMessage);
    }



    private static boolean processInput(String userInput) {
        if(userInput.equals("bye")) {
            exit();
            return false;
        } else if (userInput.equals("list")) {
            String listTask = Task.printArray();
            String output = "   _____________________________________________\n"
                    + listTask
                    + "   _____________________________________________";
            System.out.println(output);

            return true;
        } else {
            Task newTask = new Task(userInput);
            String output = "   _____________________________________________\n"
                    + "       " + "added: " + userInput +"\n"
                    + "   _____________________________________________";
            System.out.println(output);
            return true;
        }
    }

    public static void main(String[] args) {
        welcome();
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();

        String[] taskList = new String[100];


        while(processInput(userInput)) {
            userInput = sc.nextLine();
        }

    }
}
