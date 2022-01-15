import java.util.Scanner;

public class Duke {
    private static int id = 0;
    private static final Task[] tasks = new Task[100];

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
            String reqType = parsedReq[0];

            // Main response
            System.out.print(divider);
            if (request.equals("bye")) {
                System.out.println("    Goodbye! Till the next time we meet!\n");
                break;
            } else if (request.equals("list")) {
                System.out.println("    Your outstanding tasks as of now are as listed: ");
                for (int i = 0; i < id; i++) {
                    System.out.println("    " + (i + 1) + ". " + tasks[i]);
                }
            } else if (reqType.equals("mark")) {
                Task t = tasks[Integer.parseInt(parsedReq[1]) - 1];
                t.markAsDone();
                System.out.println("    Great job! I've marked the task as completed: ");
                System.out.println("    " + t);
            } else if (reqType.equals("unmark")) {
                Task t = tasks[Integer.parseInt(parsedReq[1]) - 1];
                t.markAsUndone();
                System.out.println("    No problem, I've marked the task as uncompleted: ");
                System.out.println("    " + t);
            } else if (reqType.equals("todo")) {
                Task newTask = new Todo(request.substring(5));
                tasks[id] = newTask;
                id++;
                System.out.println("    Task added: ");
                System.out.println("    " + newTask);
            } else if (reqType.equals("deadline")) {
                int dIndex = request.indexOf("/by");
                Task newTask = new Deadline(request.substring(9, dIndex), request.substring(dIndex + 4));
                tasks[id] = newTask;
                id++;
                System.out.println("    Task added: ");
                System.out.println("    " + newTask);
            } else if (reqType.equals("event")) {
                int eIndex = request.indexOf("/at");
                Task newTask = new Event(request.substring(6, eIndex), request.substring(eIndex + 4));
                tasks[id] = newTask;
                id++;
                System.out.println("    Task added: ");
                System.out.println("    " + newTask);
            }
            System.out.print(divider);
        }
    }
}


