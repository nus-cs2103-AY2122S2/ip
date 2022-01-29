import java.util.Scanner;

/**
 * Handles instructions from system IO, and chooses the action to perform accordingly.
 */
class InstructionHandler {

    private final String TERMINATE_INSTRUCTION = Instruction.getTerminateInstruction();
    private final String OUTPUT_PREFIX = ">> ";
    private TaskManager tasks;

    protected InstructionHandler(TaskManager tasks) {
        this.tasks = tasks;
    }

    /**
     * Receives the instructions from user, and performs them one by one and prints the message, until a terminating
     * instruction is received.
     */
    protected void doInstructions() throws IllegalArgumentException {

        Scanner sc = new Scanner(System.in);

        Instruction nextInstruction;
        while (true) {


            try {
                nextInstruction = Instruction.of(sc.nextLine(), this.tasks);
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
                System.out.println(OUTPUT_PREFIX + "Try again please:");
                continue;
            }

            System.out.println(OUTPUT_PREFIX + nextInstruction.act());

            if (nextInstruction.getDescription().equals(TERMINATE_INSTRUCTION)) {
                // Current instruction terminates the program.
                break;
            }

        }
    }

}
