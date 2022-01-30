import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Scanner;

public class Duke {
    static TaskList taskList = new TaskList();
    static final Path folderPath = Path.of("..", "..", "..", "data");

    static String logo = " __   ___\n"
            + "/  | /  / _____  _    _  _____  _____    __\n"
            + "|  |/  / / ____// \\  / \\/  _  \\/  _  \\  \\__\\\n"
            + "|     /  | |___ |  \\ | || | | || |_| /_ |  |\n"
            + "|     \\  | ____|| | \\| || | | ||  ___  ||  |\n"
            + "|  |\\  \\ | |___ | |\\ | || |_| || |___| ||  |\n"
            + "\\__/ \\__\\\\_____\\\\_/ \\__/\\_____/\\_______/|__|\n";

    static void say(String line) {
        System.out.println("    " + line);
    }

    static void load() throws IOException {
        try {
            Scanner fileScanner = new Scanner(folderPath.resolve("tasks.txt"));
            while (fileScanner.hasNextLine()) {
                String[] taskString = fileScanner.nextLine().split(",.,");

                Task t;
                if (taskString[0].equals("T")) {
                    t = new ToDo(taskString[2]);
                } else if (taskString[0].equals("D")) {
                    t = new Deadline(taskString[2], taskString[3]);
                } else {
                    t = new Event(taskString[2], taskString[3]);
                }

                if (taskString[1].equals("1")) {
                    t.done();
                }

                taskList.add(t);
            }
        } catch (NoSuchFileException e) {
            say("Starting with new list of tasks");
        }
    }

    static void save() throws IOException {
        if (!Files.exists(folderPath)) {
            Files.createDirectories(folderPath);
        }

        Path filePath = folderPath.resolve("tasks.txt");

        BufferedWriter out = Files.newBufferedWriter(filePath);

        for (Task task : taskList) {
            out.append(task.toSave());
        }

        out.close();
    }

    public static void main(String[] args) throws IOException {
        load();

        System.out.println(logo);
        say("Hello there, how may I help you?");

        Scanner sc = new Scanner(System.in);

        MainLoop:
        while (true) {
            String[] cmd = sc.nextLine().split("\\s", 2);
            String[] fields;
            Task t;


            switch (cmd[0]) {
            case "bye":
                try {
                    save();
                } catch (Exception e) {
                    say("File save unsuccessful, exit anyway? [y/N]");
                    String response = sc.nextLine();
                    if (response.equals("y")) {
                        say("May the force be with you");
                        break MainLoop;
                    } else {
                        break;
                    }
                }
                say("Goodbye, old friend");
                break MainLoop;

            case "list":
                say("Here are the tasks in your list:");
                say(taskList.toString());
                break;

            case "mark":
                t = taskList.get(Integer.parseInt(cmd[1]) - 1);
                t.done();
                say("I've marked this task as done:");
                say(t.toString());
                break;

            case "unmark":
                t = taskList.get(Integer.parseInt(cmd[1]) - 1);
                t.undone();
                say("I've marked this task as undone:");
                say(t.toString());
                break;

            case "todo":
                String name = cmd[1];
                if (name == null) {
                    say("The description of a todo cannot be empty");
                    break;
                }
                t = new ToDo(name);
                taskList.add(t);
                say("added: " + t);
                break;

            case "deadline":
                fields = cmd[1].split(" /by ");
                t = new Deadline(fields[0], fields[1]);
                taskList.add(t);
                say("added: " + t);
                break;

            case "event":
                fields = cmd[1].split(" /at ");
                t = new Event(fields[0], fields[1]);
                taskList.add(t);
                say("added: " + t);
                break;

            case "delete":
                t = taskList.remove(Integer.parseInt(cmd[1]) - 1);
                say("I have removed this task: ");
                say(t.toString());
                break;

            default:
                say("The command you are using is not in the archives.");
                break;
            }
        }

        sc.close();
    }
}