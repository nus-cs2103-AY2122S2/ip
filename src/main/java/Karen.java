import java.util.Scanner;

public class Karen {
    String[] toDos;
    int runningIndex;

    public Karen() {
        this.toDos = new String[100];
        this.runningIndex = 0;
    }

    public void processCommand(String command) {
        if (!command.equals("bye") && !command.equals("list") )  {
            this.toDos[this.runningIndex] = command;
            this.runningIndex++;
        }
        this.echo(command);
    }

    public void echo(String command, Boolean greet) {
        System.out.println("~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*");
        System.out.println(command);
        System.out.println("~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*\n");
    }

    public void echo(String command) {
        String output = command;
        // commands with extra functionality
        if (command.equals("bye")) {
            output = "Goodbye - I'll be seeing your manager's manager next.";
        } else if (command.equals("list")) {
            // formatting list
            int counter = 0;
            output = "";
            for (String item: this.toDos) {
                output = output.concat(String.format("%d: %s\n", counter+1, item));
                counter++;
                if (counter >= this.runningIndex) {
                    break;
                }
            }
        } else {
            output = String.format("added: %s", command);
        }

        System.out.println("~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*");
        System.out.println(output);
        System.out.println("~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*\n");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Karen manager = new Karen();

        // greet
        manager.echo("Hello, my name is Karen.\nI'll be speaking (to) as your manager today.",true);

        while (true) {
            // take in input as commands
            String cmd = scanner.nextLine();
            manager.processCommand(cmd);

            // exit loop
            if (cmd.equals("bye")) {
                break;
            }
        }
        System.exit(0);
    }
}
