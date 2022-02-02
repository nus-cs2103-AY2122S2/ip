import java.util.Scanner;

public class Gene {
    private final static Ui geneUi = new Ui();
    private final static Storage geneStorage = new Storage("");
    private final static TaskList geneList = new TaskList(geneStorage);

    public static void main(String[] args) {
        String logo = " GGGG                      \n"
                    + "G    G   eeee   n nnn    eeee \n"
                    + "G       e    e  nn   n  e    e\n"
                     + "G  GGG  eeeeee  n    n  eeeeee\n"
                + "G    G  e       n    n  e     \n"
                + "G    G  e    e  n    n  e    e\n"
                + " GGGG    eeee   n    n   eeee ";
        System.out.println("Hello from\n" + logo);

        geneList.initFile();

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine() ) {
            try {
                Command currCommand = Parser.parseCommand(scanner.nextLine());
                if (currCommand.isExit()) {
                    break;
                }

                currCommand.execute(geneList, geneUi, geneStorage);
            } catch (UnrecognizedCommandException e) {
                System.out.println("----------------------------" +
                        "----------------------------\n" +
                        "OOPS!!! I'm sorry, but I don't know what that means :-(" + "\n"
                        + "--------------------------------------------------------");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Ui.exitProg();
    }
}
