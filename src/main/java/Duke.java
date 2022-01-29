public class Duke {

    private static final String WELCOME_MESSAGE = "Hello, this is Duke!\nWhat can I do for you today?";
    private static final String END_MESSAGE = "Bye!";
    private static final String FILE_PATH = "data/tasks.txt";

    public static void main(String[] args) {

        Storage storage = new Storage(FILE_PATH);
        TaskManager taskManager = new TaskManager(storage);

        System.out.println(WELCOME_MESSAGE);

        InstructionHandler instructionHandler = new InstructionHandler(taskManager);
        instructionHandler.doInstructions();
        taskManager.writeBack(storage);
    }
}
