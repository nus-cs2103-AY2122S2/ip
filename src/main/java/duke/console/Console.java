package duke.console;

import duke.task.TaskList;
import duke.bot.BotMessage;
import duke.bot.BotType;
import duke.bot.DioBotMessage;
import duke.bot.JJBABotMessage;
import duke.command.CommandFeedback;

import java.util.Scanner;

public class Console {
    private Scanner sc;
    private BotMessage bot;

    public static final String lineFormat = "    ____________________________________________________________";

    public Console(){
        this(new JJBABotMessage());
    }

    public Console(BotMessage bot){
        this.sc = new Scanner(System.in);
        this.bot = bot;
    }

    public static String formatMessage(String msg) {
        String[] msgs = msg.split("\n");

        String finalMsg = "";

        for (String line : msgs) {
            finalMsg += "      " + line + System.lineSeparator();
        }
        return finalMsg;
    }

    public String read() {
        return sc.nextLine().trim();
    }

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

    public void print(String msg) {
        System.out.println(formatMessage(msg));
    }

    private void printTaskList(TaskList taskList) {
        System.out.println("      " + bot.getListMessage(taskList.isEmpty()));
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(String.format("      %d. %s", i + 1, taskList.get(i).toString()));
        }
        System.out.println(lineFormat);
    }

    private void println(String msg) {
        print(msg);
        System.out.println(lineFormat);
    }

    public void printError(String errorMsg) {
        println("⚠ " + errorMsg);
    }

    public void printInvalidCommand(String errorMsg) {
        if (errorMsg.isBlank()) {
            printError(bot.getInvalidCommandMessage());
        } else {
            printError(bot.getInvalidCommandMessage() + "\n  '" + errorMsg + "'");
        }
    }

    public void printCommandFeedback(CommandFeedback cf) {
        switch (cf.cType) {
        case ADD:
            println(bot.getAddMessage(cf.taskList, cf.task));
            break;
        case DELETE:
            println(bot.getDeleteMessage(cf.taskList, cf.task));
            break;
        case LIST:
            printTaskList(cf.taskList);
            break;
        case MARK:
            println(bot.getMarkMessage(cf.task));
            break;
        case UNMARK:
            println(bot.getUnmarkMessage(cf.task));
            break;
        case BOT:
            println(bot.getBotMessage());
            break;
        case EXIT:
            println(bot.getExitMessage());
        break;
        }
    }

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
