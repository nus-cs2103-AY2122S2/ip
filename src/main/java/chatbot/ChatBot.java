package chatbot;

import chatbot.command.*;
import chatbot.task.Task;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class ChatBot {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String KEYWORD_QUIT = "bye";

    private final ArrayList<Task> tasks;
    private final Hashtable<String, Command> commands;
    private final Command invalidCommand;

    public ChatBot() {
        this.tasks = new ArrayList<>();
        this.commands = new Hashtable<>();
        this.commands.put(DeadlineCommand.KEYWORD, new DeadlineCommand());
        this.commands.put(EventCommand.KEYWORD, new EventCommand());
        this.commands.put(ListCommand.KEYWORD, new ListCommand());
        this.commands.put(MarkCommand.KEYWORD, new MarkCommand());
        this.commands.put(ToDoCommand.KEYWORD, new ToDoCommand());
        this.commands.put(UnmarkCommand.KEYWORD, new UnmarkCommand());
        this.invalidCommand = new Command() {
            @Override
            public String execute(String[] input, ArrayList<Task> tasks) {
                return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
            }
        };
    }

    public void run() {
        // Greetings.
        System.out.println("Hello from\n" + LOGO);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        // User interaction.
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String[] input = scanner.nextLine().split("\\s+");

            // Close application.
            if (input[0].equals(KEYWORD_QUIT)) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            // Special command.
            if (commands.containsKey(input[0])) {
                System.out.println(commands.get(input[0]).execute(input, tasks));
                continue;
            }

            // Invalid command.
            System.out.println(invalidCommand.execute(input, tasks));
        }
    }
}
