import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Scanner;
import java.util.LinkedList;

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
        LinkedList<Task> tasks = new LinkedList<>();
        String directoryPath = "./data";
        String filePath = "./data/duke.txt";
        File file = new File(filePath);
        File directory = new File(directoryPath);
        loadTasks(directory, file, tasks);
        String input;
        Scanner sc = new Scanner(System.in);
        input = sc.next();
        while (!input.equalsIgnoreCase("bye")) {
            System.out.println("___________________________________________________________");
            int id;
            switch(input) {
                case "list" :
                    System.out.println("Here are the tasks on your list, sir.");
                    for (int i = 1; i <= tasks.size(); i++) {
                        System.out.println(i +". " + tasks.get(i - 1));
                    }
                    break;
                case "todo" :
                    sc.reset();
                    try {
                        input = sc.nextLine().strip();
                        if (input.isEmpty()) throw new DukeException("Do what, sir?");
                        Todo task = new Todo(input);
                        tasks.add(task);
                        addTasks(file, task);
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
                        tasks.add(task);
                        addTasks(file, task);
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
                        tasks.add(task);
                        addTasks(file, task);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "mark" :
                    id = sc.nextInt();
                    updateTasks(file, id, "mark");
                    if (id <= tasks.size()) {
                        tasks.get(id - 1).markAsDone();
                    }
                    break;
                case "unmark" :
                    id = sc.nextInt();
                    updateTasks(file, id, "unmark");
                    if (id <= tasks.size()) {
                        tasks.get(id - 1).markAsNotDone();
                    }
                    break;
                case "remove" :
                    id = sc.nextInt();
                    updateTasks(file, id, "remove");
                    if (id <= tasks.size()) {
                        tasks.remove(id - 1);
                    }
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

    public static void loadTasks(File directory, File file, LinkedList tasks) {
        try {
            if (!directory.exists()) {
                directory.mkdir();
            }
            if (!file.exists()) {
                file.createNewFile();
                return;
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String type = scanner.next();
                String status = scanner.next();
                String description = scanner.nextLine().strip();
                switch (type) {
                    case "[T]" :
                        Todo task = new Todo(description);
                        if (status.equals("[X]")) {
                            task.markAsDone();
                        }
                        tasks.add(task);
                        break;
                    case "[D]" :
                        String[] by = description.split("by: ");
                        SimpleDateFormat format = new SimpleDateFormat("MMM d yyyy");
                        try {
                            Date date = format.parse(by[1].substring(0, by[1].length() - 1));
                            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                            Deadline deadline = new Deadline(by[0].substring(0, by[0].length() - 2), localDate);
                            if (status.equals("[X]")) {
                                deadline.markAsDone();
                            }
                        tasks.add(deadline);
                        } catch (ParseException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "[E]" :
                        String[] venue = description.split("at: ");
                        Event event = new Event(venue[0].substring(0, venue[0].length() - 2), venue[1].substring(0, venue[1].length() - 1));
                        if (status.equals("[X]")) {
                            event.markAsDone();
                        }
                        tasks.add(event);
                        break;
                    default :
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void readFile(File file) {
        try {
            Scanner reader = new Scanner(file);
            System.out.println("Here are the tasks on your list, sir.");
            while (reader.hasNextLine()) {
                System.out.println("  " + reader.nextLine());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addTasks(File file, Task task) {
        try {
            FileWriter writer = new FileWriter(file.getPath(), true);
            writer.write(task.toString() + "\n");
            writer.close();
            System.out.println("I've added this to your tasks sir: " + task);
            }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateTasks(File file, int id, String command) {
        try {
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
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

