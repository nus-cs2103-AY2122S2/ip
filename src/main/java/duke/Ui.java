package duke;

import duke.util.Constants;

public class Ui {
    public void greetings() {
        log(Constants.GREETINGS);
    }

    public void bye() {
        log(Constants.BYE);
    }

    public void showLoadingError(Exception e) {
        log("An error has occurred:\n" + e.getMessage());
    }

    public void log(String args) {
        System.out.println(Constants.HORIZONTAL_LINE);
        System.out.println(args);
        System.out.println(Constants.HORIZONTAL_LINE);
    }
}
