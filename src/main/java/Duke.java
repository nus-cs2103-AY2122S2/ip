public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);


        TaskList taskList = new TaskList();
        Storage storage = new Storage(taskList);
        Ui ui = new Ui(taskList, storage);

        ui.uiHandler();
    }
}

