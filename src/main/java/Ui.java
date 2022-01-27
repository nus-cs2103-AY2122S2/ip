public class Ui {
    private static final String LINE = "____________________________________________________________\n";

    public void printWelcomeMessage() {
        String logo = "   ____        _        \n"
                + "  |  _ \\ _   _| | _____ \n"
                + "  | |_| | | | | |/ / _ \\\n"
                + "  | |__/| |_| |   <  __/\n"
                + "  |_|    \\__,_|_|\\_\\___|\n";

        System.out.print(LINE + logo + "Hello! I'm Puke, your friendly neighbourhood chatbot!\n"
                + "What do you want to do?\n" + LINE);
    }

    public void printCommandHead() {
        System.out.print("> ");
    }

    public void printResponse(String s) {
        System.out.print(s + "\n" + LINE);
    }

    public void printError(Exception e) {
        System.out.print(e.getMessage() + "\n" + LINE);
    }

    public void printExit() {
        System.out.print("Alright bye. Come back to Puke anytime!\n" + LINE);
    }
}
