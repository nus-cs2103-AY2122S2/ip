import java.util.Scanner;

public class Dazz {
    private Storage storage;
    private final Ui ui;
    private final Parser logic;


    public Dazz(TaskList taskList) {
        this.ui = new Ui();
        this.logic = new Parser(taskList);
        this.storage = new Storage();
    }

    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        Dazz dazz = new Dazz(taskList);
        Scanner scanner = new Scanner(System.in);
        dazz.ui.startUp();
        dazz.storage.loadList(taskList);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            System.out.println(Ui.HORIZONTAL_LINE);
            dazz.logic.run(input);
            System.out.println(Ui.HORIZONTAL_LINE);
            if (input.startsWith("bye")) {
                break;
            }
        }
        dazz.storage.updateList(taskList);
        scanner.close();
    }
}

