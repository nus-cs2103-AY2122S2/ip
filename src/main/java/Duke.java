import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    private final String botName;
    private ArrayList<Task> tasks;
    private final String line ="+" + String.valueOf('-').repeat(50) + "+\n";

    private Duke(String botName) {
        this.botName = botName;
        this.tasks = getTasks();
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

    private void checkInput(String input) {
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
                sb.append(toggleStatus("mark", Integer.parseInt(command[1]) - 1));
                break;
            case "unmark":
                sb.append(toggleStatus("unmark", Integer.parseInt(command[1]) - 1));
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
                throw new DukeException(Error.INVALID);
            }
        }
        catch (DukeException e) {
            sb.append(e.invalidInput());
        }
        sb.append(line);
        System.out.print(sb);
    }

    private StringBuilder toggleStatus(String action, int index) {
        StringBuilder sb = new StringBuilder();
        try {
            if (index >= tasks.size() || index < 0) {
                throw new DukeException(Error.LISTERROR);
            }
            switch (action) {
            case "mark":
                tasks.get(index).markItem();
                sb.append("Alfred, mark it as done!\n  ").append(tasks.get(index).toString()).append("\n");
                FileSave.writetoFile(tasks);
                break;
            case "unmark":
                tasks.get(index).unmarkItem();
                sb.append("Make up your mind. Alfred, unmark it!\n  ").append(tasks.get(index).toString()).append("\n");
                FileSave.writetoFile(tasks);
                break;
            }
            return sb;
        }
        catch (DukeException e) {
            return sb.append(printList() + e.listError());
        }
    }

    private ArrayList<Task> getTasks() {
        FileSave.createFile();
        tasks = new ArrayList<>();
        List<String> lines = FileSave.readFile();
        if (lines != null) {
            for (String line : lines) {
                String[] item = line.split("\\|");
                switch(item[0]) {
                case "D":
                    tasks.add(new Deadline(item[1].equals("1"), item[2], item[3]));
                    break;
                case "E":
                    tasks.add(new Event(item[1].equals("1"), item[2], item[3]));
                    break;
                default:
                    tasks.add(new Todo(item[1].equals("1"), item[2]));
                }
            }
        }
        return tasks;
    }

    private String addTask(String[] command){
        try {
            String task;
            Task t = null;
            String description;
            String details;
            if (command.length <= 1) {
                throw new DukeException(Error.EMPTYDESC);
            }
            task = command[1];
            switch (command[0]) {
                case "todo":
                        description = task;
                        t = new Todo(description);
                        tasks.add(t);
                        return printTask(t);
                case "deadline":
                        description = task.split(" /")[0];
                        details = task.split("/by ")[1];
                        t = new Deadline(description, details);
                        tasks.add(t);
                        return printTask(t);
                case "event":
                        description = task.split(" /")[0];
                        details = task.split("/at ")[1];
                        t = new Event(description, details);
                        tasks.add(t);
                        return printTask(t);
            }
            tasks.add(t);
            FileSave.writetoFile(tasks);
            return printTask(t);
        }
        catch (DukeException e) {
            return e.emptyDesc();
        }
        catch (DateTimeParseException e) {
            return "Get the date format right!\n" + "dd/MM/yyyy HH:mm OR yyyy-MM-dd HH:mm\n";
        }
    }

    private String deleteTask(int index) {
        try {
            if (index >= tasks.size() || index < 0) {
                throw new DukeException(Error.LISTERROR);
            }
            Task t = tasks.get(index);
            tasks.remove(index);
            FileSave.writetoFile(tasks);
            return "Got it. Task removed:\n  " + t + "\n" + t.printNoOfTasks(tasks.size()) + "\n";
        }
        catch (DukeException e) {
            return printList() + e.listError();
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
        return "Got it. Task added:\n  " + t + "\n" + t.printNoOfTasks(tasks.size()) + "\n";
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
