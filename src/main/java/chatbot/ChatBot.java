package chatbot;

import chatbot.exception.ChatBotException;
import chatbot.util.Parser;
import chatbot.util.Storage;
import chatbot.util.TaskList;
import chatbot.util.Ui;

import java.util.Scanner;

public class ChatBot {

    private static final String SAVE_FILE_DIRECTORY = "data";
    private static final String SAVE_FILE_NAME = "data.txt";

    public static void main(String[] args) {
        Ui innkeeper = new Ui();
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

        innkeeper.greet(taskList.isEmpty());
        while (loop.equals(true)) {
            innkeeper.prompt();
            String rawInput = sc.nextLine();
            loop = parser.parse(rawInput);
        }
        sc.close();
    }
}
