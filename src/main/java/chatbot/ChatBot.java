package chatbot;

import chatbot.command.*;
import chatbot.task.Task;
import chatbot.util.FileUtils;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class ChatBot {
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
    private static final String BOT_NAME = "Delphine";
    private static final String KEYWORD_QUIT = "bye";
    private static final String SAVE_FILE_PATH = "./data/Delphine.save";

    private final Hashtable<String, Command> commands;
    private final Command invalidCommand;

    public ChatBot() {
        this.commands = new Hashtable<>();
        this.commands.put(DeadlineCommand.KEYWORD, new DeadlineCommand());
        this.commands.put(DeleteCommand.KEYWORD, new DeleteCommand());
        this.commands.put(EventCommand.KEYWORD, new EventCommand());
        this.commands.put(ListCommand.KEYWORD, new ListCommand());
        this.commands.put(MarkCommand.KEYWORD, new MarkCommand());
        this.commands.put(ToDoCommand.KEYWORD, new ToDoCommand());
        this.commands.put(UnmarkCommand.KEYWORD, new UnmarkCommand());
        this.commands.put(ResetCommand.KEYWORD, new ResetCommand());
        this.invalidCommand = new Command() {
            @Override
            public CommandOutput execute(String[] input, ArrayList<Task> tasks) {
                return new CommandOutput("☹ OOPS!!! I'm sorry, but I don't know what that means :-(", false);
            }
        };
    }

    public void run() {
        // Load save file.
        ArrayList<Task> tasks = FileUtils.Load(SAVE_FILE_PATH);
        if (tasks == null) {
            tasks = new ArrayList<>();
        }

        // Greetings.
        System.out.println(LOGO);
        System.out.println(String.format("Hello! I'm %s!\nWhat can I do for you?", BOT_NAME));

        // User interaction.
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            // Read input.
            String[] input = scanner.nextLine().split("\\s+");

            // Check for blank input.
            if (input.length == 0) {
                continue;
            }

            // Close application.
            if (input[0].equals(KEYWORD_QUIT)) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            // Execute command.
            CommandOutput commandOutput = commands.containsKey(input[0]) ? commands.get(input[0]).execute(input, tasks) : invalidCommand.execute(input, tasks);
            System.out.println(commandOutput.output);
            if (commandOutput.isListEdited) {
                FileUtils.Save(SAVE_FILE_PATH, tasks);
            }
        }
    }
}
