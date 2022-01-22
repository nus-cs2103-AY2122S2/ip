import java.io.FileNotFoundException;

/**
 * Echo, a Personal Assistant Chatbot that helps a person to keep track of various things.
 */
public class Echo {

    private final Storage STORAGE;
    private TaskList tasks;
    private final Ui UI;

    public Echo(String filePath) {
        UI = new Ui();
        STORAGE = new Storage(filePath);
        try {
            tasks = new TaskList(STORAGE.load());
        } catch (FileNotFoundException fnf) {
            UI.welcomeNewUser();
            tasks = new TaskList();
        } catch (EchoException e) {
            UI.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        UI.showWelcome();
        String fullCommand = UI.readCommand();
        while (!fullCommand.equals("bye")) {
            try {
                UI.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, UI, STORAGE);
            } catch (EchoException e) {
                UI.showError(e.getMessage());
            } finally {
                UI.showLine();
                fullCommand = UI.readCommand();
            }
        }
        UI.sayBye();
    }

    public static void main(String[] args) {
        String filePath = System.getProperty("user.home") + "/git/CS2103ip/data/echo.txt";
        new Echo(filePath).run();
    }
}
