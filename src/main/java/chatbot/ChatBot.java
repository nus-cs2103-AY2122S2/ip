package chatbot;

import chatbot.command.CommandOutput;
import chatbot.command.DeadlineCommand;
import chatbot.command.DeleteCommand;
import chatbot.command.EventCommand;
import chatbot.command.FindCommand;
import chatbot.command.ListCommand;
import chatbot.command.MarkCommand;
import chatbot.command.ResetCommand;
import chatbot.command.ToDoCommand;
import chatbot.command.UnmarkCommand;
import chatbot.task.TaskList;
import chatbot.util.Parser;
import chatbot.util.Ui;

import java.util.Scanner;

public class ChatBot {
    private static final String BOT_NAME = "Delphine";
    private static final String SAVE_FILE_PATH = "./data/save_file";
    private static final String QUIT_TRIGGER = "bye";
    private static final String LOGO =
            "⣿⡟⠙⠛⠋⠩⠭⣉⡛⢛⠫⠭⠄⠒⠄⠄⠄⠈⠉⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" + "⣿⡇⠄⠄⠄⠄⣠⠖⠋⣀⡤⠄⠒⠄⠄⠄⠄⠄⠄⠄⠄⠄⣈⡭⠭⠄⠄⠄⠉⠙\n" + "⣿⡇⠄⠄⢀⣞⣡⠴⠚⠁⠄⠄⢀⠠⠄⠄⠄⠄⠄⠄⠄⠉⠄⠄⠄⠄⠄⠄⠄⠄\n"
                    + "⣿⡇⠄⡴⠁⡜⣵⢗⢀⠄⢠⡔⠁⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄\n" + "⣿⡇⡜⠄⡜⠄⠄⠄⠉⣠⠋⠠⠄⢀⡄⠄⠄⣠⣆⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⢸\n"
                    + "⣿⠸⠄⡼⠄⠄⠄⠄⢰⠁⠄⠄⠄⠈⣀⣠⣬⣭⣛⠄⠁⠄⡄⠄⠄⠄⠄⠄⢀⣿\n" + "⣏⠄⢀⠁⠄⠄⠄⠄⠇⢀⣠⣴⣶⣿⣿⣿⣿⣿⣿⡇⠄⠄⡇⠄⠄⠄⠄⢀⣾⣿\n"
                    + "⣿⣸⠈⠄⠄⠰⠾⠴⢾⣻⣿⣿⣿⣿⣿⣿⣿⣿⣿⢁⣾⢀⠁⠄⠄⠄⢠⢸⣿⣿\n" + "⣿⣿⣆⠄⠆⠄⣦⣶⣦⣌⣿⣿⣿⣿⣷⣋⣀⣈⠙⠛⡛⠌⠄⠄⠄⠄⢸⢸⣿⣿\n"
                    + "⣿⣿⣿⠄⠄⠄⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠇⠈⠄⠄⠄⠄⠄⠈⢸⣿⣿\n" + "⣿⣿⣿⠄⠄⠄⠘⣿⣿⣿⡆⢀⣈⣉⢉⣿⣿⣯⣄⡄⠄⠄⠄⠄⠄⠄⠄⠈⣿⣿\n"
                    + "⣿⣿⡟⡜⠄⠄⠄⠄⠙⠿⣿⣧⣽⣍⣾⣿⠿⠛⠁⠄⠄⠄⠄⠄⠄⠄⠄⠃⢿⣿\n" + "⣿⡿⠰⠄⠄⠄⠄⠄⠄⠄⠄⠈⠉⠩⠔⠒⠉⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠐⠘⣿\n"
                    + "⣿⠃⠃⠄⠄⠄⠄⠄⠄⣀⢀⠄⠄⡀⡀⢀⣤⣴⣤⣤⣀⣀⠄⠄⠄⠄⠄⠄⠁⢹";

    private final Parser parser;

    public ChatBot() {
        this.parser = new Parser();
        this.parser.addCommand(DeadlineCommand.TRIGGER, new DeadlineCommand());
        this.parser.addCommand(DeleteCommand.TRIGGER, new DeleteCommand());
        this.parser.addCommand(EventCommand.TRIGGER, new EventCommand());
        this.parser.addCommand(ListCommand.TRIGGER, new ListCommand());
        this.parser.addCommand(MarkCommand.TRIGGER, new MarkCommand());
        this.parser.addCommand(ToDoCommand.TRIGGER, new ToDoCommand());
        this.parser.addCommand(UnmarkCommand.TRIGGER, new UnmarkCommand());
        this.parser.addCommand(ResetCommand.TRIGGER, new ResetCommand());
        this.parser.addCommand(FindCommand.TRIGGER, new FindCommand());
    }

    public void run() {
        Ui.println(LOGO + String.format("\nHello! I'm %s!\nWhat can I do for you?", BOT_NAME));
        TaskList taskList = TaskList.create(SAVE_FILE_PATH);

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
            if (input[0].equals(QUIT_TRIGGER)) {
                Ui.println("Bye. Hope to see you again soon!");
                break;
            }

            // Execute command.
            CommandOutput commandOutput = parser.getCommand(input[0]).execute(input, taskList);
            Ui.println(commandOutput.output);
            Ui.playSound(commandOutput.sfxFile);
        }
    }
}