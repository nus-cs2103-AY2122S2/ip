import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    // Array containing tasks
    private static final ArrayList<Task> tasks = new ArrayList<>();

    public static void listTasks() {
        System.out.println("    Your outstanding tasks as of now are as listed:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + tasks.get(i));
        }
    }

    public static void markTask(String[] parsedReq) throws DukeException {
        if (parsedReq.length != 2) {
            throw new DukeException("Please tell me which task you would like to be marked as done.");
        } else {
            try {
                int curr = Integer.parseInt(parsedReq[1]) - 1;
                Task t = tasks.get(curr);
                t.markAsDone();
                System.out.println("    Great job! I've marked the task as completed:");
                System.out.println("    " + t);
            } catch (NumberFormatException n) {
                throw new DukeException("Please enter a valid task number to mark as done!");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("The task you specified does not exist. :(");
            }
        }
    }

    public static void unmarkTask(String[] parsedReq) throws DukeException {
        if (parsedReq.length != 2) {
            throw new DukeException("Please tell me which task you would like to be marked as undone.");
        } else {
            try {
                int curr = Integer.parseInt(parsedReq[1]) - 1;
                Task t = tasks.get(curr);
                t.markAsUndone();
                System.out.println("    No problem, I've marked the task as uncompleted:");
                System.out.println("    " + t);
            } catch (NumberFormatException n) {
                throw new DukeException("Please enter a valid task number to mark as undone!");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("The task you specified does not exist. :(");
            }
        }
    }

    public static void createTodo(String request, String[] parsedReq) throws DukeException {
        if (parsedReq.length == 1) {
            throw new DukeException("The description of a todo cannot be empty.");
        } else {
            Task newTask = new Todo(request.substring(5));
            tasks.add(newTask);
            System.out.println("    Task added:");
            System.out.println("    " + newTask);
        }
    }

    public static void createDeadline(String request, String[] parsedReq) throws DukeException {
        if (parsedReq.length == 1) {
            throw new DukeException("The description of a deadline cannot be empty.");
        } else if (!request.contains(" /by ")) {
            throw new DukeException("You left the deadline of the deadline empty!");
        } else {
            int dIndex = request.indexOf("/by");
            Task newTask = new Deadline(request.substring(9, dIndex), request.substring(dIndex + 4));
            tasks.add(newTask);
            System.out.println("    Task added:");
            System.out.println("    " + newTask);
        }
    }

    public static void createEvent(String request, String[] parsedReq) throws DukeException {
        if (parsedReq.length == 1) {
            throw new DukeException("The description of an event cannot be empty.");
        } else if (!request.contains(" /at ")) {
            throw new DukeException("You left the date of the event empty!");
        } else {
            int eIndex = request.indexOf("/at");
            Task newTask = new Event(request.substring(6, eIndex), request.substring(eIndex + 4));
            tasks.add(newTask);
            System.out.println("    Task added:");
            System.out.println("    " + newTask);
        }
    }

    public static void deleteTask(String[] parsedReq) throws DukeException {
        if (parsedReq.length != 2) {
            throw new DukeException("Please tell me which task you would like to delete.");
        } else {
            try {
                int curr = Integer.parseInt(parsedReq[1]) - 1;
                Task t = tasks.get(curr);
                tasks.remove(curr);
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
            // Removes all leading and trailing white spaces
            String request = userInput.nextLine().strip();
            String[] parsedReq = request.split(" ");

            // Main response
            System.out.print(divider);
            try {
                if (request.equals("bye")) {
                    System.out.println("    Goodbye! Till the next time we meet!");
                } else if (request.equals("list")) {
                    listTasks();
                } else if (request.startsWith("mark")) {
                    markTask(parsedReq);
                } else if (request.startsWith("unmark")) {
                    unmarkTask(parsedReq);
                } else if (request.startsWith("todo")) {
                    createTodo(request, parsedReq);
                } else if (request.startsWith("deadline")) {
                    createDeadline(request, parsedReq);
                } else if (request.startsWith("event")) {
                    createEvent(request, parsedReq);
                } else if (request.startsWith("delete")) {
                    deleteTask(parsedReq);
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


