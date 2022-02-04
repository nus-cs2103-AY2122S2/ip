package gene;

import gene.command.Command;
import gene.component.Parser;
import gene.component.Storage;
import gene.component.TaskList;
import gene.component.Ui;
import gene.exception.UnrecognizedCommandException;

import java.util.Scanner;

public class Gene {
    private final static Ui geneUi = new Ui();
    private final static Storage geneStorage = new Storage("gene.txt");
    private final static TaskList geneList = new TaskList(geneStorage);

    public static void main(String[] args) {

        geneUi.printGreeting();

        geneList.initFile();

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine() ) {
            try {
                Command currCommand = Parser.parseCommand(scanner.nextLine());
                if (currCommand.isExit()) {
                    currCommand.execute(geneList, geneUi, geneStorage);
                    break;
                }

                currCommand.execute(geneList, geneUi, geneStorage);
            } catch (UnrecognizedCommandException e) {
                geneUi.print(e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Ui.exitProg();
    }
}
