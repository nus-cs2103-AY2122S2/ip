package duke.console;

import duke.task.TaskList;
import duke.bot.BotMessage;
import duke.bot.BotType;
import duke.bot.DioBotMessage;
import duke.bot.JJBABotMessage;
import duke.command.CommandFeedback;

import java.util.Scanner;

/**
 * Represents the UI system of the program. A <code>Console</code> object can be created to
 * print and format the output of the program.
 */
public class Console {
    private Scanner sc;
    private BotMessage bot;

    public static final String lineFormat = "    ____________________________________________________________";

    /**
     * Creates a default instance of a Console object.
     */
    public Console(){
        this(new JJBABotMessage());
    }

    /**
     * Creates an instance of a Console object.
     *
     * @param bot type of message bot to output messages.
     */
    public Console(BotMessage bot){
        this.sc = new Scanner(System.in);
        this.bot = bot;
    }

    /**
     * Returns a formatted message with spacing with the given input message.
     *
     * @param msg message to be formatted.
     * @return a formatted message with spacing.
     */
    public static String formatMessage(String msg) {
        String[] msgs = msg.split("\n");

        String finalMsg = "";

        for (String line : msgs) {
            finalMsg += "      " + line + System.lineSeparator();
        }
        return finalMsg;
    }

    /**
     * Reads and returns the user input.
     *
     * @return user input.
     */
    public String read() {
        return sc.nextLine().trim();
    }

    /**
     * Prints a welcome message of the program.
     */
    public void printWelcomeMessage() {
        String logo = "   ___   _________  ___  \n" +
                "  |_  | |_  | ___ \\/ _ \\ \n" +
                "    | |   | | |_/ / /_\\ \\\n" +
                "    | |   | | ___ \\  _  |\n" +
                "/\\__/ /\\__/ / |_/ / | | |\n" +
                "\\____/\\____/\\____/\\_| |_/";
        print(logo);
        print("Welcome to JJBA Bot!");
        println(bot.getBotMessage());
    }

    /**
     * Prints a message without a line separator. The
     * message will be formatted with spacing.
     *
     * @param msg message to be formatted and print.
     */
    public void print(String msg) {
        System.out.print(formatMessage(msg));
    }

    /**
     * Prints the given task list.
     *
     * @param botMessage the botMessage being printed.
     * @param taskList a task list.
     */
    private void printTaskList(String botMessage, TaskList taskList) {
        System.out.println("      " + botMessage);
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(String.format("      %d. %s", i + 1, taskList.get(i).toString()));
        }
        System.out.println(lineFormat);
    }

    /**
     * Prints a message with a line separator. The
     * message will be formatted with spacing.
     *
     *
     * @param msg message to be formatted and print.
     */
    private void println(String msg) {
        print(msg);
        System.out.println(lineFormat);
    }

    /**
     * Prints an error message with a line separator
     * and a warning symbol '⚠'. The message will be
     * formatted with spacing.
     *
     * @param errorMsg error message to be formatted and print.
     */
    public void printError(String errorMsg) {
        println("⚠ " + errorMsg);
    }

    /**
     * Prints an error message due to an invalid command.
     *
     * @param errorMsg error message to be formatted and print.
     */
    public void printInvalidCommand(String errorMsg) {
        if (errorMsg.isBlank()) {
            printError(bot.getInvalidCommandMessage());
        } else {
            printError(bot.getInvalidCommandMessage() + "\n  '" + errorMsg + "'");
        }
    }

    /**
     * Prints the output of an executed command based on the command type
     * given in the command feedback.
     *
     * @param comFeed command feedback of an executed command.
     */
    public void printCommandFeedback(CommandFeedback comFeed) {
        switch (comFeed.cType) {
        case ADD:
            println(bot.getAddMessage(comFeed.taskList, comFeed.task));
            break;
        case DELETE:
            println(bot.getDeleteMessage(comFeed.taskList, comFeed.task));
            break;
        case LIST:
            printTaskList(bot.getListMessage(comFeed.taskList.isEmpty()), comFeed.taskList);
            break;
        case MARK:
            println(bot.getMarkMessage(comFeed.task));
            break;
        case UNMARK:
            println(bot.getUnmarkMessage(comFeed.task));
            break;
        case BOT:
            println(bot.getBotMessage());
            break;
        case FIND:
            printTaskList(bot.getFindListMessage(comFeed.taskList.isEmpty()), comFeed.taskList);
            break;
        case EXIT:
            println(bot.getExitMessage());
        break;
        }
    }

    /**
     * Handles the changing of bot types for the program.
     *
     * @param botType bot type to be changed.
     */
    public void setBot(BotType botType) {
        switch (botType) {
        case JJBA:
            bot = new JJBABotMessage();
            break;
        case DIO:
            bot = new DioBotMessage();
            break;
        }
    }
}
