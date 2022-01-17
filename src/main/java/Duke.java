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
        System.out.print(s);
    }

    private void checkInput(String input){
        StringBuilder sb = new StringBuilder();
        int itemIndex;
        sb.append(line);
        String[] command = input.split(" ", 2);
        try {
            switch (command[0]) {
                case "list":
                    sb.append(printList());
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
                case "event":
                case "deadline":
                    sb.append(addTask(command));
                    break;
                case "delete":
                    sb.append(deleteTask(Integer.parseInt(command[1]) - 1));
                    break;
                default:
                    throw new DukeException("I'm sorry, but I don't know what that means.");
            }
        }
        catch (DukeException e) {
            sb.append(e);
        }
        sb.append(line);
        System.out.print(sb);
    }

    private String addTask(String[] command) throws DukeException{
        Task t;
        String description;
        String details;
        try {
            String task;
            task = command[1];
            switch (command[0]) {
                case "todo":
                        description = task;
                        t = new Todo(description);
                        tasks.add(t);
                        return printTask(t);
                case "deadline":
                        description = task.split("/")[0];
                        details = task.split("/by")[1];
                        t = new Deadline(description, details);
                        tasks.add(t);
                        return printTask(t);
                case "event":
                        description = task.split("/")[0];
                        details = task.split("/at")[1];
                        t = new Event(description, details);
                        tasks.add(t);
                        return printTask(t);
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("The description of a task cannot be empty.");
        }
        return "";
    }

    private String deleteTask(int index) throws DukeException {
        try {
            if (index >= tasks.size()) {
                throw new DukeException("Index does not exists in array.");
            }
            Task t = tasks.get(index);
            t.decrementTask();
            tasks.remove(index);
            return "Got it. Task removed:\n  " + t + "\n" + t.printNoOfTasks() + "\n";
        }
        catch (DukeException e) {
            return printList() + e;
        }
    }

    private String printList() {
        StringBuilder s = new StringBuilder();
        if (tasks.size() == 0) {
            return "List is empty.\n";
        }
        for (int i = 0; i < tasks.size(); i++) {
            s.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return s.toString();
    }

    private String printTask(Task t) {
        t.incrementTask();
        return "Got it. Task added:\n  " + t + "\n" + t.printNoOfTasks() + "\n";
    }

    private boolean Response(String input){
        if (input.equals("bye")) {
            Terminate();
            return false;
        }
        else {
            checkInput(input);
            return true;
        }
    }

    private void Run(){
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