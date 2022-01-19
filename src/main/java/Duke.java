import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // Create new variables
        String tempString;
        Echo tempEcho = new Echo();
        Memory memory = new Memory();
        boolean looping = true;

        tempEcho.EchoString("Heya! I'm Duke!\n" +
                "What can I do for ya?");

        // While loop
        while(looping) {

            tempString = scanner.nextLine();

            // Switch case
            switch (tempString) {

                case "bye":
                    looping = false;
                    break;

                case "list":
                    memory.ListAll();
                    break;

                default:
                    memory.AddString(tempString);

            }
        }

        // The last thing Duke says
        tempEcho.EchoString("Ok then, see ya!");
    }
}
