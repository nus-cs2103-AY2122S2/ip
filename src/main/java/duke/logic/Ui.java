package duke.logic;

import java.util.Scanner;

public class Ui {
    private static final String logo = "\n"
            + ".        :       ...     :::.    :::. :::  .   .,::::::\n"
            + ";;,.    ;;;   .;;;;;;;.  `;;;;,  `;;; ;;; .;;,.;;;;''''\n"
            + "[[[[, ,[[[[, ,[[     \\[[,  [[[[[. '[[ [[[[[/'   [[cccc\n"
            + "$$$$$$$$\"$$$ $$$,     $$$  $$$ \"Y$c$$_$$$$,     $$\"\"\"\"\n"
            + "888 Y88\" 888o\"888,_ _,88P  888    Y88\"888\"88o,  888oo,__\n"
            + "MMM  M'  \"MMM  \"YMMMMMP\"   MMM     YM MMM \"MMP\" \"\"\"\"YUMMM\n";
    private static final String intro = "I MONKE. WHAT WANT?";
    private Scanner sc;

    public void openScanner() {
        this.sc = new Scanner(System.in);
    }

    public void closeScanner() {
        this.sc.close();
    }

    public String readCommand() {
        return this.sc.nextLine();
    }

    /**
     * Displays and returns intro.
     *
     * @return Intro string.
     */
    public String showIntro() {
        System.out.println(wrap(logo + "\n" + intro));
        return intro;
    }

    /**
     * Displays and returns message.
     *
     * @param message Message to display and return.
     * @return Given message.
     */
    public String showMessage(String message) {
        System.out.println(wrap(message));
        return message;
    }

    /**
     * Displays and returns error message.
     *
     * @param e Error to display and return.
     * @return Message or given error.
     */
    public String showError(DukeException e) {
        e.printStackTrace();
        return e.getMessage();
    }

    /**
     * Displays and returns farewell message.
     *
     * @return Farewell message.
     */
    public String showBye() {
        System.out.println(wrap("BYE!!!"));
        return "BYE!!!";
    }

    private String wrap(String text) {
        String line = "____________________________________________________________\n";
        return line + text + "\n" + line;
    }
}
