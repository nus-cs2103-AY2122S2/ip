import util.OutputHandler;

import java.util.Scanner;

public class ShayBot {
    private static final String INTRO_LOGO = "╭━━━┳╮        ╭━━╮   ╭╮\n"
            + "┃╭━╮┃┃        ┃╭╮┃  ╭╯╰╮\n"
            + "┃╰━━┫╰━┳━━┳╮ ╭┫╰╯╰┳━┻╮╭╯\n"
            + "╰━━╮┃╭╮┃╭╮┃┃ ┃┃╭━╮┃╭╮┃┃\n"
            + "┃╰━╯┃┃┃┃╭╮┃╰━╯┃╰━╯┃╰╯┃╰╮\n"
            + "╰━━━┻╯╰┻╯╰┻━╮╭┻━━━┻━━┻━╯\n"
            + "          ╭━╯┃\n"
            + "          ╰━━╯";
    private static final String INTRO_MESSAGE = "Hiya! I'm ShayBot\n"
            + "How may I help you?";
    private static final String EXIT_MESSAGE = "Bye~! See you next time!";

    private final OutputHandler outputHandler;


    public ShayBot() {
        outputHandler = new OutputHandler();
    }

    public void startInteraction() {
        outputHandler.print(INTRO_LOGO, false);
        outputHandler.print(INTRO_MESSAGE);
        runInteraction();
    }

    private void runInteraction() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String txt = sc.nextLine();
            if (txt.equals("bye")) {
                endInteraction();
                break;
            }
            outputHandler.print(txt);
        }
        sc.close();
    }

    private void endInteraction() {
        outputHandler.print(EXIT_MESSAGE);
    }
}
