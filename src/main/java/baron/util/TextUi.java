package baron.util;

import baron.message.Message;
import baron.tasks.TaskManager;

/**
 * Deals with interactions with the user.
 */
public class TextUi {
    private static final String separator = "_____________________________________\n";
    private final TaskManager taskManager;

    /**
     * Constructs a {@code TextUi} object with the specified {@code TaskManager}.
     *
     * @param taskManager the {@code TaskManager} to be used in showWelcomeMessage().
     */
    public TextUi(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    /**
     * Shows welcome message and shows how many tasks user has.
     */
    public void showWelcomeMessage() {
        String logo = "______                       \n"
                + "| ___ \\                      \n"
                + "| |_/ / __ _ _ __ ___  _ __  \n"
                + "| ___ \\/ _` | '__/ _ \\| '_ \\ \n"
                + "| |_/ / (_| | | | (_) | | | |\n"
                + "\\____/ \\__,_|_|  \\___/|_| |_|\n";
        String message = "What can I do for you?";
        System.out.println("Hello from\n" + logo + Message.generateNoOfTasksMessage(this.taskManager.getTaskCount())
                + "\n" + message);
    }

    /**
     * Prints the command output in between two lines using System.out.println.
     *
     * @param commandOutput the command output to be printed.
     */
    public static void printCommandOutput(String commandOutput) {
        System.out.println(TextUi.separator + commandOutput + "\n" + TextUi.separator);
    }
}
