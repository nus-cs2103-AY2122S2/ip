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

        if (command.equals("bye")) {
            System.out.println(" See you again! :)");
            System.exit(0);
        } else if (command.equals("list")) {
            Task[] tasksList = Task.getTaskList();

            for (int i = 0; i < Task.getTaskCount(); i++) {
                System.out.println(" " + (i + 1) + ". " + tasksList[i].getDescription());
            }
        } else {
            Task task = new Task(input);
            Task.addToList(task);
            System.out.println(" added: " + input);
        }
        System.out.println("__________________________________________________________");
        return;
    }
}