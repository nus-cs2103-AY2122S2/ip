import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        storage = new Storage("data/tasks.txt");
        tasks = new TaskList();
        try {
            storage.load();
        } catch (CustomException e) {
            ui.showLoadingError();
        }
    }

    public void run() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ui.greet();
        ui.addLineBreak();

        while (true) {
            String instruct = br.readLine();
            try {
                if (instruct.equals("bye")) {
                    storage.addToFile();
                    ui.endSession();
                    break;
                } else if (instruct.equals("list")) {
                    tasks.reportList();
                } else {
                    String[] details = instruct.split(" ");
                    if (details[0].equals("mark")) {
                        tasks.markAsDone(Integer.parseInt(details[1]));
                    } else if (details[0].equals("unmark")) {
                        tasks.markNotDone(Integer.parseInt(details[1]));
                    } else if (details[0].equals("delete")) {
                        tasks.deleteTask(Integer.parseInt(details[1]));
                    } else {
                        String taskType = details[0];
                        if (!(taskType.equals("todo") || taskType.equals("deadline")
                                || taskType.equals("event"))) {
                            throw new CustomException("sorry, this isn't a valid command yet!");
                        } else {
                            tasks.addTask(taskType, instruct);
                        }
                    }
                }
                ui.addLineBreak();
            } catch (CustomException e) {
                System.out.println(e.getMessage());
                ui.addLineBreak();
            }
        }
    }

    public static void main(String[] args) throws IOException {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        new Duke().run();
    }
}
