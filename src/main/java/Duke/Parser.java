package Duke;

import Task.Deadline;
import Task.Event;
import Task.TaskList;
import Task.Todo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    public void process(TaskList tasklist) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String cmd = br.readLine();
        int n = tasklist.size();

        while(!cmd.equals("bye")){
            String[] c = cmd.split(" ");
            try {
                if (cmd.equals("todo") || cmd.equals("deadline") || cmd.equals("event")) {
                    throw new DukeException(UI.gdes);
                } else if (cmd.equals("mark") || cmd.equals("unmark") || cmd.equals("delete")) {
                    throw new DukeException(UI.gnum);
                } else if (c[0].equals("list")) {
                    tasklist.printTaskList();
                } else if (c[0].equals("mark")) {
                    int no = Integer.parseInt(c[1]) - 1;
                    tasklist.mark(no);
                } else if (c[0].equals("unmark")) {
                    int no = Integer.parseInt(c[1]) - 1;
                    tasklist.unMark(no);
                } else if (c[0].equals("todo")) {
                    tasklist.add(new Todo(cmd.substring(4)), n);
                } else if (c[0].equals("deadline")) {
                    String[] x = cmd.substring(8).split("/by ");
                    tasklist.add(new Deadline(x[0], formatedDate(x[1])), n);
                } else if (c[0].equals("event")) {
                    String[] x = cmd.substring(5).split("/at ");
                    tasklist.add(new Event(x[0], x[1]), n);
                } else if (c[0].equals("delete")) {
                    int no = Integer.parseInt(c[1]) - 1;
                    tasklist.delete(no);
                } else if (c[0].equals("find")) {
                    tasklist.find(c[1]);
                } else {
                    throw new DukeException(UI.invalid);
                }
            } catch (DukeException e) {
                UI.printWithLines(e.getMessage());
            }
            n = tasklist.size();
            cmd = br.readLine();
        }
    }

    public String formatedDate(String input) {
        DateTimeFormatter formatIn = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter formatOut = DateTimeFormatter.ofPattern("MMM-dd-yyyy HH:mm a");
        return LocalDateTime.parse(input, formatIn).format(formatOut);
    }
}
