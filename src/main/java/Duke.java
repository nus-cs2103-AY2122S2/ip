import java.util.Scanner;

public class Duke {
    private final String botName;

    private Duke(String botName) {
        this.botName = botName;
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
        System.out.println(s);
    }

    private void Print(String text) {
        int input_len = text.length();
        StringBuilder sb = new StringBuilder(input_len);
        String line = "+" + String.valueOf('-').repeat(input_len + 2) + "+";
        String output = "\n" + "| " + text + " |" + "\n";
        sb.append(line + output + line);
        System.out.println(sb);
    }

    private void userReply(String text) {
        Response(text);
    }

    private boolean Response(String text) {
        if (text.equals("bye")) {
            Terminate();
            return false;
        }
        else {
            Print(text);
            return true;
        }
    }

    private void Run() {
        Greeting();
        Scanner sc = new Scanner(System.in);
        while (Response(sc.nextLine())) {
            userReply(sc.nextLine());
        }
        sc.close();
    }

    private void Terminate() {
        Print("Bye. This city needs me. na na na na na na BATMAN");
        String exit_logo = "      ▄   ▄\n" +
                            " ▄█▄  █▀█▀█  ▄█▄\n" +
                            " ▀▀████▄█▄████▀▀\n" +
                            "      ▀█▀█▀";
        System.out.println(exit_logo);
        System.exit(0);
    }


    public static void main(String[] args) {
        Duke bot = new Duke("Batman");
        bot.Run();
    }
}
