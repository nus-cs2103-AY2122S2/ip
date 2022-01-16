public class Duke {

    private static final String WELCOME_MESSAGE = "Hello, this is Duke!\nWhat can I do for you today?";
    private static final String END_MESSAGE = "Bye!";

    public static void main(String[] args) {

        System.out.println(WELCOME_MESSAGE);

        InstructionHandler taskHandler = new InstructionHandler();
        taskHandler.doInstructions();
    }
}
