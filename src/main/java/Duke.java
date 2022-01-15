import java.util.Scanner;

public class Duke {
    private static int id = 0;
    private static final Task[] tasks = new Task[100];

    public static void listTasks() {
        System.out.println("    Your outstanding tasks as of now are as listed:");
        for (int i = 0; i < id; i++) {
            System.out.println("    " + (i + 1) + ". " + tasks[i]);
        }
    }

    public static void markTask(int i) {
        Task t = tasks[i];
        t.markAsDone();
        System.out.println("    Great job! I've marked the task as completed:");
        System.out.println("    " + t);
    }

    public static void unmarkTask(int i) {
        Task t = tasks[i];
        t.markAsUndone();
        System.out.println("    No problem, I've marked the task as uncompleted:");
        System.out.println("    " + t);
    }

    public static void createTodo(String request, String[] parsedReq) throws DukeException {
        if (parsedReq.length == 1) {
            throw new DukeException("The description of a todo cannot be empty.");
        } else {
            Task newTask = new Todo(request.substring(5));
            tasks[id] = newTask;
            id++;
            System.out.println("    Task added:");
            System.out.println("    " + newTask);
        }
    }

    public static void createDeadline(String request, String[] parsedReq) throws DukeException {
        if (parsedReq.length == 1) {
            throw new DukeException("The description of a deadline cannot be empty.");
        } else {
            int dIndex = request.indexOf("/by");
            Task newTask = new Deadline(request.substring(9, dIndex), request.substring(dIndex + 4));
            tasks[id] = newTask;
            id++;
            System.out.println("    Task added:");
            System.out.println("    " + newTask);
        }
    }

    public static void createEvent(String request, String[] parsedReq) throws DukeException {
        if (parsedReq.length == 1) {
            throw new DukeException("The description of an event cannot be empty.");
        } else {
            int eIndex = request.indexOf("/at");
            Task newTask = new Event(request.substring(6, eIndex), request.substring(eIndex + 4));
            tasks[id] = newTask;
            id++;
            System.out.println("    Task added:");
            System.out.println("    " + newTask);
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

        while (userInput.hasNextLine()) {
            String request = userInput.nextLine();
            String[] parsedReq = request.split(" ");

            // Main response
            System.out.print(divider);
            try {
                if (request.equals("bye")) {
                    System.out.println("    Goodbye! Till the next time we meet!");
                } else if (request.equals("list")) {
                    listTasks();
                } else if (request.startsWith("mark")) {
                    markTask(Integer.parseInt(parsedReq[1]) - 1);
                } else if (request.startsWith("unmark")) {
                    unmarkTask(Integer.parseInt(parsedReq[1]) - 1);
                } else if (request.startsWith("todo")) {
                    createTodo(request, parsedReq);
                } else if (request.startsWith("deadline")) {
                    createDeadline(request, parsedReq);
                } else if (request.startsWith("event")) {
                    createEvent(request, parsedReq);
                } else {
                    throw new DukeException("My apologies, but it seems that I do not understand your request.");
                }
            } catch (DukeException e) {
                System.out.println("    " + e.getMessage());
            }
            System.out.print(divider);
        }
    }
}


