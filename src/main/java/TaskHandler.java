import java.util.Scanner;

/**
 * Handles input of tasks from system IO, and chooses the action to perform accordingly.
 */
class TaskHandler {

    private String TERMINATE_INSTRUCTION = "bye";
    private String OUTPUT_PREFIX = ">> ";

    /**
     * Receives the instructions from user, and performs them one by one and prints the message, until a terminating
     * instruction is received.
     */
    protected void doTasks() {

        Scanner sc = new Scanner(System.in);

        Task nextTask = new Task(sc.next());
        while (!nextTask.getDescription().equals(TERMINATE_INSTRUCTION)) {

            nextTask.act();
            System.out.println(OUTPUT_PREFIX + nextTask.getMessage());

            nextTask = new Task(sc.next());
        }

        System.out.println(OUTPUT_PREFIX + "See you!");
    }

}
