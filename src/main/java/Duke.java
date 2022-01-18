import java.util.*;


public class Duke {

    private static boolean isBye(String s) {
        return !s.equals("bye");
    }

    private static void welcome() {
        String welcomeMessage = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"
                + "Hello! I'm JJ\n"
                + "What do you want? :D \n";

        System.out.println("Hello from\n" + welcomeMessage);
    }

    private static void exit() {
        String goodByeMessage = "   __________________________________________________\n"
                + "       " + "Bye. I hope to see you soon." +"\n"
                + "   __________________________________________________";
        System.out.println(goodByeMessage);
    }



    private static boolean processInput(String userInput) {
        if(userInput.equals("bye")) {
            exit();
            return false;
        } else if (userInput.equals("list")) {
            String listTask = Task.printArray();
            String output = "   __________________________________________________\n"
                    + listTask
                    + "   __________________________________________________";
            System.out.println(output);

            return true;
        } else if (userInput.split(" ")[0].equals("mark")) {
            String[] input = userInput.split(" ");
            Task task =  Task.getTaskList()[Integer.parseInt(input[1]) - 1];
            task.markDone();

            String output = "   __________________________________________________\n"
                    + "       I have marked the following task as done! :D \n"
                    + "       " + task + "\n"
                    + "   __________________________________________________";

            System.out.println(output);
            return true;

        } else if (userInput.split(" ")[0].equals("unmark")) {
            String[] input = userInput.split(" ");
            Task task =  Task.getTaskList()[Integer.parseInt(input[1]) - 1];
            task .markNotDone();

            String output = "   __________________________________________________\n"
                    + "       OK I have marked the following task as not done yet! :D \n"
                    + "       " + task + "\n"
                    + "   __________________________________________________";
            System.out.println(output);

            return true;
        } else {
            String newTaskMessage = createTask(userInput);
            System.out.println(newTaskMessage);
            return true;
        }
    }

    public static String createTask(String input) {
        String[] splitString = input.split("/");
        String[] instruction = splitString[0].split(" ");
        Task currentTask = null;

        switch(instruction[0]) {
            case "todo":
                currentTask = new ToDo(input.substring(4));
                break;
            case "event":
                currentTask = new Event(splitString[0].substring(5), splitString[1].substring(3));
                break;
            case "deadline":
                currentTask = new Deadline(splitString[0].substring(8), splitString[1].substring(3));
                break;
        }

        String output = "   __________________________________________________\n"
                + "       Got it! I have added this following task :D \n"
                + "       " + currentTask + "\n"
                + "       " + "Now you have " + Task.getCounter() + " tasks in your list.\n"
                + "   __________________________________________________";

        return output;
    }

    public static void main(String[] args) {
        welcome();
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();

        String[] taskList = new String[100];


        while(processInput(userInput)) {
            userInput = sc.nextLine();
        }

    }
}
