import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private final String botName;
    private final ArrayList<Task> tasks;
    private final String line ="+" + String.valueOf('-').repeat(50) + "+\n";

    private Duke(String botName) {
        this.botName = botName;
        tasks = new ArrayList<>();
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
        String s = String.format(line + logo + "\n" + "Hello! I'm %s"
                            +"\n" + "What can I do for you?\n" + line, this.botName);
        System.out.println(s);
    }

    private void Print(String text) {
        StringBuilder sb = new StringBuilder();
        Task t;
        int itemIndex;
        String description;
        String details;
        sb.append(line);
        String[] command = text.split(" ", 2);
        switch(command[0]) {
            case "list":
                for (int i = 0; i < tasks.size(); i++) {
                    sb.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
                }
                break;
            case "mark":
                itemIndex = Integer.parseInt(command[1]) - 1;
                tasks.get(itemIndex).markItem();
                sb.append("Alfred, mark it as done!\n  ").append(tasks.get(itemIndex).toString()).append("\n");
                break;
            case "unmark":
                itemIndex = Integer.parseInt(command[1]) - 1;
                tasks.get(itemIndex).unmarkItem();
                sb.append("Make up your mind. Alfred, unmark it!\n  ").append(tasks.get(itemIndex).toString()).append("\n");
                break;
            case "todo":
                description = command[1];
                t = new Todo(description);
                tasks.add(t);
                sb.append("Got it. Task added:\n  ").append(t).append("\n").append(t.printNoOfTasks()).append("\n");
                break;
            case "deadline":
                description = command[1].split("/")[0];
                details = command[1].split("/by")[1];
                t = new Deadline(description, details);
                tasks.add(t);
                sb.append("Got it. Task added:\n  ").append(t).append("\n").append(t.printNoOfTasks()).append("\n");
                break;
            case "event":
                description = command[1].split("/")[0];
                details = command[1].split("/at")[1];
                t = new Event(description, details);
                tasks.add(t);
                sb.append("Got it. Task added:\n  ").append(t).append("\n").append(t.printNoOfTasks()).append("\n");
                break;
            default:
                t = new Task(text);
                tasks.add(t);
                sb.append("added: ").append(t.description).append("\n").append(t.printNoOfTasks()).append("\n");
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
        else {
            Print(text);
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
        String exitText = "Bye. This city needs me. na na na na na na BATMAN\n" +
                            "          _,     _   _     ,_\n" +
                "      .-'` /     \\'-'/     \\ `'-.\n" +
                "     /    |      |   |      |    \\\n" +
                "    ;      \\_  _/     \\_  _/      ;\n" +
                "   |         ``         ``         |\n" +
                "   |                               |\n" +
                "    ;    .-.   .-.   .-.   .-.    ;\n" +
                "     \\  (   '.'   \\ /   '.'   )  /\n" +
                "      '-.;         V         ;.-'\n" +
                "          `                 `\n";
        System.out.print(line + exitText + line);
        System.exit(0);
    }

    public static void main(String[] args) {
        Duke bot = new Duke("Batman");
        bot.Run();
    }
}