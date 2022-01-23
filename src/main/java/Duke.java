import java.util.Scanner;
import java.util.ArrayList;


public class Duke {
    private static Storage storage;
    private static InputDecoder inputDecoder;
    private static TaskList taskList;

    public static void main(String[] args) throws DukeException {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");

        storage = new Storage();
        ArrayList<Task> tasks = storage.loadList();
        taskList.tasks = tasks;
        System.out.println(tasks.size());
        inputDecoder = new InputDecoder();

        while (true) {
            String str = sc.nextLine();
            if (str.equals("bye")) {
                break;
            } else {
                inputDecoder.decode(str);
                storage.updateTasks(taskList.tasks);
            }
        }
    }
}
