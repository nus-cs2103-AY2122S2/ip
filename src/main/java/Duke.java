
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String FILE_PATH = "./data/test.txt";

    private static final UI ui = new UI();

    public static void main(String[] args) {
        run();
    }
    public static void run(){
        ui.showWelcome();
        boolean isExit = false;
        Scanner sc = new Scanner(System.in);
        List<Task> taskList = Storage.loadFromFile(FILE_PATH);
        while(!isExit){
            try{
                String commandLine = sc.nextLine();
                Command c = Parser.parse(commandLine);
                c.execute(taskList, ui);
                isExit = c.isExit();
            } catch(DukeException e){
                ui.printContent(e.getMessage());
            }
        }
        ui.showExitMessage();
        sc.close();
    }
}