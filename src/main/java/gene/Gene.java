package gene;

import gene.command.Command;
import gene.component.Parser;
import gene.component.Storage;
import gene.component.TaskList;
import gene.component.Ui;
import gene.exception.UnrecognizedCommandException;

import java.util.Scanner;

/**
 * The Gene program is as of right now a simple program that tracks
 * to do, event and deadline tasks. Crud actions are supported.
 *
 * @author Eugene Chia
 * @version 1.0
 * @since 2022-01-12
 */
public class Gene {
    private final Ui geneUi;
    private final Storage geneStorage;
    private final TaskList geneList;

    /**
     * The constructor for the Gene class
     * @param filePath the name of the file to be initiated to store user input
     */
    private Gene(String filePath) {
        this.geneUi = new Ui();
        this.geneStorage = new Storage(filePath);
        this.geneList = new TaskList(geneStorage);
    }

    /**
     * This method is the main method that runs the entire program,
     * it first prints the greeting, followed by initializing a file,
     * then takes in user input from the system.
     * Finally, when an exit command is given, the program will be exited.
     * @return void
     */
    private void run() {
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

    }

    /**
     * main method for Gene
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        (new Gene("gene.txt")).run();
    }
}
