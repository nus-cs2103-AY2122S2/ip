public class DukeUi {

    public DukeUi() {

    }

    public void printLoadError() {
        String err = "_______________________________________________________\n"
                + "* Load to duke.txt failed *\n"
                + "Please try again!\n"
                + "If the problem persists, please contact the administrator!\n"
                + "_______________________________________________________\n";
        System.out.println(err);
    }

    public void printWriteError() {
        String err = "_______________________________________________________\n"
                + "* Write to duke.txt failed *\n"
                + "Please try again!\n"
                + "If the problem persists, please contact the administrator!\n"
                + "_______________________________________________________\n";
        System.out.println(err);
    }

    public void printGreeting() {
        String logo = "_______________________________________________________\n"
                + " ____        _         _    ____ _   _ \n"
                + "|  _ \\ _   _| | _____ | | /  ___| | | |\n"
                + "| | | | | | | |/ / _ \\| | | |   | |_| |\n"
                + "| |_| | |_| |   <  __/| |_| |___|  _  |\n"
                + "|____/ \\__,_|_|\\_\\___||___|\\____|_| |_|\n"
                + "Hello! I'm DukeLCH\n"
                + "How can I be of service?\n" //Simple Greet
                + "_______________________________________________________\n";
        System.out.println(logo);
    }

    public void printFoundArgumentError() {
        String err = "_______________________________________________________\n"
                + "* Arguments detected *\n"
                + "This command does not require any arguments.\n"
                + "Are you sure this command is what you mean? If so, please try again!\n"
                + "_______________________________________________________\n";
        System.out.println(err);
    }

    public void printInvalidArgumentError() {
        String err = "_______________________________________________________\n"
                + "* Invalid entry detected *\n"
                + "Please provide a valid entry!\n"
                + "_______________________________________________________\n";
        System.out.println(err);
    }

    public void printMissingArgumentError(String keyword) {
        String err = "_______________________________________________________\n"
                + "* No arguments detected *\n"
                + "Please provide a description for your " + keyword + "!\n"
                + "_______________________________________________________\n";
        System.out.println(err);
    }

    public void printMissingDateTimeArgumentError(String keyword) {
        String str;
        switch (keyword) {
        case "deadline":
            str = "Check if you have typed '/by' to indicate the time frame!\n"
                    + "e.g. deadline return book /by Sunday 7pm\n";
        case "event":
            str = "Check if you have typed '/at' to indicate the time frame!\n"
                    + "e.g. event project meeting /at 20/2/2022 1000H-1200H\n";

        default:
            str = "";
        }
        String err = "_______________________________________________________\n"
                + "* Time frame not detected *\n"
                + "Please provide a time frame for your " + keyword + "!\n"
                + str
                + "_______________________________________________________\n";
        System.out.println(err);
    }

    public void printListOfCommands() {
        String err = "_______________________________________________________\n"
                + "* Unrecognised keyword used *\n"
                + "Please try the keywords provided below:\n"
                + "    1. list\n"
                + "    2. todo [description]\n"
                + "    3. deadline [description] /by [date] [time]\n"
                + "    4. event [description] /at [date] [time]\n"
                + "    5. mark [#entry]\n"
                + "    6. unmark [#entry]\n"
                + "    7. delete [#entry]\n"
                + "    8. update\n"
                + "    9. bye\n"
                + "_______________________________________________________\n";
        System.out.println(err);
    }
}
