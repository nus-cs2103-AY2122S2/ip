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
    private final TaskList taskList;


    public ShayBot() {
        outputHandler = new OutputHandler();
        taskList = new TaskList();
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
            if (txt.equals("list")) {
                listTasks();
            } else {
                addTask(txt);
            }
        }
        sc.close();
    }

    private void addTask(String task) {
        taskList.addTask(task);
        outputHandler.print("Hiya! I've added: " + task);
    }

    private void listTasks() {
        outputHandler.print(taskList.toString());
    }

    private void endInteraction() {
        outputHandler.print(EXIT_MESSAGE);
    }
}
