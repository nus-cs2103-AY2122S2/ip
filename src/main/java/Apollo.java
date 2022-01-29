import java.util.ArrayList;

public class Apollo {
    public static ArrayList<Task> tasks;
    private static Commands input;

    protected static void printMessage(String message) {
        System.out.println("\t________________________________________________________________________\n\t "
                + message.replace("\n","\n\t ")
                + "\n\t________________________________________________________________________\n");
    }

    private static void initialise() {
        tasks = new ArrayList<>();
        input = new Commands();
        Banner.welcomeMsg();
    }

    private static void run() {
        try {
            input.response();
        } catch (ApolloException error) {
            printMessage(error.getMessage());
            run();
        } catch (NumberFormatException error) {
            printMessage("Please enter an Integer instead. ");
            run();
        }
    }

    public static void stop() {
        printMessage("See you next time. \n"
                + "I am always available when you need me. ");
    }

    public static void addTask(Task newTask) {
        tasks.add(newTask);
        printMessage(String.format("I've added this task:\n  %s\nThere's a total of %d tasks now. ",
                newTask, tasks.size()));
    }

    public static void deleteTask(int i) throws ApolloException {
        if (i > tasks.size()) {
            throw new ApolloException(String.format("Task %d. does not exist, unable to delete. ", i));
        }
        String message = String.format("Alright, I've remove this task. \n"
                + "  %s\nThere's a total of %d tasks now. ", tasks.get(i-1), tasks.size()-1);
        tasks.remove(i-1);
        printMessage(message);
    }

    public static void printList() {
        if (tasks.isEmpty()) {
            printMessage("You have no tasks left. ");
            return;
        }

        StringBuilder list = new StringBuilder();
        list.append("These are your current tasks. ");
        var counter = new Object() {
            int num = 1;
        };
        tasks.forEach(i -> {
            list.append("\n  ").append(counter.num).append(".").append(i);
            counter.num++;
        });

        printMessage(list.toString());
    }

    public static void mark(int i, boolean mark) throws ApolloException {
        if (i > tasks.size()) {
            throw new ApolloException(
                    String.format("Task %d. does not exist, unable to mark or unmark. ", i));
        }

        Task task = tasks.get(i-1);
        String message;
        if (mark) {
            task.markAsDone();
            message = "Alright, I've mark this task as done. \n  ";
        } else {
            task.markAsNotDone();
            message = "Noted, I've marked this task as not done yet. \n  ";
        }
        printMessage(message + task);
    }

    public static void save() throws ApolloException {
        Storage.save(tasks);
    }

    public static void load() throws ApolloException {
        tasks = Storage.load();
    }

    public static void main(String[] args) {
        initialise();
        run();
    }
}
