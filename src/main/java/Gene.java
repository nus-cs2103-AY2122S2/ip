import java.util.Scanner;

public class Gene {
    private final static Ui thisIdentity = new Ui();
    private final static TaskList thisList = new TaskList("gene.txt");

    public static void main(String[] args) {
        String logo = " GGGG                      \n"
                    + "G    G   eeee   n nnn    eeee \n"
                    + "G       e    e  nn   n  e    e\n"
                     + "G  GGG  eeeeee  n    n  eeeeee\n"
                + "G    G  e       n    n  e     \n"
                + "G    G  e    e  n    n  e    e\n"
                + " GGGG    eeee   n    n   eeee ";
        System.out.println("Hello from\n" + logo);

        thisList.initFile();
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            try {
                String nextKey = scanner.nextLine();

                if (Ui.exitMatch(nextKey)) {
                    break;
                }

                if (Ui.listMatch(nextKey)) {
                    System.out.println(
                            "----------------------------" +
                                    "----------------------------\n"
                                    + Gene.thisList.printList()
                                    + "--------------------------------------------------------"
                    );
                    continue;
                }

                if (Ui.deleteMatch(nextKey)) {
                    //mark
                    Gene.thisList.deleteTask(nextKey);
                    continue;
                }

                if (Ui.markMatch(nextKey)) {
                    //mark
                    Gene.thisList.markTask(nextKey);
                    continue;
                }

                if (Ui.unmarkMatch(nextKey)) {
                    //unmark
                    Gene.thisList.unmarkTask(nextKey);
                    continue;
                }

                if (Ui.todoMatch(nextKey)) {
                    //unmark
                    Gene.thisList.addTodo(nextKey);
                    continue;
                }

                if (Ui.eventMatch(nextKey)) {
                    //unmark
                    Gene.thisList.addEvent(nextKey);
                    continue;
                }

                //printmatch all/incomplete/complete events
                //printmatch all/incomplete/comlete events in a date

                if (Ui.deadlineMatch(nextKey)) {
                    //unmark
                    Gene.thisList.addDeadline(nextKey);
                    continue;
                } else {
                    String errMsg = "----------------------------" +
                            "----------------------------\n" +
                            "OOPS!!! I'm sorry, but I don't know what that means :-(" + "\n"
                            + "--------------------------------------------------------";
                    throw new UnrecognizedCommandException(errMsg);
                }
            } catch (UnrecognizedCommandException e) {
                System.out.println("----------------------------" +
                        "----------------------------\n" +
                        "OOPS!!! I'm sorry, but I don't know what that means :-(" + "\n"
                        + "--------------------------------------------------------");
            }
        }

        Ui.exitProg();
    }
}
