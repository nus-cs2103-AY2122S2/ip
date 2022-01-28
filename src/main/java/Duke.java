import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.io.IOException;

public class Duke {

    protected static ArrayList<Task> list;
    public static void main(String[] args) throws DukeException {
        Ui.welcome();

        try {
            list = LoadFile.load();

            if (!LoadFile.loaded) {
                list = new ArrayList<>();
            }

            Parser.start();

        } catch (DukeException e) {
            System.err.print(e);
        } catch (IOException e) {
            System.err.print(e);
        } catch (DateTimeParseException e) {
            System.err.println("Oops! That was not a valid date format, please try again in the format yyyy-mm-dd!");
        }
    }
}
