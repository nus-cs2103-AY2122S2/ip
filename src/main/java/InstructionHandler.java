import java.util.Scanner;

/**
 * Handles instructions from system IO, and chooses the action to perform accordingly.
 */
class InstructionHandler {

    private String TERMINATE_INSTRUCTION = "bye";
    private String OUTPUT_PREFIX = ">> ";

    /**
     * Receives the instructions from user, and performs them one by one and prints the message, until a terminating
     * instruction is received.
     */
    protected void doInstructions() {

        Scanner sc = new Scanner(System.in);

        Instruction nextInstruction = new Instruction(sc.nextLine());
        while (!nextInstruction.getDescription().equals(TERMINATE_INSTRUCTION)) {

            nextInstruction.act();
            System.out.println(OUTPUT_PREFIX + nextInstruction.getMessage());

            nextInstruction = new Instruction(sc.nextLine());
        }

        System.out.println(OUTPUT_PREFIX + "See you!");
    }

}
