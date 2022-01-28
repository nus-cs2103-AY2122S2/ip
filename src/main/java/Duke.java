import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {

        String logo =
                " ------    -------    -------   ---        ---   ---------    -------\n" +
                        "|____  |  |   _   |  |   _   |  \\  \\      /  /  |___   ___|  |   ____|\n" +
                        "     | |  |  |_|  |  |  |_|  |   \\  \\    /  /       | |      |  |____ \n" +
                        " _   | |  |   _   |  |      _|    \\  \\  /  /        | |      |_____  |\n" +
                        "| |__| |  |  | |  |  |  |\\  \\      \\  \\/  /      ___| |___    ____|  |\n" +
                        "|______|  |__| |__|  |__| \\__\\      \\____/      |_________|  |_______|\n"
                ;
        System.out.println("Welcome home, sir.\n" + logo);
        read();
    }
    public static void read() {
        String directoryPath = "./data";
        String filePath = "./data/duke.txt";
        File file = new File(filePath);
        File directory = new File(directoryPath);
        String input;
        Scanner sc = new Scanner(System.in);
        input = sc.next();
        while (!input.equalsIgnoreCase("bye")) {
            System.out.println("___________________________________________________________");
            int id;
            switch(input) {
                case "list" :
                    readFile(directory, file);
                    break;
                case "todo" :
                    sc.reset();
                    try {
                        input = sc.nextLine().strip();
                        if (input.isEmpty()) throw new DukeException("Do what, sir?");
                        Todo task = new Todo(input);
                        addTasks(directory, file, task);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "deadline" :
                    sc.reset();
                    input = sc.nextLine().strip();
                    String[] deadline = input.split(" /by ");
                    try {
                        if (deadline.length < 2) throw new DukeException("Invalid deadline task, sir.");
                        LocalDate date = LocalDate.parse(deadline[1]);
                        Deadline task = new Deadline(deadline[0], date);
                        addTasks(directory, file, task);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "event" :
                    sc.reset();
                    input = sc.nextLine().strip();
                    String[] event = input.split(" /at ");
                    try {
                        if (event.length < 2) throw new DukeException("Invalid event task, sir.");
                        Event task = new Event(event[0], event[1]);
                        addTasks(directory, file, task);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "mark" :
                    id = sc.nextInt();
                    updateTasks(directory, file, id, "mark");
                    break;
                case "unmark" :
                    id = sc.nextInt();
                    updateTasks(directory, file, id, "unmark");
                    break;
                case "remove" :
                    id = sc.nextInt();
                    updateTasks(directory, file, id, "remove");
                    break;
                default :
                    sc.reset();
                    sc.nextLine();
                    System.out.println("Unknown command, sir.");
            }
            System.out.println("___________________________________________________________");
            input = sc.next();
        }
        System.out.println("___________________________________________________________");
        System.out.println("Good bye, sir.");
        System.out.println("___________________________________________________________");
        sc.close();
    }

    public static void readFile(File directory, File file) {
        try {
            if (!directory.exists()) {
                directory.mkdir();
            }
            if (!file.exists()) {
                System.out.println("I have created a new list for your tasks, sir.");
                file.createNewFile();
                return;
            }
            Scanner reader = new Scanner(file);
            System.out.println("Here are the tasks on your list, sir.");
            while (reader.hasNextLine()) {
                System.out.println("  " + reader.nextLine());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addTasks(File directory, File file, Task task) {
        try {
            if (!directory.exists()) {
                directory.mkdir();
            }
            if (!file.exists()) {
                System.out.println("I have created a new list for your tasks, sir.");
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file.getPath(), true);
            writer.write(task.toString() + "\n");
            writer.close();
            System.out.println("I've added this to your tasks sir: " + task);
            }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateTasks(File directory, File file, int id, String command) {
        try {
            if (!directory.exists() || !file.exists()) {
                throw new DukeException("You do not have a task list, sir.");
            }
            String path = file.getPath();
            String temp = "./data/temp.txt";
            File tempFile = new File(temp);
            tempFile.createNewFile();
            int ptr = 0;
            FileWriter writer = new FileWriter(temp, true);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                ptr++;
                if (ptr == id) {
                    String type = scanner.next();
                    String status = scanner.next();
                    String description = scanner.nextLine().strip();
                    if (command.equals("mark")) {
                        status = "[X]";
                        System.out.println("Good job, sir. This task is marked done: ");
                        writer.write(type + " " + status + " " + description + "\n");
                    } else if (command.equals("unmark")){
                        status = "[-]";
                        System.out.println("Slacking off, sir? This task is marked not done: ");
                        writer.write(type + " " + status + " " + description + "\n");
                    } else {
                        System.out.println("Too weak to handle it? This task have been removed sir: ");
                    }
                    System.out.println("  " + description);
                } else {
                    writer.write(scanner.nextLine() + "\n");
                }
            }
            writer.close();
            file.delete();
            File dump = new File(path);
            tempFile.renameTo(dump);
            if (ptr < id) {
                throw new DukeException("This is an invalid task id, sir.");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            if (e.getMessage().equals("You do not have a task list, sir.")) {
                System.out.println("Create one by using any one of these commands:");
                System.out.println("  list");
                System.out.println("  todo <task>");
                System.out.println("  deadline <task> /by <when>");
                System.out.println("  event <task> /at <venue>");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

