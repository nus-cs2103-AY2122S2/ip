public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);

        Ui ui = new Ui();
        Control control = new Control();
        CreateFile file = new CreateFile();
        boolean isCreated = file.createFile();
        if (!isCreated) {
            control.load(file.getFileName());
        }
        ui.uiHandler(control);
    }
}

