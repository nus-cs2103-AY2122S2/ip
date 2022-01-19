public class Command {
    private String command;
    private String input;
    private String argument;
    private String extraInfo;

    public Command(String input) {
        String[] inputArgs = input.trim().split(" ", 2);
        String[] extraArgs = input.trim().split("/", 2);
        this.command = inputArgs[0];
        this.input = input.trim();
        if (inputArgs.length > 1) {
            this.argument = inputArgs[1];
        }
        if (extraArgs.length > 1) {
            this.argument = inputArgs[1].substring(0, inputArgs[1].indexOf("/") );
            this.extraInfo = extraArgs[1].trim().split(" ", 2)[1];
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
                System.out.println(" Here are your tasks. Make things happen!");
                Task.printAllTasks();
                break;
            case "mark":
                System.out.println(" Well done!");
                Task.markAsDone(Integer.parseInt(argument) - 1);
                break;
            case "unmark":
                System.out.println(" Oops! Fixed that for you.");
                Task.markAsNotDone(Integer.parseInt(argument) - 1);
                break;
            case "todo":
                Todo todo = new Todo(argument);
                Task.addToList(todo);
                break;
            case "event":
                Event event = new Event(argument, extraInfo);
                Task.addToList(event);
                break;
            case "deadline":
                Deadline deadline = new Deadline(argument, extraInfo);
                Task.addToList(deadline);
                break;
            default:
                Todo task = new Todo(input);
                Task.addToList(task);
                break;
        }
        System.out.println("__________________________________________________________");
    }
}
