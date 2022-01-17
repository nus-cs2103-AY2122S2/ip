import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // greeting message
        String greetings = "    Hi there! 👋 I'm Duke\n"
                            + "    What can I do for you?";

        // divider
        String lines = "    ---------------------------------";

        System.out.println(lines);
        System.out.println(greetings);
        System.out.println(lines);
        // reading user input
        Scanner sc = new Scanner(System.in);

        // INITIALIZING VARIABLES
        /**
         * whether the user's input is bye
         */
        boolean isBye = false;

        /**
         * current number of to do tasks
         */
        int count = 0;

        /**
         * Array container for user's to do tasks
         */
        Task[] todoList = new Task[100];

        String userInput = sc.nextLine();

        while (!isBye) {
            if (userInput.equals("bye")) {
                isBye = true;

                System.out.println(lines);
                System.out.println("    Bye. See you again next time! Have a nice day 😊!");
                System.out.println(lines);
            } else {
                // storing input task in todoList
                String[] userInputArr = userInput.split(" ");

                switch (userInputArr[0]) {
                    case "list":
                        System.out.println(lines);
                        System.out.println("    Here are the tasks in your list:");
                        for (int i = 0; i < count; i++) {
                            String display = String.format("    %d.[%s] %s", i + 1, todoList[i].getStatusIcon(), todoList[i].description);
                            System.out.println(display);
                        }
                        System.out.println(lines);
                        break;

                    case "mark":
                        int taskToMark = Integer.parseInt(userInputArr[1]);
                        todoList[taskToMark - 1].markAsDone();

                        System.out.println("Nice! I've marked this task as done: ");

                        String taskString = String.format("[%s] %s", todoList[taskToMark - 1].getStatusIcon(), todoList[taskToMark - 1].description);
                        System.out.println("    " + taskString);
                        break;

                    default:
                        // creating task
                        Task inputTask = new Task(userInput);

                        // storing input task
                        todoList[count] = inputTask;

                        // displaying input task
                        System.out.println(lines);
                        System.out.println("    added: " + inputTask.description);
                        System.out.println(lines);
                        count++;
                        break;
                }
                userInput = sc.nextLine();
            }
        }
    }
}
