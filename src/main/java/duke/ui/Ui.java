package duke.ui;

public class Ui {

    public Ui () {
        welcome();
    }

    private void welcome() {
        String welcomeMessage = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"
                + "Hello! I'm JJ\n"
                + "What do you want? :D \n";

        System.out.println("Hello from\n" + welcomeMessage);
    }

    /**
     * Print exit message.
     */
    public static String exit() {
        String goodByeMessage = Ui.createLine()
                + "       " + "Bye. I hope to see you soon." +"\n"
                + Ui.createLine();
        System.out.println(goodByeMessage);

        return goodByeMessage;
    }

    /**
     * Print divider line.
     * @return divider line
     */
    public static String createLine() {
        return  "   __________________________________________________\n";
    }

}
