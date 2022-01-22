import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String home = System.getProperty("user.home");
    // contain tasks in array
    private static final ArrayList<Task> tasks = new ArrayList<>();


    private enum Command {
        BYE,
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        INVALID
    }

    private static Command parser(String input) {
        if (input.equals("bye")) {
            return Command.BYE;
        } else if (input.equals("list")) {
            return Command.LIST;
        } else if (input.startsWith("mark")) {
            return Command.MARK;
        } else if (input.startsWith("unmark")) {
            return Command.UNMARK;
        } else if (input.startsWith("todo")) {
            return Command.TODO;
        } else if (input.startsWith("deadline")) {
            return Command.DEADLINE;
        } else if (input.startsWith("event")) {
            return Command.EVENT;
        } else if (input.startsWith("delete")) {
            return Command.DELETE;
        } else {
            return Command.INVALID;
        }
    }

    private static void goodbye() {
        System.out.println("    Goodbye! Till the next time we meet!");
    }

    private static void listTasks() {
        System.out.println("    Your outstanding tasks as of now are as listed:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + tasks.get(i));
        }
    }

    private static void markTask(String request) throws DukeException {
        String[] parsedReq = request.split(" ");
        if (parsedReq.length != 2) {
            throw new DukeException("Please tell me which task you would like to be marked as done.");
        } else {
            try {
                int curr = Integer.parseInt(parsedReq[1]) - 1;
                Task t = tasks.get(curr);
                t.markAsDone();
                updateData();
                System.out.println("    Great job! I've marked the task as completed:");
                System.out.println("    " + t);
            } catch (NumberFormatException n) {
                throw new DukeException("Please enter a valid task number to mark as done!");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("The task you specified does not exist. :(");
            }
        }
    }

    private static void unmarkTask(String request) throws DukeException {
        String[] parsedReq = request.split(" ");
        if (parsedReq.length != 2) {
            throw new DukeException("Please tell me which task you would like to be marked as undone.");
        } else {
            try {
                int curr = Integer.parseInt(parsedReq[1]) - 1;
                Task t = tasks.get(curr);
                t.markAsUndone();
                updateData();
                System.out.println("    No problem, I've marked the task as uncompleted:");
                System.out.println("    " + t);
            } catch (NumberFormatException n) {
                throw new DukeException("Please enter a valid task number to mark as undone!");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("The task you specified does not exist. :(");
            }
        }
    }

    private static void createTodo(String request) throws DukeException {
        String[] parsedReq = request.strip().split(" ");
        if (parsedReq.length == 1) {
            throw new DukeException("The description of a todo cannot be empty.");
        } else {
            Todo todo = new Todo(request.substring(5).strip());
            tasks.add(todo);
            appendToData(todo.getAppendData());
            System.out.println("    Task added:");
            System.out.println("    " + todo);
        }
    }

    private static void createDeadline(String request) throws DukeException {
        if (request.strip().length() == 8) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
        String next = request.substring(8);
        String[] parsedReq = next.split("/by");
        String desc = parsedReq[0].strip();
        String by = parsedReq[1].strip();
        if (!request.contains(" /by ")) {
            throw new DukeException("You left the deadline of the deadline empty!");
        } else if (desc.length() == 0) {
            throw new DukeException("The description of a deadline cannot be empty.");
        } else if (by.length() == 0) {
            throw new DukeException("You left the deadline of the deadline empty!");
        } else {
            Deadline deadline = new Deadline(desc, by);
            tasks.add(deadline);
            appendToData(deadline.getAppendData());
            System.out.println("    Task added:");
            System.out.println("    " + deadline);
        }
    }

    private static void createEvent(String request) throws DukeException {
        if (request.strip().length() == 5) {
            throw new DukeException("The description of an event cannot be empty.");
        }
        String next = request.substring(5);
        String[] parsedReq = next.split("/at");
        String desc = parsedReq[0].strip();
        String at = parsedReq[1].strip();
        if (!request.contains(" /at ")) {
            throw new DukeException("You left the date/time of the event empty!");
        } else if (desc.length() == 0) {
            throw new DukeException("The description of an event cannot be empty.");
        } else if (at.length() == 0 ) {
            throw new DukeException("You left the date/time of the event empty!");
        } else {
            Event event = new Event(desc, at);
            tasks.add(event);
            appendToData(event.getAppendData());
            System.out.println("    Task added:");
            System.out.println("    " + event);
        }
    }

    private static void deleteTask(String request) throws DukeException {
        String[] parsedReq = request.split(" ");
        if (parsedReq.length != 2) {
            throw new DukeException("Please tell me which task you would like to delete.");
        } else {
            try {
                int curr = Integer.parseInt(parsedReq[1]) - 1;
                Task t = tasks.get(curr);
                tasks.remove(curr);
                updateData();
                System.out.println("    No problem, I've deleted that task for you:");
                System.out.println("    " + t);
                System.out.println("    You now have " + tasks.size() + " tasks remaining on your list.");
            } catch (NumberFormatException n) {
                throw new DukeException("Please enter a valid task number to delete!");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("The task you specified does not exist. :(");
            }
        }
    }

    private static void loadData() {
        try {
            String filename = home + "/ip/data";
            Path dir = Paths.get(filename);

            // check if /ip/data directory exists, if not create
            if (!Files.exists(dir)) {
                Files.createDirectory(dir);
            }

            // check if the file in /ip/data exists, if not create
            Path p = Paths.get(home + "/ip/data/duke.txt");
            if (!Files.exists(p)) {
                Files.createFile(p);
            }

            File f = new File(p.toString());
            Scanner s = new Scanner(f);

            // load data from the save file
            while (s.hasNextLine()) {
                String task = s.nextLine();
                String[] parsedTask = task.split(" \\| ");
                switch (parsedTask[0]) {
                case "T":
                    Todo t = new Todo(parsedTask[2]);
                    if (parsedTask[1].equals("1")) {
                        t.markAsDone();
                    }
                    tasks.add(t);
                    break;
                case "D":
                    Deadline d = new Deadline(parsedTask[2], parsedTask[3]);
                    if (parsedTask[1].equals("1")) {
                        d.markAsDone();
                    }
                    tasks.add(d);
                    break;
                case "E":
                    Event e = new Event(parsedTask[2], parsedTask[3]);
                    if (parsedTask[1].equals("1")) {
                        e.markAsDone();
                    }
                    tasks.add(e);
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }

    private static void appendToData(String textToAppend) {
        try {
            FileWriter fw = new FileWriter(home + "/ip/data/duke.txt", true);
            fw.write(textToAppend + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }

    private static void updateData() {
        try {
            FileWriter fw = new FileWriter(home + "/ip/data/duke.txt");
            for (Task t : tasks) {
                fw.write(t.getAppendData() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        String poogie = "    ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠛⠉⠉⠉⠉⠉⠉⠉⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                "    ⣿⣿⣿⣿⣿⣿⣿⣿⣿⠏⠄⢀⣠⣶⣶⣶⣶⣤⡀⠄⠄⠹⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                "    ⣿⣿⣿⣿⣿⣿⣿⣿⡏⠄⠄⣾⡿⢿⣿⣿⡿⢿⣿⡆⠄⠄⢻⣿⣿⣿⣿⣿⣿⣿\n" +
                "    ⣿⣿⣿⣿⣿⣿⣿⡿⠃⠄⠄⢿⣇⣸⣿⣿⣇⣸⡿⠃⠄⠄⠸⣿⣿⣿⣿⣿⣿⣿\n" +
                "    ⣿⣿⣿⣿⣿⡿⠋⠄⠄⠄⠄⠄⠉⠛⠛⠛⠛⠉⠄⠄⠄⠄⠄⠄⠙⣿⣿⣿⣿⣿\n" +
                "    ⣿⣿⣿⣿⡟⠁⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠈⢿⣿⣿⣿\n" +
                "    ⣿⣿⣿⡟⠄⠄⠄⠠⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠈⢿⣿⣿\n" +
                "    ⣿⣿⡟⠄⠄⠄⢠⣆⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⣧⠄⠄⠄⠈⢿⣿\n" +
                "    ⣿⣿⡇⠄⠄⠄⣾⣿⡀⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⢰⣿⣧⠄⠄⠄⠘⣿\n" +
                "    ⣿⣿⣇⠄⣰⣶⣿⣿⣿⣦⣀⡀⠄⠄⠄⠄⠄⠄⠄⢀⣠⣴⣿⣿⣿⣶⣆⠄⢀⣿\n" +
                "    ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠏⠄⠄⢸⣿⠇⠄⠄⠹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                "    ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣦⣤⣴⣾⣿⣶⣤⣤⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n";
        String divider = "    ============================================================\n";
        System.out.println(divider + "    Hello! My name is Poogie.\n" + poogie + divider +
                "    How may I be of service to you?\n" + divider);

        // load data from user's hard drive
        loadData();

        while (userInput.hasNextLine()) {
            String request = userInput.nextLine();
            Command c = parser(request);

            System.out.print(divider);
            try {
                switch (c) {
                case BYE:
                    goodbye();
                    break;
                case LIST:
                    listTasks();
                    break;
                case MARK:
                    markTask(request);
                    break;
                case UNMARK:
                    unmarkTask(request);
                    break;
                case TODO:
                    createTodo(request);
                    break;
                case DEADLINE:
                    createDeadline(request);
                    break;
                case EVENT:
                    createEvent(request);
                    break;
                case DELETE:
                    deleteTask(request);
                    break;
                case INVALID:
                    throw new DukeException("My apologies, but it seems that I do not understand your request.");
                }
            } catch (DukeException e) {
                System.out.println("    " + e.getMessage());
            }
            System.out.print(divider);
        }
    }
}


