import duke.command.Parser;
import java.util.Scanner;
import duke.io.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {
    public static void main(String[] args) {
        Ui p = new Ui();
        Scanner in = new Scanner(System.in);
        Parser ip = new Parser();
        TaskList tl = new TaskList();
        Storage.loadFile(System.getProperty("user.dir") + "\\data\\duke.txt", tl);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        p.print("Hello! I'm Duke," , "What can I do for you?");

        String userInput = in.nextLine();
        int runResult = ip.run(userInput, p, tl);

        if(runResult == 1) {
            Storage.saveFile("data", "duke.txt", tl.getList());
        }

        while(runResult != -1) {
            if(runResult == 1) {
                Storage.saveFile("data", "duke.txt", tl.getList());
            }
            userInput = in.nextLine();
            runResult = ip.run(userInput, p, tl);
        }
    }

}



