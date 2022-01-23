import java.util.Scanner;

public class Duke {

    private Ui ui;
    private TaskList taskList;
    private Storage storage;
    private Parser parser;

    public Duke(String path, String file_dir) {
        this.ui = new Ui();
        this.storage = new Storage(path, file_dir);
        this.parser = new Parser();

        //Reading arraylist from data.txt
        try {
            this.taskList = storage.load();
        } catch (Exception e) {
            System.out.println(e);
            this.taskList = new TaskList();
        }
    }

    public void run() {
        ui.startUp();
        String inputData;
        Scanner scan = new Scanner(System.in);
        boolean hasEnded = false;

        while(!hasEnded) {
            try {
                inputData = scan.nextLine();
                hasEnded = parser.takeInput(inputData, taskList);
            } catch (DukeException e) {
                System.out.println(e);
                ui.printSeparator();
            }

            storage.storeTasks(taskList.getTasks());
        }
    }

    public static void main(String[] args) {
        new Duke("./src/main/data/data.txt", "./src/main/data").run();
    }
}
