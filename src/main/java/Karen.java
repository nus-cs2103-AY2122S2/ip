import java.util.Scanner;

public class Karen {
    Task[] toDos;
    int runningIndex;

    public Karen() {
        this.toDos = new Task[100];
        this.runningIndex = 0;
    }

    public void echo(String statement) {
        System.out.println("~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*");
        System.out.println(statement);
        System.out.println("~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*\n");
    }

    public void processCommand(String command) {
        String output = command;
        // commands with extra functionality
        if (command.equals("bye")) {
            output = "Goodbye - I'll be seeing your manager's manager next.";
            this.echo(output);
            System.exit(0);
        }
        else if (command.equals("list")) {
            // formatting list
            int counter = 0;
            output = "";

            for (Task item: this.toDos) {
                output = output.concat(String.format("%d.%s\n", counter+1, item.toString()));
                counter++;
                if (counter >= this.runningIndex) {
                    break;
                }
            }
        }
        else if (command.matches("(un)?mark \\d+")) {
            int index = Integer.valueOf(command.split(" ")[1]);
            Task getTask = this.toDos[index-1];

            if (command.startsWith("mark")) {
                getTask.markDone();
                output = String.format("This task is finally done:\n  %s",getTask.toString());
            } else if (command.startsWith("unmark")) {
                getTask.markUndone();
                output = String.format("This task is now incomplete - unacceptable:\n  %s",getTask.toString());
            }
        }
        else {
            output = String.format("added: %s", command);
            this.toDos[this.runningIndex] = new Task(command);
            this.runningIndex++;
        }
        this.echo(output);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Karen manager = new Karen();

        // greet
        manager.echo("Hello, my name is Karen.\nI'll be speaking (to) as your manager today.");

        while (true) {
            // take in input as commands
            String cmd = scanner.nextLine();
            manager.processCommand(cmd);
        }
    }
}
