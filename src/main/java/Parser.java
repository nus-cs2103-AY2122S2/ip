import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    public void process(Tasklist tasklist) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String cmd = br.readLine();
        int n = tasklist.size();

        while(!cmd.equals("bye")){
            String[] c = cmd.split(" ");
            try {
                if (c[0].equals("list")) {
                    tasklist.printTaskList();
                } else if (c[0].equals("mark")) {
                    if (cmd.equals("mark")) {
                        throw new DukeException(UI.gnum);
                    }
                    int no = Integer.parseInt(c[1]) - 1;
                    tasklist.mark(no);
                } else if (c[0].equals("unmark")) {
                    if (cmd.equals("unmark")) {
                        throw new DukeException(UI.gnum);
                    }
                    int no = Integer.parseInt(c[1]) - 1;
                    tasklist.unmark(no);
                } else if (c[0].equals("todo")) {
                    if (cmd.equals("todo")) {
                        throw new DukeException(UI.gdes);
                    }
                    tasklist.add(new Todo(cmd.substring(4)), n);
                    n+=1;
                } else if (c[0].equals("deadline")) {
                    if (cmd.equals("deadline")) {
                        throw new DukeException(UI.gdes);
                    }

                    String[] x = cmd.substring(8).split("/by ");
                    DateTimeFormatter formatIn = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    DateTimeFormatter formatOut = DateTimeFormatter.ofPattern("MMM-dd-yyyy HH:mm a");
                    String formatedDate = LocalDateTime.parse(x[1],formatIn).format(formatOut);

                    tasklist.add(new Deadline(x[0], formatedDate), n);
                    n+=1;
                } else if (c[0].equals("event")) {
                    if (cmd.equals("event")) {
                        throw new DukeException(UI.gdes);
                    }
                    String[] x = cmd.substring(5).split("/at ");
                    tasklist.add(new Event(x[0], x[1]), n);

                    n+=1;
                } else if (c[0].equals("delete")) {
                    if (cmd.equals("delete")) {
                        throw new DukeException(UI.gnum);
                    }
                    int no = Integer.parseInt(c[1]) - 1;
                    tasklist.delete(no);
                    n-=1;
                } else {
                    throw new DukeException(UI.invalid);
                }
            } catch (DukeException e) {
                UI.printWithLines(e.getMessage());
            }
            cmd = br.readLine();
        }
    }
}
