package chatbot;

import chatbot.command.CommandOutput;
import chatbot.command.DeadlineCommand;
import chatbot.command.DeleteCommand;
import chatbot.command.EventCommand;
import chatbot.command.FindCommand;
import chatbot.command.ListCommand;
import chatbot.command.MarkCommand;
import chatbot.command.ResetCommand;
import chatbot.command.TerminateCommand;
import chatbot.command.ToDoCommand;
import chatbot.command.UnmarkCommand;
import chatbot.task.TaskList;
import chatbot.util.Parser;
import chatbot.util.Ui;

import java.util.Scanner;

/**
 * Task management chat bot.
 */
public class ChatBot {
    private static final String BOT_NAME = "Delphine";
    private static final String SAVE_FILE_PATH = "./data/save_file";
    private static final String LOGO =
              "⣿⡟⠙⠛⠋⠩⠭⣉⡛⢛⠫⠭⠄⠒⠄⠄⠄⠈⠉⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n"
            + "⣿⡇⠄⠄⠄⠄⣠⠖⠋⣀⡤⠄⠒⠄⠄⠄⠄⠄⠄⠄⠄⠄⣈⡭⠭⠄⠄⠄⠉⠙\n"
            + "⣿⡇⠄⠄⢀⣞⣡⠴⠚⠁⠄⠄⢀⠠⠄⠄⠄⠄⠄⠄⠄⠉⠄⠄⠄⠄⠄⠄⠄⠄\n"
            + "⣿⡇⠄⡴⠁⡜⣵⢗⢀⠄⢠⡔⠁⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄\n"
            + "⣿⡇⡜⠄⡜⠄⠄⠄⠉⣠⠋⠠⠄⢀⡄⠄⠄⣠⣆⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⢸\n"
            + "⣿⠸⠄⡼⠄⠄⠄⠄⢰⠁⠄⠄⠄⠈⣀⣠⣬⣭⣛⠄⠁⠄⡄⠄⠄⠄⠄⠄⢀⣿\n"
            + "⣏⠄⢀⠁⠄⠄⠄⠄⠇⢀⣠⣴⣶⣿⣿⣿⣿⣿⣿⡇⠄⠄⡇⠄⠄⠄⠄⢀⣾⣿\n"
            + "⣿⣸⠈⠄⠄⠰⠾⠴⢾⣻⣿⣿⣿⣿⣿⣿⣿⣿⣿⢁⣾⢀⠁⠄⠄⠄⢠⢸⣿⣿\n"
            + "⣿⣿⣆⠄⠆⠄⣦⣶⣦⣌⣿⣿⣿⣿⣷⣋⣀⣈⠙⠛⡛⠌⠄⠄⠄⠄⢸⢸⣿⣿\n"
            + "⣿⣿⣿⠄⠄⠄⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠇⠈⠄⠄⠄⠄⠄⠈⢸⣿⣿\n"
            + "⣿⣿⣿⠄⠄⠄⠘⣿⣿⣿⡆⢀⣈⣉⢉⣿⣿⣯⣄⡄⠄⠄⠄⠄⠄⠄⠄⠈⣿⣿\n"
            + "⣿⣿⡟⡜⠄⠄⠄⠄⠙⠿⣿⣧⣽⣍⣾⣿⠿⠛⠁⠄⠄⠄⠄⠄⠄⠄⠄⠃⢿⣿\n"
            + "⣿⡿⠰⠄⠄⠄⠄⠄⠄⠄⠄⠈⠉⠩⠔⠒⠉⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠐⠘⣿\n"
            + "⣿⠃⠃⠄⠄⠄⠄⠄⠄⣀⢀⠄⠄⡀⡀⢀⣤⣴⣤⣤⣀⣀⠄⠄⠄⠄⠄⠄⠁⢹";

    private final Parser parser;

    /**
     * Constructs a chat bot.
     */
    public ChatBot() {
        this.parser = new Parser();
        this.parser.addCommand(new TerminateCommand());
        this.parser.addCommand(new DeadlineCommand());
        this.parser.addCommand(new DeleteCommand());
        this.parser.addCommand(new EventCommand());
        this.parser.addCommand(new ListCommand());
        this.parser.addCommand(new MarkCommand());
        this.parser.addCommand(new ToDoCommand());
        this.parser.addCommand(new UnmarkCommand());
        this.parser.addCommand(new ResetCommand());
        this.parser.addCommand(new FindCommand());
    }

    /**
     * Runs the chat bot.
     */
    public void run() {
        Ui.println(LOGO + String.format("\nHello! I'm %s!\nWhat can I do for you?", BOT_NAME));
        TaskList taskList = TaskList.create(SAVE_FILE_PATH);

        // User interaction.
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            // Execute command.
            CommandOutput commandOutput = parser.executeCommand(scanner.nextLine(), taskList);
            Ui.println(commandOutput.output);
            Ui.playSound(commandOutput.sfxFile);
            if (commandOutput.terminate) {
                break;
            }
        }
    }
}