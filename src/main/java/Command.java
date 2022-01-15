public class Command {
    private static String[] commands = new String[100];
    private static int numOfCommands = 0;
    private int indexOfCommand;
    private String command;
    private boolean isExit;

    public Command(String command) {
        if (!command.equals("list") || !command.equals("bye")) {
            indexOfCommand = numOfCommands;
            numOfCommands++;
            commands[indexOfCommand] = command;
        }
        this.command = command.toLowerCase();
        isExit = false;
    }

    public boolean execute() {
        if (command.equals(new String ("bye"))) {
            System.out.println("    Bye. Hope to see you again soon!");
            isExit = true;
        } else if (command.equals(new String("list"))) {
            for (int i = 0;i < numOfCommands; i++) {
                int indexToPrint = i + 1;
                System.out.println("    " + indexToPrint + ". " + commands[i]);
            }
        } else {
            System.out.println("    added: " + command);
        }
        return isExit;
    }

}
