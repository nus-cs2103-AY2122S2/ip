import java.util.Scanner;

/**
 * Handles instructions from system IO, and chooses the action to perform accordingly.
 */
class InstructionHandler {

    private final String TERMINATE_INSTRUCTION = Instruction.getTerminateInstruction();
    private final String OUTPUT_PREFIX = ">> ";

    /**
     * Receives the instructions from user, and performs them one by one and prints the message, until a terminating
     * instruction is received.
     */
    protected void doInstructions() {

        Scanner sc = new Scanner(System.in);

        Instruction nextInstruction = Instruction.of(sc.nextLine());
        while (true) {
            System.out.println(OUTPUT_PREFIX + nextInstruction.act());

            if (nextInstruction.getDescription().equals(TERMINATE_INSTRUCTION)) {
                // Current instruction terminates the program.
                break;
            }
            nextInstruction = Instruction.of(sc.nextLine());
        }
    }

}
