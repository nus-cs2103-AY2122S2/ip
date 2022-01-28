package duke;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n\n";
        String start = "Hello! I am Duke_two.\n"
                + "Your Personal Assistant.\n"
                + "What can I do for you?\n"
                + "________________________________";
        System.out.println(logo + start);


        TaskList taskList = new TaskList();
        Storage storage = new Storage(taskList);
        Ui ui = new Ui(taskList, storage);

        ui.uiHandler();
    }
}

