import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class Duke {

    private Ui ui;

    public static void main(String[] args) throws Exception{
        Ui ui = new Ui();
        Storage storage = new Storage("../../data.txt");
        ui.showWelcome();
        Scanner s = new Scanner(System.in);
        boolean isExit = false;
        DukeList list = new DukeList(storage);
        while (!isExit){
            String inp = ui.readInput();
            Command c = DukeParser.parseInput(inp);
            c.execute(ui,list);
            isExit = c.isExit();
        }
    }
}
