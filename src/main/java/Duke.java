import java.util.Scanner;

public class Duke {
    public TaskList userTaskList;
    public FileManager fileManager;
    Ui ui;

    public Duke(String savedTasksPath){
        this.userTaskList = new TaskList();
        this.fileManager = new FileManager(savedTasksPath);
        ui = new Ui();
    }

    public void run() throws DukeException {
        Scanner sc = new Scanner(System.in);
        this.userTaskList = this.fileManager.loadTasks();
        Parser parser = new Parser();
        ui.print("Hello, My Dear Friend... I'm Duke, your personal motivator!");
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
