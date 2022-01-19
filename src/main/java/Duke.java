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

        tempEcho.echoString("Heya! I'm Duke!\n" +
                "What can I do for ya?");

        // While loop
        while(looping) {

            tempString = scanner.nextLine();

            // If-else chain. Was switch case
            if (tempString.equals("bye")) {
                break;
            } else if (tempString.equals("list")) {
                memory.listAll();
            } else if (tempString.equals("mark")) {         // Testing mark
                tempEcho.echoString("'mark' is an incomplete command. \n" +
                        "Try 'mark 1' to mark your task as done,\n" +
                        "or 'mark my calendar' to add a task instead.");
            } else if (tempString.startsWith("mark ")) {
                String[] tempStrArray = tempString.split(" ", 2);

                // If user only inputs 'mark '
                if (tempStrArray.length <= 1) {
                    tempEcho.echoString("'mark' is an incomplete command. \n" +
                            "Try 'mark 1' to mark your task as done,\n" +
                            "or 'mark my calendar' to add a task instead.");
                } else {

                    String testString = tempStrArray[1];

                    // Test if single number after 'mark'
                    try {
                        int address = Integer.parseInt(testString);
                        memory.setDone(address);
                    }
                    catch (NumberFormatException exception){
                        memory.addTask(tempString);
                    }
                }
            } else if (tempString.equals("unmark")) {       // Testing unmark
                tempEcho.echoString("'unmark' is an incomplete command. \n" +
                        "Try 'unmark 1' to mark your task as undone,\n" +
                        "or 'unmark my calendar' to add a task instead.");
            } else if (tempString.startsWith("unmark ")) {
                String[] tempStrArray = tempString.split(" ", 2);

                // If user only inputs 'unmark '
                if (tempStrArray.length <= 1) {
                    tempEcho.echoString("'unmark' is an incomplete command. \n" +
                            "Try 'unmark 1' to mark your task as undone,\n" +
                            "or 'unmark my calendar' to add a task instead.");
                } else {

                    String testString = tempStrArray[1];

                    // Test if single number after 'unmark'
                    try {
                        int address = Integer.parseInt(testString);
                        memory.setUndone(address);
                    }
                    catch (NumberFormatException exception){
                        memory.addTask(tempString);
                    }
                }
            } else {
                memory.addTask(tempString);
            }
        }

        // The last thing Duke says
        tempEcho.echoString("Ok then, see ya!");
    }
}
