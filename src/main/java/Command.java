public class Command {
    private String command;
    private String input;

    public Command(String input) {
        String[] inputArgs = input.trim().split(" ");
        this.command = inputArgs[0];
        this.input = input.trim();
    }

    public void run() {
        if(command.equals("")) {
            return;
        }

        System.out.printf(" ");

        if (command.equals("bye")) {
            System.out.println("See you again! :)");
            System.exit(0);
        } else {
            Task task = new Task(input);
            Task.addToList(task);
            System.out.println("added: " + input);
        }
        System.out.println("__________________________________________________________");
        return;
    }
}