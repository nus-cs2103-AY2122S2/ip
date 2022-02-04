import chatbot.ChatBot;

import javafx.application.Application;

public class Main {
    // IMPORTANT: Main class must not be a child of javafx.application.Application.
    public static void main(String[] args) {
        Application.launch(ChatBot.class, args);
    }
}