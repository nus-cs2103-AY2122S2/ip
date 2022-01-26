package duke;

import duke.command.Command;
import duke.managers.FileManager;
import duke.managers.TaskList;
import duke.misc.Quote;

import java.util.Scanner;

public class Duke {
    private TaskList userTaskList;
    private FileManager fileManager;
    Ui ui;

    /**
     * Duke Constructor
     *
     * @param savedTasksPath final path of saved tasks file
     */
    public Duke(String savedTasksPath){
        this.userTaskList = new TaskList();
        this.fileManager = new FileManager(savedTasksPath);
        ui = new Ui();
    }

    /**
     * Main duke run method which starts duke Chat Bot
     *
     * @throws DukeException if exceptions occur throughout the program
     */
    public void run() throws DukeException {
        Scanner sc = new Scanner(System.in);
        this.userTaskList = this.fileManager.loadTasks();
        Parser parser = new Parser();
        ui.print("Hello, My Dear Friend... I'm duke.Duke, your personal motivator!");
        Quote quoteOfTheDay = new Quote();
//        duke.print(quoteOfTheDay.generateQuote());
        ui.print("What can i do for you today?");
        while (true) {
            try {
                String userTaskString = sc.nextLine();
                Command c = parser.parseUserCommand(userTaskString);
                c.executeTask(this.userTaskList, this.fileManager);
//                this.performCommand(parser.parseUserCommand(userTaskString), userTaskString);
            } catch (DukeException exception) {
                ui.print(exception);
            }
        }
    }

    public static void main(String[] args) throws DukeException {
        Duke duke = new Duke("./data/duke.txt");
        duke.run();
    }
}
