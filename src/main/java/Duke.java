import ui.ChatBot;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Jiaaa-yang
 *
 * Main entry class to run ChatBot instance.
 */
public class Duke {
    private static final String DATA_FOLDER_PATH = "data";
    private static final String TASK_FILE_PATH = "duke.txt";

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        File dataFile = null;
        try {
            dataFile = getDataFile();
        } catch (IOException e) {
            System.err.println("Failed to connect to data file: " + e.getMessage());
            System.exit(1);
        }

        ChatBot chatBot = new ChatBot(dataFile);
        chatBot.initialise();

        while (!chatBot.hasTerminated()) {
            String input = scanner.nextLine();
            chatBot.runCommand(input);
        }
    }

    /**
     * Gets File instance corresponding to the data file
     * storing saved tasks.
     *
     * @return File object containing saved tasks.
     * @throws IOException if the file cannot be created
     */
    private static File getDataFile() throws IOException {
        File dataFolder = new File(DATA_FOLDER_PATH);
        dataFolder.mkdir();

        String filePath = DATA_FOLDER_PATH + File.separator + TASK_FILE_PATH;
        File dataFile = new File(filePath);
        dataFile.createNewFile();
        return dataFile;
    }
}
