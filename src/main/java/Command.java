public class Command {
    private String command;
    private String input;
    private String argument;

    public Command(String input) {
        String[] inputArgs = input.trim().split(" ", 2);
        this.command = inputArgs[0];
        this.input = input.trim();
        if (inputArgs.length > 1) {
            this.argument = inputArgs[1];
        }
    }

    public void run() {
        if(command.equals("")) {
            return;
        }

        switch (command) {
            case "bye":
                System.out.println(" See you again! :)");
                System.exit(0);
            case "list":
                System.out.println(" Here's your to-do list. Make things happen!");
                Task.printTasks();
                break;
            case "mark":
                System.out.println(" Well done!");
                Task.markAsDone(Integer.parseInt(argument) - 1);
                break;
            case "unmark":
                System.out.println(" Oops! Fixed that for you.");
                Task.markAsNotDone(Integer.parseInt(argument) - 1);
                break;
            default:
                Task task = new Task(input);
                Task.addToList(task);
                break;
        }
        System.out.println("__________________________________________________________");
    }
}
