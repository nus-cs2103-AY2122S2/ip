import java.util.*;

public class Duke {

    public static void main(String[] args) {

        FastIO io = new FastIO();
        DukeManager duke = new DukeManager();

        duke.greet();

        boolean exited = false;

        while (!exited) {
            String input = io.nextLine();
            if (input.equals("bye")) {
                exited = true;
            } else {
                try {
                    duke.handle(input);
                } catch (DukeException exception) {
                    duke.echo(exception.getMessage());
                }
            }
        }
        duke.bye();
        io.close();
    }




}
