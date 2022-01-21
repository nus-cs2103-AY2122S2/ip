import java.util.ArrayList;

public class Apollo {
    private static ArrayList<Task> tasks;
    private static Commands input;

    protected static void printMessage(String message) {
        System.out.println("\t________________________________________________________________________\n\t " +
                message.replace("\n","\n\t ") +
                "\n\t________________________________________________________________________\n");
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
            printMessage("Please enter an Integer instead.");
            run();
        }
    }

    public static void stop() {
        printMessage("See you next time. \n" +
                "I am always available when you need me. ");
    }

    public static void addTask(Task newTask) {
        tasks.add(newTask);
        printMessage(String.format("I've added this task:\n  %s\nThere's a total of %d tasks now. ",
                newTask, tasks.size()));
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

    public static void mark(int i, boolean mark) {
        if (i > tasks.size()) {
            printMessage("Please add more items first. ");
            return;
        }

        Task task = tasks.get(i-1);
        String msg;
        if (mark) {
            task.markAsDone();
            msg = "Alright, I've mark this task as done.\n  ";
        } else {
            task.markAsNotDone();
            msg = "Noted, I've marked this task as not done yet.\n  ";
        }
//        System.out.println(items.size());
        printMessage(msg + task);
    }

    public static void main(String[] args) {
        initialise();
        run();
    }
}
