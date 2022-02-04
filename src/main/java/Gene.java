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
                geneUi.printUnrecognised();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Ui.exitProg();
    }
}
