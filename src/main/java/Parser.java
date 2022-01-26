import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Parser {

    public static void parse(String userInput, TaskList tl) throws DukeException {

        StringTokenizer st = new StringTokenizer(userInput, " ");
        String curr = st.nextToken();


        switch (curr){

            case "list":
                tl.printList();
                break;

            case "mark":

                try {
                    int toMark = Integer.parseInt(st.nextToken());
                    tl.markTaskAsCompleted(toMark);
                } catch (DukeException | NumberFormatException e){
                    throw new DukeException.DukeInvalidNumberException();
                }
                break;

            case "unmark":

                try {
                    int toUnmark = Integer.parseInt(st.nextToken());
                    tl.markTaskAsUncomplete(toUnmark);
                } catch (DukeException | NumberFormatException e) {
                    throw new DukeException.DukeInvalidNumberException();
                }
                break;

            case "todo":

                try {
                    tl.addToDo(st.nextToken(""));
                } catch (NoSuchElementException e){
                    throw new DukeException.DukeNoTaskGivenException();
                }

                break;

            case "deadline":

                try {
                    userInput = userInput.replace(curr, "");
                    String[] spl = userInput.split("/by ");
                    if(spl.length <= 1){
                        throw new DukeException.DukeNoTimeProvided();
                    }
                    tl.addDeadline(spl[0], spl[1]);
                } catch (DukeException e) {
                    throw e;
                }
                break;

            case "event":

                try {
                    userInput = userInput.replace(curr, "");
                    String[] splo = userInput.split("/at ");
                    if (splo.length <= 1) {
                        throw new DukeException.DukeNoTimeProvided();
                    }
                    tl.addEvent(splo[0], splo[1]);
                } catch (DukeException e){
                    throw e;
                }
                break;

            case "delete":

                try {
                    int toDelete = Integer.parseInt(st.nextToken());
                    tl.deleteTask(toDelete);
                } catch (NumberFormatException | DukeException e){
                    throw new DukeException.DukeInvalidNumberException();
                }
                break;

            case "bye":

                break;

            default:
                throw new DukeException.DukeInvalidCommandException();
        }
    }
}
