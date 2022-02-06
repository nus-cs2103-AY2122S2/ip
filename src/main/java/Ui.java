public class Ui {
    private static final String divider = "~~~~~~~~~~~~~~~~~~~~~~~";
    private static final String prince = "______       _\n"
            +  "| ___ \\     (_)\n"
            + "| |_/ /_ __  _  _ __    ___  ___\n"
            + "|  __/| '__|| || '_ \\  / __|/ _ \\ \n"
            + "| |   | |   | || | | || (__|  __/\n"
            + "\\_|   |_|   |_||_| |_| \\___|\\___|\n";

    public Ui() {
        System.out.println("Hello I'm\n" + prince);
        System.out.println("How can I help you today?");
        System.out.println(divider);
    }

    void printDivider() {
        System.out.println(divider);
    }

    void showError(DukeException e) {
        System.out.println(e.getMessage());
        System.out.println(divider);
    }
}
