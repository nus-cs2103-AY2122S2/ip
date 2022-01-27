import java.util.Scanner;
import java.util.Locale;

public class Duke {
    private static TaskList taskList = new TaskList();
    private static Storage storage = new Storage();
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        storage.readData(taskList);
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Hello from\n" + Response.LOGO);
        Ui.wrapPrint(Response.RESPONSE_WELCOME);
        Over over = new Over();

        while (!over.isOver) {
            String userInput = myScanner.nextLine();
            try {
                Command command = Parser.parse(userInput, taskList, over);
                command.execute();
            } catch (NumberFormatException e) {
                //mark command
                //unmark command
                Ui.wrapPrint("mark command must precede with a decimal number!");
                //delete command
                Ui.wrapPrint("delet command must precede with a decimal number!");
            } catch (IndexOutOfBoundsException e) {
                //todo command
                Ui.wrapPrint("please specify what to do");
                //deadline command
                Ui.wrapPrint(Response.RESPONSE_MISSINGDATE);
                //event command
                Ui.wrapPrint(Response.RESPONSE_MISSINGTIME);
                //} catch (DukeCannotUnderstandException e) {
                //Format.wrapPrint(Response.RESPONSE_CANTUNDERSTAND);
            } catch (NoTimeGivenException e) {
                Ui.wrapPrint("specify the time please\n");
            }
        }
        storage.writeData(taskList);
        myScanner.close();
    }
}
