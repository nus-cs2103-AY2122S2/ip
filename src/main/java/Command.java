public class Command {
    private String command;
    private boolean isExit;

    public Command(String command) {
        this.command = command.toLowerCase();
        isExit = false;
    }

    public boolean execute() {
        if (command.equals(new String ("bye"))) {
            System.out.println("Bye. Hope to see you again soon!");
            isExit = true;
        } else {
            System.out.println("    " + command);
        }
        return isExit;
    }

}
