package baron.util;

import baron.messages.Messages;
import baron.tasks.TaskManager;

public class TextUi {
    private static final String separator = "_____________________________________\n";
    private final TaskManager taskManager;

    public TextUi(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    public void showWelcomeMessage() {
        String logo = "______                       \n" +
                "| ___ \\                      \n" +
                "| |_/ / __ _ _ __ ___  _ __  \n" +
                "| ___ \\/ _` | '__/ _ \\| '_ \\ \n" +
                "| |_/ / (_| | | | (_) | | | |\n" +
                "\\____/ \\__,_|_|  \\___/|_| |_|\n";
        String message = "What can I do for you?";
        System.out.println("Hello from\n" + logo + Messages.generateNoOfTasksMessage(this.taskManager.getTaskCount())
                + "\n" + message);
    }

    public static void printCommandOutput(String commandOutput) {
        System.out.println(TextUi.separator + commandOutput + "\n" + TextUi.separator);
    }
}
