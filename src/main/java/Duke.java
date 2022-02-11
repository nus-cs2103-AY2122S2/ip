import bot.Bot;

import parser.Parser;

import storage.Storage;

import tasklist.StorageTaskList;
import tasklist.TaskList;

import ui.Ui;

public class Duke {
    private static final String APP_PATH = "/test";

    public static void main(String[] args) throws Exception {
        final Parser parser = new Parser();
        final Ui ui = new Ui(System.in, System.out);
        final TaskList taskList = new StorageTaskList(new Storage(Duke.APP_PATH));

        final Bot bot = new Bot(parser, ui, taskList);
        bot.run();
    }
}
