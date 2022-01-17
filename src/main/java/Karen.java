import java.util.Scanner;

public class Karen {
    public void echo(String command_name) {
        String output = command_name;
        if (command_name.equals("bye")) {
            output = "Goodbye - I'll be seeing your manager's manager next.";
        }

        System.out.println("~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*");
        System.out.println(output);
        System.out.println("~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*\n");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Karen manager = new Karen();

        // greet
        manager.echo("Hello, my name is Karen.\nI'll be speaking (to) as your manager today.");

        while (true) {
            // take in input as commands
            String cmd = scanner.nextLine();
            manager.echo(cmd);

            // exit loop
            if (cmd.equals("bye")) {
                break;
            }
        }
        System.exit(0);
    }
}
