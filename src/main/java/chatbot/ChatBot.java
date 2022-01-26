package chatbot;

import chatbot.command.*;
import chatbot.task.TaskList;
import chatbot.util.Parser;
import chatbot.util.Storage;
import chatbot.util.Ui;

import java.util.Optional;
import java.util.Scanner;

public class ChatBot {
    private static final String BOT_NAME = "Delphine";
    private static final String SAVE_FILE_PATH = "./data/save_file";
    private static final String QUIT_KEYWORD = "bye";
    private static final String LOGO =
            "⣿⡟⠙⠛⠋⠩⠭⣉⡛⢛⠫⠭⠄⠒⠄⠄⠄⠈⠉⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
            "⣿⡇⠄⠄⠄⠄⣠⠖⠋⣀⡤⠄⠒⠄⠄⠄⠄⠄⠄⠄⠄⠄⣈⡭⠭⠄⠄⠄⠉⠙\n" +
            "⣿⡇⠄⠄⢀⣞⣡⠴⠚⠁⠄⠄⢀⠠⠄⠄⠄⠄⠄⠄⠄⠉⠄⠄⠄⠄⠄⠄⠄⠄\n" +
            "⣿⡇⠄⡴⠁⡜⣵⢗⢀⠄⢠⡔⠁⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄\n" +
            "⣿⡇⡜⠄⡜⠄⠄⠄⠉⣠⠋⠠⠄⢀⡄⠄⠄⣠⣆⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⢸\n" +
            "⣿⠸⠄⡼⠄⠄⠄⠄⢰⠁⠄⠄⠄⠈⣀⣠⣬⣭⣛⠄⠁⠄⡄⠄⠄⠄⠄⠄⢀⣿\n" +
            "⣏⠄⢀⠁⠄⠄⠄⠄⠇⢀⣠⣴⣶⣿⣿⣿⣿⣿⣿⡇⠄⠄⡇⠄⠄⠄⠄⢀⣾⣿\n" +
            "⣿⣸⠈⠄⠄⠰⠾⠴⢾⣻⣿⣿⣿⣿⣿⣿⣿⣿⣿⢁⣾⢀⠁⠄⠄⠄⢠⢸⣿⣿\n" +
            "⣿⣿⣆⠄⠆⠄⣦⣶⣦⣌⣿⣿⣿⣿⣷⣋⣀⣈⠙⠛⡛⠌⠄⠄⠄⠄⢸⢸⣿⣿\n" +
            "⣿⣿⣿⠄⠄⠄⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠇⠈⠄⠄⠄⠄⠄⠈⢸⣿⣿\n" +
            "⣿⣿⣿⠄⠄⠄⠘⣿⣿⣿⡆⢀⣈⣉⢉⣿⣿⣯⣄⡄⠄⠄⠄⠄⠄⠄⠄⠈⣿⣿\n" +
            "⣿⣿⡟⡜⠄⠄⠄⠄⠙⠿⣿⣧⣽⣍⣾⣿⠿⠛⠁⠄⠄⠄⠄⠄⠄⠄⠄⠃⢿⣿\n" +
            "⣿⡿⠰⠄⠄⠄⠄⠄⠄⠄⠄⠈⠉⠩⠔⠒⠉⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠐⠘⣿\n" +
            "⣿⠃⠃⠄⠄⠄⠄⠄⠄⣀⢀⠄⠄⡀⡀⢀⣤⣴⣤⣤⣀⣀⠄⠄⠄⠄⠄⠄⠁⢹";

    private final Parser parser;

    public ChatBot() {
        this.parser = new Parser();
        this.parser.addCommand(DeadlineCommand.KEYWORD, new DeadlineCommand());
        this.parser.addCommand(DeleteCommand.KEYWORD, new DeleteCommand());
        this.parser.addCommand(EventCommand.KEYWORD, new EventCommand());
        this.parser.addCommand(ListCommand.KEYWORD, new ListCommand());
        this.parser.addCommand(MarkCommand.KEYWORD, new MarkCommand());
        this.parser.addCommand(ToDoCommand.KEYWORD, new ToDoCommand());
        this.parser.addCommand(UnmarkCommand.KEYWORD, new UnmarkCommand());
        this.parser.addCommand(ResetCommand.KEYWORD, new ResetCommand());
    }

    public void run() {
        // Greetings.
        Ui.println(LOGO + String.format("\nHello! I'm %s!\nWhat can I do for you?", BOT_NAME));
        // Load save file.
        TaskList taskList = (TaskList) Optional.ofNullable(Storage.Load(SAVE_FILE_PATH)).orElse(new TaskList());

        // User interaction.
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            // Read input and split it by whitespace.
            String[] input = scanner.nextLine().split("\\s+");
            // Check for blank input.
            if (input.length == 0) {
                continue;
            }
            // Close application.
            if (input[0].equals(QUIT_KEYWORD)) {
                Ui.println("Bye. Hope to see you again soon!");
                break;
            }

            // Execute command.
            CommandOutput commandOutput = parser.getCommand(input[0]).execute(input, taskList);
            Ui.println(commandOutput.output);
            Ui.playSound("audio/ding.wav");
            if (commandOutput.isListEdited) {
                Storage.Save(SAVE_FILE_PATH, taskList);
            }
        }
    }
}