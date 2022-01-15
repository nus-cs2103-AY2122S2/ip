import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private final String botName;
    private ArrayList<String> items;

    private Duke(String botName) {
        this.botName = botName;
        items = new ArrayList<>();
    }

    private void Greeting() {
        String logo = "          .  .\n" +
                "          |\\_|\\\n" +
                "          | a_a\\\n" +
                "          | | \"]\n" +
                "      ____| '-\\___\n" +
                "     /.----.___.-'\\\n" +
                "   /   .-. (~v~) /|\n" +
                "  |'|  \\:  .--  / \\\n" +
                " / |-/  \\_/____/\\/~|\n" +
                "|/  \\ |  []_|_|_] \\ |\n" +
                "| \\  | \\ |___   _\\ ]_}\n" +
                "| |    |  /  /  |    \\\n" +
                "\\ |    |/\\|  |/|/\\    \\\n" +
                " \\|\\ |\\|  |  | / /\\/\\__\\\n" +
                "  \\ \\| | /   | |__\n" +
                "       / |   |____)\n" +
                "       |_/";
        String s = String.format(logo + "\n" + "Hello! I'm %s"
                            +"\n" + "What can I do for you?", this.botName);
        Print(3,s);
    }

    private void Print(int choice, String text) {
        String line ="+" + String.valueOf('-').repeat(50) + "+\n";
        StringBuilder sb = new StringBuilder();
        sb.append(line);
        switch(choice) {
            case 1:
                for (int i = 0; i < items.size(); i++) {
                    sb.append((i+1) + ". " + items.get(i) + "\n");
                }
                break;
            case 2:
                items.add(text);
                sb.append("added: " + text + "\n");
                break;
            default:
                sb.append(text + "\n");
                break;
        }
        sb.append(line);
        System.out.println(sb);
    }

    private boolean Response(String text) {
        if (text.equals("bye")) {
            Terminate();
            return false;
        }
        else if (text.equals("list")){
            Print(1, "");
            return true;
        }
        else {
            Print(2, text);
            return true;
        }
    }

    private void Run() {
        Greeting();
        Scanner sc = new Scanner(System.in);
        boolean valid = Response(sc.nextLine());
        while (valid) {
            valid = Response(sc.nextLine());
        }
        sc.close();
    }

    private void Terminate() {
        String exit_text = "Bye. This city needs me. na na na na na na BATMAN\n" +
                            "      ▄   ▄\n" +
                            " ▄█▄  █▀█▀█  ▄█▄\n" +
                            " ▀▀████▄█▄████▀▀\n" +
                            "      ▀█▀█▀";
        Print(3,exit_text);
        System.exit(0);
    }

    public static void main(String[] args) {
        Duke bot = new Duke("Batman");
        bot.Run();
    }
}
