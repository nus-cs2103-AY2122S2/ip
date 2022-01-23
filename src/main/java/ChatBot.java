import java.util.Scanner;

public class ChatBot {

    private static final String SAVE_FILE_DIRECTORY = "../data";
    private static final String SAVE_FILE_NAME = "data.txt";

    public static void main(String[] args) throws ChatBotException {
        Ui innkeeper = new Ui();
        innkeeper.greet();

        TaskList taskList = new TaskList();
        Storage storage = new Storage(SAVE_FILE_DIRECTORY, SAVE_FILE_NAME);
        try {
            storage.loadData(taskList);
        } catch (ChatBotException e) {
            innkeeper.error(e.getMessage());
        }

        Parser parser = new Parser(innkeeper, storage, taskList);
        Scanner sc = new Scanner(System.in);

        Boolean loop = true;
        while (loop.equals(true)) {
            innkeeper.prompt();
            String rawInput = sc.nextLine();
            loop = parser.parse(rawInput);
        }
        sc.close();
    }
}
